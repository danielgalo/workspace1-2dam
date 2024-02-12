package hlc.ud04.actividad401;

import hlc.ud04.actividad401.autenticador.AutenticadorTOTP;
import hlc.ud04.actividad401.controlacceso.ControlAccesoFichero;
import hlc.ud04.actividad401.seguridad.SistemaSeguridadTOTP;
import hlc.ud04.appsec.core.Clientes;
import hlc.ud04.appsec.core.GestorPersistencia;
import hlc.ud04.appsec.interfaz.Interfaz;
import hlc.ud04.appsec.interfaz.consola.InterfazConsola;
import hlc.ud04.appsec.persistencia.GestorPersistenciaSqlite;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;

/**
 * Clase principal
 */
public class MainAppTOPT {

	/** Ruta a la base de datos */
	private static final String DB_PATH = "db/base.db";

	/**
	 * Método principal
	 * 
	 * @param args argumentos del programa
	 */
	public static void main(String[] args) {

		// Gestor de persistencias de base de datos (SQLite)
		GestorPersistencia gestor = new GestorPersistenciaSqlite(DB_PATH);

		// Gestor de clientes (Contiene CRUD)
		Clientes clientes = new Clientes(gestor);

		// Sistema de seguridad (Autenticación (¿Eres quien dices que eres?) y Control
		// de acceso(¿Puedes hacer x operaciones?))
		SistemaSeguridad sistSeguridad = new SistemaSeguridadTOTP(new AutenticadorTOTP(), new ControlAccesoFichero());

		Interfaz interfaz = new InterfazConsola(clientes, sistSeguridad);
		interfaz.run();

	}

}
