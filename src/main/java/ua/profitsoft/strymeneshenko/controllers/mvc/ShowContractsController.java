package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.profitsoft.strymeneshenko.service.ContractService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShowContractsController {

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showContracts(HttpServletRequest request) throws Exception {
        request.getSession().setAttribute("contracts", contractService.getAllList());
        return new ModelAndView("showAllContracts");
    }
}
