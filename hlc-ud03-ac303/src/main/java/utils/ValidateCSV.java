package utils;

import java.util.ArrayList;
import java.util.List;

public class ValidateCSV implements DataValidationI {

	private CSVFileUtils util;

	private List<String> errors = new ArrayList<String>();
	private List<String> okData = new ArrayList<String>();

	/**
	 * 
	 * @param util
	 */
	public ValidateCSV(CSVFileUtils util) {
		this.util = util;
	}

	@Override
	public void validateField(boolean validation, String field, int counter) {
		if (validation) {
			okData.add("Row " + counter + "-> OK: " + field);
		} else {
			errors.add("Row " + counter + "-> Incorrect: " + field);
		}
	}

	@Override
	public void validateAll() {

		List<String[]> data = util.getDataList();
		int counter = 1;

		for (String[] row : data) {

			String title = row[0];
			String name = row[1];
			String surname = row[2];
			String phoneNumber = row[3];
			String cp = row[4];
			String email = row[5];
			String url = row[6];
			String userName = row[7];
			String password = row[8];
			String registerDate = row[9];

			validateField(Validation.validateTitle(title), title, counter);
			validateField(Validation.validateName(name), name, counter);
			validateField(Validation.validateSurNames(surname), surname, counter);
			validateField(Validation.validatePhoneNumber(phoneNumber), phoneNumber, counter);
			validateField(Validation.validateCP(cp), cp, counter);
			validateField(Validation.validateEmail(email), email, counter);
			validateField(Validation.validateURL(url), url, counter);
			validateField(Validation.validateUserName(userName), userName, counter);
			validateField(Validation.validatePassword(password), password, counter);
			validateField(Validation.validateRegisterDate(registerDate), registerDate, counter);
			counter++;
		}
	}

	@Override
	public void printValidation() {

		if (okData.size() > 0) {
			System.out.println("--- CORRECT DATA ---");
			printList(okData);
		} else {
			System.out.println("--- No correct data found ---");
		}
		
		if (errors.size() > 0) {
			System.out.println("--- INCORRECT DATA ---");
			printList(errors);
		} else {
			System.out.println("--- No errors found ---");
		}

	}

	@Override
	public List<String> getErrors() {
		return errors;
	}

	@Override
	public List<String> getCorrectData() {
		return okData;
	}

	private void printList(List<String> list) {
		for (String string : list) {
			System.out.println(string);
		}
	}

}
