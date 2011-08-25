package org.webhop.ywdc.paypal.sdk;



import java.util.ArrayList;
import java.util.List;

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;
import com.paypal.sdk.util.Util;



public class DoCapture 
{
	
	private NVPCallerServices caller = null;
	
	public DoCaptureResponse doCapture(String TxType, String authId, String completeCodeType, String amount, String invoiceId,String note)
	{
		NVPEncoder encoder = new NVPEncoder();
		NVPDecoder decoder = new NVPDecoder();
		NVPCallerServices caller = null;
		try
		{
		caller = new NVPCallerServices();
		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
	
		// Set up your API credentials, PayPal end point, API operation and version.
		 ProfileConfiguration profileConfig = new ProfileConfiguration();
         // Set up your API credentials, PayPal end point, API operation and version.
         profile.setAPIUsername(profileConfig.getUsername());
			profile.setAPIPassword(profileConfig.getPassword());
			profile.setSignature(profileConfig.getSignature());
			profile.setEnvironment(profileConfig.getEnvironment());
		profile.setSubject("");
		caller.setAPIProfile(profile);
		encoder.add("VERSION", "51.0");
		encoder.add("METHOD","DoCapture");
		
		// Add request-specific fields to the request string.
		encoder.add("TRXTYPE","D");
		encoder.add("AUTHORIZATIONID",authId);
		encoder.add("COMPLETETYPE",completeCodeType);
		encoder.add("AMT",amount);
		if(!Util.isEmpty(invoiceId)) encoder.add("INVNUM",invoiceId);
		encoder.add("NOTE",note);
		String NVPRequest = encoder.encode();
		
		// Execute the API operation and obtain the response.
		String NVPResponse = caller.call(NVPRequest);
		decoder.decode(NVPResponse);
		
		}catch(Exception ex)
		{
		ex.printStackTrace();
		}
		String ackString = decoder.get("ACK");
		
		DoCaptureResponse docaptureResponse = new DoCaptureResponse();
		docaptureResponse.setResponse(ackString);
		if(ackString.compareTo("Failure") == 0)
		{
			List<String> errorList = new ArrayList<String>();
			//HashMap<Integer, String> errorMap = new HashMap<Integer, String>();
			Integer numberofErrors = 0;
			//ErrorCodes errorCodes = new ErrorCodes();
		//	HashMap<Integer, ErrorCode> errorMap = errorCodes.getErrorMap();
			while(decoder.get("L_ERRORCODE" + numberofErrors.toString()) != null)
			{
				String errorCode = decoder.get("L_ERRORCODE" + numberofErrors.toString());
				Integer errorcodeInt = Integer.parseInt(errorCode);
				errorList.add(errorCode);
				//errorMap.put(numberofErrors, errorCode);
				System.out.println(decoder.get("L_ERRORCODE" + numberofErrors.toString()));
				
				numberofErrors ++;
			}
			docaptureResponse.setErrorcodeList(errorList);
		}
		else
		{
			String authorizationId = decoder.get("TRANSACTIONID");
			docaptureResponse.setTRANSACTIONID(authorizationId);
		}
		
		return docaptureResponse;
		
	}
	
}
