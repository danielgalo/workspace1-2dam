package hlc.ud04.actividad401.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

public class UsuarioManagementSQLite {

	private static final String SQLITE_URL = "jdbc:sqlite:";

	private String dbPath;

	/**
	 * 
	 * @param dbPath
	 */
	public UsuarioManagementSQLite(String dbPath) {
		setDbPath(SQLITE_URL + dbPath);
	}

	/**
	 * Devuelve un objeto usuario con el id del usuario encontrado
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario getUsuarioByNombreUsuario(String usuario) {
		Usuario usuarioEncontrado = null;

		try (Connection con = DriverManager.getConnection(dbPath);
				PreparedStatement pStmnt = con.prepareStatement("SELECT * FROM usuario WHERE usuario = ?");) {

			pStmnt.setString(1, usuario);

			ResultSet result = pStmnt.executeQuery();

			if (result.next()) {
				usuarioEncontrado = new Usuario(result.getInt("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarioEncontrado;
	}

	/**
	 * @return the dbPath
	 */
	public String getDbPath() {
		return dbPath;
	}

	/**
	 * @param dbPath the dbPath to set
	 */
	public void setDbPath(String dbPath) {
		this.dbPath = dbPath;
	}

}
