package org.webhop.ywdc.client;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webhop.ywdc.extras.State;
import org.webhop.ywdc.guice.GuiceModule;
import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class PaymentAction extends ActionSupport 
{
	
	private static final long serialVersionUID = 3076025948729884963L;
	public String serviceId;
	public List<String> cardIssues;
	public List<State> stateList;
	
	public String totalshippingCost;
	public String firstname;
	
	public Long cardnumber;
	public Integer cardtype;
	public String expmonth;
	public String expyear;
	public String lastname;
	public String address;
	public String city;
	public String state;
	public Integer zipcode;
	public Integer cvv;
	public String country;
	public Long phonenumber;
	public String emailaddress;
	
	
	public String input()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices ecommerceServices = injector.getInstance(AuthenticationServices.class);
		ecommerceServices.setConnection(connection);
		ecommerceServices.setInjector(injector);
		
		Map session = ActionContext.getContext().getSession();
		HashMap<String, String> shippingCarrier = new HashMap<String, String>();
		if(serviceId != null)
		{
			shippingCarrier.put("serviceId", serviceId);
			shippingCarrier.put("totalshippingCost", totalshippingCost);
			session.put("shippingcarrier", shippingCarrier);
		}
		else
		{
			//cardIssues = (List<String>) session.get("cardIssues");
		}
		HashMap<String, String> paymentMap = (HashMap<String, String>) session.get("payment");
		
		if(paymentMap != null)
		{
			firstname = paymentMap.get("firstname");
			lastname = paymentMap.get("lastname");
			
			String cardnumberString = paymentMap.get("cardnumber");
			cardnumber = Long.parseLong(cardnumberString);
			
			cardtype = Integer.parseInt(paymentMap.get("cardtype"));
			expmonth = paymentMap.get("expmonth");
			expyear = paymentMap.get("expyear");
			address = paymentMap.get("address");
			city = paymentMap.get("city");
			state = paymentMap.get("state");
			country = paymentMap.get("country");
			zipcode = Integer.parseInt(paymentMap.get("zipcode"));
			cvv = Integer.parseInt(paymentMap.get("cvv"));
			phonenumber = Long.parseLong(paymentMap.get("phonenumber"));
			emailaddress = paymentMap.get("email");
			
			
			
		}
		
		return "input";
		
	}
	
	@SuppressWarnings("unchecked")
	public String execute()
	{
	//	stateList = new ArrayList<State>();
	//	stateList.add(new State("AL", "Alibama"));stateList.add(new State("AK", "Alaska"));stateList.add(new State("AR", "Arizona"));stateList.add(new State("AR", "Arkansas"));stateList.add(new State("CA", "California"));stateList.add(new State("CO", "Colorado"));stateList.add(new State("CT", "Connecticut"));stateList.add(new State("DE", "Deleware"));stateList.add(new State("FL", "Florida"));stateList.add(new State("GA", "Georgia"));stateList.add(new State("HI", "Hawaii"));stateList.add(new State("ID", "Idaho"));stateList.add(new State("IL", "Illinois"));stateList.add(new State("IN", "Indiana"));stateList.add(new State("IA", "Iowa"));stateList.add(new State("KS", "Kansas"));stateList.add(new State("KY", "Kentucky"));stateList.add(new State("LA", "Louisiana"));stateList.add(new State("ME", "Maine"));stateList.add(new State("MD", "Maryland"));stateList.add(new State("MA", "Massachusetts"));stateList.add(new State("MI", "Montana"));stateList.add(new State("NE", "Nebraska"));stateList.add(new State("NV", "Nevada"));stateList.add(new State("NH", "New Hampshire"));stateList.add(new State("NJ", "New Jersey"));stateList.add(new State("NM", "New Mexico"));stateList.add(new State("NY", "New York"));stateList.add(new State("NC", "North Carolina"));stateList.add(new State("ND", "North Dakota"));stateList.add(new State("OH", "Ohio"));stateList.add(new State("OK", "Oklahoma"));stateList.add(new State("OR", "Oregon"));stateList.add(new State("PA", "Pennsylvania"));stateList.add(new State("RI", "Rhode Island"));stateList.add(new State("SC", "South Carolina"));stateList.add(new State("TN", "Tennessee"));stateList.add(new State("TX", "Texas"));stateList.add(new State("UT", "Utah")); stateList.add(new State("VA", "Virginia"));stateList.add(new State("WI", "Wisconsin"));stateList.add(new State("WY", "Wyoming"));
		
		
		
		return SUCCESS;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getTotalshippingCost() {
		return totalshippingCost;
	}
	public void setTotalshippingCost(String totalshippingCost) {
		this.totalshippingCost = totalshippingCost;
	}
	public List<String> getCardIssues() {
		return cardIssues;
	}
	public void setCardIssues(List<String> cardIssues) {
		this.cardIssues = cardIssues;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public Long getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(Long cardnumber) {
		this.cardnumber = cardnumber;
	}
	public Integer getCardtype() {
		return cardtype;
	}
	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
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
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public List<State> getStateList() {
		
		stateList = new ArrayList<State>();
		stateList.add(new State("AL", "Alibama"));stateList.add(new State("AK", "Alaska"));stateList.add(new State("AR", "Arizona"));stateList.add(new State("AR", "Arkansas"));stateList.add(new State("CA", "California"));stateList.add(new State("CO", "Colorado"));stateList.add(new State("CT", "Connecticut"));stateList.add(new State("DE", "Deleware"));stateList.add(new State("FL", "Florida"));stateList.add(new State("GA", "Georgia"));stateList.add(new State("HI", "Hawaii"));stateList.add(new State("ID", "Idaho"));stateList.add(new State("IL", "Illinois"));stateList.add(new State("IN", "Indiana"));stateList.add(new State("IA", "Iowa"));stateList.add(new State("KS", "Kansas"));stateList.add(new State("KY", "Kentucky"));stateList.add(new State("LA", "Louisiana"));stateList.add(new State("ME", "Maine"));stateList.add(new State("MD", "Maryland"));stateList.add(new State("MA", "Massachusetts"));stateList.add(new State("MI", "Montana"));stateList.add(new State("NE", "Nebraska"));stateList.add(new State("NV", "Nevada"));stateList.add(new State("NH", "New Hampshire"));stateList.add(new State("NJ", "New Jersey"));stateList.add(new State("NM", "New Mexico"));stateList.add(new State("NY", "New York"));stateList.add(new State("NC", "North Carolina"));stateList.add(new State("ND", "North Dakota"));stateList.add(new State("OH", "Ohio"));stateList.add(new State("OK", "Oklahoma"));stateList.add(new State("OR", "Oregon"));stateList.add(new State("PA", "Pennsylvania"));stateList.add(new State("RI", "Rhode Island"));stateList.add(new State("SC", "South Carolina"));stateList.add(new State("TN", "Tennessee"));stateList.add(new State("TX", "Texas"));stateList.add(new State("UT", "Utah")); stateList.add(new State("VA", "Virginia"));stateList.add(new State("WI", "Wisconsin"));stateList.add(new State("WY", "Wyoming"));
		
		return stateList;
	}
	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}
}
