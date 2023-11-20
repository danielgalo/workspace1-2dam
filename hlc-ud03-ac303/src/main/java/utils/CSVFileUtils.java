package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVFileUtils {

	private String path;
	private boolean hasHeader;
	private CSVParser csvParser;
	BufferedReader reader;

	/**
	 * Constructor
	 * 
	 * @param path
	 * @param hasHeader
	 */
	public CSVFileUtils(String path, boolean hasHeader) {
		setPath(path);
		this.hasHeader = hasHeader;

		// Initialize buffers and parsers
		initializeBuffer();
		initializeCSVParser(path);

	}

	public List<String[]> getDataList() {

		List<String[]> rows = new ArrayList<>();

		// If it has header, skip it
		skipHeader();

		// Iterate trough fields
		for (CSVRecord csvRecord : csvParser) {
			// Obtain column values as an array
			String[] values = new String[csvRecord.size()];
			for (int i = 0; i < csvRecord.size(); i++) {
				values[i] = csvRecord.get(i);
			}
			// Add value
			rows.add(values);
		}

		closeCSVParser();
		closeReader();

		return rows;
	}

	/**
	 * Skips the header line if it has it
	 */
	private void skipHeader() {
		if (hasHeader) {
			try {
				reader.readLine();
			} catch (IOException e) {
				System.err.println("Error skipping header.");
			}
		}
	}

	/**
	 * 
	 */
	private void closeCSVParser() {
		try {
			if (csvParser != null) {
				csvParser.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void closeReader() {
		try {
			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the CSV parser
	 * 
	 * @param path
	 */
	private void initializeCSVParser(String path) {
		try {
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
		} catch (IOException e) {
			System.err.println("Couldn't find file " + path);
		}
	}

	/**
	 * Initializes the buffer
	 */
	private void initializeBuffer() {
		try {
			reader = new BufferedReader(new FileReader(getPath()));
		} catch (FileNotFoundException e) {
			System.err.println("File not accessible or doesnt exists " + path);
		}
	}

	/**
	 * Validates if a file has the csv extension
	 * 
	 * @param file
	 * @return true if the file has the csv extension
	 */
	private static boolean isFicheroCSV(String file) {

		File archivo = new File(file);

		// Comprobar si el archivo existe y si es un archivo
		if (archivo.exists() && archivo.isFile()) {
			return archivo.getName().toLowerCase().endsWith(".csv");
		}

		return false;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		if (path != null && !path.isBlank() && isFicheroCSV(path)) {
			this.path = path;
		}
	}

	/**
	 * @return the hasHeader
	 */
	public boolean isHasHeader() {
		return hasHeader;
	}

	/**
	 * @param hasHeader the hasHeader to set
	 */
	public void setHasHeader(boolean hasHeader) {
		this.hasHeader = hasHeader;
	}

	/**
	 * @return the csvParser
	 */
	public CSVParser getCsvParser() {
		return csvParser;
	}

	/**
	 * @param csvParser the csvParser to set
	 */
	public void setCsvParser(CSVParser csvParser) {
		this.csvParser = csvParser;
	}

}
