package org.webhop.ywdc.webservices.ups;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.AddressType;
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
import org.webhop.ywdc.webservices.ups.ratingserviceselectionresponse.RatingServiceSelectionResponseType;


public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{
		
		RatingServiceSelectionRequestType rateRequest = new RatingServiceSelectionRequestType();
		
		
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
		pickupType.setDescription("pickitup");
		//#############################
		
		ShipmentType shipment = new ShipmentType();
		
		//###########
		ShipperType shipperType = new ShipperType();
		
		AddressType shipper_address = new AddressType();
		shipper_address.setAddressLine1("add2");
		shipper_address.setCity("the city");
		shipper_address.setCountryCode("US");
		shipper_address.setPostalCode("34322");
		shipper_address.setStateProvinceCode("OR");
		shipperType.setShipperNumber("9UR87");
		shipperType.setAddress(shipper_address);
		//############
		
		//##############
		
		ShipToType shipTo = new ShipToType();
		AddressType shipTo_address = new AddressType();
		shipTo_address.setAddressLine1("shiptoaddress");
		shipTo_address.setCity("Portland");
		shipTo_address.setCountryCode("US");
		shipTo_address.setPostalCode("97239");
		shipTo_address.setStateProvinceCode("OR");
		shipTo.setAttentionName("ship to name");
		shipTo.setCompanyName("ship to company name");
		shipTo.setPhoneNumber("543214545");
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
		serviceType.setCode("03");
		//###############
		
		
		//############
		PackageType pakage = new PackageType();
		PackagingTypeType packagingType = new PackagingTypeType();
		packagingType.setCode("04");
		packagingType.setDescription("04 packaging type");
		
		PackageWeightType packagingWeight = new PackageWeightType();
		packagingWeight.setWeight(2.1);
		UnitOfMeasurementType unitofMeasurement = new UnitOfMeasurementType();
		unitofMeasurement.setCode("lbs");
		packagingWeight.setUnitOfMeasurement(unitofMeasurement);
		packagingType.setCode("02");
		packagingType.setDescription("code 02 type");
		
		pakage.setDescription("the package");
		pakage.setPackageWeight(packagingWeight);
		pakage.setPackagingType(packagingType);
		//##################
		PackageType pakage2 = new PackageType();
		PackagingTypeType packagingType2 = new PackagingTypeType();
		packagingType2.setCode("04");
		packagingType2.setDescription("04 packaging type");
		
		PackageWeightType packagingWeight2 = new PackageWeightType();
		packagingWeight2.setWeight(2.1);
		UnitOfMeasurementType unitofMeasurement2 = new UnitOfMeasurementType();
		unitofMeasurement2.setCode("lbs");
		packagingWeight2.setUnitOfMeasurement(unitofMeasurement);
		packagingType2.setCode("02");
		packagingType2.setDescription("code 02 type");
		
		pakage2.setDescription("the package");
		pakage2.setPackageWeight(packagingWeight);
		pakage2.setPackagingType(packagingType);
		//##################
		
		shipment.setDescription("dscrptn of shipment");
		List<PackageType>packageList = new ArrayList<PackageType>();
		packageList.add(pakage);
		packageList.add(pakage2);
		//shipment.setPackage(packageList);
		
		shipment.setPackage(packageList);
		
		shipment.setService(serviceType);
		shipment.setShipFrom(shipFrom);
		shipment.setShipper(shipperType);
		shipment.setShipTo(shipTo);
		
	//	RatingServiceSelectionRequestType ratingserviceselectionRequest = new RatingServiceSelectionRequestType();
		rateRequest.setPickupType(pickupType);
		rateRequest.setRequest(request);
		rateRequest.setShipment(shipment);
		
		
		/////////////////////////////////&*******************
		
		UpsWebservice ups = new UpsWebservice(rateRequest);
		RatingServiceSelectionResponseType rssResponse = new RatingServiceSelectionResponseType();
		rssResponse = ups.getRatingserviceselectionresponseType();
		System.out.println(rssResponse.getResponse().getResponseStatusDescription());
		
		
	}

}
