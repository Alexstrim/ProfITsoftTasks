package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.service.ClientService;
import ua.profitsoft.strymeneshenko.service.ContractService;
import ua.profitsoft.strymeneshenko.service.InsuredPersonService;

@Controller
@SessionAttributes(names = {"clients", "persons", "contract"})
@RequestMapping("/addContract")
public class AddContractController {

    @Autowired
    private InsuredPersonService insuredPersonService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ContractService contractService;

    @RequestMapping
    public ModelAndView index(ModelAndView model) throws Exception{
        model.addObject("clients", clientService.getAllList());
        model.addObject("persons", insuredPersonService.getAllList());
        model.addObject("contract", new Contract());
        model.setViewName("addNewContract1Step");
        return model;
    }

    @RequestMapping(value = "/saveContract1", method = RequestMethod.POST)
    public ModelAndView finishSaveContract1(@Validated @ModelAttribute Contract contract, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("addNewContract1Step");
        }
        return new ModelAndView("addNewContract2Step");
    }


    @RequestMapping(value = "/saveContract2", method = RequestMethod.POST)
    public ModelAndView finishSaveContract2(@RequestParam("clientId") Long id_Client, @Validated @ModelAttribute Contract contract, BindingResult result) throws Exception{
        Client client = clientService.read(id_Client);
        contract.setClient(client);
        if(result.hasErrors()){
            return new ModelAndView("addNewContract2Step");
        }
        return new ModelAndView("addNewContract3Step");
    }

    @RequestMapping(value = "/saveInsuredPersonToContract", method = RequestMethod.POST)
    public ModelAndView finishInsuredPersonToContract(@RequestParam("personId") Long personId, @Validated @ModelAttribute Contract contract, BindingResult result) throws Exception{
        InsuredPerson insuredPerson = insuredPersonService.read(personId);
        contract.addInsuredPerson(insuredPerson);
        if(result.hasErrors()){
            return new ModelAndView("addNewContract3Step");
        }
        return new ModelAndView("addNewContract3Step");
    }

    @RequestMapping(value = "/finishContract", method = RequestMethod.POST)
    public ModelAndView finishSaveContract3(@Validated @ModelAttribute Contract contract, BindingResult result, SessionStatus status) throws Exception{
        if(result.hasErrors()){
            return new ModelAndView("addNewContract3Step");
        }
        contractService.create(contract);
        status.setComplete();
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/exit")
    public ModelAndView exit(SessionStatus status){
        status.setComplete();
        return new ModelAndView("redirect:/");
    }
}
