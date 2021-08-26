package com.ingeint.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_Employee_Incidents extends X_HR_Employee_Incidents {
	
	private static final long serialVersionUID = -4670517453389460437L;

	public MHR_Employee_Incidents(Properties ctx, int HR_Employee_Incidents_ID, String trxName) {
		super(ctx, HR_Employee_Incidents_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHR_Employee_Incidents(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MHR_Employee_Incidents(MHR_Shift_Incident_Line il) {
		
		this (il.getCtx(), 0, il.get_TrxName());
		if (il.get_ID()==0)
			throw new IllegalArgumentException("Header not saved");
		setHR_Shift_Incident_Line_ID(il.get_ID()); //parent
	}
	
	public static MHR_Employee_Incidents create(MHR_Shift_Incident_Line il, MHR_Shifts_Line eline) {
		MHR_Employee_Incidents Ei = new MHR_Employee_Incidents(il);
		Ei.setHR_Concept_ID(eline.getHR_Concept_ID());
		Ei.setColumnType(eline.getColumnType());
		Ei.setAmount(eline.getAmount());
		Ei.setQty(eline.getQty());
		Ei.setHR_Shifts_Line_ID(eline.getHR_Shifts_Line_ID());
		Ei.setAD_Org_ID(il.getAD_Org_ID());
		return Ei;
		
	}

}
