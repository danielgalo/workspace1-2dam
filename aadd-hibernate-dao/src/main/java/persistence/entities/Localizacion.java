package persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa una película en la tabla peliculas de la base de datos
 */
@Entity
@Table(name = "localizaciones")
public class Localizacion {

	/** Nombre de la localizacion */
	@Id
	@Column(name = "localizacion")
	private String nombreLocalizacion;

	/**
	 * @return the nombreLocalizacion
	 */
	public String getNombreLocalizacion() {
		return nombreLocalizacion;
	}

	/**
	 * @param nombreLocalizacion the nombreLocalizacion to set
	 */
	public void setNombreLocalizacion(String nombreLocalizacion) {
		this.nombreLocalizacion = nombreLocalizacion;
	}

}
