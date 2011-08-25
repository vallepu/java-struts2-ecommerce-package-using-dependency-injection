package org.webhop.ywdc.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.webhop.ywdc.beans.ProductShipping;
import org.webhop.ywdc.beans.ShippingCarrier;
import org.webhop.ywdc.beans.ShippingServices;
import org.webhop.ywdc.dtos.PublicProduct;
import org.webhop.ywdc.guice.GuiceModule;

import org.webhop.ywdc.services.AuthenticationServices;
import org.webhop.ywdc.util.HibernateConnection;
import org.webhop.ywdc.webservices.ups.UpsWebservice;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.AddressType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.DimensionsType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.PackageType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.PackageWeightType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.PackagingTypeType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.PickupTypeType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.RatingServiceSelectionRequestType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.RequestType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.ServiceType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.ShipFromType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.ShipToType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.ShipmentType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.ShipperType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.TransactionReferenceType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.UnitOfMeasurementType;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionresponse.ErrorType;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.sslplugin.annotation.Secured;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Secured
public class ShippingCarrierAction extends ActionSupport 
{
	public String address;
	public String carrier;
	public String city;
	public String country;
	public String fullname;
	public String phonenumber;
	public String state;
	public String zipcode;
	public HashMap<Integer, String> servicescostMap;
	public List<ShippingCarrier> shippingCarriers;
	public HashMap<Integer, String> carrierMap;
	public List<ErrorType> errorList;
	
	
	public List<ShippingServices> totalservicesList;
	
	@SuppressWarnings("unchecked")
	public String execute()
	{
		Injector injector = Guice.createInjector(new GuiceModule());
		HibernateConnection connection = injector.getInstance(HibernateConnection.class);
		AuthenticationServices ecommerceServices = injector.getInstance(AuthenticationServices.class);
		ecommerceServices.setConnection(connection);
		ecommerceServices.setInjector(injector);
		
		//ecommerceServices.getShippingServices();
		HashMap<String, String> shipping = new HashMap<String, String>();
		shipping.put("address", address);
		shipping.put("city", city);
		shipping.put("country", country);
		shipping.put("fullname", fullname);
		shipping.put("phonenumber", phonenumber);
		shipping.put("state", state);
		shipping.put("zipcode", zipcode);
		
		Map session = ActionContext.getContext().getSession();
		session.put("shipping", shipping);
		
		carrierMap = new HashMap<Integer, String>();
		totalservicesList = new ArrayList<ShippingServices>();
		shippingCarriers = ecommerceServices.getShippingCarriers();
		for(ShippingCarrier sCarrier: shippingCarriers)
		{	
			carrierMap.put(sCarrier.getId(), sCarrier.getCarrier());
			List<ShippingServices> carrierServices = ecommerceServices.getShippingServicesByCarrierId(sCarrier.getId());
			totalservicesList.addAll(carrierServices);
		}
		//List<ShippingServices> upsServices = ecommerceServices.getShippingServicesByCarrierId(1);
		
		
		if(address != null && address.length() != 0 && city != null && city.length() != 0 && country != null && country.length() != 0 && fullname != null && fullname.length() != 0 && phonenumber != null && phonenumber.length() != 0 && state != null && state.length() != 0 && zipcode != null && zipcode.length() != 0)
		{
			//HashMap<String, Double> shippingMap = new HashMap<String, Double>();
			
			HashMap cartMap = (HashMap)session.get("cart");
			List<PublicProduct> cartList = ecommerceServices.getPublicProductByHashMap(cartMap);
			servicescostMap = new HashMap<Integer, String>();
			
			for(ShippingServices shippingService: totalservicesList)
			{
				RatingServiceSelectionRequestType rssrType = new RatingServiceSelectionRequestType();
				
				//FILLING OUT SHIPPMENT INFORMATION 
				
				//######################
				RequestType request = new RequestType();
				TransactionReferenceType transactionreference = new TransactionReferenceType();
				transactionreference.setCustomerContext("Bare Bones Rate Request");
				transactionreference.setXpciVersion("1.0001");
				request.setRequestAction("Rate");
				request.setRequestOption("Rate");
				request.setTransactionReference(transactionreference);
				//######################
				
				//##############################
				PickupTypeType pickupType = new PickupTypeType();
				pickupType.setCode("01");
				pickupType.setDescription("Daily Pickup");
				//#############################
				
				ShipmentType shipment = new ShipmentType();
				
				//###########
				ShipperType shipperType = new ShipperType();
				
				AddressType shipper_address = new AddressType();
				shipper_address.setAddressLine1("add2");
				shipper_address.setCity("Portland");
				shipper_address.setCountryCode("US");
				shipper_address.setPostalCode("97239");
				shipper_address.setStateProvinceCode("OR");
				shipperType.setShipperNumber("9UR87");
				shipperType.setAddress(shipper_address);
				//############
				
				//##############
				
				//*****************THIS INFO SHOULD BE SET FROM THE CUSTOMER SUBMITTED INFO*************
				//*******************************************************************************
				//************************************************************
				//*********************************************
				
				ShipToType shipTo = new ShipToType();
				AddressType shipTo_address = new AddressType();
				shipTo_address.setAddressLine1(address);
				shipTo_address.setCity(city);
				shipTo_address.setCountryCode(country);
				shipTo_address.setPostalCode(zipcode);
				shipTo_address.setStateProvinceCode(state);
				shipTo.setAttentionName(fullname);
				//shipTo.setCompanyName("ship to company name");
			
				shipTo.setPhoneNumber(phonenumber);
				shipTo.setAddress(shipTo_address);
				
				//##################
				
				//#############
				
				ShipFromType shipFrom = new ShipFromType();
				//shipFrom.setAttentionName("David Pugh");
				shipFrom.setCompanyName("Davids Company");
				//shipFrom.setFaxNumber(154);
				AddressType shipFrom_address = new AddressType();
				shipFrom_address.setAddressLine1("add1");
				shipFrom_address.setCity("Gold Hill");
				shipFrom_address.setCountryCode("US");
				shipFrom_address.setPostalCode("97525");
				shipFrom_address.setStateProvinceCode("OR");
				shipFrom.setAddress(shipFrom_address);
				
				//######################
				
				//###############
				ServiceType serviceType = new ServiceType();
				//serviceType.setCode("03");
				serviceType.setCode(shippingService.getServicecode());
				//###############
				
				
				//############
				
				/////*******************************************************************************
				//*****************NEED TO ACCESS THE SESSION VARIABLES FOR CART INFO
				//***************************NEED TO ITERATE THRU EACH AND ADD TO LIST************
				//********************************************************************************8
				
				List<PackageType>packageList = new ArrayList<PackageType>();
				
				//Map session = ActionContext.getContext().getSession();
				for(PublicProduct pProduct: cartList)
				{
					Integer productId = pProduct.getId();
					ProductShipping productshippingInfo = ecommerceServices.getProductShippingByProductId(productId);
					Integer numProducts =  (Integer) cartMap.get(productId);
					Integer numCounter = 1;
					while(numCounter <= numProducts)	
					{
						PackageType pakage = new PackageType();
						Double height = productshippingInfo.getHeight();
						Double width = productshippingInfo.getWidth();
						Double length = productshippingInfo.getLength();
						if(height != null  && length != null  && width != null)
						{
							if(height > 0  && length > 0  && width > 0)
							{
								DimensionsType dimensions = new DimensionsType();
								dimensions.setHeight(productshippingInfo.getHeight());
								dimensions.setLength(productshippingInfo.getLength());
								dimensions.setWidth(productshippingInfo.getWidth());
								pakage.setDimensions(dimensions);
							}
						}
						
						PackagingTypeType packagingType = new PackagingTypeType();
						packagingType.setCode("02");
						packagingType.setDescription("Inches");
						
						PackageWeightType packagingWeight = new PackageWeightType();
						double productWeight = productshippingInfo.getWeight();
						
						//double numproductsAsDouble = (double) numProducts;
						//double productweightByQuantity = (productWeight)*(numproductsAsDouble);
						packagingWeight.setWeight(productWeight);
						UnitOfMeasurementType unitofMeasurement = new UnitOfMeasurementType();
						unitofMeasurement.setCode("lbs");
						packagingWeight.setUnitOfMeasurement(unitofMeasurement);
						//packagingType.setCode("02");
						//packagingType.setDescription("code 02 type");
						
						pakage.setDescription("the package");
						pakage.setPackageWeight(packagingWeight);
						pakage.setPackagingType(packagingType);
						
						shipment.setDescription("dscrptn of shipment");
					
						packageList.add(pakage);
						numCounter++;
					}
				
			
				shipment.setPackage(packageList);
				
				shipment.setService(serviceType);
				shipment.setShipFrom(shipFrom);
				shipment.setShipper(shipperType);
				shipment.setShipTo(shipTo);
				
			//	RatingServiceSelectionRequestType ratingserviceselectionRequest = new RatingServiceSelectionRequestType();
				rssrType.setPickupType(pickupType);
				rssrType.setRequest(request);
				rssrType.setShipment(shipment);
				
				
				
				UpsWebservice ups = null;
				//END FILLING OUT SHIPMENT INFORMATION
				
				try 
				{
					ups = new UpsWebservice(rssrType);
					
					//check the ups object for errors. if there are errors there then collect them and print the errors so the person
					//knows what they did wrong.......................... otherwise it will throw an error
					//*****************************
					//&&&&&&&&&&&&&&&&&&&&&&&**********************************
				
				
				} 
				catch (JAXBException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String responseDescription = ups.getRatingserviceselectionresponseType().getResponse().getResponseStatusDescription();
				if(responseDescription.compareTo("Failure") == 0)
				{
					errorList = ups.getRatingserviceselectionresponseType().getResponse().getError();
					ActionContext.getContext().getParameters().put("errorList", errorList);
					return "failure";
				}
				else
				{
					
				
				
				//String serviceName = shippingService.getServicename();
				Integer serviceId = shippingService.getId();
				String totalforService = ups.getRatingserviceselectionresponseType().getRatedShipment().getTotalCharges().getMonetaryValue();
				servicescostMap.put(serviceId, totalforService);
				session.put("servicesCost", servicescostMap);
				}
			}//end iteration thru session publicproduct information **********************************
				
			}//en iteration thru shipping services...at this point the servicecostMap should be filled!!!!!!!!!!!!1
			//*********************************************************************************************//*******
			//***************************************************************************************************8
			
		}
		else
		{
			ActionContext.getContext().getParameters().put("formerror", "Please fill out the shipping form completely");
			return "failure";
		}
		//need to create a property hashmap that contains all the different
		//shipping options, and how much they cost.
		//attach these to some radio buttons in a from that they people can choose from
		//for their shipping options.
		
		return SUCCESS;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public HashMap<Integer, String> getServicescostMap() {
		return servicescostMap;
	}
	public void setServicescostMap(HashMap<Integer, String> servicescostMap) {
		this.servicescostMap = servicescostMap;
	}
	public List<ShippingCarrier> getShippingCarriers() {
		return shippingCarriers;
	}
	public void setShippingCarriers(List<ShippingCarrier> shippingCarriers) {
		this.shippingCarriers = shippingCarriers;
	}
	public List<ShippingServices> getTotalservicesList() {
		return totalservicesList;
	}
	public void setTotalservicesList(List<ShippingServices> totalservicesList) {
		this.totalservicesList = totalservicesList;
	}
	public HashMap<Integer, String> getCarrierMap() {
		return carrierMap;
	}
	public void setCarrierMap(HashMap<Integer, String> carrierMap) {
		this.carrierMap = carrierMap;
	}
	public List<ErrorType> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<ErrorType> errorList) {
		this.errorList = errorList;
	}
}
