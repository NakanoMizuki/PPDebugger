
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for visibleVariablesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="visibleVariablesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}VariableInfoLeaf" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visibleVariablesType", propOrder = {
    "localVariableInfosAndFieldInfos"
})
public class VisibleVariablesType {

    @XmlElements({
        @XmlElement(name = "FieldInfo", type = FieldInfo.class),
        @XmlElement(name = "LocalVariableInfo", type = LocalVariableInfo.class)
    })
    protected List<Object> localVariableInfosAndFieldInfos;

    /**
     * Gets the value of the localVariableInfosAndFieldInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localVariableInfosAndFieldInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalVariableInfosAndFieldInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FieldInfo }
     * {@link LocalVariableInfo }
     * 
     * 
     */
    public List<Object> getLocalVariableInfosAndFieldInfos() {
        if (localVariableInfosAndFieldInfos == null) {
            localVariableInfosAndFieldInfos = new ArrayList<Object>();
        }
        return this.localVariableInfosAndFieldInfos;
    }

}
