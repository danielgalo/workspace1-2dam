package psp.unidad02.actividad205.threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WorkerThread extends Thread {

	private static final String REGEX_WORD = "^[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ]+$";
	private static List<String[]> words = new ArrayList<>();

	/** Path to the txt file */
	private String filePath;

	/**
	 * Constructor
	 * 
	 * @param filePath
	 */
	public WorkerThread(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(filePath));

			String line;
			int lineCounter = 0;
			while ((line = reader.readLine()) != null) {
				words.add(line.split(" "));
				lineCounter++;
			}

			// For each array in the words list
			for (String[] strings : words) {
				// For each word in the array
				for (String string : strings) {
					// If the word is a proper word
					if (Pattern.matches(REGEX_WORD, string)) {
						// Add it to the index
						// TODO Funciona, palabras que vayan seguidas o antecedidas de tabuladores no
						// las pilla
						System.out.println(string);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
