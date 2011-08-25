//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.10.08 at 08:43:38 PM PDT 
//


package org.webhop.ywdc.webservices.ups.ratingserviceselectionrequest;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.webhop.ywdc.rssrequest package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RatingServiceSelectionRequest_QNAME = new QName("", "RatingServiceSelectionRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.webhop.ywdc.rssrequest
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShipmentType }
     * 
     */
    public ShipmentType createShipmentType() {
        return new ShipmentType();
    }

    /**
     * Create an instance of {@link ShipperType }
     * 
     */
    public ShipperType createShipperType() {
        return new ShipperType();
    }

    /**
     * Create an instance of {@link DimensionsType }
     * 
     */
    public DimensionsType createDimensionsType() {
        return new DimensionsType();
    }

    /**
     * Create an instance of {@link PickupTypeType }
     * 
     */
    public PickupTypeType createPickupTypeType() {
        return new PickupTypeType();
    }

    /**
     * Create an instance of {@link ShipToType }
     * 
     */
    public ShipToType createShipToType() {
        return new ShipToType();
    }

    /**
     * Create an instance of {@link CustomerClassificationType }
     * 
     */
    public CustomerClassificationType createCustomerClassificationType() {
        return new CustomerClassificationType();
    }

    /**
     * Create an instance of {@link ServiceType }
     * 
     */
    public ServiceType createServiceType() {
        return new ServiceType();
    }

    /**
     * Create an instance of {@link PackageType }
     * 
     */
    public PackageType createPackageType() {
        return new PackageType();
    }

    /**
     * Create an instance of {@link PackagingTypeType }
     * 
     */
    public PackagingTypeType createPackagingTypeType() {
        return new PackagingTypeType();
    }

    /**
     * Create an instance of {@link UnitOfMeasurementType }
     * 
     */
    public UnitOfMeasurementType createUnitOfMeasurementType() {
        return new UnitOfMeasurementType();
    }

    /**
     * Create an instance of {@link RequestType }
     * 
     */
    public RequestType createRequestType() {
        return new RequestType();
    }

    /**
     * Create an instance of {@link RatingServiceSelectionRequestType }
     * 
     */
    public RatingServiceSelectionRequestType createRatingServiceSelectionRequestType() {
        return new RatingServiceSelectionRequestType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link ShipFromType }
     * 
     */
    public ShipFromType createShipFromType() {
        return new ShipFromType();
    }

    /**
     * Create an instance of {@link TransactionReferenceType }
     * 
     */
    public TransactionReferenceType createTransactionReferenceType() {
        return new TransactionReferenceType();
    }

    /**
     * Create an instance of {@link PackageWeightType }
     * 
     */
    public PackageWeightType createPackageWeightType() {
        return new PackageWeightType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RatingServiceSelectionRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RatingServiceSelectionRequest")
    public JAXBElement<RatingServiceSelectionRequestType> createRatingServiceSelectionRequest(RatingServiceSelectionRequestType value) {
        return new JAXBElement<RatingServiceSelectionRequestType>(_RatingServiceSelectionRequest_QNAME, RatingServiceSelectionRequestType.class, null, value);
    }

}