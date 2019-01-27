package ua.profitsoft.strymeneshenko.validate;

import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.data.Individual;
import ua.profitsoft.strymeneshenko.data.LegalEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClientValidate implements IValidate<Client> {
    @Override
    public Map<String, String> validate(Client entity) {
        Map<String,String> errors = new LinkedHashMap<>();
        if(entity.getAdress() == null || entity.getAdress().equals("")){
            errors.put("errorAddress", "Input the address, please!");
        }
        if(entity instanceof Individual){
            Individual individual = (Individual) entity;
            if(individual.getFirstName() == null || individual.getFirstName().equals("")){
                errors.put("errorFirstName", "Input the first name, please!");
            }
            if(individual.getLastName() == null || individual.getLastName().equals("")){
                errors.put("errorLastName", "Input the last name, please!");
            }
        }
        if(entity instanceof LegalEntity){
            LegalEntity legalEntity = (LegalEntity) entity;
            if(legalEntity.getNameOrganization() == null || legalEntity.getNameOrganization().equals("")){
                errors.put("errorNameOrganization", "Input the name organization, please!");
            }
        }
        return errors;
    }
}
