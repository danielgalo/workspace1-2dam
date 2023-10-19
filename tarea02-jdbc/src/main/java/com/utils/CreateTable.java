package com.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase usada para crear tablas en la base de datos tarea02jdbc
 */
public class CreateTable {

	/**
	 * Crea la tabla empleados
	 */
	public static void createEmpleado() {

		Connection con = Conexion.conectar();

		try {

			Statement sentencia = con.createStatement();

			sentencia.execute(getSentenciaCreateEmpl());

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			closeConnection(con);

		}

	}

	/**
	 * Crea la tabla localidad
	 */
	public static void createLocalidad() {

		Connection con = Conexion.conectar();

		try {

			Statement sentencia = con.createStatement();

			sentencia.execute(getSentenciaCreateLoc());

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			closeConnection(con);

		}

	}

	/**
	 * Crea la tabla provincia
	 */
	public static void createProvincia() {

		Connection con = Conexion.conectar();

		try {

			Statement sentencia = con.createStatement();

			sentencia.execute(getSentenciaCreateProv());

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			closeConnection(con);

		}

	}

	/**
	 * Cierra la conexion a la base de datos
	 * 
	 * @param con
	 */
	private static void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve una sentencia sql para crear una tabla de empleados
	 * 
	 * @return cadena de sentencia sql
	 */
	private static String getSentenciaCreateEmpl() {

		return "CREATE TABLE IF NOT EXISTS Empleado(id int, dni char(9) unique key, nombre varchar(100), FechaNac date, telefono varchar(15), salario double(10,2), CodLocalidad char(9), primary key(id), foreign key(CodLocalidad) references Localidad(CodLocalidad));";

	}

	/**
	 * Devuelve una sentencia sql para crear una tabla de localidades
	 * 
	 * @return cadena de sentencia sql
	 */
	private static String getSentenciaCreateLoc() {

		return "CREATE TABLE IF NOT EXISTS Localidad (CodLocalidad char(9), Nombre varchar(100), CodProvincia char(9), primary key(CodLocalidad), foreign key(CodProvincia) references Provincia(CodProvincia));";

	}

	/**
	 * Devuelve una sentencia sql para crear una tabla de provincias
	 * 
	 * @return cadena de sentencia sql
	 */
	private static String getSentenciaCreateProv() {

		return "CREATE TABLE IF NOT exists Provincia(CodProvincia char(9), nombre varchar(100), CodRegion char(5), primary key(CodProvincia));";

	}

}
