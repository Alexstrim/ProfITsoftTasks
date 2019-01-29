package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.profitsoft.strymeneshenko.service.ContractService;

@Controller
public class ShowInfoContractController {

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/showInfoContract", method = RequestMethod.GET)
    public ModelAndView showContractInfo(@RequestParam(value = "contractId") Long id, ModelAndView model) throws Exception {
        model.addObject("contract", contractService.read(id));
        model.setViewName("showInfoContract");
        return model;
    }
}
