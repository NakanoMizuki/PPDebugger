
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EdgeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EdgeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}edge.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EdgeType")
@XmlRootElement(name = "Edge")
public class Edge {

    @XmlAttribute(required = true)
    protected String fromID;
    @XmlAttribute(required = true)
    protected String toID;
    @XmlAttribute(required = true)
    protected DependencyType kind;

    /**
     * Gets the value of the fromID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromID() {
        return fromID;
    }

    /**
     * Sets the value of the fromID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromID(String value) {
        this.fromID = value;
    }

    /**
     * Gets the value of the toID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToID() {
        return toID;
    }

    /**
     * Sets the value of the toID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToID(String value) {
        this.toID = value;
    }

    /**
     * Gets the value of the kind property.
     * 
     * @return
     *     possible object is
     *     {@link DependencyType }
     *     
     */
    public DependencyType getKind() {
        return kind;
    }

    /**
     * Sets the value of the kind property.
     * 
     * @param value
     *     allowed object is
     *     {@link DependencyType }
     *     
     */
    public void setKind(DependencyType value) {
        this.kind = value;
    }

}
