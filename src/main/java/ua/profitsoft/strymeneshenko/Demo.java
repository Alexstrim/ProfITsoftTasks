package ua.profitsoft.strymeneshenko;

import ua.profitsoft.strymeneshenko.data.*;
import ua.profitsoft.strymeneshenko.db.dao.ConractsFileDAO;
import ua.profitsoft.strymeneshenko.db.dao.IDao;
import ua.profitsoft.strymeneshenko.service.*;
import ua.profitsoft.strymeneshenko.util.UtilDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        Client c1 = new Individual("Anatoliy", "Petrov", "Kiev, Adb st. 123");
        InsuredPerson ip1 = new InsuredPerson("Dmitriy", "Pypkin", "11.08.1987", 1423, 94572439L);
        InsuredPerson ip2 = new InsuredPerson("Foma", "Nevernuy", "22.09.1988", 2222, 94572229L);
        Set<InsuredPerson> persons = new HashSet<InsuredPerson>();
        persons.add(ip1);
        persons.add(ip2);

        Contract contract = new Contract("03.11.2018", "03.11.2018", "03.11.2019", c1, persons);
        contract.setNumber(10L);

        IService<Client> clientService = new ClientService();
        IService<InsuredPerson> insuredPersonService = new InsuredPersonService();
        IService<Contract> contractService = new ContractService();

        try {
            clientService.create(c1);
            insuredPersonService.create(ip1);
            insuredPersonService.create(ip2);
            contractService.create(contract);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
