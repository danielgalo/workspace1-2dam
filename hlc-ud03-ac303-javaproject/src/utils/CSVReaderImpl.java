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

		List<String> errorList = new ArrayList<String>();
		return errorList;

	}

	@Override
	public List<String> getValidationsCorrect() {
		// TODO Auto-generated method stub
		return null;
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

	private void validate() {

		List<String[]> data = getData();

		for (String[] strings : data) {

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
