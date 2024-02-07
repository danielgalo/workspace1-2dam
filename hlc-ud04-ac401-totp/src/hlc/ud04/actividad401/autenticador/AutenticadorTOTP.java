package hlc.ud04.actividad401.autenticador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hlc.ud04.actividad401.auth.otp.GeneradorHOTP;
import hlc.ud04.actividad401.persistencia.UsuarioManagementSQLite;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

/**
 * Realiza la autenticación de un usuario. Se busca al usuario, se obtiene su
 * secreto, se genera una clave OTP, se le pide dicha clave y se verifica
 */
public class AutenticadorTOTP implements Autenticador {

	private static final String DB_PATH = "db/base.db";
	ResultSet result;
	ResultSet resultSecreto;

	@Override
	public Usuario finalizaAutenticacion(Desafio desafio, RespuestaDesafio respuesta) {

		// Usuario a devolver
		Usuario usuario = null;

		// Obtener respuesta y desafio
		DesafioTOTP desafioTOTP = (DesafioTOTP) desafio;
		RespuestaDesafioTOTP respuestaTOTP = (RespuestaDesafioTOTP) respuesta;

		// Calcular el PIN TOTP
		GeneradorHOTP generador = new GeneradorHOTP();
		int espera = (int) (System.currentTimeMillis() / 30000);
		String otpPassword = generador.genera(desafioTOTP.getSecreto(), espera);

		// Comparar con el introducido en la respuesta
		if (otpPassword.equals(respuestaTOTP.getPin())) {

			// El pin es correcto, busca al usuario en la base de datos para devolverlo
			UsuarioManagementSQLite management = new UsuarioManagementSQLite(DB_PATH);
			usuario = management.getUsuarioByNombreUsuario(desafioTOTP.getUsuario());
		}

		return usuario;
	}

	@Override
	public Desafio iniciaAutenticacion(String usuario) {

		// Campos para obtener el secreto
		int idUsuario;
		String secreto = "";

		try {
			// Conexion a la BBDD
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
			Statement selectUsuario = con.createStatement();

			// Consulta, buscar al usuario
			result = selectUsuario.executeQuery("SELECT * FROM usuario WHERE usuario = '" + usuario + "'");

			// Si hay resultados y coinciden, guardar su id
			if (result.next() && result.getString("usuario").equals(usuario)) {
				idUsuario = result.getInt("id");

				// Obtener el secreto del usuario
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

		// Devolver un desafio con el secreto
		return new DesafioTOTP(usuario, secreto);
	}

}
