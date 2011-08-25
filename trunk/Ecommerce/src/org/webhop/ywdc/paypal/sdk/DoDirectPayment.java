package org.webhop.ywdc.paypal.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;



public class DoDirectPayment 
{
	
	private NVPCallerServices caller = null;
	
	public DoDirectPaymentResponse DoDirectPaymentCode(String paymentAction, String ipAddress, String amount,String cardType,
								String acct,String expdate,String cvv2, String firstName,
								String lastName, String street, String city, String state, 
								String zip, String countryCode, String invoicenumber, String description, String shippingFullname, String shippingAddress, String shippingCity, String shippingState, String shippingCountry, String shippingZipcode, String shippingPhonenumber)
	{
		NVPEncoder encoder = new NVPEncoder();
		NVPDecoder decoder = new NVPDecoder();
		
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
			encoder.add("METHOD","DoDirectPayment");

			// Add request-specific fields to the request string.
			encoder.add("PAYMENTACTION",paymentAction);
			encoder.add("IPADDRESS", ipAddress);
			encoder.add("AMT",amount);
			encoder.add("CREDITCARDTYPE",cardType);		
			encoder.add("ACCT",acct);						
			encoder.add("EXPDATE",expdate);
			encoder.add("CVV2",cvv2);
			encoder.add("FIRSTNAME",firstName);
			encoder.add("LASTNAME",lastName);										
			encoder.add("STREET",street);
			encoder.add("CITY",city);	
			encoder.add("STATE",state);			
			encoder.add("ZIP",zip);	
			encoder.add("COUNTRYCODE",countryCode);
			encoder.add("INVNUM", invoicenumber);
			encoder.add("CUSTOM", description);
			
			encoder.add("SHIPTONAME", shippingFullname);
			encoder.add("SHIPTOSTREET", shippingAddress);
			encoder.add("SHIPTOCITY", shippingCity);
			encoder.add("SHIPTOSTATE", shippingState);
			encoder.add("SHIPTOZIP", shippingZipcode);
			encoder.add("SHIPTOCOUNTRY", shippingCountry);
			encoder.add("SHIPTOPHONENUM", shippingPhonenumber);
			
			encoder.add("RETURNFMFDETAILS", "1");

			// Execute the API operation and obtain the response.
			String NVPRequest = encoder.encode();
			String NVPResponse =(String) caller.call(NVPRequest);
			decoder.decode(NVPResponse);
			
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		DoDirectPaymentResponse ddpResponse = new DoDirectPaymentResponse();
		String response = decoder.get("ACK");
		if(response.compareTo("Success") == 0)
		{
			
			String transactionID = decoder.get("TRANSACTIONID");
			String amt = decoder.get("AMT");
			String avsCode = decoder.get("AVSCODE");
			String cvv2match = decoder.get("CVV2MATCH");
			String L_FMFfilter1 = decoder.get("L_FMFfilter1");
			String L_FMFfilter4 = decoder.get("L_FMFfilter4");
			ddpResponse.setRESPONSE(response);
			ddpResponse.setTRANSACTIONID(transactionID);
			ddpResponse.setAMT(amt);
			ddpResponse.setAVSCODE(avsCode);
			ddpResponse.setCVV2MATCH(cvv2match);
			ddpResponse.setL_FMFfilter1(L_FMFfilter1);
			ddpResponse.setL_FMFfilter1(L_FMFfilter4);
		}
		else
		{
			List<String> errorList = new ArrayList<String>();
			
			Integer numberofErrors = 0;
			ErrorCodes errorCodes = new ErrorCodes();
			HashMap<Integer, ErrorCode> errorMap = errorCodes.getErrorMap();
			while(decoder.get("L_ERRORCODE" + numberofErrors.toString()) != null)
			{
				String errorCode = decoder.get("L_ERRORCODE" + numberofErrors.toString());
		
				errorList.add(errorCode);
				
				numberofErrors ++;
			}
			ddpResponse.setErrorcodeList(errorList);
			ddpResponse.setRESPONSE(response);
		
		}
		
		return ddpResponse;
	}
	
}
