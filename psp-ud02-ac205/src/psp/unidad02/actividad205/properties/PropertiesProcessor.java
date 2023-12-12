package psp.unidad02.actividad205.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import psp.unidad02.actividad205.loggers.Logger;

/**
 * Clase encargada de procesar información de un archivo de propiedades
 */
public class PropertiesProcessor {

	/** Nombre de la clase para usar en logs */
	private static final String CLASS_NAME = PropertiesProcessor.class.getName();

	/** Ruta al archivo de propiedades */
	private String propertiesFilePath;
	/** Propiedad de la carpeta de entrada */
	private String inputFolder;
	/** Propiedad de la carpeta de salida */
	private String outputFile;

	/**
	 * Constructor, inicia las propiedades del archivo.
	 * 
	 * @param propertiesfilePath path to the properties file
	 */
	public PropertiesProcessor(String propertiesFilePath) {
		this.propertiesFilePath = propertiesFilePath;
		// Obtener las propiedades
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

		Logger.info("Procesando propiedades del archivo " + propertiesFilePath, CLASS_NAME);

		try (FileInputStream fileInputStream = new FileInputStream(propertiesFilePath)) {
			// Conseguir la propiedad
			properties.load(fileInputStream);
			propertyValue = properties.getProperty(property);

			// Si el valor es nulo (la propiedad no es correcta) asignar valor por defecto
			if (propertyValue == null || propertyValue.isBlank()) {
				Logger.info("La propiedad \"" + property
						+ "\" no existe, es null o su valor no es válido. Se le asignará el valor por defecto: \""
						+ defaultValue + "\".", CLASS_NAME);
				// Set default value
				propertyValue = defaultValue;
			}

			Logger.info("Valor de la propiedad \"" + property + "\" es: \"" + propertyValue + "\".", CLASS_NAME);

		} catch (IOException e) {
			Logger.problem("Hubo un problema accediendo al archivo de propiedades: \"" + propertiesFilePath
					+ "\". Asignando valor por defecto...", CLASS_NAME);
			// Si hubo un error accediendo al archivo, asignar el valor por defecto
			propertyValue = defaultValue;
		}

		return propertyValue;

	}

	/**
	 * @return the propertiesfilePath
	 */
	public String getPropertiesfilePath() {
		return propertiesFilePath;
	}

	/**
	 * @param propertiesfilePath the propertiesfilePath to set
	 */
	public void setPropertiesfilePath(String propertiesfilePath) {
		this.propertiesFilePath = propertiesfilePath;
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
