package psp.unidad02.relacion02.actividad02;

import java.util.ArrayList;
import java.util.List;

import psp.unidad02.relacion02.actividad02.models.Cliente;
import psp.unidad02.relacion02.actividad02.models.Pizzero;
import psp.unidad02.relacion02.actividad02.utils.Parametros;

/**
 * Clase principal de la Pizzeria Luigi
 */
public class PizzeriaLuigi {

	// Lista de pizzeros
	private static List<Pizzero> pizzeros = new ArrayList<>();
	// Lista de clientes
	private static List<Cliente> clientes = new ArrayList<>();
	// Número de clientes en el local
	private static int clientela = 0;

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Controlar y conseguir parametros
		Parametros param = new Parametros(args);
		int numeroPizzeros = param.getPizzeros();
		int numeroClientes = param.getClientes();

		clientela = numeroClientes;

		// Crear pizzeros y clientes
		addPizzeros(numeroPizzeros);
		addClientes(numeroClientes);

		System.out.println("--- Abre la pizzería ---");
		System.out.println("Clientes: " + numeroClientes);
		System.out.println("Pizzeros : " + numeroPizzeros);

		// Ejecutar los pizzeros y clientes
		for (Pizzero p : pizzeros) {
			p.start();

		}
		for (Cliente c : clientes) {
			c.start();

		}

	}

	/**
	 * Añade una cantidad de objetos Cliente a una lista
	 * 
	 * @param numeroClientes
	 */
	private static void addClientes(int numeroClientes) {
		int idCliente = 1;

		for (int i = 0; i < numeroClientes; i++) {
			clientes.add(new Cliente(idCliente));
			idCliente++;
		}
	}

	/**
	 * Añade una cantidad de objetos Pizzero a una lista
	 * 
	 * @param numeroPizzeros
	 */
	private static void addPizzeros(int numeroPizzeros) {

		int idPizzero = 1;

		for (int i = 0; i < numeroPizzeros; i++) {
			pizzeros.add(new Pizzero(idPizzero));
			idPizzero++;
		}
	}

	/**
	 * Reduce en uno la clientela de la pizzería, es decir este método es usado
	 * cuando un cliente acaba con su servicio
	 */
	public static void reduceClientela() {
		clientela--;
	}

	/**
	 * @return the clientela
	 */
	public static int getClientela() {
		return clientela;
	}

}
