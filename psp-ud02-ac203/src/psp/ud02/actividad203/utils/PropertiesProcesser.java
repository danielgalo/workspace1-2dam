package psp.ud02.actividad203.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * 
 */
public class PropertiesProcesser {

	/** Input folder by default (project workspace) */
	private static final String DEFAULT_INPUT_FOLDER = "/";
	/** Output folder by default (project workspace) */
	private static final String DEFAULT_OUTPUT_FOLDER = "/output/";
	/** Maximun image width by default */
	private static final int DEFAULT_MAX_WIDTH = 100;
	/** Minimun image width by default */
	private static final int DEFAULT_MIN_WIDTH = 100;

	/** Input folder property name */
	private static final String PROPERTY_INPUT_FOLDER = "inputfolder";
	/** Input folder property name */
	private static final String PROPERTY_OUTPUT_FOLDER = "outputfolder";
	/** Maximun image width property name */
	private static final String PROPERTY_MAX_WIDTH = "maxwidth";
	/** Minimun image width property name */
	private static final String PROPERTY_MIN_WIDTH = "minwidth";

	/** Path of the configuration file */
	private String path;

	/**
	 * Constructor
	 * 
	 * @param path
	 */
	public PropertiesProcesser(String path) {
		this.path = path;
	}

	/**
	 * Obtains the input folder path specified in the configuration file if it's
	 * valid. If it's not valid return the default value.
	 * 
	 * @return the intput folder path
	 */
	public String getInputFolder() {

		return getFolder(PROPERTY_INPUT_FOLDER, DEFAULT_INPUT_FOLDER);

	}

	/**
	 * 
	 * @return
	 */
	public int getMaxWidth() {
		return getImageScale(PROPERTY_MAX_WIDTH, DEFAULT_MAX_WIDTH);
	}

	/**
	 * Obtains either the width or the heigth of an image if it's valid and the
	 * configuration file is accessible. If not obtains it's default value
	 * 
	 * @param property     name of the property
	 * @param defaultValue default value of the property. It's also the maximun
	 *                     value it can be
	 * @return
	 */
	private int getImageScale(String property, int defaultValue) {

		Integer scale = 0;
		// Properties instance
		Properties properties = new Properties();
		// Get the input stream
		FileInputStream input = null;

		try {
			input = new FileInputStream(path);
			// Load the properties
			properties.load(input);

			scale = Integer.parseInt(properties.getProperty(property));

			if (scale == null || scale > defaultValue) {
				scale = defaultValue;
			}

		} catch (IOException e) {
			// If there was a problem accessing the configuration file, assing the default
			// value too
			scale = defaultValue;

		}

		// Close the input stream
		closeInput(input);

		return scale;
	}

	/**
	 * Obtains the folder specified in a configuration file if it's valid and exists
	 * 
	 * @param property     name of the property in the file
	 * @param defaultValue default value used if the property isnt found or the
	 *                     configuration file isn't accessible
	 * @return the path to the folder
	 */
	private String getFolder(String property, String defaultValue) {

		String folder = null;
		// Properties instance
		Properties properties = new Properties();
		// Get the input stream
		FileInputStream input = null;

		try {
			input = new FileInputStream(path);
			// Load the properties
			properties.load(input);

			// Assing the property
			folder = properties.getProperty(property);

			// If the property exists and it's an existing folder
			if (folder == null || !Files.isDirectory(Path.of(folder))) {
				folder = defaultValue;
			}

		} catch (IOException e) {
			// If there was a problem accessing the configuration file, assing the default
			// value too
			folder = defaultValue;

		}

		// Close the input stream
		closeInput(input);

		return folder;

	}

	/**
	 * @param input
	 */
	private void closeInput(FileInputStream input) {
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

}
