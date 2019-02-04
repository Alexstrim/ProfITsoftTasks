package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.service.ContractService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes(types = Contract.class)
public class EditContractController {

    @Autowired
    private ContractService contractService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    @RequestMapping(value = "/goEditContract", method = RequestMethod.GET)
    public ModelAndView goEdit(@RequestParam(value = "contractId") Long id, ModelAndView model) throws Exception {
        Contract contract = contractService.read(id);
        model.addObject("editContract", contract);
        model.setViewName("editContract");
        return model;
    }

    @RequestMapping(value = "/applyEditContract", method = RequestMethod.POST)
    public ModelAndView applyEdit(@ModelAttribute("editContract") Contract contract) throws Exception {
        contractService.update(contract);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/editContract", method = RequestMethod.POST)
    public ModelAndView Edit(@ModelAttribute("editContract")Contract editContract,@RequestParam("dateConclusion")Date dateConc,@RequestParam("startDate")Date dateStart,@RequestParam("endDate")Date endDate) throws Exception {
        editContract.setDateConclusion(dateConc);
        editContract.setStartDate(dateStart);
        editContract.setEndDate(endDate);
        return new ModelAndView("editContract");
    }
}
