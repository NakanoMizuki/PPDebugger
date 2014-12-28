
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrimitiveValueInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrimitiveValueInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}PrimitiveValueInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrimitiveValueInfoType", propOrder = {
    "primitiveTypeName",
    "primitiveValue"
})
@XmlRootElement(name = "PrimitiveValueInfo")
public class PrimitiveValueInfo {

    @XmlElement(required = true)
    protected String primitiveTypeName;
    @XmlElement(required = true)
    protected String primitiveValue;

    /**
     * Gets the value of the primitiveTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimitiveTypeName() {
        return primitiveTypeName;
    }

    /**
     * Sets the value of the primitiveTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimitiveTypeName(String value) {
        this.primitiveTypeName = value;
    }

    /**
     * Gets the value of the primitiveValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimitiveValue() {
        return primitiveValue;
    }

    /**
     * Sets the value of the primitiveValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimitiveValue(String value) {
        this.primitiveValue = value;
    }

}
