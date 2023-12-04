package psp.unidad02.actividad205.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class that process information from a properties file. Use getters to obtain
 * the properties.
 */
public class PropertiesProcessor {

	/** Path to the properties file */
	private String propertiesfilePath;
	/** Input folder where the files will be putted in */
	private String inputFolder;
	/** Output file where de index file is gonna be created */
	private String outputFile;

	/**
	 * Constructor
	 * 
	 * @param propertiesfilePath path to the properties file
	 */
	public PropertiesProcessor(String propertiesfilePath) {
		this.propertiesfilePath = propertiesfilePath;
		// Obtain the properties
		setOutputFile(
				getProperty(PropertiesConstants.OUTPUT_FILE_PROPERTY_NAME, PropertiesConstants.OUTPUT_FILE_DEFAULT));
		setInputFolder(
				getProperty(PropertiesConstants.INPUT_FOLDER_PROPERTY_NAME, PropertiesConstants.INPUT_FOLDER_DEFAULT));

	}

	/**
	 * Obtains the proper outputfile property value.
	 * 
	 * If the properties file isn't accesible, or the property is not found or
	 * incorrect, set a default value.
	 * 
	 * @param property     name of the property
	 * @param defaultValue default value to assign in case of error
	 * 
	 * @return the output file value
	 * 
	 */
	private String getProperty(String property, String defaultValue) {

		Properties properties = new Properties();
		String propertyValue = null;

		try (FileInputStream fileInputStream = new FileInputStream(propertiesfilePath)) {
			// Load the properties
			properties.load(fileInputStream);
			// Get the property
			propertyValue = properties.getProperty(property);

			// If outputFileProperty is still null (property not found or incorrect)
			if (propertyValue == null || propertyValue.isBlank()) {
				// Set default value
				propertyValue = defaultValue;
			}

		} catch (IOException e) {
			e.printStackTrace();
			// If there was an error accessing the file, assign a default value
			propertyValue = defaultValue;
		}

		return propertyValue;

	}

	/**
	 * @return the propertiesfilePath
	 */
	public String getPropertiesfilePath() {
		return propertiesfilePath;
	}

	/**
	 * @param propertiesfilePath the propertiesfilePath to set
	 */
	public void setPropertiesfilePath(String propertiesfilePath) {
		this.propertiesfilePath = propertiesfilePath;
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
	 * @return the outputFile
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * @param outputFile the outputFile to set
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

}
