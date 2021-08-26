package com.ingeint.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.eevolution.model.MHRConcept;

public class MHR_Shifts extends X_HR_Shifts {

	private static final long serialVersionUID = -5415340952735980206L;
	private static CCache<Integer, MHR_Shifts> s_cache = new CCache<Integer, MHR_Shifts>(Table_Name, 100);
	/** Cache by Value */
	private static CCache<String, MHR_Shifts> s_cacheValue = new CCache<String, MHR_Shifts>(Table_Name+"_Value", 100);


	public MHR_Shifts(Properties ctx, int HR_Shifts_ID, String trxName) {
		super(ctx, HR_Shifts_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHR_Shifts(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static MHR_Shifts get(Properties ctx, int HR_Shifts_ID)
	{
		if (HR_Shifts_ID <= 0)
			return null;
		//
		MHR_Shifts Shifts = s_cache.get(HR_Shifts_ID);
		if (Shifts != null)
			return Shifts;
		//
		Shifts = new MHR_Shifts(ctx, HR_Shifts_ID, null);
		if (Shifts.get_ID() == HR_Shifts_ID)
		{
			s_cache.put(HR_Shifts_ID, Shifts);
		}
		else
		{
			Shifts = null;	
		}
		return Shifts; 
	}
	
	public static MHR_Shifts forValue(Properties ctx, String value)
	{
		if (Util.isEmpty(value, true))
		{
			return null;
		}
		
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		final String key = AD_Client_ID+"#"+value;
		MHR_Shifts Shifts = s_cacheValue.get(key);
		if (Shifts != null)
		{
			return Shifts;
		}
		
		final String whereClause = COLUMNNAME_Value+"=? AND AD_Client_ID IN (?,?)"; 
		Shifts = new Query(ctx, Table_Name, whereClause, null)
							.setParameters(new Object[]{value, 0, AD_Client_ID})
							.setOnlyActiveRecords(true)
							.setOrderBy("AD_Client_ID DESC")
							.first();
		if (Shifts != null)
		{
			s_cacheValue.put(key, Shifts);
			s_cache.put(Shifts.get_ID(), Shifts);
		}
		return Shifts;
	}
	
	protected MHR_Shifts_Line[] t_lines = null;
	
	MHR_Shifts_Line[] getLines(String whereClause, String orderClause) {
		StringBuilder whereClauseFinal = new StringBuilder(
				MHR_Shifts_Line.COLUMNNAME_HR_Shifts_ID + "=? ");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(whereClause);
		if (orderClause.length() == 0)
			orderClause = MHR_Shifts_Line.COLUMNNAME_HR_Shifts_ID;
		//
		List<MHR_Shifts_Line> list = new Query(getCtx(), MHR_Shifts_Line.Table_Name,
				whereClauseFinal.toString(), get_TrxName()).setParameters(get_ID()).setOrderBy(orderClause).list();

		return list.toArray(new MHR_Shifts_Line[list.size()]);
	} // getLines
	
	public MHR_Shifts_Line[] getLines(boolean requery, String orderBy) {
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
