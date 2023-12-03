package psp.ud02.actividad203.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import psp.ud02.actividad203.constants.PropertiesConstants;

/**
 * 
 */
public class PropertiesProcesser {

	/** Path of the configuration file */
	private String path;
	/** File input */
	private FileInputStream inputStream;
	/** Properties object */
	private Properties properties;

	/** Output folder */
	private String outputFolder;
	/** Input Folder */
	private String inputFolder;
	/** Max width */
	private Integer maxWidth;
	/** Max heigth */
	private Integer maxHeigth;

	/**
	 * Constructor
	 * 
	 * @param path
	 */
	public PropertiesProcesser(String path) {
		// Assign the file path
		this.path = path;

		// Initialize properties and the file input
		initializeInputStream(path);
		initializeProperties();

		if (properties != null) {
			// Obtain max width and heigth
			setMaxWidth(getImgScale(false));
			setMaxHeigth(getImgScale(true));
			// Obtain input and output folders
			setOutputFolder(getFolder(true));
			setInputFolder(getFolder(false));
		}
	}

	/**
	 * Obtains the path of a folder
	 * 
	 * @param output
	 * @return the path to the output folder if "output" is true, if false return
	 *         the input folder path
	 */
	private String getFolder(boolean output) {

		String folder = "";
		String propertyFolder;
		String defaultValue;

		// Obtain the output folder or the input folder
		if (output) {

			propertyFolder = PropertiesConstants.PROPERTY_OUTPUT_FOLDER;
			defaultValue = PropertiesConstants.DEFAULT_OUTPUT_FOLDER;

		} else {

			propertyFolder = PropertiesConstants.PROPERTY_INPUT_FOLDER;
			defaultValue = PropertiesConstants.DEFAULT_INPUT_FOLDER;

		}

		// Get the value
		folder = properties.getProperty(propertyFolder);

		if (folder != null) {
			// If folder is not null (property name was correct)
			return folder;
		} else {
			// If folder is null (property wasn't found)
			return defaultValue;
		}

	}

	/**
	 * Obtains either the width or the heigth specified in the properties file. If
	 * the property name is not valid, a default value is assigned.
	 * 
	 * @param heigth
	 * @return the heigth property if "height" is true, if false return the width
	 */
	private int getImgScale(boolean heigth) {

		String imgProperty;
		int defaultValue = 0;
		Integer value;

		try {

			// Obtain the height or the width
			if (heigth) {

				imgProperty = PropertiesConstants.PROPERTY_MAX_HEIGTH;
				defaultValue = PropertiesConstants.DEFAULT_MAX_HEIGTH;

			} else {

				imgProperty = PropertiesConstants.PROPERTY_MAX_WIDTH;
				defaultValue = PropertiesConstants.DEFAULT_MAX_WIDTH;

			}

			value = Integer.parseInt(properties.getProperty(imgProperty));

		} catch (NumberFormatException | NullPointerException e) {

			// If the value is null (the property name is not correct), assign the default
			// value
			value = defaultValue;

		}

		return value;
	}

	/**
	 * Initialize and load properties
	 */
	private void initializeProperties() {

		if (inputStream != null) {
			// Initialize properties
			properties = new Properties();

			try {
				// Load properties
				properties.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initializes the input stream of the properties file
	 * 
	 * @param path
	 */
	private void initializeInputStream(String path) {

		try {

			inputStream = new FileInputStream(path);

		} catch (FileNotFoundException e) {

			// If the file is not accesible, assign default values
			setMaxHeigth(PropertiesConstants.DEFAULT_MAX_HEIGTH);
			setMaxWidth(PropertiesConstants.DEFAULT_MAX_WIDTH);
			setOutputFolder(PropertiesConstants.DEFAULT_OUTPUT_FOLDER);
			setInputFolder(PropertiesConstants.DEFAULT_INPUT_FOLDER);

		}
	}

	/**
	 * @return the inputStream
	 */
	public FileInputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(FileInputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the inputFolder
	 */
	public String getInputFolder() {
		return inputFolder;
	}

	/**
	 * @param inputFolder the inputFolder to set
	 */
	public void setInputFolder(String inputFolder) {
		this.inputFolder = inputFolder;
	}

	/**
	 * @return the maxWidth
	 */
	public Integer getMaxWidth() {
		return maxWidth;
	}

	/**
	 * @param maxWidth the maxWidth to set
	 */
	public void setMaxWidth(Integer maxWidth) {
		this.maxWidth = maxWidth;
	}

	/**
	 * @return the maxHeigth
	 */
	public Integer getMaxHeigth() {
		return maxHeigth;
	}

	/**
	 * @param maxHeigth the maxHeigth to set
	 */
	public void setMaxHeigth(Integer maxHeigth) {
		this.maxHeigth = maxHeigth;
	}

	/**
	 * @param input
	 */
	public void closeInput(FileInputStream input) {
		try {
			if (input != null) {
				input.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		this.path = path;
	}

	public String getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}

}
