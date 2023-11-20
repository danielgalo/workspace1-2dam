package main;

import utils.CSVFileUtils;
import utils.ValidateCSV;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {

		CSVFileUtils csvUtils = new CSVFileUtils(args[0], false);
		csvUtils.getDataList();

		ValidateCSV validation = new ValidateCSV(csvUtils);

		validation.printValidation();

	}

}