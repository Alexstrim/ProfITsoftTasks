package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.profitsoft.strymeneshenko.service.ContractService;

@Controller
public class DeleteContractController {

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/deleteContract", method = RequestMethod.POST)
    public ModelAndView showContracts(@RequestParam(value = "contractId") Long id) throws Exception {
        contractService.delete(id);
        return new ModelAndView("redirect:/");
    }
}
