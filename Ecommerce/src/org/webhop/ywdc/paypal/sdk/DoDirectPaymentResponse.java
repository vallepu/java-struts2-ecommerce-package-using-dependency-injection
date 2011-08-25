package org.webhop.ywdc.paypal.sdk;

import java.util.List;

public class DoDirectPaymentResponse 
{
	public String RESPONSE;
	public String TRANSACTIONID;
	public String AMT;
	public String AVSCODE;
	public String CVV2MATCH;
	public String L_FMFfilter1;
	public String L_FMFfilter4;
	public List<String> errorcodeList;
	
	
	
	public DoDirectPaymentResponse()
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
	public String getAMT() 
	{
		return AMT;
	}
	public void setAMT(String amt) 
	{
		AMT = amt;
	}
	public String getAVSCODE() 
	{
		return AVSCODE;
	}
	public void setAVSCODE(String avscode) 
	{
		AVSCODE = avscode;
	}
	public String getCVV2MATCH() 
	{
		return CVV2MATCH;
	}
	public void setCVV2MATCH(String cvv2match) 
	{
		CVV2MATCH = cvv2match;
	}
	public String getRESPONSE() 
	{
		return RESPONSE;
	}
	public void setRESPONSE(String response) 
	{
		RESPONSE = response;
	}
	public String getL_FMFfilter1() 
	{
		return L_FMFfilter1;
	}
	public void setL_FMFfilter1(String ffilter1) 
	{
		L_FMFfilter1 = ffilter1;
	}
	public String getL_FMFfilter4() 
	{
		return L_FMFfilter4;
	}
	public void setL_FMFfilter4(String ffilter4) 
	{
		L_FMFfilter4 = ffilter4;
	}
	public List<String> getErrorcodeList() {
		return errorcodeList;
	}
	public void setErrorcodeList(List<String> errorcodeList) {
		this.errorcodeList = errorcodeList;
	}
}
