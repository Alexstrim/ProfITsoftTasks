
package ua.profitsoft.strymeneshenko.generate;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.profitsoft.strymeneshenko.generate package. 
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

    private final static QName _GetAllContractsRequest_QNAME = new QName("http://www.profItsoft.org/ContractService", "getAllContractsRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.profitsoft.strymeneshenko.generate
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllContractsResponse }
     * 
     */
    public GetAllContractsResponse createGetAllContractsResponse() {
        return new GetAllContractsResponse();
    }

    /**
     * Create an instance of {@link Contract }
     * 
     */
    public Contract createContract() {
        return new Contract();
    }

    /**
     * Create an instance of {@link Persons }
     * 
     */
    public Persons createPersons() {
        return new Persons();
    }

    /**
     * Create an instance of {@link Individual }
     * 
     */
    public Individual createIndividual() {
        return new Individual();
    }

    /**
     * Create an instance of {@link InsuredPerson }
     * 
     */
    public InsuredPerson createInsuredPerson() {
        return new InsuredPerson();
    }

    /**
     * Create an instance of {@link Client }
     * 
     */
    public Client createClient() {
        return new Client();
    }

    /**
     * Create an instance of {@link LegalEntity }
     * 
     */
    public LegalEntity createLegalEntity() {
        return new LegalEntity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.profItsoft.org/ContractService", name = "getAllContractsRequest")
    public JAXBElement<Object> createGetAllContractsRequest(Object value) {
        return new JAXBElement<Object>(_GetAllContractsRequest_QNAME, Object.class, null, value);
    }

}
