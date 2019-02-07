package ua.profitsoft.strymeneshenko.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.profitsoft.strymeneshenko.generate.Contract;
import ua.profitsoft.strymeneshenko.generate.GetAllContractsResponse;
import ua.profitsoft.strymeneshenko.service.ContractService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
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
        return new GetAllContractsResponse().setContract(service.getAllList());
    }
}
