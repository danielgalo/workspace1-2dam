package utils;

import java.util.List;

/**
 * Interface used to work with files
 */
public interface FileReaderI {

	/**
	 * Obtains the number of columns of a row
	 * 
	 * @param rowNumber Optional. If null the row is by default the first one. Must
	 *                  be 0 or greater and less than the count of rows, if it's not
	 *                  the default row will be used.
	 * @return the column count
	 */
	public int getColumnCount(Integer rowNumber);

	/**
	 * Prints data from a list
	 */
	public void printAllData();

	/**
	 * Obtains a list with data with validation errors
	 * 
	 * @return error message list
	 */
	public List<String> getValidationErrors();

	/**
	 * Obtains a list with the correct data validated
	 * 
	 * @return validated data list
	 */
	public List<String> getValidationsCorrect();

}
