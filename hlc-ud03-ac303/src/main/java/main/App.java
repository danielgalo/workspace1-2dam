package main;

import utils.CSVFileUtils;
import utils.ValidateCSV;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("Must provide a path for a CSV File.");
		} else {
			CSVFileUtils csvUtils = new CSVFileUtils(args[0], false);
			ValidateCSV validation = new ValidateCSV(csvUtils);
			validation.validateAll();
			validation.printValidation();
		}

	}

}