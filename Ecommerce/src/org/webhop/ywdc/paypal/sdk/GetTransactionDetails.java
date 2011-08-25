package org.webhop.ywdc.paypal.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;



public class GetTransactionDetails 
{

        public GetTransactionDetailsResponse getTransactionDetailsCode(String transactionId)
    	{
    		NVPCallerServices caller = null;
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
                ProfileConfiguration profileConfig = new ProfileConfiguration();
                // Set up your API credentials, PayPal end point, API operation and version.
                profile.setAPIUsername(profileConfig.getUsername());
    			profile.setAPIPassword(profileConfig.getPassword());
    			profile.setSignature(profileConfig.getSignature());
    			profile.setEnvironment(profileConfig.getEnvironment());
    	        profile.setSubject("");
    	        caller.setAPIProfile(profile);
    		encoder.add("VERSION", "51.0");	
    		encoder.add("METHOD","GetTransactionDetails");

		// Add request-specific fields to the request string.
    		encoder.add("TRANSACTIONID",transactionId);

		// Execute the API operation and obtain the response.
    		String NVPRequest = encoder.encode();
    		String NVPResponse = caller.call(NVPRequest);
    		decoder.decode(NVPResponse);
			
    		}catch (Exception ex)
    		{
    			ex.printStackTrace();
    		}
    		GetTransactionDetailsResponse gtdResponse = new GetTransactionDetailsResponse();
    		String response = decoder.get("ACK");
    		gtdResponse.setResponse(response);
    		if(response.compareTo("Success") == 0)
    		{
    			
    			String transactionID = decoder.get("TRANSACTIONID");
    			String recieverEmail = decoder.get("RECEIVEREMAIL");
    			String recieverId = decoder.get("RECEIVERID");
    			String parenttransactionId = decoder.get("PARENTTRANSACTIONID");
    			String receiptId = decoder.get("RECEIPTID");
    			String transactionType = decoder.get("TRANSACTIONTYPE");
    			String paymentType = decoder.get("PAYMENTTYPE");
    			String orderTime = decoder.get("ORDERTIME");
    			String amt = decoder.get("AMT");
    			String currencyCode = decoder.get("CURRENCYCODE");
    			String feeAmt = decoder.get("FEEAMT");
    			String taxAmt = decoder.get("TAXAMT");
    			
    			gtdResponse.setTransactionID(transactionID);
    			gtdResponse.setRecieverEmail(recieverEmail);
    			gtdResponse.setRecieverId(recieverId);
    			gtdResponse.setParenttransactionId(parenttransactionId);
    			gtdResponse.setReceiptId(receiptId);
    			gtdResponse.setTransactionType(transactionType);
    			gtdResponse.setPaymentType(paymentType);
    			gtdResponse.setOrderTime(orderTime);
    			gtdResponse.setAmt(amt);
    			gtdResponse.setCurrencyCode(currencyCode);
    			gtdResponse.setFeeAmt(feeAmt);
    			gtdResponse.setTaxAmt(taxAmt);
    			
    			String exchangeRate = decoder.get("EXCHANGERATE");
    			String paymentStatus = decoder.get("PAYMENTSTATUS");
    			String pendingReason = decoder.get("PENDINGREASON");
    			String reasonCode = decoder.get("REASONCODE");
    			String protectionEligibility = decoder.get("PROTECTIONELIGIBILITY");
    			String protectioneligibilityType = decoder.get("PROTECTIONELIGIBILITYTYPE");	
    			String invoiceNumber = decoder.get("INVNUM");
    			String custom = decoder.get("CUSTOM");
    			String note = decoder.get("NOTE");
    			String salesTax = decoder.get("SALESTAX");
    			String settlementAmt = decoder.get("SETTLEAMT");
    			
    			gtdResponse.setExchangeRate(exchangeRate);
    			gtdResponse.setPaymentStatus(paymentStatus);
    			gtdResponse.setPendingReason(pendingReason);
    			gtdResponse.setReasonCode(reasonCode);
    			gtdResponse.setProtectionEligibility(protectionEligibility);
    			gtdResponse.setProtectioneligibilityType(protectioneligibilityType);
    			gtdResponse.setInvoiceNumber(invoiceNumber);
    			gtdResponse.setCustom(custom);
    			gtdResponse.setNote(note);
    			gtdResponse.setSalesTax(salesTax);
    			gtdResponse.setSettlementAmt(settlementAmt);
    			
    		}
    		else
    		{
    			List<String> errorList = new ArrayList<String>();
    			//HashMap<Integer, String> errorMap = new HashMap<Integer, String>();
    			Integer numberofErrors = 0;
    			ErrorCodes errorCodes = new ErrorCodes();
    			HashMap<Integer, ErrorCode> errorMap = errorCodes.getErrorMap();
    			while(decoder.get("L_ERRORCODE" + numberofErrors.toString()) != null)
    			{
    				String errorCode = decoder.get("L_ERRORCODE" + numberofErrors.toString());
    		
    				errorList.add(errorCode);
    				
    				numberofErrors ++;
    			}
    			gtdResponse.setErrorList(errorList);
    		}
    		
    		
    		
			return gtdResponse;
			
    	}
	}


