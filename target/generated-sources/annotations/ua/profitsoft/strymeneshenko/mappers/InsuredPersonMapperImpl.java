package ua.profitsoft.strymeneshenko.mappers;

import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-02-08T12:52:09+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class InsuredPersonMapperImpl implements InsuredPersonMapper {

    @Override
    public ua.profitsoft.strymeneshenko.generate.InsuredPerson fromPerson(InsuredPerson person) {
        if ( person == null ) {
            return null;
        }

        ua.profitsoft.strymeneshenko.generate.InsuredPerson insuredPerson = new ua.profitsoft.strymeneshenko.generate.InsuredPerson();

        insuredPerson.setId( person.getId() );
        insuredPerson.setFirstName( person.getFirstName() );
        insuredPerson.setLastName( person.getLastName() );
        insuredPerson.setDateOfBirth( dateToXmlGregorianCalendar( person.getDateOfBirth() ) );
        if ( person.getCost() != null ) {
            insuredPerson.setCost( BigInteger.valueOf( person.getCost() ) );
        }
        if ( person.getIdentificationNumber() != null ) {
            insuredPerson.setIdentificationNumber( person.getIdentificationNumber() );
        }

        return insuredPerson;
    }

    @Override
    public InsuredPerson toPerson(ua.profitsoft.strymeneshenko.generate.InsuredPerson person) {
        if ( person == null ) {
            return null;
        }

        InsuredPerson insuredPerson = new InsuredPerson();

        insuredPerson.setIdentificationNumber( person.getIdentificationNumber() );
        insuredPerson.setFirstName( person.getFirstName() );
        insuredPerson.setLastName( person.getLastName() );
        insuredPerson.setDateOfBirth( xmlGregorianCalendarToDate( person.getDateOfBirth() ) );
        if ( person.getCost() != null ) {
            insuredPerson.setCost( person.getCost().intValue() );
        }
        insuredPerson.setId( person.getId() );

        return insuredPerson;
    }

    private static Date xmlGregorianCalendarToDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return xcal.toGregorianCalendar().getTime();
    }

    private XMLGregorianCalendar dateToXmlGregorianCalendar( Date date ) {
        if ( date == null ) {
            return null;
        }

        try {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime( date );
            return DatatypeFactory.newInstance().newXMLGregorianCalendar( c );
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }
}
