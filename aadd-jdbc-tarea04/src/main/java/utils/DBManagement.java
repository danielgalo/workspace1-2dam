package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase usada para administrar la base de datos de la tarea 4
 */
public class DBManagement {

	/** Sentencia SQL para insertar un profesor */
	private static final String SQL_INSERT_PROFESOR = "INSERT INTO Profesor (NIF_P, Nombre, Especialidad, Telefono) VALUES (?, ?, ?, ?)";

	/** Sentencia SQL para insertar una asignatura */
	private static final String SQL_INSERT_ASIGNATURA = "INSERT INTO Asignatura (CodAsignatura, Nombre, IdProfesor) VALUES (?, ?, ?)";

	/** Sentencia SQL para insertar un alumno */
	private static final String SQL_INSERT_ALUMNO = "INSERT INTO Alumno (Nombre, FechaNacimiento, Telefono) VALUES (?, ?, ?)";

	/** Id del profesor */
	private static int idProfe;

	/**
	 * Inserta un profesor en la tabla profesor
	 * 
	 * @param datos
	 * @return el id del profesor
	 */
	public static int insertaProfesor(String datos[]) {
		Connection con = null;

		try {

			con = Conexion.conectar();
			con.setAutoCommit(false);

			PreparedStatement sentencia = con.prepareStatement(SQL_INSERT_PROFESOR, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, datos[1]);
			sentencia.setString(2, datos[2]);
			sentencia.setString(3, datos[3]);
			sentencia.setString(4, datos[4]);

			sentencia.executeUpdate();

			ResultSet clave = sentencia.getGeneratedKeys();
			clave.next();

			idProfe = clave.getInt(1);

			System.out.println("Insert profesor OK." + idProfe);

			con.commit();

		} catch (SQLException e) {

			e.printStackTrace();
			rollBack(con);

		} finally {
			closeConnection(con);
		}
		return idProfe;

	}

	/**
	 * @param con
	 */
	private static void rollBack(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Inserta una asignatura en la tabla asignaturas
	 * 
	 * @param datos de la asignatura
	 */
	public static void insertaAsignatura(String datos[]) {

		Connection con = Conexion.conectar();

		try {
			con.setAutoCommit(false);
			PreparedStatement sentencia = con.prepareStatement(SQL_INSERT_ASIGNATURA);
			sentencia.setString(1, datos[1]);
			sentencia.setString(2, datos[2]);
			sentencia.setInt(3, idProfe);

			sentencia.executeUpdate();

			System.out.println("Insert asignatura OK.");
			con.commit();
		} catch (SQLException e) {

			e.printStackTrace();
			rollBack(con);

		} finally {

			closeConnection(con);

		}

	}

	/**
	 * Inserta un alumno en la tabla alumno
	 * 
	 * @param datos del alumno
	 */
	public static void insertaAlumno(String datos[]) {

		Connection con = Conexion.conectar();

		try {

			con.setAutoCommit(false);
			PreparedStatement sentencia = con.prepareStatement(SQL_INSERT_ALUMNO);
			sentencia.setString(1, datos[1]);
			sentencia.setString(2, datos[2]);

			sentencia.executeUpdate();

			System.out.println("Insert alumno OK.");
			con.commit();
		} catch (SQLException e) {

			e.printStackTrace();
			rollBack(con);

		} finally {

			closeConnection(con);

		}

	}

	/**
	 * @param con
	 */
	private static void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
