package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	public boolean next() {
		return false;
	}

	@Override
	public int getColumnCount() {

		int coulumnCount = 0;

		BufferedReader reader = null;

		// Read the first line, see how many columns it has by splitting it
		try {

			reader = new BufferedReader(new FileReader(path));
			String[] line = reader.readLine().split(CSV_SEPARATOR);
			coulumnCount = line.length;

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			closeConnection(reader);

		}

		return coulumnCount;
	}

	/**
	 * @param reader
	 */
	private void closeConnection(BufferedReader reader) {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getData(int column) {
		// TODO Auto-generated method stub
		return null;
	}

}