package com.ingeint.event;

import java.math.BigDecimal;
import java.util.List;

import org.compiere.model.PO;
import org.compiere.model.Query;
import org.eevolution.model.MHRProcess;

import com.ingeint.base.CustomEventHandler;
import com.ingeint.model.MHRLoan;
import com.ingeint.model.MHRLoanLines;

public class AfterCompletedHRProcess extends CustomEventHandler {

	@Override
	protected void doHandleEvent() {
		MHRProcess Process = (MHRProcess) getPO();
		
		List<MHRLoanLines> Lines = new Query(Process.getCtx(), MHRLoanLines.Table_Name, "HR_Process_ID = ?", Process.get_TrxName()).setParameters(Process.get_ID()).list();
		
		for (MHRLoanLines Line : Lines) {
			MHRLoan Loan = new MHRLoan(Line.getCtx(), Line.getHR_Loan_ID(), Line.get_TrxName());
			BigDecimal amount = Loan.getOpenAmt().subtract(Line.getAmt());
			Loan.setOpenAmt(amount);
			Loan.saveEx();
		}
	}

}
