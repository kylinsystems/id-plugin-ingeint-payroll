package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRContract extends X_HR_Contract {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 458745728578291699L;

	public MHRContract(Properties ctx, int HR_Contract_ID, String trxName) {
		super(ctx, HR_Contract_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MHRContract(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	

}
