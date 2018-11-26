package ua.profitsoft.strymeneshenko;

import java.io.File;
import java.io.IOException;

import ua.profitsoft.strymeneshenko.dao.ConractsFileDAO;
import ua.profitsoft.strymeneshenko.dao.IDaoFile;
import ua.profitsoft.strymeneshenko.entity.Contract;
import ua.profitsoft.strymeneshenko.service.ContractServiceFile;
import ua.profitsoft.strymeneshenko.service.IServiceFile;

public class Demo {
    public static void main(String[] args) {

        //Reading data from a file
        System.out.println("==================Чтение данных из файла==================");
        IDaoFile file = new ConractsFileDAO();
        try {
            Contract contract = (Contract) file.read(123334L);
            System.out.println(contract);
        } catch (IOException e) {
            e.printStackTrace();
        }
        IServiceFile<Contract> con = new ContractServiceFile();
        try {
            Contract contract1 = (Contract) con.read(123333L);
            System.out.println(contract1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
