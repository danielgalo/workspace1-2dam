package models;

import java.io.Serializable;

/**
 * Represents and employee
 */
public class Empleado implements Serializable {

	/** serial version */
	private static final long serialVersionUID = 1L;

	/** Employee's company */
	private String empresa;

	/** Emmployee's age */
	private int edad;

	/** Number of employees in charge */
	private int numEmpleados;

	/**
	 * Constructor
	 * 
	 * @param empresa
	 * @param edad
	 * @param numEmpleados
	 */
	public Empleado(String empresa, int edad, int numEmpleados) {
		setEmpresa(empresa);
		setEdad(edad);
		setNumEmpleados(numEmpleados);
	}

	@Override
	public String toString() {
		return "Empresa : " + this.empresa + "\nEdad: " + this.edad + "\nNúmero empleados: " + this.numEmpleados + "\n";
	}

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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
	 * @return the numEmpleados
	 */
	public int getNumEmpleados() {
		return numEmpleados;
	}

	/**
	 * @param numEmpleados the numEmpleados to set
	 */
	public void setNumEmpleados(int numEmpleados) {
		this.numEmpleados = numEmpleados;
	}

}
