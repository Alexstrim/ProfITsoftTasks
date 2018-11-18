package ua.profitsoft.strymeneshenko.filereader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ua.profitsoft.strymeneshenko.entity.Contract;
import ua.profitsoft.strymeneshenko.entity.Individual;
import ua.profitsoft.strymeneshenko.entity.InsuredPerson;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyReader {

    //Метод поиска застрахованных лиц из отдельного файла. Без использования библиотеки Apache Commons CSV.
    public static Set<InsuredPerson> getInsuredPersonsFromFile(File file) throws IOException {
        Set<InsuredPerson> persons = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //Пропускаем первую строку с наименованием полей
        String line = reader.readLine();

        Scanner scanner = null;
        int index = 0;
        while((line = reader.readLine()) != null){
            if(line.equals("")){
                continue;
            }
            InsuredPerson person = new InsuredPerson();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()){
                String rezult = scanner.next();
                if(index == 0){
                    person.setIdentificationNumber(Long.parseLong(rezult));
                }else if(index == 1){
                    person.setFirstName(rezult);
                }else if(index == 2){
                    person.setLastName(rezult);
                }else if(index == 3){
                    person.setDateOfBirth(stringToDate(rezult));
                }else if(index == 4){
                    person.setCost(Integer.parseInt(rezult));
                }else{
                    System.out.println("Not valid data");
                }
                index++;
            }
            index = 0;
            persons.add(person);
        }
        return persons;
    }

//Преобразование строки в дату
    public static Date stringToDate(String data){
        Date date = null;
        SimpleDateFormat sdf  = new SimpleDateFormat();
        sdf.applyPattern("dd.MM.yyyy");
        try {
            date = sdf.parse(data);
        } catch (ParseException e) {
            System.out.println("Not valid date");
            e.printStackTrace();
        }
        return date;
    }
//Поиск контрактов из файла. В нем хранятся только ИНН застрахованных лиц, по ним находим полные сущности.
//Метод демнострирует работу библиотеки Apache Commons CSV.
    public static List<Contract> getContractsFromFile(File fileContract, File fileInsuredPerson) throws IOException {

        List<Contract> contracts = new ArrayList<>();
        Reader reader = new FileReader(fileContract);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Number", "DateConclusion", "StartDate", "EndDate", "Client", "Persons").parse(reader);
        Scanner scanner = null;
        for (CSVRecord record: records) {
            Contract contract = new Contract();
            contract.setNumber(Long.parseLong(record.get("Number")));
            contract.setDateConclusion(stringToDate(record.get("DateConclusion")));
            contract.setStartDate(stringToDate(record.get("StartDate")));
            contract.setEndDate(stringToDate(record.get("EndDate")));

            Individual client = new Individual();
            scanner = new Scanner(record.get("Client"));
            scanner.useDelimiter(";");
            int index = 0;
            while(scanner.hasNext()){
                if(index == 0){
                    client.setFirstName(scanner.next());
                }else if(index == 1){
                    client.setLastName(scanner.next());
                }else if(index == 2){
                    client.setAdress(scanner.next());
                }else{
                    System.out.println("Not valid data");
                }
                index++;
            }
            contract.setClient(client);

            //Контейнер из файла всех застрахованных лиц
            Set<InsuredPerson> allPersons = getInsuredPersonsFromFile(fileInsuredPerson);
            //Контейнер застрахованных лиц для текущего контракта
            Set<InsuredPerson> personsForContract = new HashSet<>();
            scanner = new Scanner(record.get("Persons"));
            scanner.useDelimiter(";");
            while(scanner.hasNext()){
                long INN = Long.parseLong(scanner.next());
                //Находим застрахованное лицо из общего контейнера по ИНН и добававляем в текущий контракт
                //для формирования сущности
                personsForContract.add(contract.findPersonByIN(INN, allPersons));
            }
            contract.setPersons(personsForContract);
            contracts.add(contract);
        }
        return contracts;
    }
}
