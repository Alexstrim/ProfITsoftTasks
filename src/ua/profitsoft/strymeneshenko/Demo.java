package ua.profitsoft.strymeneshenko;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ua.profitsoft.strymeneshenko.entity.Client;
import ua.profitsoft.strymeneshenko.entity.Contract;
import ua.profitsoft.strymeneshenko.entity.Individual;
import ua.profitsoft.strymeneshenko.entity.InsuredPerson;

public class Demo {

	public static void main(String[] args) {

		Date date = new Date();

		Client client = new Individual("Alex", "Edison", "Lubotin, Popova street 7");

		InsuredPerson ip1 = new InsuredPerson();
		InsuredPerson ip2 = new InsuredPerson("Dima", "Pypkin", "14.14.2000", 700);
		InsuredPerson ip3 = new InsuredPerson("Yaroslav", "Zhykov", "14.14.1999", 800);
		InsuredPerson ip4 = new InsuredPerson("Roman", "Zayarniy", "14.14.2001", 900);
		InsuredPerson ip5 = new InsuredPerson("Alexandr", "Sergeev", "14.14.1978", 6000);

		Set<InsuredPerson> persons = new HashSet<>();
		persons.add(ip1);
		persons.add(ip2);
		persons.add(ip3);
		persons.add(ip4);
		persons.add(ip5);

		Contract contract = new Contract(12345678, date, date, 24, client, persons);
		System.out.println(contract);
		System.out.println("Общая стоимость страховки по договору: " + contract.allCost());
	}

}
