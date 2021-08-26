package com.ingeint.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

public class MHR_Shift_Incident extends X_HR_Shift_Incident {

	private static final long serialVersionUID = -6112381476217067543L;
	
	//Set Cache Values
	private static CCache<Integer, MHR_Shift_Incident> s_cache = new CCache<Integer, MHR_Shift_Incident>(Table_Name, 100);
	
	public MHR_Shift_Incident(Properties ctx, int HR_Shift_Incident_ID, String trxName) {
		super(ctx, HR_Shift_Incident_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHR_Shift_Incident(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static MHR_Shift_Incident get(Properties ctx, int HR_Shift_Incident_ID)
	{
		if (HR_Shift_Incident_ID <= 0)
			return null;
		//
		MHR_Shift_Incident ShiftsI = s_cache.get(HR_Shift_Incident_ID);
		if (ShiftsI != null)
			return ShiftsI;
		//
		ShiftsI = new MHR_Shift_Incident(ctx, HR_Shift_Incident_ID, null);
		if (ShiftsI.get_ID() == HR_Shift_Incident_ID)
		{
			s_cache.put(HR_Shift_Incident_ID, ShiftsI);
		}
		else
		{
			ShiftsI = null;	
		}
		return ShiftsI; 
	}
	
	public static MHR_Shift_Incident CreateLinesDetail(MHR_Shift_Incident ShiftsIncident, int HR_Shifts_ID) throws AdempiereSystemError {
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT C_BPartner_ID, MAX(HR_Department_ID), MAX(HR_Job_ID)")
			.append("FROM HR_Employee ")
			.append("WHERE HR_Payroll_ID = ? ")
			.append("AND AD_Org_ID = ? ")
			.append("AND IsActive = ?")
			.append("AND StartDate <= ? ");
			
			if (ShiftsIncident.getHR_Department_ID() > 0)
				sql.append(" AND HR_Department_ID = ").append(ShiftsIncident.getHR_Department_ID());
			
			if (ShiftsIncident.getHR_Job_ID() > 0)
				sql.append(" AND HR_Job_ID = ").append(ShiftsIncident.getHR_Job_ID());
			
			if (ShiftsIncident.getC_BPartner_ID() > 0)
				sql.append(" AND C_BPartner_ID = ").append(ShiftsIncident.getC_BPartner_ID());
			
			sql.append(" GROUP BY C_BPartner_ID");
			
			pstmt = DB.prepareStatement(sql.toString(), ShiftsIncident.get_TrxName());
			pstmt.setInt(1, ShiftsIncident.getHR_Payroll_ID());
			pstmt.setInt(2, ShiftsIncident.getAD_Org_ID());
			pstmt.setString(3, "Y");
			pstmt.setTimestamp(4, ShiftsIncident.getHR_Period().getStartDate());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				
				boolean exist = getPartnerFromLine(rs.getInt(1), ShiftsIncident.get_ID(), ShiftsIncident.getCtx(), ShiftsIncident.get_TrxName());
				
				if (exist)
					break;
				
				MHR_Shift_Incident_Line il = new MHR_Shift_Incident_Line(ShiftsIncident);
				
				if (HR_Shifts_ID > 0)
					il.setHR_Shifts_ID(HR_Shifts_ID);
				
				il.setC_BPartner_ID(rs.getInt(1));
				il.setHR_Department_ID(rs.getInt(2));
				il.setHR_Job_ID(rs.getInt(3));
				il.setHR_Payroll_ID(ShiftsIncident.getHR_Payroll_ID());
				il.setAD_Org_ID(ShiftsIncident.getAD_Org_ID());
				il.saveEx();
				
				if (HR_Shifts_ID > 0) {
					
					MHR_Shift_Incident.CreateLinesDetailFromEvent(il, HR_Shifts_ID);
					
				}			
			}	
		} catch (Exception e) {
			throw new AdempiereSystemError("System Error: " + e.getLocalizedMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return null;
		
	}
	
	public static MHR_Shift_Incident CreateLinesDetailFromEvent(MHR_Shift_Incident_Line il, int HR_Shifts_ID) throws AdempiereSystemError {
		
		MHR_Shift_Incident.deletelines(il);
		MHR_Shifts Shift = MHR_Shifts.get(il.getCtx(), HR_Shifts_ID);
		MHR_Shifts_Line[] elines = Shift.getLines(true, MHR_Employee_Incidents.COLUMNNAME_HR_Concept_ID);
		
		for (MHR_Shifts_Line eline : elines) {
			
			MHR_Employee_Incidents Ei = MHR_Employee_Incidents.create(il , eline);
			Ei.saveEx();					
		}
		
		return null;
	}	
		
	
	
	public static void deletelines(MHR_Shift_Incident_Line line) {
		MHR_Employee_Incidents[] elines = line.getLines(true, MHR_Employee_Incidents.COLUMNNAME_HR_Concept_ID);
		
		for (MHR_Employee_Incidents eline : elines) {
			eline.deleteEx(true);
		}
		
	}
	
	static boolean getPartnerFromLine(int C_BPartner_ID, int Record_ID, Properties Ctx, String TrxName) {
		
	    String Where = " C_BPartner_ID = ? AND HR_Shift_Incident_ID = ?";
		
	    MHR_Shift_Incident_Line si = new Query(Ctx, MHR_Shift_Incident_Line.Table_Name, Where, TrxName)
	    		.setParameters(new Object[] {C_BPartner_ID,  Record_ID}).first();
	    if (si != null)
	    	return true;
	    else
	    	return false;
		
	}
	
	// Get Lines
	protected MHR_Shift_Incident_Line[] t_lines = null;
	
	MHR_Shift_Incident_Line[] getLines(String whereClause, String orderClause) {
		StringBuilder whereClauseFinal = new StringBuilder(
				MHR_Shift_Incident_Line.COLUMNNAME_HR_Shift_Incident_ID + "=? ");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(whereClause);
		if (orderClause.length() == 0)
			orderClause = MHR_Shift_Incident_Line.COLUMNNAME_HR_Shift_Incident_ID;
		//
		List<MHR_Shift_Incident_Line> list = new Query(getCtx(), MHR_Shift_Incident_Line.Table_Name,
				whereClauseFinal.toString(), get_TrxName()).setParameters(get_ID()).setOrderBy(orderClause).list();

		return list.toArray(new MHR_Shift_Incident_Line[list.size()]);
	} // getLines
	
	public MHR_Shift_Incident_Line[] getLines(boolean requery, String orderBy) {
		if (t_lines != null && !requery){
			set_TrxName(t_lines, get_TrxName());
			return t_lines;
		}
		//
		String orderClause = "";
		if (orderBy != null && orderBy.length() > 0)
			orderClause += orderBy;
		else
			orderClause += MHR_Shift_Incident_Line.COLUMNNAME_C_BPartner_ID;
		t_lines = getLines(null, orderClause);
		return t_lines;
	} // getLines
}
