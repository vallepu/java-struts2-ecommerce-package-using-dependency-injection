package org.webhop.ywdc.tools;

public class ErrorCode 
{
	public Integer code;
	
	public String shortMessage;
	public String longMessage;
	public String correctiveAction;
	public Boolean externalError;
	
	public ErrorCode(boolean external, Integer errorCode, String shortMessage, String longMessage, String correction)
	{
		
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

}
