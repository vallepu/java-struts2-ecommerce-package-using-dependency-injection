package org.webhop.ywdc.webservices.ups;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import org.webhop.ywdc.webservices.ups.*;

import org.webhop.ywdc.webservices.ups.accessrequest.AccessRequestType;
import org.webhop.ywdc.webservices.ups.accessrequest.ObjectFactory;
import org.webhop.ywdc.webservices.ups.ratingserviceselectionresponse.RatingServiceSelectionResponseType;
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

public class UpsWebservice 
{
	private String LICENSE_NUMBER;
	private String USER_NAME;
	private String PASSWORD;
	private String ENDPOINT_URL;
	
	private RatingServiceSelectionRequestType ratingserviceselectionrequestType;
	
	private RatingServiceSelectionResponseType ratingserviceselectionresponseType;
	private AccessRequestType accessrequestType;
	public StringWriter strWriter;
	public BufferedReader reader;
	

	public UpsWebservice(RatingServiceSelectionRequestType raterequestType) throws JAXBException
	{
		Properties configFile = new Properties();
		   
		try 
		{
			configFile.load(this.getClass().getClassLoader().getResourceAsStream("upsconfig.properties"));
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		LICENSE_NUMBER =  configFile.getProperty("licensenumber"); 
		USER_NAME =  configFile.getProperty("username"); 
		PASSWORD =  configFile.getProperty("password"); 
		ENDPOINT_URL =  configFile.getProperty("endpointurl"); 
		
		
		
		accessrequestType = new AccessRequestType();
		ratingserviceselectionrequestType = new RatingServiceSelectionRequestType();
		ratingserviceselectionresponseType = new RatingServiceSelectionResponseType();
		
		StringWriter strWriter = null;
		RatingServiceSelectionResponseType rTypeForReturn = null;
        try {	    
        	
    		accessrequestType = new AccessRequestType();
    		populateAccessRequest(accessrequestType);
    		JAXBContext accessRequestJAXBC = JAXBContext.newInstance("org.webhop.ywdc.webservices.ups.accessrequest");
    		Marshaller accessRequestMarshaller = accessRequestJAXBC.createMarshaller();
    		JAXBElement<AccessRequestType> accessRequest = (new org.webhop.ywdc.webservices.ups.accessrequest.ObjectFactory()).createAccessRequest(accessrequestType);//createAccessRequestType(arType);
    		
		
			strWriter = new StringWriter();       		       
			accessRequestMarshaller.marshal(accessRequest, strWriter);
			
			
			JAXBContext jaxbContext = JAXBContext.newInstance("org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest");
			Marshaller rateRequestMarshaller = jaxbContext.createMarshaller();
			JAXBElement<RatingServiceSelectionRequestType> rateRequest = (new org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.ObjectFactory()).createRatingServiceSelectionRequest(raterequestType);
			rateRequestMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			//rateRequestMarshaller.marshal(rateRequest, System.out);
			
			rateRequestMarshaller.marshal(rateRequest, strWriter);
			strWriter.flush();
			
			
			rTypeForReturn = contactService(strWriter.getBuffer().toString());
		
        } catch (Exception e) {
        	
			e.printStackTrace();
		} finally{
			try{
				if(strWriter != null){
					strWriter.close();
					strWriter = null;
				}
			}catch (Exception e) {
		       
		       	e.printStackTrace();
			}
			
		}
		ratingserviceselectionresponseType = rTypeForReturn;	
	}
	
	public void marshalRateRequest() throws JAXBException, IOException
	{
		
		JAXBContext jaxbContext = JAXBContext.newInstance("org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest");
		Marshaller rateRequestMarshaller = jaxbContext.createMarshaller();
		JAXBElement<RatingServiceSelectionRequestType> rateRequest = (new org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest.ObjectFactory()).createRatingServiceSelectionRequest(ratingserviceselectionrequestType);
		rateRequestMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		rateRequestMarshaller.marshal(rateRequest, System.out);
		
		rateRequestMarshaller.marshal(rateRequest, strWriter);
		strWriter.flush();
		strWriter.close();
	}
	private RatingServiceSelectionResponseType contactService(String xmlInputString) throws Exception{		
		
		OutputStream outputStream = null;
		RatingServiceSelectionResponseType rType = null;
		try {

			URL url = new URL(ENDPOINT_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// Setup HTTP POST parameters
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			
			outputStream = connection.getOutputStream();		
			outputStream.write(xmlInputString.getBytes());
			outputStream.flush();
			outputStream.close();
		
			rType = readURLConnection(connection);
			connection.disconnect();
			
		} catch (Exception e) {
			
			throw e;
		} finally {						
			if(outputStream != null){
				outputStream.close();
				outputStream = null;
			}
		}		
		return rType;
	}
	
	public RatingServiceSelectionResponseType readURLConnection(URLConnection uc) throws Exception {
	
		BufferedReader reader = null;
		RatingServiceSelectionResponseType temprssrType = null;
		try {
			reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			temprssrType = unmarshalBufferedReaderToObjects(reader);
		
			reader.close();
		} catch (Exception e) {
			System.out.println("Could not read from URL: " + e.toString());
			throw e;
		} finally {
			if(reader != null){
				//reader.close();
				reader = null;
			}
		}
		return temprssrType;
	}
	
	

	private RatingServiceSelectionResponseType unmarshalBufferedReaderToObjects(BufferedReader reader) throws JAXBException
    {
    	JAXBContext jaxbContext = JAXBContext.newInstance("org.webhop.ywdc.webservices.ups.ratingserviceselectionresponse");
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		@SuppressWarnings("unchecked")
		JAXBElement<RatingServiceSelectionResponseType> ratingserviceselectionresponseElement = (JAXBElement<RatingServiceSelectionResponseType>) unmarshaller.unmarshal(reader);
		
		RatingServiceSelectionResponseType rssrType = ratingserviceselectionresponseElement.getValue();
		return rssrType;
    }
	private void populateAccessRequest(AccessRequestType accessRequest)
	{
		accessRequest.setAccessLicenseNumber(LICENSE_NUMBER);
    	accessRequest.setUserId(USER_NAME);
    	accessRequest.setPassword(PASSWORD);
	}
	
	public RatingServiceSelectionRequestType getRatingserviceselectionrequestType() 
	{
		return ratingserviceselectionrequestType;
	}
	public void setRatingserviceselectionrequestType(RatingServiceSelectionRequestType ratingserviceselectionrequestType) 
	{
		this.ratingserviceselectionrequestType = ratingserviceselectionrequestType;
	}
	public RatingServiceSelectionResponseType getRatingserviceselectionresponseType() 
	{
		return ratingserviceselectionresponseType;
	}
	public void setRatingserviceselectionresponseType(RatingServiceSelectionResponseType ratingserviceselectionresponseType) 
	{
		this.ratingserviceselectionresponseType = ratingserviceselectionresponseType;
	}
	public StringWriter getStrWriter() 
	{
		return strWriter;
	}
	public void setStrWriter(StringWriter strWriter) 
	{
		this.strWriter = strWriter;
	}
	public BufferedReader getReader() 
	{
		return reader;
	}
	public void setReader(BufferedReader reader) 
	{
		this.reader = reader;
	}


}
