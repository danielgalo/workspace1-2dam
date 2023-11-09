package utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase usada para ejecutar procedures en la base de datos bd_neptuno2
 */
public class NeptunoProcedures {

	/** SQL para lanzar el procedure iva_nota */
	private static final String SQL_CALL_IVA_NOTA = "{call iva_nota(?)}";

	/**
	 * Llama al procedure iva_nota(var_iva decimal(4,2)) de la base de datos neptuno
	 */
	public static void callIvaNota(double iva) {

		Connection con = Conexion.conectar();

		try {

			CallableStatement sentencia = con.prepareCall(SQL_CALL_IVA_NOTA);
			sentencia.setDouble(1, iva);
			sentencia.execute();

			System.out.println("Procedure ejecutado correctamente, iva cambiado: " + iva);

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

}
