/**
 * 
 */
package utils;

import java.util.List;

/**
 * Contains all the methods to validate fields
 */
public interface DataValidationI {

	/**
	 * Validates a single field
	 * 
	 * @param validation
	 * @param field
	 * @param counter
	 */
	public void validateField(boolean validation, String field, int counter);

	/**
	 * Validates all fields, add errors to error list and add correct data to
	 * correct data list
	 */
	public void validateAll();

	/**
	 * Prints the all validation information
	 */
	public void printValidation();

	/**
	 * Obtain a list with error messages form incorrect fields
	 * 
	 * @return list with error messages
	 */
	public List<String> getErrors();

	/**
	 * Obtain a list with messages corresponding data that is ok
	 * 
	 * @return list with ok messages
	 */
	public List<String> getCorrectData();

}
