
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ClassName_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "className");
    private final static QName _Value_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "value");
    private final static QName _ConditionString_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "conditionString");
    private final static QName _CallerVisibleVariables_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "callerVisibleVariables");
    private final static QName _MethodName_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "methodName");
    private final static QName _OwnerObject_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "ownerObject");
    private final static QName _PrimitiveTypeName_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "primitiveTypeName");
    private final static QName _LoopCount_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "loopCount");
    private final static QName _TypeName_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "typeName");
    private final static QName _CaughtException_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "caughtException");
    private final static QName _ObjectInfo_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "ObjectInfo");
    private final static QName _ThreadName_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "threadName");
    private final static QName _ReferredVariable_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "referredVariable");
    private final static QName _CallerObject_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "callerObject");
    private final static QName _DefinedVariable_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "definedVariable");
    private final static QName _ReturnType_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "returnType");
    private final static QName _VisibleVariables_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "visibleVariables");
    private final static QName _PrimitiveValue_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "primitiveValue");
    private final static QName _CalleeVisibleVariables_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "calleeVisibleVariables");
    private final static QName _EvaluatedValue_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "evaluatedValue");
    private final static QName _ReturnValue_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "returnValue");
    private final static QName _CalleeObject_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "calleeObject");
    private final static QName _ThrownException_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "thrownException");
    private final static QName _VariableName_QNAME = new QName("http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", "variableName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CatchEnd }
     * 
     */
    public CatchEnd createCatchEnd() {
        return new CatchEnd();
    }

    /**
     * Create an instance of {@link VariableDefinition }
     * 
     */
    public VariableDefinition createVariableDefinition() {
        return new VariableDefinition();
    }

    /**
     * Create an instance of {@link Node }
     * 
     */
    public Node createNode() {
        return new Node();
    }

    /**
     * Create an instance of {@link TryStart }
     * 
     */
    public TryStart createTryStart() {
        return new TryStart();
    }

    /**
     * Create an instance of {@link ConditionEnd }
     * 
     */
    public ConditionEnd createConditionEnd() {
        return new ConditionEnd();
    }

    /**
     * Create an instance of {@link Edge }
     * 
     */
    public Edge createEdge() {
        return new Edge();
    }

    /**
     * Create an instance of {@link ExceptionOccurrence }
     * 
     */
    public ExceptionOccurrence createExceptionOccurrence() {
        return new ExceptionOccurrence();
    }

    /**
     * Create an instance of {@link MethodEntry }
     * 
     */
    public MethodEntry createMethodEntry() {
        return new MethodEntry();
    }

    /**
     * Create an instance of {@link ObjectInfoType }
     * 
     */
    public ObjectInfoType createObjectInfoType() {
        return new ObjectInfoType();
    }

    /**
     * Create an instance of {@link CatchStart }
     * 
     */
    public CatchStart createCatchStart() {
        return new CatchStart();
    }

    /**
     * Create an instance of {@link BPDG }
     * 
     */
    public BPDG createBPDG() {
        return new BPDG();
    }

    /**
     * Create an instance of {@link ValueInfoLeafType }
     * 
     */
    public ValueInfoLeafType createValueInfoLeafType() {
        return new ValueInfoLeafType();
    }

    /**
     * Create an instance of {@link FieldInfo }
     * 
     */
    public FieldInfo createFieldInfo() {
        return new FieldInfo();
    }

    /**
     * Create an instance of {@link LoopStart }
     * 
     */
    public LoopStart createLoopStart() {
        return new LoopStart();
    }

    /**
     * Create an instance of {@link ConstructorExit }
     * 
     */
    public ConstructorExit createConstructorExit() {
        return new ConstructorExit();
    }

    /**
     * Create an instance of {@link FinallyEnd }
     * 
     */
    public FinallyEnd createFinallyEnd() {
        return new FinallyEnd();
    }

    /**
     * Create an instance of {@link VariableReference }
     * 
     */
    public VariableReference createVariableReference() {
        return new VariableReference();
    }

    /**
     * Create an instance of {@link TryEnd }
     * 
     */
    public TryEnd createTryEnd() {
        return new TryEnd();
    }

    /**
     * Create an instance of {@link Nodes }
     * 
     */
    public Nodes createNodes() {
        return new Nodes();
    }

    /**
     * Create an instance of {@link DummyEvent }
     * 
     */
    public DummyEvent createDummyEvent() {
        return new DummyEvent();
    }

    /**
     * Create an instance of {@link VisibleVariablesType }
     * 
     */
    public VisibleVariablesType createVisibleVariablesType() {
        return new VisibleVariablesType();
    }

    /**
     * Create an instance of {@link PrimitiveValueInfo }
     * 
     */
    public PrimitiveValueInfo createPrimitiveValueInfo() {
        return new PrimitiveValueInfo();
    }

    /**
     * Create an instance of {@link VariableInfoLeafType }
     * 
     */
    public VariableInfoLeafType createVariableInfoLeafType() {
        return new VariableInfoLeafType();
    }

    /**
     * Create an instance of {@link MethodSignature }
     * 
     */
    public MethodSignature createMethodSignature() {
        return new MethodSignature();
    }

    /**
     * Create an instance of {@link ArgumentValues }
     * 
     */
    public ArgumentValues createArgumentValues() {
        return new ArgumentValues();
    }

    /**
     * Create an instance of {@link FinallyStart }
     * 
     */
    public FinallyStart createFinallyStart() {
        return new FinallyStart();
    }

    /**
     * Create an instance of {@link Edges }
     * 
     */
    public Edges createEdges() {
        return new Edges();
    }

    /**
     * Create an instance of {@link LoopEnd }
     * 
     */
    public LoopEnd createLoopEnd() {
        return new LoopEnd();
    }

    /**
     * Create an instance of {@link ConditionStart }
     * 
     */
    public ConditionStart createConditionStart() {
        return new ConditionStart();
    }

    /**
     * Create an instance of {@link Thread }
     * 
     */
    public Thread createThread() {
        return new Thread();
    }

    /**
     * Create an instance of {@link ConstructorEntry }
     * 
     */
    public ConstructorEntry createConstructorEntry() {
        return new ConstructorEntry();
    }

    /**
     * Create an instance of {@link MethodExit }
     * 
     */
    public MethodExit createMethodExit() {
        return new MethodExit();
    }

    /**
     * Create an instance of {@link Looping }
     * 
     */
    public Looping createLooping() {
        return new Looping();
    }

    /**
     * Create an instance of {@link ArgumentTypes }
     * 
     */
    public ArgumentTypes createArgumentTypes() {
        return new ArgumentTypes();
    }

    /**
     * Create an instance of {@link LocalVariableInfo }
     * 
     */
    public LocalVariableInfo createLocalVariableInfo() {
        return new LocalVariableInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "className")
    public JAXBElement<String> createClassName(String value) {
        return new JAXBElement<String>(_ClassName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValueInfoLeafType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "value")
    public JAXBElement<ValueInfoLeafType> createValue(ValueInfoLeafType value) {
        return new JAXBElement<ValueInfoLeafType>(_Value_QNAME, ValueInfoLeafType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "conditionString")
    public JAXBElement<String> createConditionString(String value) {
        return new JAXBElement<String>(_ConditionString_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VisibleVariablesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "callerVisibleVariables")
    public JAXBElement<VisibleVariablesType> createCallerVisibleVariables(VisibleVariablesType value) {
        return new JAXBElement<VisibleVariablesType>(_CallerVisibleVariables_QNAME, VisibleVariablesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "methodName")
    public JAXBElement<String> createMethodName(String value) {
        return new JAXBElement<String>(_MethodName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "ownerObject")
    public JAXBElement<ObjectInfoType> createOwnerObject(ObjectInfoType value) {
        return new JAXBElement<ObjectInfoType>(_OwnerObject_QNAME, ObjectInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "primitiveTypeName")
    public JAXBElement<String> createPrimitiveTypeName(String value) {
        return new JAXBElement<String>(_PrimitiveTypeName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "loopCount")
    public JAXBElement<Integer> createLoopCount(Integer value) {
        return new JAXBElement<Integer>(_LoopCount_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "typeName")
    public JAXBElement<String> createTypeName(String value) {
        return new JAXBElement<String>(_TypeName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "caughtException")
    public JAXBElement<String> createCaughtException(String value) {
        return new JAXBElement<String>(_CaughtException_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "ObjectInfo")
    public JAXBElement<ObjectInfoType> createObjectInfo(ObjectInfoType value) {
        return new JAXBElement<ObjectInfoType>(_ObjectInfo_QNAME, ObjectInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "threadName")
    public JAXBElement<String> createThreadName(String value) {
        return new JAXBElement<String>(_ThreadName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VariableInfoLeafType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "referredVariable")
    public JAXBElement<VariableInfoLeafType> createReferredVariable(VariableInfoLeafType value) {
        return new JAXBElement<VariableInfoLeafType>(_ReferredVariable_QNAME, VariableInfoLeafType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "callerObject")
    public JAXBElement<ObjectInfoType> createCallerObject(ObjectInfoType value) {
        return new JAXBElement<ObjectInfoType>(_CallerObject_QNAME, ObjectInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VariableInfoLeafType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "definedVariable")
    public JAXBElement<VariableInfoLeafType> createDefinedVariable(VariableInfoLeafType value) {
        return new JAXBElement<VariableInfoLeafType>(_DefinedVariable_QNAME, VariableInfoLeafType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "returnType")
    public JAXBElement<String> createReturnType(String value) {
        return new JAXBElement<String>(_ReturnType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VisibleVariablesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "visibleVariables")
    public JAXBElement<VisibleVariablesType> createVisibleVariables(VisibleVariablesType value) {
        return new JAXBElement<VisibleVariablesType>(_VisibleVariables_QNAME, VisibleVariablesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "primitiveValue")
    public JAXBElement<String> createPrimitiveValue(String value) {
        return new JAXBElement<String>(_PrimitiveValue_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VisibleVariablesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "calleeVisibleVariables")
    public JAXBElement<VisibleVariablesType> createCalleeVisibleVariables(VisibleVariablesType value) {
        return new JAXBElement<VisibleVariablesType>(_CalleeVisibleVariables_QNAME, VisibleVariablesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValueInfoLeafType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "evaluatedValue")
    public JAXBElement<ValueInfoLeafType> createEvaluatedValue(ValueInfoLeafType value) {
        return new JAXBElement<ValueInfoLeafType>(_EvaluatedValue_QNAME, ValueInfoLeafType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValueInfoLeafType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "returnValue")
    public JAXBElement<ValueInfoLeafType> createReturnValue(ValueInfoLeafType value) {
        return new JAXBElement<ValueInfoLeafType>(_ReturnValue_QNAME, ValueInfoLeafType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "calleeObject")
    public JAXBElement<ObjectInfoType> createCalleeObject(ObjectInfoType value) {
        return new JAXBElement<ObjectInfoType>(_CalleeObject_QNAME, ObjectInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "thrownException")
    public JAXBElement<ObjectInfoType> createThrownException(ObjectInfoType value) {
        return new JAXBElement<ObjectInfoType>(_ThrownException_QNAME, ObjectInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.agusa.i.is.nagoya-u.ac.jp/person/knhr/BXModel", name = "variableName")
    public JAXBElement<String> createVariableName(String value) {
        return new JAXBElement<String>(_VariableName_QNAME, String.class, null, value);
    }

}
