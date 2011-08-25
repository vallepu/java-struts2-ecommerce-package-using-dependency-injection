package org.webhop.ywdc.paypal.sdk;

import java.util.HashMap;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	
	/*	GetTransactionDetails dtDetails = new GetTransactionDetails();
		GetTransactionDetailsResponse gtdResponse = dtDetails.getTransactionDetailsCode("6PV04860WV8148327");

		ErrorCodes eCodes = new ErrorCodes();
		HashMap<Integer, ErrorCode> eMap = eCodes.getErrorMap();
		System.out.println(gtdResponse.getAmt());
		// TODO Auto-generated method stub
*/
		
		RefundTransaction rTran = new RefundTransaction();
		RefundTransactionResponse rtResponse = rTran.RefundTransactionCode("Full", "90G39540XF237423V", "99.00", "refund");
		System.out.println(rtResponse.getResponse());
		
		
	}

}
