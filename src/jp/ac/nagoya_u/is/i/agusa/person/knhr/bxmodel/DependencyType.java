
package jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dependencyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="dependencyType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="data"/>
 *     &lt;enumeration value="control"/>
 *     &lt;enumeration value="methodInvocation"/>
 *     &lt;enumeration value="startEnd"/>
 *     &lt;enumeration value="dummy"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "dependencyType")
@XmlEnum
public enum DependencyType {

    @XmlEnumValue("data")
    DATA("data"),
    @XmlEnumValue("control")
    CONTROL("control"),
    @XmlEnumValue("methodInvocation")
    METHOD_INVOCATION("methodInvocation"),
    @XmlEnumValue("startEnd")
    START_END("startEnd"),
    @XmlEnumValue("dummy")
    DUMMY("dummy");
    private final String value;

    DependencyType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DependencyType fromValue(String v) {
        for (DependencyType c: DependencyType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
