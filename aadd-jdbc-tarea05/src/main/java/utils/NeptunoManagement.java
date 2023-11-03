package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Administra la base de datos bd_neptuno2
 */
public class NeptunoManagement {

	private static final String SQL_INSERT_CATEGORIAS = "INSERT INTO categorias (categoria, descripcion) VALUES (?, ?)";
	private static final int NUMERO_INSERTS = 20;

	/**
	 * Inserta 20 categorías en Batchs en la tabla categorias
	 */
	public static void insertaCategoriasBatch() {

		Connection con = Conexion.conectar();

		try {

			con.setAutoCommit(false);

			PreparedStatement sentencia = con.prepareStatement(SQL_INSERT_CATEGORIAS);

			// Hacer 20 inserts
			for (int i = 0; i < NUMERO_INSERTS; i++) {
				prepInsertCategorias(sentencia, "Nueva " + (i + 1), "Descripción nueva " + (i + 1));
				sentencia.addBatch();
			}

			// Ejecutar batch
			sentencia.executeBatch();

			System.out.println("Inserts realizados correctamente.");

			con.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Prepara la sentencia insert para la tabla categorias
	 * 
	 * @param sentencia
	 * @param categoria
	 * @param descripcion
	 * @throws SQLException
	 */
	private static void prepInsertCategorias(PreparedStatement sentencia, String categoria, String descripcion)
			throws SQLException {
		sentencia.setString(1, categoria);
		sentencia.setString(2, descripcion);
	}

}
