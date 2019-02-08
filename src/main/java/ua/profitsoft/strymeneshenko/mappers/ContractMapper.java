package ua.profitsoft.strymeneshenko.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.profitsoft.strymeneshenko.generate.Contract;

import java.util.List;

@Mapper(uses = {ClientMapper.class, InsuredPersonMapper.class})
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper( ContractMapper.class);

    @Mapping(source = "persons", target = "person")
    Contract fromSimpleContract(ua.profitsoft.strymeneshenko.data.Contract con);

    List<Contract> fromSimpleContractList(List<ua.profitsoft.strymeneshenko.data.Contract> list);

    List<ua.profitsoft.strymeneshenko.data.Contract> toSimpleContractList(List<Contract> list);

    ua.profitsoft.strymeneshenko.data.Contract toSimpleContract(Contract contract);
}
