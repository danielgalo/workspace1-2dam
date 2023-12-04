package aadd.hibernate.tarea02.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa una tabla correo
 */
@Entity
@Table(name = "T_CORREO")
public class Correo {

	/** Id del correo */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CORREO")
	private int id;

	@Column(name = "DIRECCION_CORREO")
	private String direccion;

	@ManyToOne
	@JoinColumn(name = "ID_PROFESOR")
	private Profesor relatedProfesor;

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
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the relatedProfesor
	 */
	public Profesor getRelatedProfesor() {
		return relatedProfesor;
	}

	/**
	 * @param relatedProfesor the relatedProfesor to set
	 */
	public void setRelatedProfesor(Profesor relatedProfesor) {
		this.relatedProfesor = relatedProfesor;
	}

}
