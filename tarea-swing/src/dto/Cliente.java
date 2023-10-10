package dto;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String nombre;
	private String apellidos;
	private int edad;
	private String provincia;

	private List<Cliente> listaClientes;

	/**
	 * Constructor completo
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 * @param provincia
	 */
	public Cliente(String nombre, String apellidos, int edad, String provincia) {
		super();
		setNombre(nombre);
		setApellidos(apellidos);
		setEdad(edad);
		setProvincia(provincia);

		listaClientes = new ArrayList<Cliente>();
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the listaClientes
	 */
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	/**
	 * @param listaClientes the listaClientes to set
	 */
	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	/**
	 * 
	 * @param c
	 */
	public void addCliente(Cliente c) {
		listaClientes.add(c);
	}

	/**
	 * 
	 * @return una cadena con todos los datos del cliente
	 */
	public String getDatosCliente() {
		return "Nombre: " + getNombre() + " | Apellidos: " + getApellidos() + " | Edad: " + getEdad() + " | Provincia: "
				+ getProvincia();
	}

}
