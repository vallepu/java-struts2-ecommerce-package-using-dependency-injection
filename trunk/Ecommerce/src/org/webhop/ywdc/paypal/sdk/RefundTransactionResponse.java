package org.webhop.ywdc.paypal.sdk;

import java.util.List;

public class RefundTransactionResponse 
{
	public String refundtransactionId;
	
	public String feerefundAmt;
	public String grossrefundAmt;
	public String netrefundAmt;
	public String totalrefundedAmt;
	public List<String> errorList;
	public String response;
	

	

	public RefundTransactionResponse(){}
	
	public String getRefundtransactionId() {
		return refundtransactionId;
	}
	public void setRefundtransactionId(String refundtransactionId) {
		this.refundtransactionId = refundtransactionId;
	}
	public String getFeerefundAmt() {
		return feerefundAmt;
	}
	public void setFeerefundAmt(String feerefundAmt) {
		this.feerefundAmt = feerefundAmt;
	}
	public String getGrossrefundAmt() {
		return grossrefundAmt;
	}
	public void setGrossrefundAmt(String grossrefundAmt) {
		this.grossrefundAmt = grossrefundAmt;
	}
	public String getNetrefundAmt() {
		return netrefundAmt;
	}
	public void setNetrefundAmt(String netrefundAmt) {
		this.netrefundAmt = netrefundAmt;
	}
	public String getTotalrefundedAmt() {
		return totalrefundedAmt;
	}
	public void setTotalrefundedAmt(String totalrefundedAmt) {
		this.totalrefundedAmt = totalrefundedAmt;
	}
	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
