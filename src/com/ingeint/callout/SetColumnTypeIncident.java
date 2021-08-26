package com.ingeint.callout;
import org.eevolution.model.MHRConcept;
import com.ingeint.base.CustomCallout;
import com.ingeint.model.MHR_Employee_Incidents;


public class SetColumnTypeIncident extends CustomCallout {
	
	@Override
	protected String start() {
		if (getTab().getValue(MHR_Employee_Incidents.COLUMNNAME_HR_Concept_ID) != null) {
			int HR_Concept_ID = (int) getTab().getValue(MHR_Employee_Incidents.COLUMNNAME_HR_Concept_ID);
			MHRConcept Concept = MHRConcept.get(null, HR_Concept_ID);
			getTab().setValue(MHR_Employee_Incidents.COLUMNNAME_ColumnType, Concept.getColumnType());
		} else 
			getTab().setValue(MHR_Employee_Incidents.COLUMNNAME_ColumnType, null);
		
		return null;
	}

}
