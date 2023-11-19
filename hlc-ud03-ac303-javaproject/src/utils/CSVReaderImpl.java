package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderImpl implements FileReaderI {

	/** Separator used in CSV files */
	private static final String CSV_SEPARATOR = ",";

	/** Path of the CSV file */
	private String path;

	/** If the file has a header with the column type */
	private boolean hasHeader;

	List<String> errorData = new ArrayList<String>();

	List<String> correctData = new ArrayList<String>();

	/**
	 * Constructor
	 * 
	 * @param path
	 * @param hasHeader
	 */
	public CSVReaderImpl(String path, boolean hasHeader) {
		this.path = path;
		this.hasHeader = hasHeader;
	}

	@Override
	public int getColumnCount(Integer rowNumber) {

		List<String[]> data = getData();

		int count = 0;

		// If rowNumber is valid
		if (rowNumber != null && rowNumber >= 0 && rowNumber <= data.size()) {
			count = data.get(rowNumber - 1).length;
			// If it's null or not valid
		} else {
			count = data.get(0).length;
		}

		return count;

	}

	@Override
	public List<String> getValidationErrors() {
		return errorData;

	}

	@Override
	public List<String> getValidationsCorrect() {
		return correctData;
	}

	@Override
	public void printAllData() {

		List<String[]> data = getData();

		for (String[] strings : data) {
			for (int i = 0; i < strings.length; i++) {
				System.out.print(strings[i] + " | ");
			}
			System.out.println();
		}

	}

	public void validateAll() {

		List<String[]> data = getData();
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

	public void printValidation() {
		System.out.println("--CORRECT FIELDS--");
		printList(correctData);
		System.out.println("--INCORRECT FIELDS--");
		printList(errorData);
	}

	private void printList(List<String> list) {
		for (String string : list) {
			System.out.println(string);
		}
	}

	private void validateField(boolean fieldValidation, String field, int counter) {
		if (fieldValidation) {
			correctData.add("Row " + counter + "-> OK: " + field);
		} else {
			errorData.add("Row " + counter + "-> Incorrect: " + field);
		}
	}

	/**
	 * 
	 * @return
	 */
	private List<String[]> getData() {

		List<String[]> data = new ArrayList<String[]>();

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(path));

			// If it has header, skip it
			if (hasHeader) {
				reader.readLine();
			}

			String lineRead;

			// Read the file
			while ((lineRead = reader.readLine()) != null) {
				// Store data, splitting by [,]
				data.add(lineRead.split(CSV_SEPARATOR));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeReader(reader);
		}
		return data;
	}

	/**
	 * Closes reader's Buffer
	 * 
	 * @param reader
	 */
	private void closeReader(BufferedReader reader) {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
