package com.ingeint.process;

import org.eevolution.model.MHRPayroll;
import org.eevolution.model.MHRProcess;

import com.ingeint.base.CustomProcess;

public class RecreateCumulatedMovements extends CustomProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MHRProcess process = new MHRProcess(getCtx(), getRecord_ID(), get_TrxName());
		MHRPayroll payroll = new MHRPayroll(getCtx(), process.getHR_Payroll_ID(), get_TrxName());
		if (payroll.get_ValueAsBoolean("IsCummulatedAccounting"))
			MHRProcess.createCumulatedMovements(process);
		return null;
	}

}
