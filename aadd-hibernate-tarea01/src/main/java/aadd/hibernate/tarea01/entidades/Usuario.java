package aadd.hibernate.tarea01.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa una tabla profesor
 */
@Entity
@Table(name = "usuarios_hibernate")
public class Usuario {

	/** Id del usuario */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO")
	private int id;

	/** Nombre del usuario */
	@Column(name = "NOMBRE_USUARIO")
	private String nombre;

	/** Apellidos del usuario */
	@Column(name = "APELLIDOS_USUARIO")
	private String apellidos;

	/**
	 * Constructor vacío
	 */
	public Usuario() {

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

}
