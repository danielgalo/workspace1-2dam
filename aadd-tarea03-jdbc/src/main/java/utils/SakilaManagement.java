package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.Pelicula;

/**
 * Clase para manejar la base de datos sakila
 */
public class SakilaManagement {

	private ResultSet resultSetPeliculas;
	private Connection con;

	public SakilaManagement() {
		con = Conexion.conectar();
	}

	/**
	 * Saca todas las películas de la tabla film a una lista de objetos Pelicula
	 * 
	 * @return resultSet de peliculas
	 */
	public void getAllPeliculas() {

		// List<Pelicula> peliculas = new ArrayList<Pelicula>();

		String select = "SELECT * FROM film";
		try {

			Statement sentencia = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSetPeliculas = sentencia.executeQuery(select);

			/*
			 * while (resultado.next()) {
			 * 
			 * 
			 * Pelicula pelicula = new Pelicula(resultado.getInt("film_id"),
			 * resultado.getString("title"), resultado.getString("description"),
			 * resultado.getInt("release_year"), resultado.getInt("language_id"),
			 * resultado.getInt("original_language_id"),
			 * resultado.getInt("rental_duration"), resultado.getDouble("rental_rate"),
			 * resultado.getInt("length"), resultado.getDouble("replacement_cost"),
			 * resultado.getString("rating"), resultado.getString("special_features"),
			 * resultado.getString("last_update"));
			 * 
			 * peliculas.add(pelicula); }
			 */
		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public Pelicula getPrimeraPelicula() {

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
