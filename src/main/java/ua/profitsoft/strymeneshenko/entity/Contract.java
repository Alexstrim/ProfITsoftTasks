package ua.profitsoft.strymeneshenko.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//Сущность договор
public class Contract {

	private long number;
	private Date dateConclusion;
	private Date startDate;
	private Date endDate;
	private Client client;
	private Set<InsuredPerson> persons = new HashSet<InsuredPerson>();

	public Contract() {
		number = 0;
		dateConclusion = new Date();
		startDate = this.dateConclusion;
		endDate = editDate(startDate, 12);
		client = new Individual();
		persons.add(new InsuredPerson());
	}

	public Contract(long number, Date dateConclusion, Date startDate, int monthDuration, Client client,
			Set<InsuredPerson> persons) {
		this.number = number;
		this.dateConclusion = dateConclusion;
		this.startDate = startDate;
		this.endDate = editDate(this.startDate, monthDuration);
		this.client = client;
		this.persons = persons;
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

	// Общая стоимость по договору
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
			sum = persons.stream().mapToInt(ip -> ip.getCost()).sum();
			break;
		default:
			break;
		}
		return sum;
	}
	
	//Проход по списку через итератор и цыкл while
	private int iteratorWithWhileLoop(int sum) {
		Iterator<InsuredPerson> iterator = persons.iterator();
		while(iterator.hasNext()){
			InsuredPerson ip = iterator.next();
			sum+= ip.getCost();
		}
		return sum;
	}
	
	//Проход по списку через итератор и цыкл for
	private int basicLoopWithIterator(int sum) {
		for(Iterator<InsuredPerson> iterator = persons.iterator(); iterator.hasNext(); ){
			InsuredPerson ip = iterator.next();
			sum+= ip.getCost();
		}
		return sum;
	}
	
	//Проход по списку через цикл foreach
	private int foreach(int sum) {
		for (InsuredPerson insuredPerson : persons) {
			sum += insuredPerson.getCost();
		}
		return sum;
	}
	
	//Проход по списку через цикл for
	private int basicLoop(int sum, List<InsuredPerson> personsArrayList) {
		for(int i = 0; i < persons.size(); i++){
			InsuredPerson ip = personsArrayList.get(i);
			sum+=ip.getCost();
		}
		return sum;
	}

	// Метод добавляет застрахованное лицо в договор
	public void addInsuredPerson(InsuredPerson ep) {
		persons.add(ep);
	}

	// Метод удаляет застрахованное лицо из договора
	public void removeInsuredPerson(InsuredPerson ep) {
		if (persons.contains(ep)) {
			persons.remove(ep);
		} else {
			System.out.println("Застрахованное ицо не принадлежит данному договру!");
		}
	}

	// Метод изменяет дату на кол-во месяцев(переданных в параметр month) вперед
	// или назад
	private Date editDate(Date date, int month) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		String d = sdf.format(date);
		try {
			calendar.setTime(sdf.parse(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.MONTH, month);
		d = sdf.format(calendar.getTime());
		try {
			date = sdf.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public void printPersonsInAlphaBeticalOrder(){
		TreeSet<InsuredPerson> personsTree = new TreeSet<>(new SortByName());
		personsTree.addAll(persons);
		for (InsuredPerson insuredPerson : personsTree) {
			System.out.print(insuredPerson);
		}
		System.out.println();
	}
	
	public void printPersonsByDateOfBirth(){
		TreeSet<InsuredPerson> personsTree = new TreeSet<>(new SortByDateOfBirth());
		personsTree.addAll(persons);
		for (InsuredPerson insuredPerson : personsTree) {
			System.out.print(insuredPerson);
		}
		System.out.println();
	}
	
	public InsuredPerson findPersonByIN(long id){
		for (InsuredPerson insuredPerson : persons) {
			if(insuredPerson.getIdentificationNumber() == id){
				return insuredPerson;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Contract [number=" + number + System.lineSeparator() + "dateConclusion=" + dateConclusion
				+ System.lineSeparator() + "startDate=" + startDate + System.lineSeparator() + "endDate=" + endDate
				+ System.lineSeparator() + "client=" + client + System.lineSeparator() + "persons=" + persons
				+ System.lineSeparator() + "]";
	}

}

class SortByName implements Comparator<InsuredPerson>{

	@Override
	public int compare(InsuredPerson o1, InsuredPerson o2) {
		int result = o1.getLastName().compareTo(o2.getLastName());
		if(result != 0){
			return result;
		}
		return o1.getFirstName().compareTo(o2.getLastName());
	}
	
}

class SortByDateOfBirth implements Comparator<InsuredPerson>{

	@Override
	public int compare(InsuredPerson o1, InsuredPerson o2) {
		return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
	}
	
}