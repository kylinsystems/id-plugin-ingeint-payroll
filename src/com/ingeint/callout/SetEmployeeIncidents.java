package com.ingeint.callout;

import org.compiere.util.AdempiereSystemError;
import com.ingeint.base.CustomCallout;
import com.ingeint.model.MHR_Shift_Incident;
import com.ingeint.model.MHR_Shift_Incident_Line;

public class SetEmployeeIncidents extends CustomCallout {

	@Override
	protected String start() {
		int incident_ID = (int) getTab().getValue(MHR_Shift_Incident_Line.COLUMNNAME_HR_Shift_Incident_Line_ID);
		MHR_Shift_Incident_Line il = MHR_Shift_Incident_Line.get(getCtx(), incident_ID);
		
		if(getTab().getValue(MHR_Shift_Incident_Line.COLUMNNAME_HR_Shifts_ID) != null) {
			int HR_Shifts_ID = (int) getTab().getValue(MHR_Shift_Incident_Line.COLUMNNAME_HR_Shifts_ID);
			try {
				MHR_Shift_Incident.CreateLinesDetailFromEvent(il, HR_Shifts_ID);
			} catch (AdempiereSystemError e) {
				e.printStackTrace();
			}			
		} else
			MHR_Shift_Incident.deletelines(il);
		return null;
	}

}
