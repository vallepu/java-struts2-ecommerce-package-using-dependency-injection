package org.webhop.ywdc.admin;

import org.webhop.ywdc.beans.OrderFinalizedShipping;
import org.webhop.ywdc.beans.OrderHistory;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.paypal.sdk.DoCapture;
import org.webhop.ywdc.paypal.sdk.DoCaptureResponse;
import org.webhop.ywdc.paypal.sdk.RefundTransaction;
import org.webhop.ywdc.paypal.sdk.RefundTransactionResponse;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class CompleteOrders extends ActionSupport
{
	private static final long serialVersionUID = -749061982760143551L;
	public String historynote;
	public String orderId;
	public String submithistory;
	public String transactionId;
	public String total;
	public String refund;
	public String submitrefund;
	public String shippingcost;
	
	public String confirmrefund;
	public String refundnote;
	
	
	public String UserTempToken;
	
	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices service = injector.getInstance(AuthenticationServices.class);
		service.setConnection(connection);
		service.setInjector(injector);
		
		if(submithistory != null)
		{
			
		
			if(historynote != null)
			{
				
				OrderHistory orderHistory = new OrderHistory();
				orderHistory.setNote(historynote);
				orderHistory.setOrderid(orderId);
				
				orderHistory.setStatus("Authorization");
				
				service.addOrderHistory(orderHistory);
			}
		}
		if(submitrefund != null)
		{
			
		
			if(historynote != null && historynote.compareTo("") != 0)
			{
				
				OrderHistory orderHistory = new OrderHistory();
				orderHistory.setNote(historynote);
				orderHistory.setOrderid(orderId);
				
				orderHistory.setStatus("Capture");
				
				service.addOrderHistory(orderHistory);
			}
			
		
				if(confirmrefund != null)
				{
					if(confirmrefund.compareTo("true") == 0)
					{
						if(refundnote != null)
						{
							OrderHistory orderHistory = new OrderHistory();
							orderHistory.setNote(refundnote);
							orderHistory.setOrderid(orderId);
							
							orderHistory.setStatus("Refund");
							
							service.addOrderHistory(orderHistory);
							
							
						}
						
						System.out.println("test");
						
					
						RefundTransaction doRefund = new RefundTransaction();
						RefundTransactionResponse refundtransactionResponse = doRefund.RefundTransactionCode("Full", transactionId, total, "Refunded");
						String response = refundtransactionResponse.getResponse();
						if(response.compareTo("Success") == 0)
						{
							refund="Amount Refunded";
							service.changeOrderStatusToRefund(orderId);
							service.updateOrderPaymentTransactionId(orderId, refundtransactionResponse.getRefundtransactionId());
						}
						else
						{
							refund="Refund Failed. Please login to PayPal Merchant Exchange for a manual refund";
						}
					}
					else
					{
						refund="Make sure to check checkbox to actually refund the order.";
					}
				}
				else
				{
					refund="Make sure to check checkbox to actually refund the order.";
				}
		}
		
		
		return SUCCESS;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}

	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
	public String getHistorynote() {
		return historynote;
	}
	public void setHistorynote(String historynote) {
		this.historynote = historynote;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSubmithistory() {
		return submithistory;
	}
	public void setSubmithistory(String submithistory) {
		this.submithistory = submithistory;
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getShippingcost() {
		return shippingcost;
	}
	public void setShippingcost(String shippingcost) {
		this.shippingcost = shippingcost;
	}
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	public String getSubmitrefund() {
		return submitrefund;
	}
	public void setSubmitrefund(String submitrefund) {
		this.submitrefund = submitrefund;
	}
	public String getRefundnote() {
		return refundnote;
	}
	public void setRefundnote(String refundnote) {
		this.refundnote = refundnote;
	}
	public String getConfirmrefund() {
		return confirmrefund;
	}
	public void setConfirmrefund(String confirmrefund) {
		this.confirmrefund = confirmrefund;
	}
}
