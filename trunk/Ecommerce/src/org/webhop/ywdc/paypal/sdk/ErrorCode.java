package org.webhop.ywdc.paypal.sdk;

public class ErrorCode 
{
	public Integer code;
	
	public String shortMessage;
	public String longMessage;
	public String correctiveAction;
	public Boolean externalError;
	
	
	public ErrorCode(boolean external, Integer errorCode, String shortMessage, String longMessage, String correction)
	{
		setExternalError(external);
		setCode(errorCode);
		setShortMessage(shortMessage);
		setLongMessage(longMessage);
		setCorrectiveAction(correction);
		
	}
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}

	public String getLongMessage() {
		return longMessage;
	}

	public void setLongMessage(String longMessage) {
		this.longMessage = longMessage;
	}

	public String getCorrectiveAction() {
		return correctiveAction;
	}

	public void setCorrectiveAction(String correctiveAction) {
		this.correctiveAction = correctiveAction;
	}
	public Boolean getExternalError() {
		return externalError;
	}
	public void setExternalError(Boolean externalError) {
		this.externalError = externalError;
	}

}
