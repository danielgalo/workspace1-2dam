package aadd.hibernate.tarea02.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase que representa la tabla profesor
 */
@Entity
@Table(name = "T_PROFESOR")
public class Profesor implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Id del Profesor */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PROFESOR")
	private int id;

	@Column(name = "NOMBRE_PROFESOR")
	private String nombre;

	@Column(name = "APELLIDOS_PROFESOR")
	private String apellidos;

	@OneToMany(mappedBy = "relatedProfesor")
	private List<Correo> correos;

	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "profesores")
	private List<Modulo> modulos = new ArrayList<>();

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

	/**
	 * @return the correos
	 */
	public List<Correo> getCorreos() {
		return correos;
	}

	/**
	 * @param correos the correos to set
	 */
	public void setCorreos(List<Correo> correos) {
		this.correos = correos;
	}

	/**
	 * @return the modulos
	 */
	public List<Modulo> getModulos() {
		return modulos;
	}

	/**
	 * @param modulos the modulos to set
	 */
	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

}
