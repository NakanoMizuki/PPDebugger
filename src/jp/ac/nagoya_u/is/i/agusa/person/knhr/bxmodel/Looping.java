﻿
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoopingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoopingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}Looping"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}behaviorEventLeaf.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoopingType", propOrder = {
    "thread",
    "conditionString",
    "visibleVariables",
    "evaluatedValue",
    "loopCount"
})
@XmlRootElement(name = "Looping")
public class Looping {

    @XmlElement(required = true)
    protected Thread thread;
    @XmlElement(required = true)
    protected String conditionString;
    @XmlElement(required = true)
    protected VisibleVariablesType visibleVariables;
    @XmlElement(required = true)
    protected ValueInfoLeafType evaluatedValue;
    protected int loopCount;
    @XmlAttribute(required = true)
    protected String eventNumber;
    @XmlAttribute
    protected String lineNumber;
    @XmlAttribute
    protected String sourcePath;

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
     * Gets the value of the conditionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionString() {
        return conditionString;
    }

    /**
     * Sets the value of the conditionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionString(String value) {
        this.conditionString = value;
    }

    /**
     * Gets the value of the visibleVariables property.
     * 
     * @return
     *     possible object is
     *     {@link VisibleVariablesType }
     *     
     */
    public VisibleVariablesType getVisibleVariables() {
        return visibleVariables;
    }

    /**
     * Sets the value of the visibleVariables property.
     * 
     * @param value
     *     allowed object is
     *     {@link VisibleVariablesType }
     *     
     */
    public void setVisibleVariables(VisibleVariablesType value) {
        this.visibleVariables = value;
    }

    /**
     * Gets the value of the evaluatedValue property.
     * 
     * @return
     *     possible object is
     *     {@link ValueInfoLeafType }
     *     
     */
    public ValueInfoLeafType getEvaluatedValue() {
        return evaluatedValue;
    }

    /**
     * Sets the value of the evaluatedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueInfoLeafType }
     *     
     */
    public void setEvaluatedValue(ValueInfoLeafType value) {
        this.evaluatedValue = value;
    }

    /**
     * Gets the value of the loopCount property.
     * 
     */
    public int getLoopCount() {
        return loopCount;
    }

    /**
     * Sets the value of the loopCount property.
     * 
     */
    public void setLoopCount(int value) {
        this.loopCount = value;
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
     * Gets the value of the lineNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineNumber() {
        return lineNumber;
    }

    /**
     * Sets the value of the lineNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineNumber(String value) {
        this.lineNumber = value;
    }

    /**
     * Gets the value of the sourcePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcePath() {
        return sourcePath;
    }

    /**
     * Sets the value of the sourcePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcePath(String value) {
        this.sourcePath = value;
    }

}
