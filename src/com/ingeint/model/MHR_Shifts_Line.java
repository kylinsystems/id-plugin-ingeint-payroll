package com.ingeint.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CCache;

public class MHR_Shifts_Line extends X_HR_Shifts_Line {

	private static final long serialVersionUID = -5222818461013533703L;
	
	private static CCache<Integer, MHR_Shifts_Line> s_cache = new CCache<Integer, MHR_Shifts_Line>(Table_Name, 100);

	public MHR_Shifts_Line(Properties ctx, int HR_Shifts_Line_ID, String trxName) {
		super(ctx, HR_Shifts_Line_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHR_Shifts_Line(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static MHR_Shifts_Line get(Properties ctx, int HR_Shifts_Line_ID)
	{
		if (HR_Shifts_Line_ID <= 0)
			return null;
		//
		MHR_Shifts_Line ShiftsL = s_cache.get(HR_Shifts_Line_ID);
		if (ShiftsL != null)
			return ShiftsL;
		//
		ShiftsL = new MHR_Shifts_Line(ctx, HR_Shifts_Line_ID, null);
		if (ShiftsL.get_ID() == HR_Shifts_Line_ID)
		{
			s_cache.put(HR_Shifts_Line_ID, ShiftsL);
		}
		else
		{
			ShiftsL = null;	
		}
		return ShiftsL; 
	}

}
