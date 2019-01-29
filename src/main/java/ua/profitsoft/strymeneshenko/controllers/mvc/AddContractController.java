package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ua.profitsoft.strymeneshenko.service.ClientService;
import ua.profitsoft.strymeneshenko.service.ContractService;
import ua.profitsoft.strymeneshenko.service.InsuredPersonService;

@Controller
@SessionAttributes(names = "clients, persons")
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
        model.setViewName("addNewContract1Step");
        return model;
    }

    @RequestMapping(value = "/saveContract1")
    public ModelAndView saveContract() throws Exception{

    }
}
