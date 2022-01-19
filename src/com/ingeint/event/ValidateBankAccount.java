package com.ingeint.event;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPBankAccount;
import org.compiere.model.MSysConfig;

import com.ingeint.base.CustomEventHandler;

public class ValidateBankAccount extends CustomEventHandler {

	@Override
	protected void doHandleEvent() {
		MBPBankAccount baccount = (MBPBankAccount) getPO();
		
		if (!baccount.getC_BPartner().isEmployee())
			return;
		
		int MaxDigit = MSysConfig.getIntValue("MaxDigitBankAccount", 0, baccount.getAD_Client_ID());
		
		if (MaxDigit == 0)
			return;
		
		if (!(MaxDigit == baccount.getA_Name().length()))
			throw new AdempiereException("@NotMaxDigitAccount@"+MaxDigit);
			
		
		
	}

}
