package ua.profitsoft.strymeneshenko.data;

import org.apache.log4j.Logger;
import ua.profitsoft.strymeneshenko.dict.IterationType;
import ua.profitsoft.strymeneshenko.util.UtilDate;

import java.util.*;

//Entity Contract
public class Contract {

    private static final Logger LOGGER = Logger.getLogger(Contract.class);

    private long number;
    private Date dateConclusion;
    private Date startDate;
    private Date endDate;
    private Client client;
    private Set<InsuredPerson> persons = new HashSet<>();

    public Contract() {
    }

    public Contract(long number, Date dateConclusion, Date startDate, int monthDuration, Client client,
                    Set<InsuredPerson> persons) {
        this.number = number;
        this.dateConclusion = dateConclusion;
        this.startDate = startDate;
        this.endDate = UtilDate.editDate(this.startDate, monthDuration);
        this.client = client;
        this.persons = persons;
    }

    public Contract(long number, String dateConclusion, String startDate, String endDate, Client client, Set<InsuredPerson> persons) {
        this.number = number;
        this.dateConclusion = UtilDate.stringToDate(dateConclusion);
        this.startDate = UtilDate.stringToDate(startDate);
        this.endDate = UtilDate.stringToDate(endDate);
        this.client = client;
        this.persons = persons;
    }

    public Contract(String dateConclusion, String startDate, String endDate, Client client, Set<InsuredPerson> persons) {
        this.dateConclusion = UtilDate.stringToDate(dateConclusion);
        this.startDate = UtilDate.stringToDate(startDate);
        this.endDate = UtilDate.stringToDate(endDate);
        this.client = client;
        this.persons = persons;
    }

    // Total value of the contract

    public int allCost(IterationType it) {
        int sum = 0;
        List<InsuredPerson> personsArrayList = new ArrayList<>();
        personsArrayList.addAll(persons);

        switch (it) {
            case BASIC_LOOP:
                sum = basicLoop(sum, personsArrayList);
                break;
            case FOREACH:
                sum = foreach(sum);
                break;
            case BASIC_LOOP_WITH_ITERATOR:
                sum = basicLoopWithIterator(sum);
                break;
            case ITERATOR_WITH_WHILE_LOOP:
                sum = iteratorWithWhileLoop(sum);
                break;
            case LAMBDA_EXPRESSION:
                sum = persons.stream().mapToInt(InsuredPerson::getCost).sum();
                break;
            default:
                break;
        }
        return sum;
    }
    //Passing through the list through an iterator and while loop

    private int iteratorWithWhileLoop(int sum) {
        Iterator<InsuredPerson> iterator = persons.iterator();
        while (iterator.hasNext()) {
            InsuredPerson ip = iterator.next();
            sum += ip.getCost();
        }
        return sum;
    }
    //Pass through the list through an iterator or for loop

    private int basicLoopWithIterator(int sum) {
        for (Iterator<InsuredPerson> iterator = persons.iterator(); iterator.hasNext(); ) {
            InsuredPerson ip = iterator.next();
            sum += ip.getCost();
        }
        return sum;
    }
    //Pass through the list through the foreach loop

    private int foreach(int sum) {
        for (InsuredPerson insuredPerson : persons) {
            sum += insuredPerson.getCost();
        }
        return sum;
    }
    //List through a for loop

    private int basicLoop(int sum, List<InsuredPerson> personsArrayList) {
        for (int i = 0; i < persons.size(); i++) {
            InsuredPerson ip = personsArrayList.get(i);
            sum += ip.getCost();
        }
        return sum;
    }
    //The method adds the insured person to the contract

    public void addInsuredPerson(InsuredPerson ep) {
        persons.add(ep);
    }
    // The method removes the insured person from the contract

    public void removeInsuredPerson(InsuredPerson ep) {
        if (persons.contains(ep)) {
            persons.remove(ep);
        } else {
            LOGGER.info("The insured person does not belong to this contract!");
        }
    }

    public Set<InsuredPerson> sortPersonsInAlphaBeticalOrder() {
        Set<InsuredPerson> personsTree = new TreeSet<>(new SortByName());
        personsTree.addAll(persons);
        return personsTree;
    }

    public Set<InsuredPerson> sortPersonsByDateOfBirth() {
        Set<InsuredPerson> personsTree = new TreeSet<>(new SortByDateOfBirth());
        personsTree.addAll(persons);
        return personsTree;
    }

    public InsuredPerson findPersonByIN(long id) {
        for (InsuredPerson insuredPerson : persons) {
            if (insuredPerson.getIdentificationNumber() == id) {
                return insuredPerson;
            }
        }
        return null;
    }

    public InsuredPerson findPersonByIN(long id, Set<InsuredPerson> persons) {
        for (InsuredPerson insuredPerson : persons) {
            if (insuredPerson.getIdentificationNumber() == id) {
                return insuredPerson;
            }
        }
        return null;
    }

    class SortByName implements Comparator<InsuredPerson> {

        @Override
        public int compare(InsuredPerson o1, InsuredPerson o2) {
            int result = o1.getLastName().compareTo(o2.getLastName());
            if (result != 0) {
                return result;
            }
            return o1.getFirstName().compareTo(o2.getLastName());
        }

    }

    class SortByDateOfBirth implements Comparator<InsuredPerson> {

        @Override
        public int compare(InsuredPerson o1, InsuredPerson o2) {
            return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
        }

    }

    @Override
    public String toString() {
        return "Contract [number=" + number + System.lineSeparator() + "dateConclusion=" + dateConclusion
                + System.lineSeparator() + "startDate=" + startDate + System.lineSeparator() + "endDate=" + endDate
                + System.lineSeparator() + "client=" + client + System.lineSeparator() + "persons=" + persons
                + System.lineSeparator() + "]";
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getDateConclusion() {
        return dateConclusion;
    }

    public void setDateConclusion(Date dateConclusion) {
        this.dateConclusion = dateConclusion;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<InsuredPerson> getPersons() {
        return persons;
    }

    public void setPersons(Set<InsuredPerson> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Contract contract = (Contract) o;
        return number == contract.number &&
                Objects.equals(dateConclusion, contract.dateConclusion) &&
                Objects.equals(startDate, contract.startDate) &&
                Objects.equals(endDate, contract.endDate) &&
                Objects.equals(client, contract.client) &&
                Objects.equals(persons, contract.persons);
    }
    @Override
    public int hashCode() {
        return Objects.hash(number, dateConclusion, startDate, endDate, client, persons);
    }

}
