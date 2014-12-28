
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for threadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="threadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}ThreadInfo"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}threadId.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "threadType", propOrder = {
    "threadName"
})
@XmlRootElement(name = "thread")
public class Thread {

    @XmlElement(required = true)
    protected String threadName;
    @XmlAttribute(required = true)
    protected String threadId;

    /**
     * Gets the value of the threadName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThreadName() {
        return threadName;
    }

    /**
     * Sets the value of the threadName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThreadName(String value) {
        this.threadName = value;
    }

    /**
     * Gets the value of the threadId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThreadId() {
        return threadId;
    }

    /**
     * Sets the value of the threadId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThreadId(String value) {
        this.threadId = value;
    }

}
