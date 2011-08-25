package org.webhop.ywdc.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.webhop.ywdc.extras.State;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionresponse.ErrorType;

import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class ShippingAction extends ActionSupport 
{
	public String fullname;
	
	public String formerror;
	

	public String address;
	public String city;
	public String state;
	public Integer zipcode;
	public List<ErrorType> errorList;
	List<State> stateList;
	public String country;
	public Long phonenumber;

	public String input()
	{
		Map session = ActionContext.getContext().getSession();
		HashMap shippingMap = (HashMap) session.get("shipping");
		if(shippingMap != null)
		{
			Set shippingSet = shippingMap.entrySet();
			Iterator iterator = shippingSet.iterator();
			while(iterator.hasNext())
			{
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				String key = (String) mapEntry.getKey();
				String value = (String) mapEntry.getValue();
				if(key == "fullname")
				{
					fullname = value;
				}
				if(key == "address")
				{
					address = value;
				}
				if(key == "city")
				{
					city = value;
				}
				if(key == "state")
				{
					state = value;
				}
				if(key == "zipcode")
				{
					if(value != null && value.length() != 0)
					{
						
					
						zipcode = Integer.parseInt(value);
					}
				}
				if(key == "country")
				{
					country = value;
				}
				if(key == "phonenumber")
				{
					phonenumber = Long.parseLong(value);
				}
				
			}
		}
		
		return "input";
	}
	
	public String execute()
	{
	//	stateList = new ArrayList<State>();
		//stateList.add(new State("AR", "Arizona"));stateList.add(new State("AL", "Alibama"));stateList.add(new State("AK", "Alaska"));stateList.add(new State("AR", "Arizona"));stateList.add(new State("AR", "Arkansas"));stateList.add(new State("CA", "California"));stateList.add(new State("CO", "Colorado"));stateList.add(new State("CT", "Connecticut"));stateList.add(new State("DE", "Deleware"));stateList.add(new State("FL", "Florida"));stateList.add(new State("GA", "Georgia"));stateList.add(new State("HI", "Hawaii"));stateList.add(new State("ID", "Idaho"));stateList.add(new State("IL", "Illinois"));stateList.add(new State("IN", "Indiana"));stateList.add(new State("IA", "Iowa"));stateList.add(new State("KS", "Kansas"));stateList.add(new State("KY", "Kentucky"));stateList.add(new State("LA", "Louisiana"));stateList.add(new State("ME", "Maine"));stateList.add(new State("MD", "Maryland"));stateList.add(new State("MA", "Massachusetts"));stateList.add(new State("MI", "Montana"));stateList.add(new State("NE", "Nebraska"));stateList.add(new State("NV", "Nevada"));stateList.add(new State("NH", "New Hampshire"));stateList.add(new State("NJ", "New Jersey"));stateList.add(new State("NM", "New Mexico"));stateList.add(new State("NY", "New York"));stateList.add(new State("NC", "North Carolina"));stateList.add(new State("ND", "North Dakota"));stateList.add(new State("OH", "Ohio"));stateList.add(new State("OK", "Oklahoma"));stateList.add(new State("OR", "Oregon"));stateList.add(new State("PA", "Pennsylvania"));stateList.add(new State("RI", "Rhode Island"));stateList.add(new State("SC", "South Carolina"));stateList.add(new State("TN", "Tennessee"));stateList.add(new State("TX", "Texas"));stateList.add(new State("UT", "Utah")); stateList.add(new State("VA", "Virginia"));stateList.add(new State("WI", "Wisconsin"));stateList.add(new State("WY", "Wyoming"));
		
		
		
		
		return SUCCESS;
	}
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String Country) {
		country = Country;
	}

	public Long getPhoneNumber() {
		return phonenumber;
	}

	public void setPhoneNumber(Long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public List<State> getStateList() {
		
		stateList = new ArrayList<State>();
		stateList.add(new State("AR", "Arizona"));stateList.add(new State("AL", "Alibama"));stateList.add(new State("AK", "Alaska"));stateList.add(new State("AR", "Arizona"));stateList.add(new State("AR", "Arkansas"));stateList.add(new State("CA", "California"));stateList.add(new State("CO", "Colorado"));stateList.add(new State("CT", "Connecticut"));stateList.add(new State("DE", "Deleware"));stateList.add(new State("FL", "Florida"));stateList.add(new State("GA", "Georgia"));stateList.add(new State("HI", "Hawaii"));stateList.add(new State("ID", "Idaho"));stateList.add(new State("IL", "Illinois"));stateList.add(new State("IN", "Indiana"));stateList.add(new State("IA", "Iowa"));stateList.add(new State("KS", "Kansas"));stateList.add(new State("KY", "Kentucky"));stateList.add(new State("LA", "Louisiana"));stateList.add(new State("ME", "Maine"));stateList.add(new State("MD", "Maryland"));stateList.add(new State("MA", "Massachusetts"));stateList.add(new State("MI", "Montana"));stateList.add(new State("NE", "Nebraska"));stateList.add(new State("NV", "Nevada"));stateList.add(new State("NH", "New Hampshire"));stateList.add(new State("NJ", "New Jersey"));stateList.add(new State("NM", "New Mexico"));stateList.add(new State("NY", "New York"));stateList.add(new State("NC", "North Carolina"));stateList.add(new State("ND", "North Dakota"));stateList.add(new State("OH", "Ohio"));stateList.add(new State("OK", "Oklahoma"));stateList.add(new State("OR", "Oregon"));stateList.add(new State("PA", "Pennsylvania"));stateList.add(new State("RI", "Rhode Island"));stateList.add(new State("SC", "South Carolina"));stateList.add(new State("TN", "Tennessee"));stateList.add(new State("TX", "Texas"));stateList.add(new State("UT", "Utah")); stateList.add(new State("VA", "Virginia"));stateList.add(new State("WI", "Wisconsin"));stateList.add(new State("WY", "Wyoming"));
		
		
		return stateList;
	}

	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}
	public List<ErrorType> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ErrorType> errorList) {
		this.errorList = errorList;
	}
	public String getFormerror() {
		return formerror;
	}

	public void setFormerror(String formerror) {
		this.formerror = formerror;
	}
}
