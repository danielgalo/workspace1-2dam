
package hlc.ud04.actividad401.autenticador;

import java.util.Map;

import hlc.ud04.actividad401.persistencia.ProveedorUsuariosSqlite;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Desafio;
import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

public class AutenticadorPassword implements Autenticador {

	Map<String, UsuarioPassword> usuarios;

	/**
	 * @param usuarios
	 */
	public AutenticadorPassword() {

		ProveedorUsuariosSqlite proveedor = new ProveedorUsuariosSqlite("jdbc:sqlite:db/base.db");

		usuarios = proveedor.leeUsuarios();
	}

	@Override
	public Usuario finalizaAutenticacion(Desafio desafio, RespuestaDesafio respuesta) {

		DesafioPassword desafioPassword = (DesafioPassword) desafio;
		RespuestaDesafioPassword respuestaPassword = (RespuestaDesafioPassword) respuesta;

		if (usuarios.containsKey(desafioPassword.getUsuario())) {

			String password = usuarios.get(desafioPassword.getUsuario()).getPassword();

			if (password.equals(respuestaPassword.getPassword())) {

				return new Usuario(usuarios.get(desafioPassword.getUsuario()).getId());

			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	@Override
	public Desafio iniciaAutenticacion(String usuario) {
		return new DesafioPassword(usuario);
	}

}
