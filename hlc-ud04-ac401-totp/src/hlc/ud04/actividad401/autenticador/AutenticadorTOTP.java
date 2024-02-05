package hlc.ud04.actividad401.autenticador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

public class AutenticadorTOTP implements Autenticador {

	private static final String DB_PATH = "db/base.db";
	ResultSet result;
	ResultSet resultSecreto;

	@Override
	public Usuario finalizaAutenticacion(Desafio arg0, RespuestaDesafio arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Desafio iniciaAutenticacion(String usuario) {
		int idUsuario;
		String secreto = "";
		// El usuario pasado, es el nombre del usuario

		// Comrpbar q existe en la base de datos
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
			Statement selectUsuario = con.createStatement();
			result = selectUsuario.executeQuery("SELECT * FROM usuario WHERE usuario = '" + usuario + "'");

			// Si hay resultados y coinciden, guardar su id
			if (result.next() && result.getString("usuario").equals(usuario)) {
				idUsuario = result.getInt("id");

				Statement selectSecretoUsuario = con.createStatement();
				resultSecreto = selectSecretoUsuario
						.executeQuery("SELECT secreto FROM secreto WHERE id_usuario = " + idUsuario);

				if (resultSecreto.next()) {
					secreto = resultSecreto.getString("secreto");
				}

			}
			con.close();
			result.close();
			resultSecreto.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new DesafioTOTP(usuario, secreto);
	}

}
