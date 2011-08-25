package org.webhop.ywdc.paypal.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;


public class RefundTransaction 
{

	public RefundTransactionResponse RefundTransactionCode(String refundType , String transactionId,String amount, String note)
	{
		NVPEncoder encoder = new NVPEncoder();
		NVPDecoder decoder = new NVPDecoder();
		NVPCallerServices caller = null;
		
	  	try
	  	{
		  	caller = new NVPCallerServices();
			APIProfile profile = ProfileFactory.createSignatureAPIProfile();
			/*
			 WARNING: Do not embed plaintext credentials in your application code.
			 Doing so is insecure and against best practices.
			 Your API credentials must be handled securely. Please consider
			 encrypting them for use in any production environment, and ensure
			 that only authorized individuals may view or modify them.
			 */

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
			encoder.add("METHOD","RefundTransaction");

		// Add request-specific fields to the request string.
			encoder.add("REFUNDTYPE",refundType);
			encoder.add("TRANSACTIONID",transactionId);
			if((refundType != null) && (refundType.length() > 0) && (refundType.equalsIgnoreCase("Partial")))
			{
				encoder.add("AMT",amount);					
			}
			encoder.add("NOTE",note);
						
		// Execute the API operation and obtain the response.
			String NVPRequest = encoder.encode();
			String NVPResponse = (String) caller.call(NVPRequest);
			decoder.decode(NVPResponse);
		  	}catch (Exception ex) {
		  		
		  		ex.printStackTrace();
		  	}
		  	
		  	RefundTransactionResponse refundtransactionResponse = new RefundTransactionResponse();
		  	String response = decoder.get("ACK");
		  	refundtransactionResponse.setResponse(response);
			if(response.compareTo("Success") == 0)
			{
				
				String refundtransactionID = decoder.get("REFUNDTRANSACTIONID");
				String feerefundAmt = decoder.get("FEEREFUNDAMT");
				String grossrefundAmt = decoder.get("GROSSREFUNDAMT");
				String netrefundAmt = decoder.get("NETREFUNDAMT");
				String totalrefundedAmt = decoder.get("TOTALREFUNDEDAMT");
				
				refundtransactionResponse.setFeerefundAmt(feerefundAmt);
				refundtransactionResponse.setGrossrefundAmt(grossrefundAmt);
				refundtransactionResponse.setNetrefundAmt(netrefundAmt);
				refundtransactionResponse.setTotalrefundedAmt(totalrefundedAmt);
				refundtransactionResponse.setRefundtransactionId(refundtransactionID);
				
			}
			else
			{
				List<String> errorList = new ArrayList<String>();
				//HashMap<Integer, String> errorMap = new HashMap<Integer, String>();
				Integer numberofErrors = 0;
				
			
				while(decoder.get("L_ERRORCODE" + numberofErrors.toString()) != null)
				{
					String errorCode = decoder.get("L_ERRORCODE" + numberofErrors.toString());
					String shortCode = decoder.get("L_SHORTMESSAGE" + numberofErrors.toString());
					String longCode = decoder.get("L_LONGMESSAGE" + numberofErrors.toString());
					//String shortCode = decoder.get("L_SHORTMESSAGE" + numberofErrors.toString());
					errorList.add(errorCode);
					System.out.println(shortCode + "long:" + longCode);
					numberofErrors ++;
				}
				refundtransactionResponse.setErrorList(errorList);
				
			
			}
			
		  	
		  	
		  	
			return refundtransactionResponse; 
	}

}
