//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.10.10 at 05:50:04 PM PDT 
//


package org.webhop.ywdc.webservices.ups.ratingserviceselectionresponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorLocationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ErrorLocationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorLocationElementName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorLocationAttributeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorLocationType", propOrder = {
    "errorLocationElementName",
    "errorLocationAttributeName"
})
public class ErrorLocationType {

    @XmlElement(name = "ErrorLocationElementName")
    protected String errorLocationElementName;
    @XmlElement(name = "ErrorLocationAttributeName")
    protected String errorLocationAttributeName;

    /**
     * Gets the value of the errorLocationElementName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorLocationElementName() {
        return errorLocationElementName;
    }

    /**
     * Sets the value of the errorLocationElementName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorLocationElementName(String value) {
        this.errorLocationElementName = value;
    }

    /**
     * Gets the value of the errorLocationAttributeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorLocationAttributeName() {
        return errorLocationAttributeName;
    }

    /**
     * Sets the value of the errorLocationAttributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorLocationAttributeName(String value) {
        this.errorLocationAttributeName = value;
    }

}
