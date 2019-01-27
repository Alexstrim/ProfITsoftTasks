package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.service.ContractService;
import ua.profitsoft.strymeneshenko.util.UtilDate;

import javax.servlet.http.HttpServletRequest;
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
    public String goEdit(@RequestParam(value = "contractId") Long id, ModelMap model) throws Exception {
        Contract contract = contractService.read(id);
        model.addAttribute("editContract", contract);
        return "editContract";
    }

    @RequestMapping(value = "/applyEditContract", method = RequestMethod.POST)
    public String applyEdit(@ModelAttribute("editContract") Contract contract) throws Exception {
        contractService.update(contract);
        return "redirect:/";
    }

    @RequestMapping(value = "/editContract", method = RequestMethod.POST)
    public String Edit(@ModelAttribute("editContract")Contract editContract,@RequestParam("dateConclusion")Date dateConc,@RequestParam("startDate")Date dateStart,@RequestParam("endDate")Date endDate) throws Exception {
        editContract.setDateConclusion(dateConc);
        editContract.setStartDate(dateStart);
        editContract.setEndDate(endDate);
        return "editContract";
    }
}
