package org.webhop.ywdc.admin;

import java.util.List;

import org.webhop.ywdc.beans.OrderCarrier;
import org.webhop.ywdc.beans.OrderFinalizedShipping;
import org.webhop.ywdc.beans.OrderHistory;
import org.webhop.ywdc.beans.OrderItems;
import org.webhop.ywdc.beans.OrderPayment;
import org.webhop.ywdc.beans.OrderShipping;
import org.webhop.ywdc.beans.OrderStatus;
import org.webhop.ywdc.beans.ShippingCarrier;
import org.webhop.ywdc.beans.ShippingServices;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.paypal.sdk.GetTransactionDetails;
import org.webhop.ywdc.paypal.sdk.GetTransactionDetailsResponse;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class CompleteOrderAction extends ActionSupport
{
	public String orderId;
	public String orderpaymentId;
	

	public OrderPayment orderPayment;
	public OrderStatus orderStatus;
	public List<OrderHistory> orderhistoryList;
	public OrderShipping orderShipping;
	public OrderCarrier orderCarrier;
	public ShippingServices shippingService;
	public ShippingCarrier shippingCarrier;
	public List<OrderItems> orderitemsList;
	public String UserTempToken;
	public GetTransactionDetailsResponse gettransactiondetailsResponse;
	public OrderFinalizedShipping orderfinalizedShipping;
	
	public String paymentStatus;
	public String total;
	public String transactionId;
	
	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices service = injector.getInstance(AuthenticationServices.class);
		service.setConnection(connection);
		service.setInjector(injector);
		orderPayment = service.getOrderPaymentByOrderPaymentId(orderpaymentId);
		transactionId = orderPayment.getTransactionid();
		orderId = orderPayment.getOrderid();
		orderStatus = service.getOrderStatusByOrderId(orderId);
		orderhistoryList = service.getOrderHistoryByOrderId(orderId);
		orderShipping = service.getOrderShippingByOrderId(orderId);
		orderCarrier = service.getOrderCarrierByOrderId(orderId);
		orderfinalizedShipping = service.getOrderFinalizedShippingByOrderId(orderId);
		Integer serviceId = orderCarrier.getServiceid();
		shippingService = service.getShippingServiceById(serviceId);
		shippingCarrier = service.getShippingCarrierById(shippingService.getCarrierid());
		orderitemsList = service.getOrderItemsByOrderId(orderId);
		orderShipping = service.getOrderShippingByOrderId(orderId);
		
		GetTransactionDetails gettransactionDetails = new GetTransactionDetails();
		gettransactiondetailsResponse = gettransactionDetails.getTransactionDetailsCode(orderPayment.getTransactionid());
		total = gettransactiondetailsResponse.getAmt();
		paymentStatus = gettransactiondetailsResponse.getPaymentStatus();
		
		return SUCCESS;
	}
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		
		this.orderId = orderId;
	}

	public OrderPayment getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(OrderPayment orderPayment) {
		this.orderPayment = orderPayment;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderHistory> getOrderhistoryList() {
		return orderhistoryList;
	}

	public void setOrderhistoryList(List<OrderHistory> orderhistoryList) {
		this.orderhistoryList = orderhistoryList;
	}

	public OrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(OrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

	public OrderCarrier getOrderCarrier() {
		return orderCarrier;
	}

	public void setOrderCarrier(OrderCarrier orderCarrier) {
		this.orderCarrier = orderCarrier;
	}

	public ShippingServices getShippingService() {
		return shippingService;
	}

	public void setShippingService(ShippingServices shippingService) {
		this.shippingService = shippingService;
	}

	public ShippingCarrier getShippingCarrier() {
		return shippingCarrier;
	}

	public void setShippingCarrier(ShippingCarrier shippingCarrier) {
		this.shippingCarrier = shippingCarrier;
	}

	public List<OrderItems> getOrderitemsList() {
		return orderitemsList;
	}

	public void setOrderitemsList(List<OrderItems> orderitemsList) {
		this.orderitemsList = orderitemsList;
	}

	public String getUserTempToken() {
		return UserTempToken;
	}

	public void setUserTempToken(String userTempToken) {
		UserTempToken = userTempToken;
	}
	public String getOrderpaymentId() {
		return orderpaymentId;
	}
	public void setOrderpaymentId(String orderpaymentId) {
		this.orderpaymentId = orderpaymentId;
	}
	public GetTransactionDetailsResponse getGettransactiondetailsResponse() {
		return gettransactiondetailsResponse;
	}
	public void setGettransactiondetailsResponse(GetTransactionDetailsResponse gettransactiondetailsResponse) {
		this.gettransactiondetailsResponse = gettransactiondetailsResponse;
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
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public OrderFinalizedShipping getOrderfinalizedShipping() {
		return orderfinalizedShipping;
	}
	public void setOrderfinalizedShipping(OrderFinalizedShipping orderfinalizedShipping) {
		this.orderfinalizedShipping = orderfinalizedShipping;
	}
	
}
