package psp.unidad02.relacion02.actividad02.utils;

/**
 * Interfaz para métodos de los usuarios de la pizzería Luigi
 */
public interface UsuarioPizzeriaI {

	/**
	 * Calcula de manera aleatoria el tiempo de espera en un rango especificado
	 * 
	 * @param max máximo del rango (incluido)
	 * @param min mínimo del rango (incluido)
	 * @return número aleatorio comprendido entre max y min
	 */
	public int getTiempoEspera(int max, int min);

	/**
	 * Devuelve una cadena a usar que corresponde a un usuario con su identificador
	 * 
	 * @return la cadena a mostrar
	 */
	public String msgUsuario();

}
