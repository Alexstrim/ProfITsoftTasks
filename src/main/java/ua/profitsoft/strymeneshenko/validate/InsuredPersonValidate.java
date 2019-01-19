package ua.profitsoft.strymeneshenko.validate;

import ua.profitsoft.strymeneshenko.data.InsuredPerson;

import java.util.LinkedHashMap;
import java.util.Map;

public class InsuredPersonValidate implements IValidate<InsuredPerson> {
    @Override
    public Map<String, String> validate(InsuredPerson entity) {
        Map<String, String> errors = new LinkedHashMap<>();
        if(entity.getFirstName() == null || entity.getFirstName().equals("")){
            errors.put("firstNameError", "Input the first name, please!");
        }
        if(entity.getLastName() == null || entity.getLastName().equals("")){
            errors.put("lastNameError", "Input the last name, please!");
        }
        if(entity.getDateOfBirth() == null || entity.getDateOfBirth().equals("")){
            errors.put("dateOfBirthError", "Input the date of Birth, please!");
        }
        if(entity.getCost() == 0){
            errors.put("costError", "Input the cost, please!");
        }
        if(entity.getIdentificationNumber() == 0){
            errors.put("identifyNumberError", "Input the identification number, please!");
        }
        return errors;
    }
}
