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
public class RefundedOrders extends ActionSupport
{
	private static final long serialVersionUID = -749061982760143551L;
	public String historynote;
	public String orderId;
	public String submithistory;
	
	
	
	
	
	
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
	

	
	
	
}
