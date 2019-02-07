
package ua.profitsoft.strymeneshenko.generate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for legalEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="legalEntity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.profItsoft.org/ContractService}client">
 *       &lt;sequence>
 *         &lt;element name="nameOrganization" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "legalEntity", propOrder = {
    "nameOrganization"
})
public class LegalEntity
    extends Client
{

    @XmlElement(required = true)
    protected String nameOrganization;

    /**
     * Gets the value of the nameOrganization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOrganization() {
        return nameOrganization;
    }

    /**
     * Sets the value of the nameOrganization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOrganization(String value) {
        this.nameOrganization = value;
    }

}
