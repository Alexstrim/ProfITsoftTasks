package ua.profitsoft.strymeneshenko.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.profitsoft.strymeneshenko.generate.InsuredPerson;

@Mapper
public interface InsuredPersonMapper {

    InsuredPersonMapper INSTANCE = Mappers.getMapper( InsuredPersonMapper.class);

    InsuredPerson fromPerson(ua.profitsoft.strymeneshenko.data.InsuredPerson person);

    @InheritInverseConfiguration
    ua.profitsoft.strymeneshenko.data.InsuredPerson toPerson(InsuredPerson person);
}
