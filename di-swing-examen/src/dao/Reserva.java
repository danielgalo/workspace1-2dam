package dao;

public class Reserva {

	private String nombre;
	private String profesor;
	private String turno;
	private Cliente cliente;

	/**
	 * @param nombre
	 * @param profesor
	 * @param turno
	 * @param cliente
	 */
	public Reserva(String nombre, String profesor, String turno, Cliente cliente) {
		this.nombre = nombre;
		this.profesor = profesor;
		this.turno = turno;
		this.cliente = cliente;
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
	 * @return the profesor
	 */
	public String getProfesor() {
		return profesor;
	}

	/**
	 * @param profesor the profesor to set
	 */
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	/**
	 * @return the turno
	 */
	public String getTurno() {
		return turno;
	}

	/**
	 * @param turno the turno to set
	 */
	public void setTurno(String turno) {
		this.turno = turno;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
