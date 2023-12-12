package models;

public class Pelicula {

	private int id;
	private String originalLanguage;
	private String original_title;
	private String overview;
	private double popularity;
	private String poster_path;
	private String release_date;

	/**
	 * 
	 */
	public Pelicula() {

	}

	/**
	 * @param id
	 * @param originalLanguage
	 * @param original_title
	 * @param overview
	 * @param popularity
	 * @param poster_path
	 * @param release_date
	 */
	public Pelicula(int id, String originalLanguage, String original_title, String overview, double popularity,
			String poster_path, String release_date) {
		this.id = id;
		this.originalLanguage = originalLanguage;
		this.original_title = original_title;
		this.overview = overview;
		this.popularity = popularity;
		this.poster_path = poster_path;
		this.release_date = release_date;
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
	 * @return the originalLanguage
	 */
	public String getOriginalLanguage() {
		return originalLanguage;
	}

	/**
	 * @param originalLanguage the originalLanguage to set
	 */
	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	/**
	 * @return the original_title
	 */
	public String getOriginal_title() {
		return original_title;
	}

	/**
	 * @param original_title the original_title to set
	 */
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	/**
	 * @return the overview
	 */
	public String getOverview() {
		return overview;
	}

	/**
	 * @param overview the overview to set
	 */
	public void setOverview(String overview) {
		this.overview = overview;
	}

	/**
	 * @return the popularity
	 */
	public double getPopularity() {
		return popularity;
	}

	/**
	 * @param popularity the popularity to set
	 */
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	/**
	 * @return the poster_path
	 */
	public String getPoster_path() {
		return poster_path;
	}

	/**
	 * @param poster_path the poster_path to set
	 */
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	/**
	 * @return the release_date
	 */
	public String getRelease_date() {
		return release_date;
	}

	/**
	 * @param release_date the release_date to set
	 */
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

}
