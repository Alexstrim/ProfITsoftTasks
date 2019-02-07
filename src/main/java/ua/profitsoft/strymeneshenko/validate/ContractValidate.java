package ua.profitsoft.strymeneshenko.validate;

import ua.profitsoft.strymeneshenko.data.Contract;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContractValidate implements IValidate<Contract> {

    @Override
    public Map<String, String> validate(Contract entity) {
        Map<String, String> errors = new LinkedHashMap<>();
        if(entity.getDateConclusion() == null || entity.getDateConclusion().equals("")){
            errors.put("dateConclusion", "Input the date conclusion, please!");
        }
        if(entity.getStartDate() == null || entity.getStartDate().equals("")){
            errors.put("startDate", "Input the start date, please!");
        }
        if(entity.getEndDate() == null || entity.getEndDate().equals("")){
            errors.put("endDate", "Input the end date, please!");
        }
        return errors;
    }
}
