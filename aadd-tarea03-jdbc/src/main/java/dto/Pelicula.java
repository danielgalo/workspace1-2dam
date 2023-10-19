package dto;

public class Pelicula {

	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private int languageId;
	private int originalLanguageId;
	private int rentalDuration;
	private double rentalRate;
	private int lenght;
	private double decimalCost;
	private String rating;
	private String specialFeatures;
	private String lastUpdate;

	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param releaseYear
	 * @param languageId
	 * @param originalLanguageId
	 * @param rentalDuration
	 * @param rentalRate
	 * @param lenght
	 * @param decimalCost
	 * @param rating
	 * @param specialFeatures
	 * @param lastUpdate
	 */
	public Pelicula(int id, String title, String description, int releaseYear, int languageId, int originalLanguageId,
			int rentalDuration, double rentalRate, int lenght, double decimalCost, String rating,
			String specialFeatures, String lastUpdate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.originalLanguageId = originalLanguageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.lenght = lenght;
		this.decimalCost = decimalCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the releaseYear
	 */
	public int getReleaseYear() {
		return releaseYear;
	}

	/**
	 * @param releaseYear the releaseYear to set
	 */
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	/**
	 * @return the languageId
	 */
	public int getLanguageId() {
		return languageId;
	}

	/**
	 * @param languageId the languageId to set
	 */
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	/**
	 * @return the originalLanguageId
	 */
	public int getOriginalLanguageId() {
		return originalLanguageId;
	}

	/**
	 * @param originalLanguageId the originalLanguageId to set
	 */
	public void setOriginalLanguageId(int originalLanguageId) {
		this.originalLanguageId = originalLanguageId;
	}

	/**
	 * @return the rentalDuration
	 */
	public int getRentalDuration() {
		return rentalDuration;
	}

	/**
	 * @param rentalDuration the rentalDuration to set
	 */
	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	/**
	 * @return the rentalRate
	 */
	public double getRentalRate() {
		return rentalRate;
	}

	/**
	 * @param rentalRate the rentalRate to set
	 */
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	/**
	 * @return the lenght
	 */
	public int getLenght() {
		return lenght;
	}

	/**
	 * @param lenght the lenght to set
	 */
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	/**
	 * @return the decimalCost
	 */
	public double getDecimalCost() {
		return decimalCost;
	}

	/**
	 * @param decimalCost the decimalCost to set
	 */
	public void setDecimalCost(double decimalCost) {
		this.decimalCost = decimalCost;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the specialFeatures
	 */
	public String getSpecialFeatures() {
		return specialFeatures;
	}

	/**
	 * @param specialFeatures the specialFeatures to set
	 */
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	/**
	 * @return the lastUpdate
	 */
	public String getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
