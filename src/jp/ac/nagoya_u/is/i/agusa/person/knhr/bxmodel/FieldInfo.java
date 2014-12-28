
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FieldInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FieldInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}FieldInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FieldInfoType", propOrder = {
    "variableName",
    "value",
    "ownerObject"
})
@XmlRootElement(name = "FieldInfo")
public class FieldInfo {

    @XmlElement(required = true)
    protected String variableName;
    @XmlElement(required = true)
    protected ValueInfoLeafType value;
    @XmlElement(required = true)
    protected ObjectInfoType ownerObject;

    /**
     * Gets the value of the variableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Sets the value of the variableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariableName(String value) {
        this.variableName = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link ValueInfoLeafType }
     *     
     */
    public ValueInfoLeafType getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueInfoLeafType }
     *     
     */
    public void setValue(ValueInfoLeafType value) {
        this.value = value;
    }

    /**
     * Gets the value of the ownerObject property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectInfoType }
     *     
     */
    public ObjectInfoType getOwnerObject() {
        return ownerObject;
    }

    /**
     * Sets the value of the ownerObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectInfoType }
     *     
     */
    public void setOwnerObject(ObjectInfoType value) {
        this.ownerObject = value;
    }

}
