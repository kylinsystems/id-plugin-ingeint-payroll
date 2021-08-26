package com.ingeint.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Util;

public class MHR_Shift_Incident_Line extends X_HR_Shift_Incident_Line {
	
	private static final long serialVersionUID = -3675015629587882721L;
	
	//Load cache Values
	private static CCache<Integer, MHR_Shift_Incident_Line> s_cache = new CCache<Integer, MHR_Shift_Incident_Line>(Table_Name, 100);

	public MHR_Shift_Incident_Line(Properties ctx, int HR_Shift_Incident_Line_ID, String trxName) {
		super(ctx, HR_Shift_Incident_Line_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHR_Shift_Incident_Line(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	// Get Values
	public static MHR_Shift_Incident_Line get(Properties ctx, int HR_Shift_Incident_Line_ID)
	{
		if (HR_Shift_Incident_Line_ID <= 0)
			return null;
		//
		MHR_Shift_Incident_Line ShiftsIL = s_cache.get(HR_Shift_Incident_Line_ID);
		if (ShiftsIL != null)
			return ShiftsIL;
		//
		ShiftsIL = new MHR_Shift_Incident_Line(ctx, HR_Shift_Incident_Line_ID, null);
		if (ShiftsIL.get_ID() == HR_Shift_Incident_Line_ID)
		{
			s_cache.put(HR_Shift_Incident_Line_ID, ShiftsIL);
		}
		else
		{
			ShiftsIL = null;	
		}
		return ShiftsIL; 
	}
	
	//Create Values
	public MHR_Shift_Incident_Line(MHR_Shift_Incident InciDent) {
			
			this (InciDent.getCtx(), 0, InciDent.get_TrxName());
			if (InciDent.get_ID()==0)
				throw new IllegalArgumentException("Header not saved");
			setHR_Shift_Incident_ID(InciDent.get_ID()); //parent
			setHR_Shift_Incident(InciDent);
	}
	
	//Create Values
	public void setHR_Shift_Incident (MHR_Shift_Incident InciDent)
	{
		setClientOrg(InciDent);		
	}
	
	//Get Lines from other Models
	protected MHR_Employee_Incidents[] t_lines = null;
	
	MHR_Employee_Incidents[] getLines(String whereClause, String orderClause) {
			StringBuilder whereClauseFinal = new StringBuilder(
					MHR_Employee_Incidents.COLUMNNAME_HR_Shift_Incident_Line_ID + "=? ");
			if (!Util.isEmpty(whereClause, true))
				whereClauseFinal.append(whereClause);
			if (orderClause.length() == 0)
				orderClause = MHR_Employee_Incidents.COLUMNNAME_HR_Shift_Incident_Line_ID;
			//
			List<MHR_Employee_Incidents> list = new Query(getCtx(), MHR_Employee_Incidents.Table_Name,
					whereClauseFinal.toString(), get_TrxName()).setParameters(get_ID()).setOrderBy(orderClause).list();
	
			return list.toArray(new MHR_Employee_Incidents[list.size()]);
	} // getLines
	
	public MHR_Employee_Incidents[] getLines(boolean requery, String orderBy) {
		if (t_lines != null && !requery){
			set_TrxName(t_lines, get_TrxName());
			return t_lines;
		}
		//
		String orderClause = "";
		if (orderBy != null && orderBy.length() > 0)
			orderClause += orderBy;
		else
			orderClause += MHR_Employee_Incidents.COLUMNNAME_HR_Concept_ID;
		t_lines = getLines(null, orderClause);
		return t_lines;
	} // getLines

}
