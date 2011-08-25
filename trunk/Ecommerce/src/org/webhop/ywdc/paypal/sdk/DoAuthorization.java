package org.webhop.ywdc.paypal.sdk;

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;



public class DoAuthorization 
{

	
	public String DoAuthorizationCode(String transactionId,String amount)
	{
		NVPDecoder decoder = new NVPDecoder();
		NVPEncoder encoder = new NVPEncoder();
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
		encoder.add("METHOD","DoAuthorization");

		// Add request-specific fields to the request string.
			encoder.add("TRANSACTIONID",transactionId);
			encoder.add("AMT",amount);
			encoder.add("CURRENCYCODE", "USD");		

		// Execute the API operation and obtain the response.
			String NVPRequest = encoder.encode();
			String NVPResponse = caller.call(NVPRequest);
			decoder.decode(NVPResponse);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return decoder.get("ACK");
		
	}
}

