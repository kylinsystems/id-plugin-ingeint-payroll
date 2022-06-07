package com.ingeint.component;

import com.ingeint.base.CustomProcessFactory;

public class ProcessFactory extends CustomProcessFactory{
	/**
	 * For initialize class. Register the process to build
	 * 
	 * <pre>
	 * protected void initialize() {
	 * 	registerProcess(PPrintPluginInfo.class);
	 * }
	 * </pre>
	 */
	@Override
	protected void initialize() {
		registerProcess(org.eevolution.process.HRCreatePeriods.class);

		registerProcess(com.ingeint.process.RecalculateLoan.class);
		registerProcess(com.ingeint.process.PaymentSelection.class);
		registerProcess(com.ingeint.process.GenerateLinesIncidents.class);
		registerProcess(com.ingeint.process.CreateAttributeFromShifts.class);
		registerProcess(org.eevolution.process.HRCreatePeriods.class);
		registerProcess(com.ingeint.process.CreateXML.class);
		registerProcess(org.eevolution.process.PayrollViaEMail.class);
		registerProcess(com.ingeint.process.RecreateCumulatedMovements.class);

	}
}
