package ua.profitsoft.strymeneshenko.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.service.InsuredPersonService;
import ua.profitsoft.strymeneshenko.validate.IValidate;
import ua.profitsoft.strymeneshenko.validate.InsuredPersonValidate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class AddInsuredPersonController {

    @Autowired
    private InsuredPersonService insuredPersonService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    @RequestMapping(value = "/addInsuredPerson", method = RequestMethod.POST)
    public ModelAndView goEdit(@Validated @ModelAttribute InsuredPerson ip, BindingResult bindingResult) throws Exception {
       if(bindingResult.hasErrors()){
           return new ModelAndView("addNewInsuredPerson");
       }
        insuredPersonService.create(ip);
        return new ModelAndView("redirect:/");
    }
}
