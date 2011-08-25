package org.webhop.ywdc.admin;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;

import org.webhop.ywdc.beans.OrderFinalizedShipping;
import org.webhop.ywdc.beans.OrderHistory;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.paypal.sdk.DoCapture;
import org.webhop.ywdc.paypal.sdk.DoCaptureResponse;
import org.webhop.ywdc.paypal.sdk.RefundTransaction;
import org.webhop.ywdc.paypal.sdk.RefundTransactionResponse;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.tools.Mailer;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class OrdersInDateRangeAction extends ActionSupport 
{
	public String UserTempToken;
	public String datefrom;
	public String dateto;
	public String datefromto;
	////////////////////////////
	public String submitrefund;
	public String refundnote;
	
	public String refund;
	public String shippingcost;
	public String confirmrefund;
	///////////////////////////////
	
	public String historynote;
	
	public String orderId;
	public String submithistory;
	public String submitcapture;
	public String transactionId;
	public String total;
	public String capture;
	public String serviceName;
	public String email;
	public String trackingnumber;
	public String carrierName;
	public String confirmcapture;
	public String shippingChange;
	public String shippingwholeNumber;
	
	
	public String execute()
	{
		
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices service = injector.getInstance(AuthenticationServices.class);
		service.setConnection(connection);
		service.setInjector(injector);
		
		if(dateto != null && datefrom != null)
		{
			Map session = ActionContext.getContext().getSession();
			session.put("dateto", dateto);
			session.put("datefrom", datefrom);
		}
		else
		{
			Map session = ActionContext.getContext().getSession();
			dateto = (String) session.get("dateto");
			datefrom = (String) session.get("datefrom");
		}
		
		if(submithistory != null)
		{
			
		
			if(historynote != null && historynote.length() != 0)
			{
				
				OrderHistory orderHistory = new OrderHistory();
				orderHistory.setNote(historynote);
				orderHistory.setOrderid(orderId);
				
				orderHistory.setStatus("Authorization");
				
				service.addOrderHistory(orderHistory);
			}
		}
		if(submitcapture != null)
		{
			
			if(confirmcapture != null)
			{
				if(confirmcapture.compareTo("true") == 0)
				{
					
					Properties configFile = new Properties();
					   
					try 
					{
						configFile.load(this.getClass().getClassLoader().getResourceAsStream("mail.properties"));
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
					
					String mailHost =  configFile.getProperty("mailhost"); 
					String mailUser =  configFile.getProperty("mailuser"); 
					String port =  configFile.getProperty("port"); 
					String mailPassword =  configFile.getProperty("password"); 
					String mailSender = configFile.getProperty("sender");
					String companyName = configFile.getProperty("companyname");
					String companyImage = configFile.getProperty("companyimage");
					String subject = "Your Order Tracking Information From " + companyName;
					
					String messageText = companyImage + "<br /><h4>Here is your tracking number for your recent order</h4><br />";
					messageText = messageText + "<b>Carrier:</b> " + carrierName + " <br /><b>Service:</b> " + serviceName;
					messageText = messageText + "<br /><b>Tracking Number:</b> " + trackingnumber;
					
					
					Mailer mailer = new Mailer();
					mailer.setFrom(mailSender);
					mailer.setHost(mailHost);
					mailer.setHtml(messageText);
					mailer.setPassword(mailPassword);
					mailer.setPort(port);
					mailer.setSubject(subject);
					mailer.setTo(email);
					mailer.setUser(mailUser);
					mailer.sendMail();
				
					
					
					OrderFinalizedShipping orderfinalizedShipping = new OrderFinalizedShipping();
					orderfinalizedShipping.setOrderid(orderId);
					
					
					
					String finalizedshippingcostString = shippingwholeNumber + "." + shippingChange;
					double finalshippingcostDouble = Double.parseDouble(finalizedshippingcostString);
					
					orderfinalizedShipping.setFinalshippingcost(finalshippingcostDouble);
					
					
				
					DoCapture doCapture = new DoCapture();
					DoCaptureResponse docaptureResponse = doCapture.doCapture("DoCapture", transactionId, "Complete", total, orderId, "DoCapture:Completing Order");
					String response = docaptureResponse.getResponse();
					if(response.compareTo("Success") == 0)
					{
						capture="Capture Succeeded";
						service.changeOrderStatusToComplete(orderId);
						service.updateOrderPaymentTransactionId(orderId, docaptureResponse.getTRANSACTIONID());
						service.addOrderFinalizedShipping(orderfinalizedShipping);
					}
					else
					{
						capture="Capture Failed. Please retry or login to PayPal Merchant Exchange for a manual capture";
					}
				}
				else
				{
					capture="Capture failed. Make sure to check Confirm Correct Shipping Amount button.";
				}
			}
			else
			{
				capture="Capture failed. Make sure to check Confirm Correct Shipping Amount button.";
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
		
		
		
		
		if(datefrom != null & dateto != null)
		{
			datefromto = datefrom + "/" + dateto;
		}
		
		return SUCCESS;
	}
	public String getUserTempToken() {
		return UserTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
	public String getDatefrom() {
		return datefrom;
	}
	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}
	public String getDateto() {
		return dateto;
	}
	public void setDateto(String dateto) {
		this.dateto = dateto;
	}
	public String getDatefromto() {
		return datefromto;
	}
	public void setDatefromto(String datefromto) {
		this.datefromto = datefromto;
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
	public String getSubmitcapture() {
		return submitcapture;
	}
	public void setSubmitcapture(String submitcapture) {
		this.submitcapture = submitcapture;
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
	public String getCapture() {
		return capture;
	}
	public void setCapture(String capture) {
		this.capture = capture;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTrackingnumber() {
		return trackingnumber;
	}
	public void setTrackingnumber(String trackingnumber) {
		this.trackingnumber = trackingnumber;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getConfirmcapture() {
		return confirmcapture;
	}
	public void setConfirmcapture(String confirmcapture) {
		this.confirmcapture = confirmcapture;
	}
	public String getShippingChange() {
		return shippingChange;
	}
	public void setShippingChange(String shippingChange) {
		this.shippingChange = shippingChange;
	}
	public String getShippingwholeNumber() {
		return shippingwholeNumber;
	}
	public void setShippingwholeNumber(String shippingwholeNumber) {
		this.shippingwholeNumber = shippingwholeNumber;
	}
	public String getSubmitrefund() {
		return submitrefund;
	}
	public void setSubmitrefund(String submitrefund) {
		this.submitrefund = submitrefund;
	}
	public String getShippingcost() {
		return shippingcost;
	}
	public void setShippingcost(String shippingcost) {
		this.shippingcost = shippingcost;
	}
	public String getConfirmrefund() {
		return confirmrefund;
	}
	public void setConfirmrefund(String confirmrefund) {
		this.confirmrefund = confirmrefund;
	}
	public String getRefundnote() {
		return refundnote;
	}
	public void setRefundnote(String refundnote) {
		this.refundnote = refundnote;
	}
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
}
