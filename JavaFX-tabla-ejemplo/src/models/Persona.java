package models;

import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Representa una persona
 */
public class Persona {

	/** Nombre de la persona */
	private String nombre;

	/** Apellidos de la persona */
	private String apellidos;

	/** Edad de la persona */
	private int edad;

	/**
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 */
	public Persona(String nombre, String apellidos, int edad) {
		setNombre(nombre);
		setApellidos(apellidos);
		setEdad(edad);
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

	// Métodos para usar en las celdas de la tabla
	public StringProperty firstNameProperty() {
		return new SimpleStringProperty(nombre);
	}

	public StringProperty lastNameProperty() {
		return new SimpleStringProperty(apellidos);
	}

	public IntegerProperty ageProperty() {
		return new SimpleIntegerProperty(edad);
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, edad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellidos, other.apellidos) && edad == other.edad && Objects.equals(nombre, other.nombre);
	}

}
