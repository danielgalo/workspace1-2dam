package persistence.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Clase que representa un genero en la tabla generos de la base de datos
 */
@Entity
@Table(name = "generos")
public class Genero {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Nombre de la pelicula */
	@Column(name = "nombre")
	private String nombre;

	/** Peliculas del genero */
	@ManyToMany
	@JoinColumn(name = "generos")
	private List<Pelicula> peliculas;

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
	 * @return the peliculas
	 */
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	/**
	 * @param peliculas the peliculas to set
	 */
	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

}
