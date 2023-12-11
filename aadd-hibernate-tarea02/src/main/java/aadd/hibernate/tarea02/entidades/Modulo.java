package aadd.hibernate.tarea02.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Clase que representa la tabla Modulo
 */
@Entity
@Table(name = "T_MODULO")
public class Modulo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Id del modulo */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MODULO")
	private int id;

	/** Nombre del modulo */
	@Column(name = "NOMBRE_MODULO")
	private String nombre;

	/** Profesores del modulo */
	@ManyToMany
	@JoinTable(name = "T_MODULO_PROFESOR", joinColumns = { @JoinColumn(name = "ID_MODULO") }, inverseJoinColumns = {
			@JoinColumn(name = "ID_PROFESOR") })
	private List<Profesor> profesores = new ArrayList<>();

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
	 * @return the profesores
	 */
	public List<Profesor> getProfesores() {
		return profesores;
	}

	/**
	 * @param profesores the profesores to set
	 */
	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}

}
