package com.ingeint.process;

import org.adempiere.exceptions.AdempiereException;
import org.eevolution.model.MHRAttribute;

import com.ingeint.base.CustomProcess;
import com.ingeint.model.MHR_Employee_Incidents;
import com.ingeint.model.MHR_Shift_Incident;
import com.ingeint.model.MHR_Shift_Incident_Line;

public class CreateAttributeFromShifts extends CustomProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		MHR_Shift_Incident ShiftsIncicent = MHR_Shift_Incident.get(getCtx(), getRecord_ID());
		MHR_Shift_Incident_Line[] linesi = ShiftsIncicent.getLines(true, MHR_Shift_Incident_Line.COLUMNNAME_C_BPartner_ID);
		
		if (linesi.length == 0)
			throw new AdempiereException("No tiene lineas a procesar");
		
		for (MHR_Shift_Incident_Line linei:linesi) {
			MHR_Employee_Incidents[] emlines = linei.getLines(true, MHR_Employee_Incidents.COLUMNNAME_HR_Concept_ID);
			
			if (emlines.length == 0)
				throw new AdempiereException("No tiene lineas de incidencias de turnos a procesar");
			
			for (MHR_Employee_Incidents emline:emlines) {
				MHRAttribute attr = new MHRAttribute(emline.getCtx(), 0, null);
				attr.setHR_Concept_ID(emline.getHR_Concept_ID());
				attr.setColumnType(emline.getColumnType());
				attr.setValidFrom(ShiftsIncicent.getHR_Period().getStartDate());
				attr.setValidTo(ShiftsIncicent.getHR_Period().getEndDate());
				attr.setHR_Payroll_ID(linei.getHR_Payroll_ID());
				attr.setHR_Department_ID(linei.getHR_Department_ID());
				attr.setHR_Job_ID(linei.getHR_Job_ID());
				attr.setC_BPartner_ID(linei.getC_BPartner_ID());
				attr.setQty(emline.getQty());
				attr.setAmount(emline.getAmount());
				attr.set_ValueNoCheck(MHR_Employee_Incidents.COLUMNNAME_HR_Employee_Incidents_ID, emline.get_ID());
				attr.saveEx();
				emline.setProcessed(true);
				emline.saveEx();
			}
			linei.setProcessed(true);
			linei.saveEx();
		}
		ShiftsIncicent.setProcessed(true);
		ShiftsIncicent.saveEx();
		return null;
	}

	

}
