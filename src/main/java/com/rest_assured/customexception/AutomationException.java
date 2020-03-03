package com.rest_assured.customexception;

@SuppressWarnings("serial")
public class AutomationException extends Exception {

	public AutomationException(String strMessage) {
		super(strMessage);
	}

}
