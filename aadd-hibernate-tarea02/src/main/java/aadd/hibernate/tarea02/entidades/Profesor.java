package aadd.hibernate.tarea02.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_PROFESOR")
public class Profesor {

	/** Id del Profesor */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PROFESOR")
	private int id;

	@Column(name = "NOMBRE_PROFESOR")
	private String nombre;

	@Column(name = "APELLIDOS_PROFESOR")
	private String apellidos;

	@OneToMany(mappedBy = "relatedProfesor")
	private List<Correo> correos;

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

}
