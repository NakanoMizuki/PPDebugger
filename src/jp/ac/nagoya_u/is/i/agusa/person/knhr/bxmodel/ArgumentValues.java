
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for argumentValuesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="argumentValuesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}ValueInfoLeaf" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "argumentValuesType", propOrder = {
    "primitiveValueInfosAndObjectInfos"
})
@XmlRootElement(name = "argumentValues")
public class ArgumentValues {

    @XmlElements({
        @XmlElement(name = "PrimitiveValueInfo", type = PrimitiveValueInfo.class),
        @XmlElement(name = "ObjectInfo", type = ObjectInfoType.class)
    })
    protected List<Object> primitiveValueInfosAndObjectInfos;

    /**
     * Gets the value of the primitiveValueInfosAndObjectInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the primitiveValueInfosAndObjectInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrimitiveValueInfosAndObjectInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrimitiveValueInfo }
     * {@link ObjectInfoType }
     * 
     * 
     */
    public List<Object> getPrimitiveValueInfosAndObjectInfos() {
        if (primitiveValueInfosAndObjectInfos == null) {
            primitiveValueInfosAndObjectInfos = new ArrayList<Object>();
        }
        return this.primitiveValueInfosAndObjectInfos;
    }

}
