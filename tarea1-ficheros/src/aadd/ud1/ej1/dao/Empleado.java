package aadd.ud1.ej1.dao;

public class Empleado {

	private String empresa;
	private int edad;
	private int numEmpleados;

	public Empleado(String empresa, int edad, int numEmpleados) {
		setEmpresa(empresa);
		setEdad(edad);
		setNumEmpleados(numEmpleados);
	}

	@Override
	public String toString() {
		return "Empresa : " + this.empresa + "\nEdad: " + this.edad + "\nNúmero empleados: " + this.numEmpleados;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getNumEmpleados() {
		return numEmpleados;
	}

	public void setNumEmpleados(int numEmpleados) {
		this.numEmpleados = numEmpleados;
	}

}
