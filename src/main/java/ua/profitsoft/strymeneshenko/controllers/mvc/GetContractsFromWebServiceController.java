package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.data.ContractResponse;
import ua.profitsoft.strymeneshenko.generate.Evo;
import ua.profitsoft.strymeneshenko.generate.EvoService;
import ua.profitsoft.strymeneshenko.generate.GetAllContractsResponse;
import ua.profitsoft.strymeneshenko.mappers.ContractMapper;

import java.util.List;

@Controller
public class GetContractsFromWebServiceController {

    @RequestMapping("/callToWS")
    public ModelAndView getContracts(){
        Evo service = new EvoService().getEvoSoap11();
        GetAllContractsResponse response = service.getAllContracts(null);
        ContractResponse contractResponse = new ContractResponse();
        contractResponse.setContracts(ContractMapper.INSTANCE.toSimpleContractList(response.getContract()));
        List<Contract> contracts = contractResponse.getContracts();
        return new ModelAndView("contractsFromWS").addObject("contracts", contracts);
    }
}

