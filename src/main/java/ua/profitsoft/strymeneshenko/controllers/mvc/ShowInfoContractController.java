package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.profitsoft.strymeneshenko.service.ContractService;

@Controller
public class ShowInfoContractController {

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/showInfoContract", method = RequestMethod.GET)
    public String showContractInfo(@RequestParam(value = "contractId") Long id, ModelMap model) throws Exception {
        model.addAttribute("contract", contractService.read(id));
        return "showInfoContract";
    }
}
