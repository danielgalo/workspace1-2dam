package utils;

/**
 * Interface used to work with files
 */
public interface FileReaderI {

	/**
	 * Retuns true if the new row is valid
	 * 
	 * @return true only if the new row is valid, false if there isn't a row
	 */
	public boolean next();

	/**
	 * Obtains the number of columns
	 * 
	 * @return the number of columns
	 */
	public int getColumnCount();

	/**
	 * Obtains the data from a column
	 * 
	 * @param column position
	 * @return the data of the column specified
	 */
	public String getData(int column);

}
