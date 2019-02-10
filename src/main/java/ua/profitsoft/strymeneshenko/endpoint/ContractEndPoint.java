package ua.profitsoft.strymeneshenko.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.generate.GetAllContractsResponse;
import ua.profitsoft.strymeneshenko.mappers.ContractMapper;
import ua.profitsoft.strymeneshenko.service.ContractService;

import java.util.List;

@Endpoint
public class ContractEndPoint{

    private static final String NAMESPACE_URI = "http://www.profItsoft.org/ContractService";

    private ContractService service;

    @Autowired
    public void setContractService (ContractService contractService){
        this.service = contractService;
    }

    @PayloadRoot(localPart = "getAllContractsRequest", namespace = NAMESPACE_URI)
    public @ResponsePayload GetAllContractsResponse getAllContracts() throws Exception {
        List<Contract> contracts = service.getAllList();
        GetAllContractsResponse allContractsResponse = new GetAllContractsResponse();
        allContractsResponse.setContract(ContractMapper.INSTANCE.fromSimpleContractList(contracts));
        return allContractsResponse;
    }
}
