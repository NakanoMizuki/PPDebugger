
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValueInfoLeafType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValueInfoLeafType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}ValueInfoLeaf"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValueInfoLeafType", propOrder = {
    "objectInfo",
    "primitiveValueInfo"
})
public class ValueInfoLeafType {

    @XmlElement(name = "ObjectInfo")
    protected ObjectInfoType objectInfo;
    @XmlElement(name = "PrimitiveValueInfo")
    protected PrimitiveValueInfo primitiveValueInfo;

    /**
     * Gets the value of the objectInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectInfoType }
     *     
     */
    public ObjectInfoType getObjectInfo() {
        return objectInfo;
    }

    /**
     * Sets the value of the objectInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectInfoType }
     *     
     */
    public void setObjectInfo(ObjectInfoType value) {
        this.objectInfo = value;
    }

    /**
     * Gets the value of the primitiveValueInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PrimitiveValueInfo }
     *     
     */
    public PrimitiveValueInfo getPrimitiveValueInfo() {
        return primitiveValueInfo;
    }

    /**
     * Sets the value of the primitiveValueInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrimitiveValueInfo }
     *     
     */
    public void setPrimitiveValueInfo(PrimitiveValueInfo value) {
        this.primitiveValueInfo = value;
    }

}
