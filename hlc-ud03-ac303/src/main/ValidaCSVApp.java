package main;

import utils.CSVReaderImpl;

public class ValidaCSVApp {

	public static void main(String[] args) {

		CSVReaderImpl csv = new CSVReaderImpl(args[0], false);

		System.out.println(csv.getColumnCount());

	}

}
