package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.profitsoft.strymeneshenko.data.Individual;
import ua.profitsoft.strymeneshenko.data.LegalEntity;
import ua.profitsoft.strymeneshenko.service.ClientService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/addIndividual", method = RequestMethod.POST)
    public ModelAndView addClient(@Validated @ModelAttribute Individual individual, BindingResult bindingResult, HttpServletRequest request) throws Exception{
        if(bindingResult.hasErrors()){
            return new ModelAndView("addNewClient");
        }
        clientService.create(individual);
        request.getSession().removeAttribute("Send");
        request.getSession().removeAttribute("clientType");
        return new ModelAndView("showAllContracts");
    }

    @RequestMapping(value = "/addLegalEntity", method = RequestMethod.POST)
    public ModelAndView addClient(@Validated @ModelAttribute LegalEntity legalEntity, BindingResult bindingResult, HttpServletRequest request) throws Exception{
        if(bindingResult.hasErrors()){
            return new ModelAndView("addNewClient");
        }
        clientService.create(legalEntity);
        request.getSession().removeAttribute("Send");
        request.getSession().removeAttribute("clientType");
        return new ModelAndView("showAllContracts");
    }
}
