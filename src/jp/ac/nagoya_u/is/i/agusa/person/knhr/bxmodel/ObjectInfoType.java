
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObjectInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObjectInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}ObjectInfo"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}objectInfo.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectInfoType", propOrder = {
    "className"
})
public class ObjectInfoType {

    protected String className;
    @XmlAttribute
    protected String objectId;
    @XmlAttribute
    protected Boolean isStatic;
    @XmlAttribute
    protected Boolean isNative;
    @XmlAttribute
    protected Boolean isSystem;
    @XmlAttribute
    protected Boolean isNull;
    @XmlAttribute
    protected Boolean isNA;

    /**
     * Gets the value of the className property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the value of the className property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassName(String value) {
        this.className = value;
    }

    /**
     * Gets the value of the objectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Sets the value of the objectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectId(String value) {
        this.objectId = value;
    }

    /**
     * Gets the value of the isStatic property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsStatic() {
        if (isStatic == null) {
            return false;
        } else {
            return isStatic;
        }
    }

    /**
     * Sets the value of the isStatic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsStatic(Boolean value) {
        this.isStatic = value;
    }

    /**
     * Gets the value of the isNative property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsNative() {
        if (isNative == null) {
            return false;
        } else {
            return isNative;
        }
    }

    /**
     * Sets the value of the isNative property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsNative(Boolean value) {
        this.isNative = value;
    }

    /**
     * Gets the value of the isSystem property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsSystem() {
        if (isSystem == null) {
            return false;
        } else {
            return isSystem;
        }
    }

    /**
     * Sets the value of the isSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSystem(Boolean value) {
        this.isSystem = value;
    }

    /**
     * Gets the value of the isNull property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsNull() {
        if (isNull == null) {
            return false;
        } else {
            return isNull;
        }
    }

    /**
     * Sets the value of the isNull property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsNull(Boolean value) {
        this.isNull = value;
    }

    /**
     * Gets the value of the isNA property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsNA() {
        if (isNA == null) {
            return false;
        } else {
            return isNA;
        }
    }

    /**
     * Sets the value of the isNA property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsNA(Boolean value) {
        this.isNA = value;
    }

}
