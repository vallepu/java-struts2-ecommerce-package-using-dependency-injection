package org.webhop.ywdc.paypal.sdk;

import java.util.List;

public class DoCaptureResponse 
{
	
	public String TRANSACTIONID;
	public String response;
	
	public List<String> errorcodeList;
	
	
	
	public DoCaptureResponse()
	{
		
	}
	public String getTRANSACTIONID() 
	{
		return TRANSACTIONID;
	}
	public void setTRANSACTIONID(String transactionid) 
	{
		TRANSACTIONID = transactionid;
	}
	
	public List<String> getErrorcodeList() {
		return errorcodeList;
	}
	public void setErrorcodeList(List<String> errorcodeList) {
		this.errorcodeList = errorcodeList;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}
