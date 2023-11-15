package main;

import utils.CSVReaderImpl;
import utils.Validation;

public class ValidaCSVApp {

	public static void main(String[] args) {

		CSVReaderImpl csv = new CSVReaderImpl(args[0], false);

		System.out.println(Validation.validateURL("http://hola.es"));

	}

}
