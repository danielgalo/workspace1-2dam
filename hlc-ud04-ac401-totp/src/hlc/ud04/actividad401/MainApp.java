package hlc.ud04.actividad401;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import hlc.ud04.actividad401.auth.otp.GeneradorHOTP;
import hlc.ud04.appsec.core.Clientes;
import hlc.ud04.appsec.core.GestorPersistencia;
import hlc.ud04.appsec.persistencia.GestorPersistenciaSqlite;

public class MainApp {

	private static final String DB_PATH = "db/base.db";

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// Gestor de persistencias de base de datos (SQLite)
		GestorPersistencia gestor = new GestorPersistenciaSqlite(DB_PATH);
		// Gestor de clientes (Contiene CRUD)
		Clientes clientes = new Clientes(gestor);

		// Sistema de seguridad (Autenticación (¿Eres quien dices que eres?) y Control
		// de acceso(¿Puedes hacer x operaciones?))

		// SistemaSeguridad sistSeguridad = new SistemaSeguridadPassword(new
		// AutenticadorPassword(),
		// new ControlAccesoSimple());

		// Interfaz de consola
		// Interfaz interfaz = new InterfazConsola(clientes, sistSeguridad);
		// interfaz.run();

		/**
		 * Implementar Sistema de seguridad OTP. Usar el control de acceso simple.
		 * Sistema de seguridad tendrá un:<br>
		 * - Autenticador.<br>
		 * * @Override iniciaAutenticacion(String usuario) por el nombre del usuario, se
		 * busca en la base de datos. Se busca su secreto para mostrarselo. Devuelve un
		 * desafío, en el cual se guarda el pin que ha introducido el usuario, después
		 * de haberle mostrado su secreto <br>
		 * * @Override finalizaAutenticacion(Desafio, Respuesta al desafio) compara el
		 * desafio que devuelve el método de iniciaAutentificacion, con la respuesta. Es
		 * decir el desafio tendrá el PIN introducido por el usuario, mientras que la
		 * respuesta guarda el PIN correcto. <br>
		 * - Control de acceso (Simple en este caso).
		 * 
		 * MÉTODO AUTENTICA DE SISTEMA DE SEGURIDAD: Pedirá el nombre del usuario. Se
		 * crea un desafio = iniciaAutenticador(nombre del usuario)
		 * 
		 * El método devuelve un usuario, al igual que el finalizaAutenticacion, asi que
		 * se devuelve este método que es el que autentica y se le pasa el desafío, y la
		 * respuesta, a la respuesta se le pasa la clave generada.
		 * 
		 * * @Override autentica(): - Crear un Desafio
		 */

		/*
		 * PROGRAMA GUARREADO PERO FUNCIONA, HAY QUE HACERLO EN CLASES COMO ESTÁ
		 * COMENTADO ARRIBA
		 */
		ResultSet resultSecreto = null;
		ResultSet result = null;

		// Obtener ese secreto introducido en la base de datos.
		try {
			// Id del usuario para buscar en la tabla de secreto
			int idUsuario = 0;
			// Secreto del usuario
			String secreto = "";

			System.out.print("Introduce el nombre de usuario: ");
			// Input ejemplo del nombre del usuario
			String userIntroducido = sc.nextLine();

			// Conexion a BBDD
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);

			// Buscar al usuario por su nombre, comprobar que existe
			Statement selectUsuario = con.createStatement();
			result = selectUsuario.executeQuery("SELECT * FROM usuario WHERE usuario = '" + userIntroducido + "'");

			// Si hay resultados y coinciden, guardar su id
			if (result.next() && result.getString("usuario").equals(userIntroducido)) {
				idUsuario = result.getInt("id");

				Statement selectSecretoUsuario = con.createStatement();
				resultSecreto = selectSecretoUsuario
						.executeQuery("SELECT secreto FROM secreto WHERE id_usuario = " + idUsuario);

				if (resultSecreto.next()) {
					secreto = resultSecreto.getString("secreto");
				}

				// A Través de ese secreto, obtener el PIN OTP
				int espera = (int) (System.currentTimeMillis() / 30000);
				GeneradorHOTP generador = new GeneradorHOTP();

				// Obtener la contraseña OTP del usuario
				String otpPassword = generador.genera(secreto, espera);

				System.out.println("Tu secreto es: " + secreto + ". Introduce la contraseña OTP: ");
				String inputOtp = sc.nextLine();

				if (inputOtp.equals(otpPassword)) {
					System.out.println("Contraseña correcta.");
				} else {
					System.out.println("Contraseña incorrecta.");
				}

			} else {
				System.out.println("No se encontró ningún usuario.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (result != null && resultSecreto != null) {
					result.close();
					resultSecreto.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
