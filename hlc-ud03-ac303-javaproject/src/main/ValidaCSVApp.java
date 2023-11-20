package main;

import utils.CSVReaderImpl;

public class ValidaCSVApp {

	public static void main(String[] args) {

		CSVReaderImpl csv = new CSVReaderImpl(args[0], false);
		// Validar todos los datos
		csv.validateAll();
		// Imprimir resultados de validación
		csv.printValidation();

	}

}
