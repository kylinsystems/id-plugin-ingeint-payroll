package com.ingeint.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBankAccount;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCurrency;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPayment;
import org.compiere.model.MSysConfig;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.MHRProcess;

import com.ingeint.model.MHRPaymentSelection;
import com.ingeint.model.MHRPaymentSelectionLine;;

public class PayrollUtils {

	public static MPayment createPayment(MHRPaymentSelectionLine psline) {

		MHRPaymentSelection ps = new MHRPaymentSelection(psline.getCtx(), psline.getHR_PaymentSelection_ID(),
				psline.get_TrxName());
		
		BigDecimal Amount;
		MBankAccount ba = null;
		MPayment payment = new MPayment(null, 0, psline.get_TrxName());
		MBPartner employee = new MBPartner(psline.getCtx(), psline.getC_BPartner_ID(), psline.get_TrxName());

		Integer C_BP_BankAccount_ID = DB.getSQLValue(psline.get_TrxName(),
				"SELECT C_BP_BankAccount_ID " + "FROM C_BP_BankAccount " + "WHERE C_BPartner_ID = ? AND C_Bank_ID = ? ",
				new Object[] { psline.getC_BPartner_ID(), psline.get_Value("C_BankAccountTo_ID") });

		if (C_BP_BankAccount_ID > 0) {
			ba = new MBankAccount(psline.getCtx(), C_BP_BankAccount_ID, psline.get_TrxName());
			payment.setAccountNo(ba.getAccountNo());
			payment.setC_BP_BankAccount_ID(C_BP_BankAccount_ID);

		}

		int CurrencyPay = MSysConfig.getIntValue("C_Currency_Payment_ID", 0, ps.getAD_Client_ID());

		if (CurrencyPay == 0)
			throw new AdempiereException("@CurrencyPayment@");

		MHRProcess pr = new MHRProcess(ps.getCtx(), ps.getHR_Process_ID(), ps.get_TrxName());
		MConversionRate rate = new MConversionRate(pr.getCtx(), pr.get_ValueAsInt("C_Conversion_Rate_ID"),
				pr.get_TrxName());
		
		if (payment.getC_BankAccount().getC_Currency_ID() != CurrencyPay)
			Amount = GetAmount(rate,  ps.getTotalPayAmt());
		else 
			Amount = ps.getTotalPayAmt();

		payment.set_ValueOfColumn("C_Currency_Pay_ID", payment.getC_BankAccount().getC_Currency_ID());
		payment.setC_Currency_ID(CurrencyPay);
		payment.set_ValueOfColumn("Amount", ps.getTotalPayAmt());
		payment.setAD_Org_ID(psline.getAD_Org_ID());
		payment.setDateAcct(ps.getDateDoc());
		payment.setC_BPartner_ID(psline.get_ValueAsInt("C_BPartner_ID"));
		payment.setC_BankAccount_ID(ps.getC_BankAccount_ID());

		if (employee.get_Value("TenderType") == null)
			throw new AdempiereException(
					"@FillTenderType@" + psline.getC_BPartner().getTaxID() + "_" + psline.getC_BPartner().getName());

		payment.setTenderType(employee.get_ValueAsString("TenderType"));
		payment.setDateTrx(payment.getDateAcct());
		payment.setC_DocType_ID(ps.getC_DocTypePayment_ID());
		payment.setDescription(psline.getDescription());
		payment.setPayAmt(Amount);
		payment.setC_Charge_ID(ps.getC_Charge_ID());
		payment.setRoutingNo(psline.getHR_PaymentSelection().getRoutingNo());
		payment.saveEx();

		if (!employee.get_Value("TenderType").equals("K")) {
			payment.processIt("CO");
			payment.setProcessed(true);
			payment.saveEx();
		}

		psline.setC_Payment_ID(payment.get_ID());
		psline.saveEx();

		return payment;
	}

	public static MPayment CreatePayment(MHRPaymentSelection ps) {
		
		BigDecimal Amount;
		int CurrencyPay = MSysConfig.getIntValue("C_Currency_Payment_ID", 0, ps.getAD_Client_ID());

		if (CurrencyPay == 0)
			throw new AdempiereException("@CurrencyPayment@");
		
		MHRProcess pr = new MHRProcess(ps.getCtx(), ps.getHR_Process_ID(), ps.get_TrxName());
		MConversionRate rate = new MConversionRate(pr.getCtx(), pr.get_ValueAsInt("C_Conversion_Rate_ID"),
				pr.get_TrxName());
		
		MOrgInfo oi = MOrgInfo.get(ps.getAD_Org_ID());
		MPayment payment = new MPayment(null, 0, ps.get_TrxName());
		
		if (ps.getC_BankAccount().getC_Currency_ID() != CurrencyPay) {
			Amount = GetAmount(rate,  ps.getTotalPayAmt());
			payment.setCurrencyRate(rate.getMultiplyRate());
			payment.setIsOverrideCurrencyRate(true);
			payment.setConvertedAmt(ps.getTotalPayAmt());
		} else 
			Amount = ps.getTotalPayAmt();
		
		payment.setC_ConversionType_ID(rate.getC_ConversionType_ID());
		payment.setAD_Org_ID(ps.getAD_Org_ID());
		payment.setC_DocType_ID(ps.getC_DocTypePayment_ID());
		payment.set_ValueOfColumn("C_Currency_Pay_ID", ps.getC_BankAccount().getC_Currency_ID());
		payment.set_ValueOfColumn("Amount", ps.getTotalPayAmt());
		payment.setC_Currency_ID(CurrencyPay);
		payment.setDateAcct(ps.getDateDoc());
		payment.set_ValueOfColumn("DateAcctCustom", ps.getDateDoc());
		payment.setDateTrx(ps.getDateDoc());
		payment.setC_BPartner_ID(oi.get_ValueAsInt("C_BPartner_ID"));
		payment.setC_BankAccount_ID(ps.getC_BankAccount_ID());
		payment.setC_Charge_ID(ps.getC_Charge_ID());
		payment.setRoutingNo(ps.getRoutingNo());
		payment.setPayAmt(Amount);
		payment.saveEx();
		payment.processIt("CO");
		payment.setProcessed(true);
		payment.saveEx();

		for (MHRPaymentSelectionLine line : ps.getLines()) {
			if (payment != null)
				line.setC_Payment_ID(payment.get_ID());
			line.saveEx();
		}

		return payment;

	}

	public static BigDecimal GetAmount(MConversionRate rate, BigDecimal Amount) {

		int stdPrecision = MCurrency.getStdPrecision(rate.getCtx(), rate.getC_Currency_ID());
		BigDecimal PayAmt = Env.ZERO;
		PayAmt = Amount.multiply(rate.getDivideRate());
		if (PayAmt.scale() > stdPrecision)
			PayAmt = PayAmt.setScale(stdPrecision, RoundingMode.HALF_UP);
		
		return PayAmt;

	}

}
