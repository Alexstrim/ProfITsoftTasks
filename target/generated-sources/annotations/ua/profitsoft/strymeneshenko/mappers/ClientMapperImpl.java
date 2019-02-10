package ua.profitsoft.strymeneshenko.mappers;

import javax.annotation.Generated;
import ua.profitsoft.strymeneshenko.data.Client;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-02-08T21:10:09+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ua.profitsoft.strymeneshenko.generate.Client generateClientFromSimleClient(Client client) {
        if ( client == null ) {
            return null;
        }

        ua.profitsoft.strymeneshenko.generate.Client client1 = new ua.profitsoft.strymeneshenko.generate.Client();

        if ( client.getId() != null ) {
            client1.setId( client.getId() );
        }
        client1.setAdress( client.getAdress() );

        return client1;
    }

    @Override
    public Client toSimpleClient(ua.profitsoft.strymeneshenko.generate.Client client) {
        if ( client == null ) {
            return null;
        }

        Client client1 = new Client();

        client1.setAdress( client.getAdress() );
        client1.setId( client.getId() );

        return client1;
    }
}
