
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VariableInfoLeafType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VariableInfoLeafType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}VariableInfoLeaf"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VariableInfoLeafType", propOrder = {
    "fieldInfo",
    "localVariableInfo"
})
public class VariableInfoLeafType {

    @XmlElement(name = "FieldInfo")
    protected FieldInfo fieldInfo;
    @XmlElement(name = "LocalVariableInfo")
    protected LocalVariableInfo localVariableInfo;

    /**
     * Gets the value of the fieldInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FieldInfo }
     *     
     */
    public FieldInfo getFieldInfo() {
        return fieldInfo;
    }

    /**
     * Sets the value of the fieldInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldInfo }
     *     
     */
    public void setFieldInfo(FieldInfo value) {
        this.fieldInfo = value;
    }

    /**
     * Gets the value of the localVariableInfo property.
     * 
     * @return
     *     possible object is
     *     {@link LocalVariableInfo }
     *     
     */
    public LocalVariableInfo getLocalVariableInfo() {
        return localVariableInfo;
    }

    /**
     * Sets the value of the localVariableInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalVariableInfo }
     *     
     */
    public void setLocalVariableInfo(LocalVariableInfo value) {
        this.localVariableInfo = value;
    }

}
