package dao;

import java.util.Date;

/**
 * Un administrador
 */
public class Administrador {

	protected String nombre;
	protected String apellidos;
	protected int edad;
	protected Date fechaNacimiento;
	protected String correoElectronico;

	/**
	 * Constructor
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 * @param fechaNacimiento
	 * @param correoElectronico
	 */
	public Administrador(String nombre, String apellidos, Date fechaNacimiento, String correoElectronico) {
		this.nombre = nombre;
		this.apellidos = apellidos;

		this.fechaNacimiento = fechaNacimiento;
		this.correoElectronico = correoElectronico;
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
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

}
