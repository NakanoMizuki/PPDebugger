
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConstructorExitType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConstructorExitType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}ConstructorExit"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}MethodEventLeaf.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConstructorExitType", propOrder = {
    "thread",
    "methodSignature",
    "callerObject",
    "calleeObject",
    "callerVisibleVariables",
    "calleeVisibleVariables"
})
@XmlRootElement(name = "ConstructorExit")
public class ConstructorExit {

    @XmlElement(required = true)
    protected Thread thread;
    @XmlElement(required = true)
    protected MethodSignature methodSignature;
    @XmlElement(required = true)
    protected ObjectInfoType callerObject;
    @XmlElement(required = true)
    protected ObjectInfoType calleeObject;
    @XmlElement(required = true)
    protected VisibleVariablesType callerVisibleVariables;
    @XmlElement(required = true)
    protected VisibleVariablesType calleeVisibleVariables;
    @XmlAttribute
    protected String eventNumber;
    @XmlAttribute
    protected String callerLineNumber;
    @XmlAttribute
    protected String calleeLineNumber;
    @XmlAttribute
    protected String callerSourcePath;
    @XmlAttribute
    protected String calleeSourcePath;

    /**
     * Gets the value of the thread property.
     * 
     * @return
     *     possible object is
     *     {@link Thread }
     *     
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Sets the value of the thread property.
     * 
     * @param value
     *     allowed object is
     *     {@link Thread }
     *     
     */
    public void setThread(Thread value) {
        this.thread = value;
    }

    /**
     * Gets the value of the methodSignature property.
     * 
     * @return
     *     possible object is
     *     {@link MethodSignature }
     *     
     */
    public MethodSignature getMethodSignature() {
        return methodSignature;
    }

    /**
     * Sets the value of the methodSignature property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodSignature }
     *     
     */
    public void setMethodSignature(MethodSignature value) {
        this.methodSignature = value;
    }

    /**
     * Gets the value of the callerObject property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectInfoType }
     *     
     */
    public ObjectInfoType getCallerObject() {
        return callerObject;
    }

    /**
     * Sets the value of the callerObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectInfoType }
     *     
     */
    public void setCallerObject(ObjectInfoType value) {
        this.callerObject = value;
    }

    /**
     * Gets the value of the calleeObject property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectInfoType }
     *     
     */
    public ObjectInfoType getCalleeObject() {
        return calleeObject;
    }

    /**
     * Sets the value of the calleeObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectInfoType }
     *     
     */
    public void setCalleeObject(ObjectInfoType value) {
        this.calleeObject = value;
    }

    /**
     * Gets the value of the callerVisibleVariables property.
     * 
     * @return
     *     possible object is
     *     {@link VisibleVariablesType }
     *     
     */
    public VisibleVariablesType getCallerVisibleVariables() {
        return callerVisibleVariables;
    }

    /**
     * Sets the value of the callerVisibleVariables property.
     * 
     * @param value
     *     allowed object is
     *     {@link VisibleVariablesType }
     *     
     */
    public void setCallerVisibleVariables(VisibleVariablesType value) {
        this.callerVisibleVariables = value;
    }

    /**
     * Gets the value of the calleeVisibleVariables property.
     * 
     * @return
     *     possible object is
     *     {@link VisibleVariablesType }
     *     
     */
    public VisibleVariablesType getCalleeVisibleVariables() {
        return calleeVisibleVariables;
    }

    /**
     * Sets the value of the calleeVisibleVariables property.
     * 
     * @param value
     *     allowed object is
     *     {@link VisibleVariablesType }
     *     
     */
    public void setCalleeVisibleVariables(VisibleVariablesType value) {
        this.calleeVisibleVariables = value;
    }

    /**
     * Gets the value of the eventNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventNumber() {
        return eventNumber;
    }

    /**
     * Sets the value of the eventNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventNumber(String value) {
        this.eventNumber = value;
    }

    /**
     * Gets the value of the callerLineNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallerLineNumber() {
        return callerLineNumber;
    }

    /**
     * Sets the value of the callerLineNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallerLineNumber(String value) {
        this.callerLineNumber = value;
    }

    /**
     * Gets the value of the calleeLineNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalleeLineNumber() {
        return calleeLineNumber;
    }

    /**
     * Sets the value of the calleeLineNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalleeLineNumber(String value) {
        this.calleeLineNumber = value;
    }

    /**
     * Gets the value of the callerSourcePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallerSourcePath() {
        return callerSourcePath;
    }

    /**
     * Sets the value of the callerSourcePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallerSourcePath(String value) {
        this.callerSourcePath = value;
    }

    /**
     * Gets the value of the calleeSourcePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalleeSourcePath() {
        return calleeSourcePath;
    }

    /**
     * Sets the value of the calleeSourcePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalleeSourcePath(String value) {
        this.calleeSourcePath = value;
    }

}
