package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.service.ContractService;
import ua.profitsoft.strymeneshenko.service.InsuredPersonService;
import ua.profitsoft.strymeneshenko.validate.IValidate;
import ua.profitsoft.strymeneshenko.validate.InsuredPersonValidate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@SessionAttributes(types = InsuredPerson.class)
public class AddInsuredPersonController {

    @Autowired
    private InsuredPersonService insuredPersonService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    @RequestMapping(value = "/addInsuredPerson", method = RequestMethod.POST)
    public String goEdit(@RequestParam(value = "firstName") String firstName,
                         @RequestParam(value = "lastName") String lastName,
                         @RequestParam(value = "date") Date date,
                         @RequestParam(value = "cost") Integer cost,
                         @RequestParam(value = "identificationNumber") Long identificationNumber,
                         ModelMap model) throws Exception {
        InsuredPerson ip = new InsuredPerson();
        ip.setFirstName(firstName);
        ip.setLastName(lastName);
        ip.setDateOfBirth(date);
        if(cost == null){
            ip.setCost(0);
        }else{
            ip.setCost(cost);
        }
        if(identificationNumber == null){
            ip.setIdentificationNumber(0L);
        }else{
            ip.setIdentificationNumber(identificationNumber);
        }


        IValidate<InsuredPerson> insuredPersonIValidate = new InsuredPersonValidate();
        Map<String,String> errors = insuredPersonIValidate.validate(ip);
        if(errors.size() == 0){
            insuredPersonService.create(ip);
            return "redirect:/";
        }else{
            model.addAttribute("errors", errors);
            return "addNewInsuredPerson";
        }
    }
}
