package psp.unidad02.actividad205.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase encargada de procesar información de un archivo de propiedades
 */
public class PropertiesProcessor {

	/** Ruta al archivo de propiedades */
	private String propertiesfilePath;
	/** Propiedad de la carpeta de entrada */
	private String inputFolder;
	/** Propiedad de la carpeta de salida */
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
	 * Obtiene el valor de una propiedad del archivo de propiedades. Si el archivo
	 * no es accesible o las propiedades no son correctas, se usará un valor por
	 * defecto de cada propiedad erronea.
	 * 
	 * @param property     nombre de la propiedad
	 * @param defaultValue valor por defecto de la propiedad
	 * @return el valor de la propiedad
	 */
	private String getProperty(String property, String defaultValue) {

		Properties properties = new Properties();
		String propertyValue = null;

		try (FileInputStream fileInputStream = new FileInputStream(propertiesfilePath)) {
			// Conseguir la propiedad
			properties.load(fileInputStream);
			propertyValue = properties.getProperty(property);

			// Si el valor es nulo (la propiedad no es correcta) asignar valor por defecto
			if (propertyValue == null || propertyValue.isBlank()) {
				// Set default value
				propertyValue = defaultValue;
			}

		} catch (IOException e) {
			e.printStackTrace();
			// Si hubo un error accediendo al archivo, asignar el valor por defecto
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
