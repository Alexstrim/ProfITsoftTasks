package ua.profitsoft.strymeneshenko.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.mapstruct.factory.Mappers;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-02-08T12:52:09+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class ContractMapperImpl implements ContractMapper {

    private final ClientMapper clientMapper = Mappers.getMapper( ClientMapper.class );
    private final InsuredPersonMapper insuredPersonMapper = Mappers.getMapper( InsuredPersonMapper.class );

    @Override
    public ua.profitsoft.strymeneshenko.generate.Contract fromSimpleContract(Contract con) {
        if ( con == null ) {
            return null;
        }

        ua.profitsoft.strymeneshenko.generate.Contract contract = new ua.profitsoft.strymeneshenko.generate.Contract();

        if ( contract.getPerson() != null ) {
            List<ua.profitsoft.strymeneshenko.generate.InsuredPerson> list = insuredPersonSetToInsuredPersonList( con.getPersons() );
            if ( list != null ) {
                contract.getPerson().addAll( list );
            }
        }
        if ( con.getNumber() != null ) {
            contract.setNumber( con.getNumber() );
        }
        contract.setDateConclusion( dateToXmlGregorianCalendar( con.getDateConclusion() ) );
        contract.setStartDate( dateToXmlGregorianCalendar( con.getStartDate() ) );
        contract.setEndDate( dateToXmlGregorianCalendar( con.getEndDate() ) );
        contract.setClient( clientMapper.generateClientFromSimleClient( con.getClient() ) );

        return contract;
    }

    @Override
    public List<ua.profitsoft.strymeneshenko.generate.Contract> fromSimpleContractList(List<Contract> list) {
        if ( list == null ) {
            return null;
        }

        List<ua.profitsoft.strymeneshenko.generate.Contract> list1 = new ArrayList<ua.profitsoft.strymeneshenko.generate.Contract>( list.size() );
        for ( Contract contract : list ) {
            list1.add( fromSimpleContract( contract ) );
        }

        return list1;
    }

    @Override
    public List<Contract> toSimpleContractList(List<ua.profitsoft.strymeneshenko.generate.Contract> list) {
        if ( list == null ) {
            return null;
        }

        List<Contract> list1 = new ArrayList<Contract>( list.size() );
        for ( ua.profitsoft.strymeneshenko.generate.Contract contract : list ) {
            list1.add( toSimpleContract( contract ) );
        }

        return list1;
    }

    @Override
    public Contract toSimpleContract(ua.profitsoft.strymeneshenko.generate.Contract contract) {
        if ( contract == null ) {
            return null;
        }

        Contract contract1 = new Contract();

        contract1.setNumber( contract.getNumber() );
        contract1.setDateConclusion( xmlGregorianCalendarToDate( contract.getDateConclusion() ) );
        contract1.setStartDate( xmlGregorianCalendarToDate( contract.getStartDate() ) );
        contract1.setEndDate( xmlGregorianCalendarToDate( contract.getEndDate() ) );
        contract1.setClient( clientMapper.toSimpleClient( contract.getClient() ) );

        return contract1;
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

    protected List<ua.profitsoft.strymeneshenko.generate.InsuredPerson> insuredPersonSetToInsuredPersonList(Set<InsuredPerson> set) {
        if ( set == null ) {
            return null;
        }

        List<ua.profitsoft.strymeneshenko.generate.InsuredPerson> list = new ArrayList<ua.profitsoft.strymeneshenko.generate.InsuredPerson>( set.size() );
        for ( InsuredPerson insuredPerson : set ) {
            list.add( insuredPersonMapper.fromPerson( insuredPerson ) );
        }

        return list;
    }
}
