package org.webhop.ywdc.webservices.ups.accessrequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {

	/**
	 * @param args
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws JAXBException 
	{
		AccessRequestType arType = new AccessRequestType();
		arType.setAccessLicenseNumber("343433");
		arType.setPassword("3k3k2l33");
		arType.setUserId("thebravedave");
		
		JAXBContext jaxbContext = JAXBContext.newInstance("org.webhop.ywdc");
	
		Marshaller marshaller = jaxbContext.createMarshaller();
		JAXBElement<AccessRequestType> artElement = (new ObjectFactory()).createAccessRequest(arType);//createAccessRequestType(arType);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(artElement, System.out);
	}

}
