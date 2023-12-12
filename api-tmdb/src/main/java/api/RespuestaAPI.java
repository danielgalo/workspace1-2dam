package api;

import models.Pelicula;

public class RespuestaAPI {

	private int page;
	private Pelicula[] results;
	private int totalPages;
	private int totalResults;

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the results
	 */
	public Pelicula[] getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(Pelicula[] results) {
		this.results = results;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the totalResults
	 */
	public int getTotalResults() {
		return totalResults;
	}

	/**
	 * @param totalResults the totalResults to set
	 */
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

}
