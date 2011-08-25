package org.webhop.ywdc.paypal.sdk;

import java.util.List;

public class GetTransactionDetailsResponse 
{
	public String transactionID; 
	
	public String recieverEmail; 
	public String recieverId;
	public String parenttransactionId; 
	public String receiptId;
	public String transactionType;
	public String paymentType;
	public String orderTime;
	public String amt;
	public String currencyCode;
	public String feeAmt;
	public String taxAmt;
	public String exchangeRate;
	public String paymentStatus;
	public String pendingReason;
	public String reasonCode;
	public String protectionEligibility;
	public String protectioneligibilityType;	
	public String invoiceNumber;
	public String custom;
	public String note;
	public String salesTax;
	public String response;
	public String settlementAmt;


	

	public List<String> errorList;
	
	
	
		public GetTransactionDetailsResponse()
	{
		
	}
	
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getRecieverEmail() {
		return recieverEmail;
	}
	public void setRecieverEmail(String recieverEmail) {
		this.recieverEmail = recieverEmail;
	}
	public String getRecieverId() {
		return recieverId;
	}
	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}
	public String getParenttransactionId() {
		return parenttransactionId;
	}
	public void setParenttransactionId(String parenttransactionId) {
		this.parenttransactionId = parenttransactionId;
	}
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}
	public String getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(String taxAmt) {
		this.taxAmt = taxAmt;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPendingReason() {
		return pendingReason;
	}
	public void setPendingReason(String pendingReason) {
		this.pendingReason = pendingReason;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getProtectionEligibility() {
		return protectionEligibility;
	}
	public void setProtectionEligibility(String protectionEligibility) {
		this.protectionEligibility = protectionEligibility;
	}
	public String getProtectioneligibilityType() {
		return protectioneligibilityType;
	}
	public void setProtectioneligibilityType(String protectioneligibilityType) {
		this.protectioneligibilityType = protectioneligibilityType;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSalesTax() {
		return salesTax;
	}
	public void setSalesTax(String salesTax) {
		this.salesTax = salesTax;
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
	public String getSettlementAmt() {
		return settlementAmt;
	}

	public void setSettlementAmt(String settlementAmt) {
		this.settlementAmt = settlementAmt;
	}
}
