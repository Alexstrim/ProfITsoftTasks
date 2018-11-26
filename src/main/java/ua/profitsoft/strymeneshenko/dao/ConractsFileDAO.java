package ua.profitsoft.strymeneshenko.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import ua.profitsoft.strymeneshenko.entity.Contract;
import ua.profitsoft.strymeneshenko.entity.Individual;
import ua.profitsoft.strymeneshenko.entity.InsuredPerson;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConractsFileDAO implements IDaoFile<Contract> {

    private static final Logger LOGGER = Logger.getLogger(ConractsFileDAO.class);
    private static final String PATH_TO_FILE_INSURED_PERSON = "resources\\insured_persons.csv";
    private static final String PATH_TO_FILE_CONTRACTS = "resources\\contracts.csv";

    @Override
    public Contract read(long number) throws IOException {
        List<Contract> contracts = getContractsFromFile(PATH_TO_FILE_CONTRACTS, PATH_TO_FILE_INSURED_PERSON);
        for (Contract contract : contracts) {
            if (contract.getNumber() == number) {
                return contract;
            }
        }
        return null;
    }

    //The method of searching for insured persons from a separate file. Without using the Apache Commons CSV library.
    public static Set<InsuredPerson> getInsuredPersonsFromFile(File file) throws IOException {
        Set<InsuredPerson> persons = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //Skip the first line with the name of the fields.
        String line = reader.readLine();

        Scanner scanner = null;
        int index = 0;
        while ((line = reader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            InsuredPerson person = new InsuredPerson();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String rezult = scanner.next();
                if (index == 0) {
                    person.setIdentificationNumber(Long.parseLong(rezult));
                } else if (index == 1) {
                    person.setFirstName(rezult);
                } else if (index == 2) {
                    person.setLastName(rezult);
                } else if (index == 3) {
                    person.setDateOfBirth(stringToDate(rezult));
                } else if (index == 4) {
                    person.setCost(Integer.parseInt(rezult));
                } else {
                    LOGGER.info("Not valid data");
                }
                index++;
            }
            index = 0;
            persons.add(person);
        }
        return persons;
    }

    public static List<Contract> getContractsFromFile(String fileContract, String fileInsuredPerson) throws IOException {
        File filePesons = new File(fileInsuredPerson);
        File fileContracts = new File(fileContract);

        List<Contract> contracts = new ArrayList<>();
        Reader reader = new FileReader(fileContracts);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Number", "DateConclusion", "StartDate", "EndDate", "Client", "Persons").parse(reader);
        for (CSVRecord record : records) {
            Contract contract = new Contract();
            contract.setNumber(Long.parseLong(record.get("Number")));
            contract.setDateConclusion(stringToDate(record.get("DateConclusion")));
            contract.setStartDate(stringToDate(record.get("StartDate")));
            contract.setEndDate(stringToDate(record.get("EndDate")));

            Individual client = new Individual();
            try(Scanner scanner = new Scanner(record.get("Client"))) {
                scanner.useDelimiter(";");
                int index = 0;
                while (scanner.hasNext()) {
                    if (index == 0) {
                        client.setFirstName(scanner.next());
                    } else if (index == 1) {
                        client.setLastName(scanner.next());
                    } else if (index == 2) {
                        client.setAdress(scanner.next());
                    } else {
                        LOGGER.info("Not valid data");
                    }
                    index++;
                }
                contract.setClient(client);
            }

            //Container from the file of all insured persons
            Set<InsuredPerson> allPersons = getInsuredPersonsFromFile(filePesons);
            //Container of insured persons for current contract
            Set<InsuredPerson> personsForContract = new HashSet<>();
            try(Scanner scanner = new Scanner(record.get("Persons"))) {
                scanner.useDelimiter(";");
                while (scanner.hasNext()) {
                    long id = Long.parseLong(scanner.next());
                    //Find the insured person from the general TIN container and add to the current contract
                    //to form an entity
                    personsForContract.add(contract.findPersonByIN(id, allPersons));
                }
                contract.setPersons(personsForContract);
                contracts.add(contract);
            }
        }
        return contracts;
    }

    //Convert string to date
    public static Date stringToDate(String data) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("dd.MM.yyyy");
        try {
            date = sdf.parse(data);
        } catch (ParseException e) {
            LOGGER.error("Not valid date", e);
        }
        return date;
    }
}
