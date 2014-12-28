
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NodeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NodeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}BehaviorEventLeaf"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel}id.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NodeType", propOrder = {
    "dummyEvent",
    "variableReference",
    "variableDefinition",
    "finallyEnd",
    "catchEnd",
    "tryEnd",
    "finallyStart",
    "catchStart",
    "tryStart",
    "exceptionOccurrence",
    "loopEnd",
    "conditionEnd",
    "looping",
    "loopStart",
    "conditionStart",
    "constructorExit",
    "methodExit",
    "constructorEntry",
    "methodEntry"
})
@XmlRootElement(name = "Node")
public class Node {

    @XmlElement(name = "DummyEvent")
    protected DummyEvent dummyEvent;
    @XmlElement(name = "VariableReference")
    protected VariableReference variableReference;
    @XmlElement(name = "VariableDefinition")
    protected VariableDefinition variableDefinition;
    @XmlElement(name = "FinallyEnd")
    protected FinallyEnd finallyEnd;
    @XmlElement(name = "CatchEnd")
    protected CatchEnd catchEnd;
    @XmlElement(name = "TryEnd")
    protected TryEnd tryEnd;
    @XmlElement(name = "FinallyStart")
    protected FinallyStart finallyStart;
    @XmlElement(name = "CatchStart")
    protected CatchStart catchStart;
    @XmlElement(name = "TryStart")
    protected TryStart tryStart;
    @XmlElement(name = "ExceptionOccurrence")
    protected ExceptionOccurrence exceptionOccurrence;
    @XmlElement(name = "LoopEnd")
    protected LoopEnd loopEnd;
    @XmlElement(name = "ConditionEnd")
    protected ConditionEnd conditionEnd;
    @XmlElement(name = "Looping")
    protected Looping looping;
    @XmlElement(name = "LoopStart")
    protected LoopStart loopStart;
    @XmlElement(name = "ConditionStart")
    protected ConditionStart conditionStart;
    @XmlElement(name = "ConstructorExit")
    protected ConstructorExit constructorExit;
    @XmlElement(name = "MethodExit")
    protected MethodExit methodExit;
    @XmlElement(name = "ConstructorEntry")
    protected ConstructorEntry constructorEntry;
    @XmlElement(name = "MethodEntry")
    protected MethodEntry methodEntry;
    @XmlAttribute(required = true)
    protected String id;

    /**
     * Gets the value of the dummyEvent property.
     * 
     * @return
     *     possible object is
     *     {@link DummyEvent }
     *     
     */
    public DummyEvent getDummyEvent() {
        return dummyEvent;
    }

    /**
     * Sets the value of the dummyEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link DummyEvent }
     *     
     */
    public void setDummyEvent(DummyEvent value) {
        this.dummyEvent = value;
    }

    /**
     * Gets the value of the variableReference property.
     * 
     * @return
     *     possible object is
     *     {@link VariableReference }
     *     
     */
    public VariableReference getVariableReference() {
        return variableReference;
    }

    /**
     * Sets the value of the variableReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link VariableReference }
     *     
     */
    public void setVariableReference(VariableReference value) {
        this.variableReference = value;
    }

    /**
     * Gets the value of the variableDefinition property.
     * 
     * @return
     *     possible object is
     *     {@link VariableDefinition }
     *     
     */
    public VariableDefinition getVariableDefinition() {
        return variableDefinition;
    }

    /**
     * Sets the value of the variableDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link VariableDefinition }
     *     
     */
    public void setVariableDefinition(VariableDefinition value) {
        this.variableDefinition = value;
    }

    /**
     * Gets the value of the finallyEnd property.
     * 
     * @return
     *     possible object is
     *     {@link FinallyEnd }
     *     
     */
    public FinallyEnd getFinallyEnd() {
        return finallyEnd;
    }

    /**
     * Sets the value of the finallyEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinallyEnd }
     *     
     */
    public void setFinallyEnd(FinallyEnd value) {
        this.finallyEnd = value;
    }

    /**
     * Gets the value of the catchEnd property.
     * 
     * @return
     *     possible object is
     *     {@link CatchEnd }
     *     
     */
    public CatchEnd getCatchEnd() {
        return catchEnd;
    }

    /**
     * Sets the value of the catchEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatchEnd }
     *     
     */
    public void setCatchEnd(CatchEnd value) {
        this.catchEnd = value;
    }

    /**
     * Gets the value of the tryEnd property.
     * 
     * @return
     *     possible object is
     *     {@link TryEnd }
     *     
     */
    public TryEnd getTryEnd() {
        return tryEnd;
    }

    /**
     * Sets the value of the tryEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link TryEnd }
     *     
     */
    public void setTryEnd(TryEnd value) {
        this.tryEnd = value;
    }

    /**
     * Gets the value of the finallyStart property.
     * 
     * @return
     *     possible object is
     *     {@link FinallyStart }
     *     
     */
    public FinallyStart getFinallyStart() {
        return finallyStart;
    }

    /**
     * Sets the value of the finallyStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinallyStart }
     *     
     */
    public void setFinallyStart(FinallyStart value) {
        this.finallyStart = value;
    }

    /**
     * Gets the value of the catchStart property.
     * 
     * @return
     *     possible object is
     *     {@link CatchStart }
     *     
     */
    public CatchStart getCatchStart() {
        return catchStart;
    }

    /**
     * Sets the value of the catchStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatchStart }
     *     
     */
    public void setCatchStart(CatchStart value) {
        this.catchStart = value;
    }

    /**
     * Gets the value of the tryStart property.
     * 
     * @return
     *     possible object is
     *     {@link TryStart }
     *     
     */
    public TryStart getTryStart() {
        return tryStart;
    }

    /**
     * Sets the value of the tryStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link TryStart }
     *     
     */
    public void setTryStart(TryStart value) {
        this.tryStart = value;
    }

    /**
     * Gets the value of the exceptionOccurrence property.
     * 
     * @return
     *     possible object is
     *     {@link ExceptionOccurrence }
     *     
     */
    public ExceptionOccurrence getExceptionOccurrence() {
        return exceptionOccurrence;
    }

    /**
     * Sets the value of the exceptionOccurrence property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExceptionOccurrence }
     *     
     */
    public void setExceptionOccurrence(ExceptionOccurrence value) {
        this.exceptionOccurrence = value;
    }

    /**
     * Gets the value of the loopEnd property.
     * 
     * @return
     *     possible object is
     *     {@link LoopEnd }
     *     
     */
    public LoopEnd getLoopEnd() {
        return loopEnd;
    }

    /**
     * Sets the value of the loopEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoopEnd }
     *     
     */
    public void setLoopEnd(LoopEnd value) {
        this.loopEnd = value;
    }

    /**
     * Gets the value of the conditionEnd property.
     * 
     * @return
     *     possible object is
     *     {@link ConditionEnd }
     *     
     */
    public ConditionEnd getConditionEnd() {
        return conditionEnd;
    }

    /**
     * Sets the value of the conditionEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConditionEnd }
     *     
     */
    public void setConditionEnd(ConditionEnd value) {
        this.conditionEnd = value;
    }

    /**
     * Gets the value of the looping property.
     * 
     * @return
     *     possible object is
     *     {@link Looping }
     *     
     */
    public Looping getLooping() {
        return looping;
    }

    /**
     * Sets the value of the looping property.
     * 
     * @param value
     *     allowed object is
     *     {@link Looping }
     *     
     */
    public void setLooping(Looping value) {
        this.looping = value;
    }

    /**
     * Gets the value of the loopStart property.
     * 
     * @return
     *     possible object is
     *     {@link LoopStart }
     *     
     */
    public LoopStart getLoopStart() {
        return loopStart;
    }

    /**
     * Sets the value of the loopStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoopStart }
     *     
     */
    public void setLoopStart(LoopStart value) {
        this.loopStart = value;
    }

    /**
     * Gets the value of the conditionStart property.
     * 
     * @return
     *     possible object is
     *     {@link ConditionStart }
     *     
     */
    public ConditionStart getConditionStart() {
        return conditionStart;
    }

    /**
     * Sets the value of the conditionStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConditionStart }
     *     
     */
    public void setConditionStart(ConditionStart value) {
        this.conditionStart = value;
    }

    /**
     * Gets the value of the constructorExit property.
     * 
     * @return
     *     possible object is
     *     {@link ConstructorExit }
     *     
     */
    public ConstructorExit getConstructorExit() {
        return constructorExit;
    }

    /**
     * Sets the value of the constructorExit property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConstructorExit }
     *     
     */
    public void setConstructorExit(ConstructorExit value) {
        this.constructorExit = value;
    }

    /**
     * Gets the value of the methodExit property.
     * 
     * @return
     *     possible object is
     *     {@link MethodExit }
     *     
     */
    public MethodExit getMethodExit() {
        return methodExit;
    }

    /**
     * Sets the value of the methodExit property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodExit }
     *     
     */
    public void setMethodExit(MethodExit value) {
        this.methodExit = value;
    }

    /**
     * Gets the value of the constructorEntry property.
     * 
     * @return
     *     possible object is
     *     {@link ConstructorEntry }
     *     
     */
    public ConstructorEntry getConstructorEntry() {
        return constructorEntry;
    }

    /**
     * Sets the value of the constructorEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConstructorEntry }
     *     
     */
    public void setConstructorEntry(ConstructorEntry value) {
        this.constructorEntry = value;
    }

    /**
     * Gets the value of the methodEntry property.
     * 
     * @return
     *     possible object is
     *     {@link MethodEntry }
     *     
     */
    public MethodEntry getMethodEntry() {
        return methodEntry;
    }

    /**
     * Sets the value of the methodEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodEntry }
     *     
     */
    public void setMethodEntry(MethodEntry value) {
        this.methodEntry = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
    	System.out.println(value);
        this.id = value;
    }

}
