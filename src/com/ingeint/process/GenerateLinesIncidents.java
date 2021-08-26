package com.ingeint.process;
import java.util.logging.Level;
import org.compiere.process.ProcessInfoParameter;
import com.ingeint.base.CustomProcess;
import com.ingeint.model.MHR_Shift_Incident;
import com.ingeint.model.MHR_Shifts;

public class GenerateLinesIncidents extends CustomProcess {

int HR_Shifts_ID = 0;	
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals(MHR_Shifts.COLUMNNAME_HR_Shifts_ID))
				HR_Shifts_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		MHR_Shift_Incident ShiftsIncicent = MHR_Shift_Incident.get(getCtx(), getRecord_ID());
		MHR_Shift_Incident.CreateLinesDetail(ShiftsIncicent, HR_Shifts_ID);
		return null;
	}
	
}
