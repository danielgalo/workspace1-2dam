package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.Pelicula;

/**
 * Clase para manejar la base de datos sakila
 */
public class SakilaManagement {

	private static final String SELECT_ALL_FILMS_ACTOR = "SELECT film.*  FROM film";
	private static final String INSERT_PELICULA = "INSERT INTO film "
			+ "(film_id, title, description, release_year, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features, last_update)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_PELICULA = "UPDATE film SET title = ?, description = ?,"
			+ " release_year = ?, language_id = ?, original_language_id = ?, rental_duration = ?, rental_rate = ?, "
			+ "length = ?,replacement_cost = ?,rating = ?, special_features = ?, last_update = ? WHERE film_id = ?";

	private ResultSet resultSetPeliculas;
	private Connection con;

	/**
	 * Constructor
	 */
	public SakilaManagement() {
		con = Conexion.conectar();
	}

	/**
	 * Actualiza la pelicula
	 * 
	 * @param pelicula
	 */
	public void updatePelicula(Pelicula pelicula) {

		try {
			PreparedStatement sentencia = con.prepareStatement(UPDATE_PELICULA);
			sentencia.setInt(13, pelicula.getId());
			sentencia.setString(1, "'" + pelicula.getTitle() + "'");
			sentencia.setString(2, "'" + pelicula.getDescription() + "'");
			sentencia.setInt(3, pelicula.getReleaseYear());
			sentencia.setInt(4, pelicula.getLanguageId());
			sentencia.setInt(5, pelicula.getOriginalLanguageId());
			sentencia.setInt(6, pelicula.getRentalDuration());
			sentencia.setDouble(7, pelicula.getRentalRate());
			sentencia.setInt(8, pelicula.getLenght());
			sentencia.setDouble(9, pelicula.getDecimalCost());
			sentencia.setString(10, "'" + pelicula.getRating() + "'");
			sentencia.setString(11, "'" + pelicula.getSpecialFeatures() + "'");
			sentencia.setString(12, "'" + pelicula.getLastUpdate() + "'");

			sentencia.executeUpdate();

			System.out.println("UPDATE OK");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserta una pelicula en la tabla film
	 * 
	 */
	public void insertPelicula(Pelicula pelicula) {

		try {
			PreparedStatement sentencia = con.prepareStatement(INSERT_PELICULA);
			sentencia.setInt(1, pelicula.getId());
			sentencia.setString(2, "'" + pelicula.getTitle() + "'");
			sentencia.setString(3, "'" + pelicula.getDescription() + "'");
			sentencia.setInt(4, pelicula.getReleaseYear());
			sentencia.setInt(5, pelicula.getLanguageId());
			sentencia.setInt(6, pelicula.getOriginalLanguageId());
			sentencia.setInt(7, pelicula.getRentalDuration());
			sentencia.setDouble(8, pelicula.getRentalRate());
			sentencia.setInt(9, pelicula.getLenght());
			sentencia.setDouble(10, pelicula.getDecimalCost());
			sentencia.setString(11, "'" + pelicula.getRating() + "'");
			sentencia.setString(12, "'" + pelicula.getSpecialFeatures() + "'");
			sentencia.setString(13, "'" + pelicula.getLastUpdate() + "'");

			sentencia.executeUpdate();

			System.out.println("INSERT OK");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Saca todas las películas de la tabla film a una lista de objetos Pelicula
	 * 
	 * @return resultSet de peliculas
	 */
	public void getAllPeliculas() {

		try {

			Statement sentencia = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSetPeliculas = sentencia.executeQuery(SELECT_ALL_FILMS_ACTOR);

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * @return la pelicula del result set
	 */
	public Pelicula getPelicula() {

		Pelicula pelicula = null;
		try {
			while (resultSetPeliculas.next()) {

				pelicula = new Pelicula(resultSetPeliculas.getInt("film_id"), resultSetPeliculas.getString("title"),
						resultSetPeliculas.getString("description"), resultSetPeliculas.getInt("release_year"),
						resultSetPeliculas.getInt("language_id"), resultSetPeliculas.getInt("original_language_id"),
						resultSetPeliculas.getInt("rental_duration"), resultSetPeliculas.getDouble("rental_rate"),
						resultSetPeliculas.getInt("length"), resultSetPeliculas.getDouble("replacement_cost"),
						resultSetPeliculas.getString("rating"), resultSetPeliculas.getString("special_features"),
						resultSetPeliculas.getString("last_update"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pelicula;
	}

	/**
	 * 
	 * @return el tamaño del resultset de las peliculas
	 */
	public int getPeliculasSize() {
		int contador = 0;

		try {

			while (resultSetPeliculas.next()) {
				contador++;
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return contador;
	}

	/**
	 * @return the resultSet
	 */
	public ResultSet getResultSetPeliculas() {
		return resultSetPeliculas;
	}

	/**
	 * Cierra una conexion SQL
	 * 
	 * @param con
	 */
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
