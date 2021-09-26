package com.ingeint.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MINGMovement extends X_ING_HRMovement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2414584892257434264L;

	public MINGMovement(Properties ctx, int ING_HRMovement_ID, String trxName) {
		super(ctx, ING_HRMovement_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MINGMovement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
