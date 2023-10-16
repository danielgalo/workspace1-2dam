package utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SakilaManagement {

	/**
	 * Hace una consulta a la tabla actor filtrando por su campo id e inserta los
	 * resultados a un fichero de texto
	 * 
	 * @param id
	 */
	public static void actorPorIdFichero(int id) {

		PrintWriter writer = null;

		try {
			writer = new PrintWriter(new FileWriter("actor.txt"));
			Connection con = Conexion.conectar();

			PreparedStatement sentencia = con.prepareStatement("SELECT * FROM actor WHERE actor_id > ?");
			sentencia.setInt(1, id);

			ResultSet resultado = sentencia.executeQuery();

			while (resultado.next()) {
				writer.println("ID: " + resultado.getInt("actor_id") + " | Nombre: " + resultado.getString("first_name")
						+ " | Apellido: " + resultado.getString("last_name") + " | Última actualización: "
						+ resultado.getString("last_update"));
			}

			writer.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
