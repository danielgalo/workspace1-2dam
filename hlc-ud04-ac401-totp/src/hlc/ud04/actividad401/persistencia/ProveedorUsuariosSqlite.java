package hlc.ud04.actividad401.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import hlc.ud04.actividad401.autenticador.UsuarioPassword;

public class ProveedorUsuariosSqlite {

	private String url;

	/**
	 * @param url
	 */
	public ProveedorUsuariosSqlite(String url) {
		super();
		this.url = url;
	}

	public Map<String, UsuarioPassword> leeUsuarios() {

		Map<String, UsuarioPassword> salida = null;

		try {

			Connection con = DriverManager.getConnection(url);

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario");

			salida = new HashMap<>();

			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				UsuarioPassword usuario = new UsuarioPassword(resultado.getString("usuario"),
						resultado.getString("password"), resultado.getLong("id"));

				salida.put(usuario.getNombre(), usuario);
			}

			con.close();
			stmt.close();
			resultado.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return salida;

	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
