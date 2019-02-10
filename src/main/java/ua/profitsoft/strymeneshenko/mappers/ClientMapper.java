package ua.profitsoft.strymeneshenko.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.profitsoft.strymeneshenko.generate.Client;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);


    Client generateClientFromSimleClient(ua.profitsoft.strymeneshenko.data.Client client);

    @InheritInverseConfiguration
    ua.profitsoft.strymeneshenko.data.Client toSimpleClient(Client client);
}
