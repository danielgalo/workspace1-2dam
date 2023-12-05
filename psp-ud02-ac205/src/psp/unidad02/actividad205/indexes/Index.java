package psp.unidad02.actividad205.indexes;

/**
 * Representa al índice de una palabra
 */
public class Index {

	/** Palabra del índice */
	private String word;
	/** Posición de la línea */
	private int line;
	/** Posición de la palabra en la fila/línea */
	private int wordPosition;
	/** Nombre del archivo en el que aparece la palabra */
	private String fileName;

	/**
	 * @param word
	 * @param line
	 * @param wordPosition
	 * @param fileName
	 */
	public Index(String word, int line, int wordPosition, String fileName) {
		this.word = word;
		this.line = line;
		this.wordPosition = wordPosition;
		this.fileName = fileName;
	}

	public String getMsgTupla() {
		return "(" + word + "," + line + ", " + wordPosition + ", " + fileName + ")";
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the wordLine
	 */
	public int getLine() {
		return line;
	}

	/**
	 * @param wordLine the wordLine to set
	 */
	public void setLine(int wordLine) {
		this.line = wordLine;
	}

	/**
	 * @return the wordPosition
	 */
	public int getWordPosition() {
		return wordPosition;
	}

	/**
	 * @param wordPosition the wordPosition to set
	 */
	public void setWordPosition(int wordPosition) {
		this.wordPosition = wordPosition;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
