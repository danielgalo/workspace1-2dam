package utils;

public class FileUtils {

	private String filePath;

	/**
	 * 
	 * @param filePath
	 */
	public FileUtils(String filePath) {
		setFilePath(filePath);
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
