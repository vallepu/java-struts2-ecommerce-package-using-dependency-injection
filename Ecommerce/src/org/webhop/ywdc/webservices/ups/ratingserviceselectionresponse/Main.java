package org.webhop.ywdc.webservices.ups.ratingserviceselectionresponse;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Main {

	/**
	 * @param args
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws JAXBException 
	{
		JAXBContext jaxbContext = JAXBContext.newInstance("org.webhop.ywdc.ratingserviceselectionresponse");
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		//unmarshaller.unmarshal(new File("src/test/resources/xml/ratingserviceselectionresponse2.xml"));


		   JAXBElement<RatingServiceSelectionResponseType> ratingserviceselectionresponseElement = (JAXBElement<RatingServiceSelectionResponseType>) unmarshaller.unmarshal(
		                   new File("src/test/resources/xml/ratingserviceselectionresponse2.xml"));
	
		  
		   RatingServiceSelectionResponseType rssrType = ratingserviceselectionresponseElement.getValue();
		   System.out.println(rssrType.getRatedShipment().getTotalCharges().getCurrencyCode());

		
		
		
	}

}
