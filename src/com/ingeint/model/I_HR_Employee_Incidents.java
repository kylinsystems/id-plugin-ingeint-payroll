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
package com.ingeint.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Employee_Incidents
 *  @author iDempiere (generated) 
 *  @version Release 8.2
 */
@SuppressWarnings("all")
public interface I_HR_Employee_Incidents 
{

    /** TableName=HR_Employee_Incidents */
    public static final String Table_Name = "HR_Employee_Incidents";

    /** AD_Table_ID=1000045 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name ColumnType */
    public static final String COLUMNNAME_ColumnType = "ColumnType";

	/** Set Column Type	  */
	public void setColumnType (String ColumnType);

	/** Get Column Type	  */
	public String getColumnType();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name HR_Concept_ID */
    public static final String COLUMNNAME_HR_Concept_ID = "HR_Concept_ID";

	/** Set Payroll Concept	  */
	public void setHR_Concept_ID (int HR_Concept_ID);

	/** Get Payroll Concept	  */
	public int getHR_Concept_ID();

	public org.eevolution.model.I_HR_Concept getHR_Concept() throws RuntimeException;

    /** Column name HR_Employee_Incidents_ID */
    public static final String COLUMNNAME_HR_Employee_Incidents_ID = "HR_Employee_Incidents_ID";

	/** Set Employee Incidents	  */
	public void setHR_Employee_Incidents_ID (int HR_Employee_Incidents_ID);

	/** Get Employee Incidents	  */
	public int getHR_Employee_Incidents_ID();

    /** Column name HR_Employee_Incidents_UU */
    public static final String COLUMNNAME_HR_Employee_Incidents_UU = "HR_Employee_Incidents_UU";

	/** Set HR_Employee_Incidents_UU	  */
	public void setHR_Employee_Incidents_UU (String HR_Employee_Incidents_UU);

	/** Get HR_Employee_Incidents_UU	  */
	public String getHR_Employee_Incidents_UU();

    /** Column name HR_Shift_Incident_Line_ID */
    public static final String COLUMNNAME_HR_Shift_Incident_Line_ID = "HR_Shift_Incident_Line_ID";

	/** Set Shift Incident Generator Line	  */
	public void setHR_Shift_Incident_Line_ID (int HR_Shift_Incident_Line_ID);

	/** Get Shift Incident Generator Line	  */
	public int getHR_Shift_Incident_Line_ID();

	public I_HR_Shift_Incident_Line getHR_Shift_Incident_Line() throws RuntimeException;

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  */
	public void setProcessed (boolean Processed);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean Processed();
	
	/** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
	
	/** Column name HR_Shifts_Line_ID */
    public static final String COLUMNNAME_HR_Shifts_Line_ID = "HR_Shifts_Line_ID";

    
    public void setHR_Shifts_Line_ID (int HR_Shifts_Line_ID);

	/** Get HR_Shifts_Line_ID  */
	public int getHR_Shifts_Line_ID();
}
