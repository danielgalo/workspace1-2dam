package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilizada para conectarse a MySQL
 */
public class Conexion {

	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String BBDD = "Northwind";
	private static final String PARAMETROS = "?serverTimezone=UTC";
	private static final String USUARIO = "root";
	private static final String CLAVE = "root";

	/**
	 * Se conecta a la base de datos
	 * 
	 * @return
	 */
	public static Connection conectar() {

		Connection conexion = null;

		try {

			conexion = DriverManager.getConnection(URL + BBDD + PARAMETROS, USUARIO, CLAVE);

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return conexion;
	}

}
