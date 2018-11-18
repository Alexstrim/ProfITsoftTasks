package ua.profitsoft.strymeneshenko;

import java.io.File;
import java.io.IOException;
import java.util.*;

import ua.profitsoft.strymeneshenko.entity.Contract;
import ua.profitsoft.strymeneshenko.filereader.MyReader;

public class Demo {
	private static final String PATH_TO_FILE_INSURED_PERSON = "resources\\insured_persons.csv";
	private static final String PATH_TO_FILE_CONTRACTS = "resources\\contracts.csv";

	public static void main(String[] args) {

		//Чтение данных из файла
		System.out.println("==================Чтение данных из файла==================");
		File filePesons = new File(PATH_TO_FILE_INSURED_PERSON);
		File fileContracts = new File(PATH_TO_FILE_CONTRACTS);

		List<Contract> contracts = new ArrayList<>();
		try {
			contracts = MyReader.getContractsFromFile(fileContracts,filePesons);
		} catch (IOException e) {
			e.printStackTrace();
		}

		contracts.forEach(System.out::println);
		System.out.println("===========================================================");
	}

}
