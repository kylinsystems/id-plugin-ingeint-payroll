/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package com.ingeint.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_Shift_Incident_Line
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_HR_Shift_Incident_Line extends PO implements I_HR_Shift_Incident_Line, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210825L;

    /** Standard Constructor */
    public X_HR_Shift_Incident_Line (Properties ctx, int HR_Shift_Incident_Line_ID, String trxName)
    {
      super (ctx, HR_Shift_Incident_Line_ID, trxName);
      /** if (HR_Shift_Incident_Line_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_HR_Shift_Incident_Line (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_HR_Shift_Incident_Line[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_Name)
			.getPO(getHR_Department_ID(), get_TrxName());	}

	/** Set Payroll Department.
		@param HR_Department_ID Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID)
	{
		if (HR_Department_ID < 1) 
			set_Value (COLUMNNAME_HR_Department_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Department_ID, Integer.valueOf(HR_Department_ID));
	}

	/** Get Payroll Department.
		@return Payroll Department	  */
	public int getHR_Department_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Department_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_Name)
			.getPO(getHR_Job_ID(), get_TrxName());	}

	/** Set Payroll Job.
		@param HR_Job_ID Payroll Job	  */
	public void setHR_Job_ID (int HR_Job_ID)
	{
		if (HR_Job_ID < 1) 
			set_Value (COLUMNNAME_HR_Job_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Job_ID, Integer.valueOf(HR_Job_ID));
	}

	/** Get Payroll Job.
		@return Payroll Job	  */
	public int getHR_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Payroll getHR_Payroll() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Payroll)MTable.get(getCtx(), org.eevolution.model.I_HR_Payroll.Table_Name)
			.getPO(getHR_Payroll_ID(), get_TrxName());	}

	/** Set Payroll.
		@param HR_Payroll_ID Payroll	  */
	public void setHR_Payroll_ID (int HR_Payroll_ID)
	{
		if (HR_Payroll_ID < 1) 
			set_Value (COLUMNNAME_HR_Payroll_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Payroll_ID, Integer.valueOf(HR_Payroll_ID));
	}

	/** Get Payroll.
		@return Payroll	  */
	public int getHR_Payroll_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Payroll_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HR_Shift_Incident getHR_Shift_Incident() throws RuntimeException
    {
		return (I_HR_Shift_Incident)MTable.get(getCtx(), I_HR_Shift_Incident.Table_Name)
			.getPO(getHR_Shift_Incident_ID(), get_TrxName());	}

	/** Set Shift Incident Generator.
		@param HR_Shift_Incident_ID Shift Incident Generator	  */
	public void setHR_Shift_Incident_ID (int HR_Shift_Incident_ID)
	{
		if (HR_Shift_Incident_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Shift_Incident_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Shift_Incident_ID, Integer.valueOf(HR_Shift_Incident_ID));
	}

	/** Get Shift Incident Generator.
		@return Shift Incident Generator	  */
	public int getHR_Shift_Incident_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Shift_Incident_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shift Incident Generator Line.
		@param HR_Shift_Incident_Line_ID Shift Incident Generator Line	  */
	public void setHR_Shift_Incident_Line_ID (int HR_Shift_Incident_Line_ID)
	{
		if (HR_Shift_Incident_Line_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Shift_Incident_Line_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Shift_Incident_Line_ID, Integer.valueOf(HR_Shift_Incident_Line_ID));
	}

	/** Get Shift Incident Generator Line.
		@return Shift Incident Generator Line	  */
	public int getHR_Shift_Incident_Line_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Shift_Incident_Line_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Shift_Incident_Line_UU.
		@param HR_Shift_Incident_Line_UU HR_Shift_Incident_Line_UU	  */
	public void setHR_Shift_Incident_Line_UU (String HR_Shift_Incident_Line_UU)
	{
		set_Value (COLUMNNAME_HR_Shift_Incident_Line_UU, HR_Shift_Incident_Line_UU);
	}

	/** Get HR_Shift_Incident_Line_UU.
		@return HR_Shift_Incident_Line_UU	  */
	public String getHR_Shift_Incident_Line_UU () 
	{
		return (String)get_Value(COLUMNNAME_HR_Shift_Incident_Line_UU);
	}

	public I_HR_Shifts getHR_Shifts() throws RuntimeException
    {
		return (I_HR_Shifts)MTable.get(getCtx(), I_HR_Shifts.Table_Name)
			.getPO(getHR_Shifts_ID(), get_TrxName());	}

	/** Set Shifts for Employee.
		@param HR_Shifts_ID Shifts for Employee	  */
	public void setHR_Shifts_ID (int HR_Shifts_ID)
	{
		if (HR_Shifts_ID < 1) 
			set_Value (COLUMNNAME_HR_Shifts_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Shifts_ID, Integer.valueOf(HR_Shifts_ID));
	}

	/** Get Shifts for Employee.
		@return Shifts for Employee	  */
	public int getHR_Shifts_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Shifts_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_ValueNoCheck (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}