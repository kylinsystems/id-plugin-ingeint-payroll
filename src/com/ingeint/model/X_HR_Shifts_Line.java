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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_Shifts_Line
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_HR_Shifts_Line extends PO implements I_HR_Shifts_Line, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210824L;

    /** Standard Constructor */
    public X_HR_Shifts_Line (Properties ctx, int HR_Shifts_Line_ID, String trxName)
    {
      super (ctx, HR_Shifts_Line_ID, trxName);
      /** if (HR_Shifts_Line_ID == 0)
        {
			setHR_Concept_ID (0);
			setHR_Shifts_ID (0);
			setHR_Shifts_Line_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Shifts_Line (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_HR_Shifts_Line[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_ValueNoCheck (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** ColumnType AD_Reference_ID=53243 */
	public static final int COLUMNTYPE_AD_Reference_ID=53243;
	/** Amount = A */
	public static final String COLUMNTYPE_Amount = "A";
	/** Date = D */
	public static final String COLUMNTYPE_Date = "D";
	/** Quantity = Q */
	public static final String COLUMNTYPE_Quantity = "Q";
	/** Text = T */
	public static final String COLUMNTYPE_Text = "T";
	/** Set Column Type.
		@param ColumnType Column Type	  */
	public void setColumnType (String ColumnType)
	{

		set_Value(COLUMNNAME_ColumnType, ColumnType);
	}

	/** Get Column Type.
		@return Column Type	  */
	public String getColumnType () 
	{
		return (String)get_Value(COLUMNNAME_ColumnType);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public org.eevolution.model.I_HR_Concept getHR_Concept() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Concept)MTable.get(getCtx(), org.eevolution.model.I_HR_Concept.Table_Name)
			.getPO(getHR_Concept_ID(), get_TrxName());	}

	/** Set Payroll Concept.
		@param HR_Concept_ID Payroll Concept	  */
	public void setHR_Concept_ID (int HR_Concept_ID)
	{
		if (HR_Concept_ID < 1) 
			set_Value (COLUMNNAME_HR_Concept_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Concept_ID, Integer.valueOf(HR_Concept_ID));
	}

	/** Get Payroll Concept.
		@return Payroll Concept	  */
	public int getHR_Concept_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Concept_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_ValueNoCheck (COLUMNNAME_HR_Shifts_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Shifts_ID, Integer.valueOf(HR_Shifts_ID));
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

	/** Set Incidents in Employee Shifts.
		@param HR_Shifts_Line_ID Incidents in Employee Shifts	  */
	public void setHR_Shifts_Line_ID (int HR_Shifts_Line_ID)
	{
		if (HR_Shifts_Line_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Shifts_Line_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Shifts_Line_ID, Integer.valueOf(HR_Shifts_Line_ID));
	}

	/** Get Incidents in Employee Shifts.
		@return Incidents in Employee Shifts	  */
	public int getHR_Shifts_Line_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Shifts_Line_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Shifts_Line_UU.
		@param HR_Shifts_Line_UU HR_Shifts_Line_UU	  */
	public void setHR_Shifts_Line_UU (String HR_Shifts_Line_UU)
	{
		set_Value (COLUMNNAME_HR_Shifts_Line_UU, HR_Shifts_Line_UU);
	}

	/** Get HR_Shifts_Line_UU.
		@return HR_Shifts_Line_UU	  */
	public String getHR_Shifts_Line_UU () 
	{
		return (String)get_Value(COLUMNNAME_HR_Shifts_Line_UU);
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}