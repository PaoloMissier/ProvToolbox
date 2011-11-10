//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-792 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.10 at 10:15:17 AM GMT 
//


package org.openprovenance.prov.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.builder.JAXBEqualsBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBHashCodeBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBToStringBuilder;


/**
 * <p>Java class for Annotation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Annotation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://openprovenance.org/prov-xml#}EmbeddedAnnotation">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="externalSubject" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *           &lt;element name="localSubject" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Annotation", namespace = "http://openprovenance.org/prov-xml#", propOrder = {
    "externalSubject",
    "localSubject"
})
public class Annotation
    extends EmbeddedAnnotation
    implements Equals, HashCode, ToString
{

    @XmlElement(namespace = "http://openprovenance.org/prov-xml#")
    @XmlSchemaType(name = "anyURI")
    protected String externalSubject;
    @XmlElement(namespace = "http://openprovenance.org/prov-xml#")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object localSubject;

    /**
     * Gets the value of the externalSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalSubject() {
        return externalSubject;
    }

    /**
     * Sets the value of the externalSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalSubject(String value) {
        this.externalSubject = value;
    }

    /**
     * Gets the value of the localSubject property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLocalSubject() {
        return localSubject;
    }

    /**
     * Sets the value of the localSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLocalSubject(Object value) {
        this.localSubject = value;
    }

    public void equals(Object object, EqualsBuilder equalsBuilder) {
        if (!(object instanceof Annotation)) {
            equalsBuilder.appendSuper(false);
            return ;
        }
        if (this == object) {
            return ;
        }
        super.equals(object, equalsBuilder);
        final Annotation that = ((Annotation) object);
        equalsBuilder.append(this.getExternalSubject(), that.getExternalSubject());
        equalsBuilder.append(this.getLocalSubject(), that.getLocalSubject());
    }

    public boolean equals(Object object) {
        if (!(object instanceof Annotation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EqualsBuilder equalsBuilder = new JAXBEqualsBuilder();
        equals(object, equalsBuilder);
        return equalsBuilder.isEquals();
    }

    public void hashCode(HashCodeBuilder hashCodeBuilder) {
        super.hashCode(hashCodeBuilder);
        hashCodeBuilder.append(this.getExternalSubject());
        hashCodeBuilder.append(this.getLocalSubject());
    }

    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new JAXBHashCodeBuilder();
        hashCode(hashCodeBuilder);
        return hashCodeBuilder.toHashCode();
    }

    public void toString(ToStringBuilder toStringBuilder) {
        super.toString(toStringBuilder);
        {
            String theExternalSubject;
            theExternalSubject = this.getExternalSubject();
            toStringBuilder.append("externalSubject", theExternalSubject);
        }
        {
            Object theLocalSubject;
            theLocalSubject = this.getLocalSubject();
            toStringBuilder.append("localSubject", theLocalSubject);
        }
    }

    public String toString() {
        final ToStringBuilder toStringBuilder = new JAXBToStringBuilder(this);
        toString(toStringBuilder);
        return toStringBuilder.toString();
    }

}