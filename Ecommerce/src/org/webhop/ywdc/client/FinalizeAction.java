package org.webhop.ywdc.client;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.webhop.ywdc.beans.OrderCarrier;
import org.webhop.ywdc.beans.OrderHistory;
import org.webhop.ywdc.beans.OrderItems;
import org.webhop.ywdc.beans.OrderPayment;
import org.webhop.ywdc.beans.OrderShipping;
import org.webhop.ywdc.beans.OrderStatus;
import org.webhop.ywdc.beans.ShippingCarrier;
import org.webhop.ywdc.beans.ShippingServices;
import org.webhop.ywdc.dtos.PublicProduct;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.paypal.sdk.DoDirectPayment;
import org.webhop.ywdc.paypal.sdk.DoDirectPaymentResponse;
import org.webhop.ywdc.paypal.sdk.ErrorCode;
import org.webhop.ywdc.paypal.sdk.ErrorCodes;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.tools.CCUtils;
import org.webhop.ywdc.tools.Mailer;

import org.webhop.ywdc.util.ConnectionProvider;
import org.webhop.ywdc.util.HibernateConnection;
import org.webhop.ywdc.util.RandomString;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
@Secured
public class FinalizeAction extends ActionSupport 
{
	
	private static final long serialVersionUID = -3701068879103736614L;
	public String firstname;
	public Long cardnumber;
	public Integer cardtype;
	public String expmonth;
	public String expyear;
	public String lastname;
	public String address;
	public String emailaddress;
	public String city;
	public String state;
	public Integer zipcode;
	public Integer cvv;
	public String country;
	public Long phonenumber;
	public String finalize;
	public String submitfinalization;
	public HashMap<String, String> paymentInfo;
	public HashMap<Integer, Integer> cartMap;
	public HashMap<String, String> shipping;
	public HashMap<String, String> shippingCarrier;
	public List<PublicProduct> cartItems;
	public HashMap<String, String> cardMap;
	public String cardName;
	public String callerIP;
	public Double total;
	
	public Double itemtotal;
	public String itemtotalString;
	
	public String totalString;
	public Double shippingtotal;
	
	public String shippingservice;
	public String shippingcarrier;
	public List<ErrorCode> publicList;
	public String responseCode;
	

	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices service = injector.getInstance(AuthenticationServices.class);
		service.setConnection(connection);
		HttpServletRequest request = ServletActionContext.getRequest();
		//callerIP = request.getRemoteAddr(); 
		///////////////////////////////////////CHANGE THIS FOR PRODUCTION///////////////////////////////
		//////////////////////////////////////****************************////////////////////////////
		//////////////////////////////////////****************************//////////////////////////////
		
		
		callerIP = "149.175.50.172";
		Map session = ActionContext.getContext().getSession();
		cardMap = new HashMap<String, String>();
		cardMap.put("0", "Visa");
		//put the rest of the values in this cardMap
		
		if(finalize != null) //then we just came from payment.jsp
		{
			
						
			
			CCUtils ccUtil = new CCUtils();
			Boolean computedcardValidation = null;
			//String cardIssues = null;
			List<String> cardIssues = new ArrayList<String>();
			Boolean returntoPayment = false;
			/*
			if(firstname == null || firstname.length() == 0 || lastname == null || lastname.length() == 0 || cardnumber == null || cardnumber.length() == 0 || address == null || address.length() == 0 || city == null || city.length() == 0 || state == null || state.length() == 0 || zipcode == null || zipcode.length() == 0 || cvv == null || cvv.length() == 0 || country == null || country.length() == 0 || phonenumber == null || phonenumber.length() == 0)
			{
				returntoPayment = true; 
				cardIssues.add("Please fill out form completely");
				ActionContext.getContext().getParameters().put("cardIssues", cardIssues);
				return "failure";
			}
			*/
			Integer computedCardType = ccUtil.getCardID(Long.toString(cardnumber));
			try 
			{
				computedcardValidation = ccUtil.validCC(Long.toString(cardnumber));
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			if(computedCardType != cardtype)
			{
				returntoPayment = true;
				cardIssues.add("Card type does not match card number. Please choose correct card type.");
			}
			if(computedcardValidation != true)
			{
				returntoPayment = true;
				cardIssues.add("Invalid credit card number. Please retype card information");
			}
			if(returntoPayment == true)
			{
				ActionContext.getContext().getParameters().put("cardIssues", cardIssues);
				return "failure";
			}
			
			//then go through session info and populate all the shipping, cart, payment
			//information properties so that the Finalize.jsp page will show them the data so 
			//that they can decide whether to finalizesubmit.
			
			
			Cart cart = new Cart();
			cartMap = cart.getSessionCartMap();
			cartItems = service.getPublicProductByHashMap(cartMap);
			shipping = (HashMap<String, String>) session.get("shipping");
			shippingCarrier = (HashMap<String, String>) session.get("shippingcarrier");
			
			itemtotal = 0.0;
			for(PublicProduct cartItem: cartItems)
			{
				Integer productId = cartItem.getId();
				double price = cartItem.getPrice();
				Integer quantity = cartMap.get(cartItem.id);
				itemtotal = itemtotal + (price * quantity);
				
			}
			
			 NumberFormat twoDForm = NumberFormat.getCurrencyInstance(Locale.US);  

			 itemtotalString = twoDForm.format(itemtotal);
				//String totaltoString = itemtotal.toString();
			
			cardName = cardMap.get(Integer.toString(cardtype));
			paymentInfo = new HashMap<String, String>();
			paymentInfo.put("firstname", firstname);
			paymentInfo.put("lastname", lastname);
			paymentInfo.put("cardnumber", Long.toString(cardnumber));
			paymentInfo.put("cardtype", Integer.toString((cardtype)));
			paymentInfo.put("expmonth", expmonth);
			paymentInfo.put("expyear", expyear);
			paymentInfo.put("address", address);
			paymentInfo.put("city", city);
			paymentInfo.put("state", state);
			paymentInfo.put("zipcode", Integer.toString(zipcode));
			paymentInfo.put("cvv", Integer.toString(cvv));
			paymentInfo.put("country", country);
			paymentInfo.put("phonenumber", Long.toString(phonenumber));
			paymentInfo.put("email", emailaddress);
		
			session.put("payment", paymentInfo);

////////////////////////////////////////////////////////////////////////////////////////
			String serviceidString = (String) shippingCarrier.get("serviceId");
			Integer serviceId = Integer.parseInt(serviceidString);
			ShippingServices shipService = service.getShippingServiceById(serviceId);
			ShippingCarrier shipCarrier = service.getShippingCarrierById(shipService.getCarrierid());
			shippingservice = shipService.getServicename();
			shippingcarrier = shipCarrier.getCarrier();
			//shippingcarrier = shipCarrier.getCarrier();
			String totalshippingcost = (String) shippingCarrier.get("totalshippingCost");
			shippingtotal = (double) Double.parseDouble(totalshippingcost);
			////////////////////////////////////////////////////////////////////////////////////////
			total = shippingtotal + itemtotal;
			
			

			 totalString = twoDForm.format(total);
			
			return SUCCESS;
		
		
		}
		else
		{
			
			
		    //else show all submitted data from session for the user to check over.
			//have form available that submits to finalize.jsp page. 
			//have conditioonal statement that checks for the submit button and if so 
			//then tries to process the credit card information
			if(submitfinalization != null)
			{
				Cart cart = new Cart();
				cartMap = cart.getSessionCartMap();
				cartItems = service.getPublicProductByHashMap(cartMap);
				shipping = (HashMap<String, String>) session.get("shipping");
				shippingCarrier = (HashMap<String, String>) session.get("shippingcarrier");
				
				total = 0.0;
				String description = "ID and Quantity of Items purchases: [";
				for(PublicProduct cartItem: cartItems)
				{
					Integer productId = cartItem.getId();
					double price = cartItem.getPrice();
					Integer quantity = cartMap.get(cartItem.id);
					description = description + "{" + productId + ":" + quantity + "}";
					total = total + (price * quantity);
					
				}
				String totalshippingcost = (String) shippingCarrier.get("totalshippingCost");
				shippingtotal = (double) Double.parseDouble(totalshippingcost);
				total = total + shippingtotal;
				
				total=total*100;
				double totalasprimitive = total;
				int totalasInt = (int) totalasprimitive;
				double rtotal = (double) totalasInt;
				rtotal = rtotal/100;
				Double roundedtotal = rtotal;
				
				description = description + "]";
					String totaltoString = total.toString();
				
				HashMap<String, String> paymentMap = (HashMap<String, String>) session.get("payment");
				String firstname = paymentMap.get("firstname");
				String lastname = paymentMap.get("lastname");
				String cardnumber = paymentMap.get("cardnumber");
				cardName = cardMap.get(paymentMap.get("cardtype"));
				String expmonth = paymentMap.get("expmonth");
				String expyear = paymentMap.get("expyear");
				String expdate = expmonth + expyear;
				String address = paymentMap.get("address");
				String city = paymentMap.get("city");
				String state = paymentMap.get("state");
				String zipcode = paymentMap.get("zipcode");
				String cvv = paymentMap.get("cvv");
				String country = paymentMap.get("country");
				String phonenumber = paymentMap.get("phonenumber");
				String email = paymentMap.get("email");
				
				String shippingAddress = shipping.get("address");
				String shippingCity = shipping.get("city");
				String shippingCountry = shipping.get("country");
				String shippingFullname = shipping.get("fullname");
				String shippingPhonenumber = shipping.get("phonenumber");
				String shippingState = shipping.get("state");
				String shippingZipcode = shipping.get("zipcode");
				
				RandomString randomString = new RandomString(20);
				String orderId = randomString.getRandomString();
				
				DoDirectPayment dodirectPayment = new DoDirectPayment();
				//wasnt adding shipping to total make sure that this is fixed
				//******************************
				DoDirectPaymentResponse dodirectpaymentResponse = dodirectPayment.DoDirectPaymentCode("Authorization", callerIP, roundedtotal.toString(), cardName, cardnumber, expdate, cvv, firstname, lastname, address, city, state, zipcode, country, orderId, description, shippingFullname, shippingAddress, shippingCity, shippingState, shippingCountry, shippingZipcode, shippingPhonenumber);
				publicList = new ArrayList<ErrorCode>();
				if(dodirectpaymentResponse == null)
				{
					ErrorCode generalError = new ErrorCode(true, 1, "There was an error processing your request", "There was an undefined error processing your request.", "Our Merchant Exchange (credit card processor) appears to be down. Please try again later.");
					publicList.add(generalError);
					
				}
				else
				{
					String transactionID = dodirectpaymentResponse.getTRANSACTIONID();
					responseCode = dodirectpaymentResponse.getRESPONSE();
					if(responseCode.compareTo("Failure") == 0)
					{
						
						List<ErrorCode> adminList = new ArrayList<ErrorCode>();
						
						ErrorCodes errorcodes = new ErrorCodes();
						List<String> errorList = dodirectpaymentResponse.getErrorcodeList();
						for(String err: errorList)
						{
							
							ErrorCode currentErrorCode = errorcodes.getErrorCodeByCode(err);
							if(currentErrorCode == null)
							{
								ErrorCode generalError = new ErrorCode(true, 1, "There was an error processing your request", "There was an undefined error processing your request.", "Our Merchant Exchange (credit card processor) appears to be down. Please try again later.");
								publicList.add(generalError);
							}
							else
							{
								if(currentErrorCode.getExternalError() == true)
								{
									publicList.add(currentErrorCode);
								}
								else
								{
									adminList.add(currentErrorCode);
								}
							}
							
						}
						if(publicList.isEmpty() == true)
						{
							ErrorCode generalError = new ErrorCode(true, 1, "There was an error processing your request", "There was an undefined error processing your request.", "Please try your submission again. If that does not work, our credit card processor currently might not be working.");
							publicList.add(generalError);
						}
						
						
						//if adminList is not empty then send the info to the admin (via email)//
						//*********************************************************************//
						//																	   //
						//S E N D    E R R O R    I N F O    T O    A D M I N I S T R A T O R  //
						//																	   //
						//*********************************************************************//
					}
					else  //else the response from paypal was a success and add all data to database
					{
						String emailString ="Thank you for your Order.";        
						emailString = emailString +  "<h4>Your Order Items</h4><br />";
						List<OrderItems> orderitemsList = new ArrayList<OrderItems>();
						for(PublicProduct cartItem: cartItems)
						{
							emailString = emailString + "<b>Name:</b> " + cartItem.getName() + "<br />";
							emailString = emailString + "<b>Price/Item:</b> $" + cartItem.getPrice().toString() + "<br />";
							Integer numberinInventoryControl = cartItem.getQuantity();
							Integer itemQuantity = cartMap.get(cartItem.getId());
							emailString = emailString + "<b>Quantity:</b> " + itemQuantity.toString() + "<br />";
							Integer numberafterorderInventoryControl = numberinInventoryControl - itemQuantity;
							cartItem.setQuantity(numberafterorderInventoryControl);
							
							OrderItems orderItem = new OrderItems();
							orderItem.setOrderid(orderId);
							orderItem.setPrice(cartItem.getPrice());
							orderItem.setProductid(cartItem.getId());
							orderItem.setProductname(cartItem.getName());
							orderItem.setQuantity(itemQuantity);
							orderitemsList.add(orderItem);
						}
						
						
						service.addOrderItems(orderitemsList);
						service.updateProductByPublicProductList(cartItems);
						//add orderitemsList to database
						
						OrderCarrier orderCarrier = new OrderCarrier();
						String ordersubmitted_serviceidString = (String) shippingCarrier.get("serviceId");
						Integer ordersubmitted_serviceId = Integer.parseInt(ordersubmitted_serviceidString);
						String ordersubmitted_totalshippingcost = (String) shippingCarrier.get("totalshippingCost");
						//Integer totalshippingcostasInt = Integer.parseInt(totalshippingcost);
						double ordersubmitted_totalshippingcostasDouble = (double) Double.parseDouble(ordersubmitted_totalshippingcost);
						orderCarrier.setServiceid(ordersubmitted_serviceId);
						orderCarrier.setOrderid(orderId);
						orderCarrier.setTotalshippingcost(ordersubmitted_totalshippingcostasDouble);
						//ADD orderCarrier TO DATABASE
						service.addOrderCarrier(orderCarrier);
						
						
						//Order History: IF DOING DIRECT SALE, THEN CHANGE THE STATUS INPUT FROM AUTHORIZE TO SALE
						OrderHistory orderHistory = new OrderHistory();
						orderHistory.setOrderid(orderId);
						orderHistory.setStatus("Authorization");
						String cvv2Match = dodirectpaymentResponse.getCVV2MATCH();
						String avsCode = dodirectpaymentResponse.getAVSCODE();
						String note = "Cvv2Match: " + cvv2Match + ", AVS Code: " + avsCode;
						orderHistory.setNote(note);
						//ADD orderHistory TO DATABASE
						service.addOrderHistory(orderHistory);
					
					
						///////////////////////////////////////////////////////////////
						////////////ORDER SHIPPING INFORMATION/////////////////////
						/////////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////////
						
						OrderShipping orderShipping = new OrderShipping();
						
						orderShipping.setAddress(shippingAddress);
						orderShipping.setCity(shippingCity);
						orderShipping.setCountry(shippingCountry);
						orderShipping.setFullname(shippingFullname);
						orderShipping.setOrderid(orderId);
						orderShipping.setPhonenumber(shippingPhonenumber);
						orderShipping.setState(shippingState);
						orderShipping.setZipcode(Integer.parseInt(shippingZipcode));
						
						
						emailString = emailString + "<h4>Shipping Information</h4><br />";
						emailString = emailString + "<b>Shipping Total:</b> $" + ordersubmitted_totalshippingcost + "<br />";
						emailString = emailString + "<b>Address:</b>" + shippingAddress + "<br />";
						emailString = emailString + "<b>City:</b>" + shippingCity + "<br />";
						emailString = emailString + "<b>Country:</b>" + shippingCountry + "<br />";
						emailString = emailString + "<b>Full Name:</b>" + shippingFullname + "<br />";
						emailString = emailString + "<b>State:</b>" + shippingState + "<br />";
						emailString = emailString + "<b>ZipCode:</b>" + shippingZipcode + "<br />";
						//NEED TO ADD orderShipping to database
						service.addOrderShipping(orderShipping);
						
						///////////////////////////////////////////////////////////////
						////////////END ORDER SHIPPING INFORMATION/////////////////////
						/////////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////////
						
						
						
						///////////////////////////////////////////////////////////////
						////////////ORDER PAYMENT INFORMATION/////////////////////
						/////////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////////
						
						OrderPayment orderPayment = new OrderPayment();
						orderPayment.setAddress(address);
						ParsePosition lastFour = new ParsePosition(12);
						NumberFormat nFormat = NumberFormat.getInstance();
						Object cnumberObject = nFormat.parseObject(cardnumber, lastFour);
						Long cnumberLong = (Long) cnumberObject;
						String cardString = cnumberLong.toString();
						Integer cardInt = Integer.parseInt(cardString);
						//
						emailString = emailString + "<br /><h4>Payment Information:</h4> " + "<br />";
						emailString = emailString + "<b>Last 4 Digits On Card:</b> " + cardString + "<br />";
						emailString = emailString + "<b>Total:</b> $" + roundedtotal.toString() + "<br />";
						orderPayment.setCardnumber(cardInt);
						orderPayment.setCity(city);
						orderPayment.setCountry(country);
						orderPayment.setEmail(email);

						Calendar expDate = Calendar.getInstance();
						expDate.set(Calendar.YEAR, Integer.parseInt(expyear));
						expDate.set(Calendar.MONTH, Integer.parseInt(expmonth));
						orderPayment.setCardtype(cardName);
						orderPayment.setExpdate(expDate); 
						orderPayment.setFirstname(firstname);
						orderPayment.setLastname(lastname);
						orderPayment.setOrderid(orderId);
						orderPayment.setPhonenumber(phonenumber);
						orderPayment.setState(state);
						
						double totalasDouble = Double.parseDouble(totaltoString);
						orderPayment.setTotal(roundedtotal);
						orderPayment.setZipcode(Integer.parseInt(zipcode));
						orderPayment.setTransactionid(transactionID);
						//NEED TO ADD orderPayment TO DATABASE
						service.addOrderPayment(orderPayment);
						///////////////////////////////////////////////////////////////
						////////////END ORDER PAYMENT INFORMATION/////////////////////
						/////////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////////
						
						
						
						
						
						OrderStatus orderStatus = new OrderStatus();
						orderStatus.setOrderid(orderId);
						orderStatus.setStatus("Incomplete");
						service.addOrderStatus(orderStatus);
						
						emailString = emailString + "<h4>You will recieve an email with your shipping tracking number when it becomes available</h4>";
						
						  
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
						String subject = "Your Order Information From " + companyName;
						emailString = companyImage + "<br />" + subject + "<br />" + emailString;
						Mailer mailer = new Mailer();
						mailer.setFrom(mailSender);
						mailer.setHost(mailHost);
						mailer.setHtml(emailString);
						mailer.setPassword(mailPassword);
						mailer.setPort(port);
						mailer.setSubject(subject);
						mailer.setTo(email);
						mailer.setUser(mailUser);
						mailer.sendMail();
					
						session.clear();
					}	
					
				}
			}
			
			return SUCCESS;
		}
		
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public Integer getCardtype() {
		return cardtype;
	}
	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getZipcode() {
		return zipcode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(Long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getFinalize() {
		return finalize;
	}
	public void setFinalize(String finalize) {
		this.finalize = finalize;
	}
	public Long getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(Long cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getSubmitfinalization() {
		return submitfinalization;
	}
	public void setSubmitfinalization(String submitfinalization) {
		this.submitfinalization = submitfinalization;
	}
	public HashMap<Integer, Integer> getCartMap() {
		return cartMap;
	}
	public void setCartMap(HashMap<Integer, Integer> cartMap) {
		this.cartMap = cartMap;
	}
	public List<PublicProduct> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<PublicProduct> cartItems) {
		this.cartItems = cartItems;
	}
	public HashMap<String, String> getShipping() {
		return shipping;
	}
	public void setShipping(HashMap<String, String> shipping) {
		this.shipping = shipping;
	}
	public HashMap<String, String> getShippingCarrier() {
		return shippingCarrier;
	}
	public void setShippingCarrier(HashMap<String, String> shippingCarrier) {
		this.shippingCarrier = shippingCarrier;
	}
	public String getExpmonth() {
		return expmonth;
	}
	public void setExpmonth(String expmonth) {
		this.expmonth = expmonth;
	}
	public String getExpyear() {
		return expyear;
	}
	public void setExpyear(String expyear) {
		this.expyear = expyear;
	}
	public HashMap<String, String> getCardMap() {
		return cardMap;
	}
	public void setCardMap(HashMap<String, String> cardMap) {
		this.cardMap = cardMap;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCallerIP() {
		return callerIP;
	}
	public void setCallerIP(String callerIP) {
		this.callerIP = callerIP;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public Double getItemtotal() {
		return itemtotal;
	}
	public void setItemtotal(Double itemtotal) {
		this.itemtotal = itemtotal;
	}
	public String getShippingservice() {
		return shippingservice;
	}
	public void setShippingservice(String shippingservice) {
		this.shippingservice = shippingservice;
	}
	public String getShippingcarrier() {
		return shippingcarrier;
	}
	public void setShippingcarrier(String shippingcarrier) {
		this.shippingcarrier = shippingcarrier;
	}
	public Double getShippingtotal() {
		return shippingtotal;
	}
	public void setShippingtotal(Double shippingtotal) {
		this.shippingtotal = shippingtotal;
	}
	public String getItemtotalString() {
		return itemtotalString;
	}
	public void setItemtotalString(String itemtotalString) {
		this.itemtotalString = itemtotalString;
	}
	public String getTotalString() {
		return totalString;
	}
	public void setTotalString(String totalString) {
		this.totalString = totalString;
	}
}
