package ua.profitsoft.strymeneshenko.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.profitsoft.strymeneshenko.generate.Contract;

@Mapper
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper( ContractMapper.class);

    Contract conToContract(ua.profitsoft.strymeneshenko.data.Contract con);
}
