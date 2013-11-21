 
package net.mothman.feedgenerator.domain;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
 
 
/**
 * Java class for anonymous complex type.
 *
 *
 
The following schema fragment specifies the expected content contained within this class.
 *
 *
 
<pre> * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="Surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="Position" type="{}PositionType"/>
 *         <element name="Age" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="TeamName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
*
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "surname"
})
@XmlRootElement(name = "PlayerDetails")
public class PlayerDetails {
 
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Surname", required = true)
    protected String surname;
    
    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *    
     */
    public String getName() {
        return name;
    }
 
    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *    
     */
    public void setName(String value) {
        this.name = value;
    }
 
    /**
     * Gets the value of the surname property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *    
     */
    public String getSurname() {
        return surname;
    }
 
    /**
     * Sets the value of the surname property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *    
     */
    public void setSurname(String value) {
        this.surname = value;
    }

	@Override
	public String toString() {
		return String.format("PlayerDetails [name=%s, surname=%s]", name,
				surname);
	}

}