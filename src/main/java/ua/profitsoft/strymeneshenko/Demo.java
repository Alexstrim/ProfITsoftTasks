package ua.profitsoft.strymeneshenko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.profitsoft.strymeneshenko.data.*;
import ua.profitsoft.strymeneshenko.service.*;

//In order to add a contract to the database,
//the client and the insured person of which we want to add to the contract must be registered in it.
@EnableTransactionManagement
public class Demo {
    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring-context.xml");

        Client client = context.getBean("individual-1", Individual.class);
        IService<Client> clientService = context.getBean(ClientService.class);

        InsuredPerson ip1 = context.getBean("insured-person-1", InsuredPerson.class);
        InsuredPerson ip2 = context.getBean("insured-person-2", InsuredPerson.class);
        IService<InsuredPerson> insuredPersonService = context.getBean(InsuredPersonService.class);

        Contract contract = context.getBean("contract-1", Contract.class);
        IService<Contract> contractService = context.getBean(ContractService.class);

        try {
            clientService.create(client);
            insuredPersonService.create(ip1);
            insuredPersonService.create(ip2);
            contractService.create(contract);
            System.out.println(contractService.read(contract.getNumber()));
            contractService.delete(contract.getNumber());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
