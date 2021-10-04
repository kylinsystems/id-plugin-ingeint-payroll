package com.ingeint.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBankAccount;
import org.compiere.model.MConversionRate;
import org.compiere.model.MConversionType;
import org.compiere.model.MCurrency;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPayment;
import org.compiere.model.MSysConfig;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zkoss.zhtml.Big;

import com.ingeint.model.MHRPaymentSelection;
import com.ingeint.model.MHRPaymentSelectionLine;;

public class PayrollUtils {

	public static MPayment createPayment(MHRPaymentSelectionLine psline) {

		MHRPaymentSelection ps = new MHRPaymentSelection(psline.getCtx(), psline.getHR_PaymentSelection_ID(),
				psline.get_TrxName());

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

		BigDecimal Amount = GetAmount(ps.getCtx(), psline.getPayAmt(), CurrencyPay,
				ps.getING_PaymentSelectionType().getC_Currency_ID(), ps.getDateDoc(), ps.getAD_Client_ID(),
				ps.getAD_Org_ID());

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

		int CurrencyPay = MSysConfig.getIntValue("C_Currency_Payment_ID", 0, ps.getAD_Client_ID());

		if (CurrencyPay == 0)
			throw new AdempiereException("@CurrencyPayment@");

		BigDecimal Amount = GetAmount(ps.getCtx(), ps.getTotalPayAmt(), CurrencyPay,
				ps.getC_BankAccount().getC_Currency_ID(), ps.getDateDoc(), ps.getAD_Client_ID(), ps.getAD_Org_ID());

		MOrgInfo oi = MOrgInfo.get(ps.getAD_Org_ID());
		MPayment payment = new MPayment(null, 0, ps.get_TrxName());
		payment.setAD_Org_ID(ps.getAD_Org_ID());
		payment.setC_DocType_ID(ps.getC_DocTypePayment_ID());
		payment.set_ValueOfColumn("C_Currency_Pay_ID", ps.getC_BankAccount().getC_Currency_ID());
		payment.set_ValueOfColumn("Amount", ps.getTotalPayAmt());
		payment.setC_Currency_ID(CurrencyPay);
		payment.setDateAcct(ps.getDateDoc());
		payment.setDateTrx(ps.getDateDoc());
		payment.setC_BPartner_ID(oi.get_ValueAsInt("C_BPartner_ID"));
		payment.setC_BankAccount_ID(ps.getC_BankAccount_ID());
		payment.setC_Charge_ID(ps.getC_Charge_ID());
		payment.setPayAmt(Amount);
		payment.saveEx();

		payment.processIt("CO");
		payment.setProcessed(true);
		payment.saveEx();

		return payment;

	}

	public static BigDecimal GetAmount(Properties Ctx, BigDecimal Amount, int currency_base_ID, int currency_ID,
			Timestamp DateTrx, int AD_Client_ID, int AD_Org_ID) {

		int stdPrecision = MCurrency.getStdPrecision(Ctx, currency_base_ID);
		int C_ConversionType_ID = MConversionType.getDefault(AD_Client_ID);
		BigDecimal PayAmt = Env.ZERO;

		if (currency_base_ID != currency_ID) {
			BigDecimal CurrencyRate = MConversionRate
					.getRate(currency_base_ID, currency_ID, DateTrx, C_ConversionType_ID, AD_Client_ID, AD_Org_ID)
					.setScale(stdPrecision, RoundingMode.HALF_UP);

			if (CurrencyRate == null)
				throw new AdempiereException("@NoCurrencyConversion@");

			PayAmt = Amount.divide(CurrencyRate, stdPrecision, RoundingMode.HALF_UP).setScale(stdPrecision,
					RoundingMode.HALF_UP);
			return PayAmt;

		} else {
			return Amount;
		}

	}

}
