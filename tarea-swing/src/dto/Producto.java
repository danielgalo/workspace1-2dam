package dto;

public class Producto {

	private String nombre;
	private double precioUnidad;
	private boolean perecedero;
	
	/**
	 * @param nombre
	 * @param precioUnidad
	 * @param perecedero
	 */
	public Producto(String nombre, double precioUnidad, boolean perecedero) {
		super();

		setNombre(nombre);
		setPrecioUnidad(precioUnidad);
		setPerecedero(perecedero);
		
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
	 * @return the precioUnidad
	 */
	public double getPrecioUnidad() {
		return precioUnidad;
	}

	/**
	 * @param precioUnidad the precioUnidad to set
	 */
	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	/**
	 * @return the perecedero
	 */
	public boolean isPerecedero() {
		return perecedero;
	}

	/**
	 * @param perecedero the perecedero to set
	 */
	public void setPerecedero(boolean perecedero) {
		this.perecedero = perecedero;
	}
	
	
	
}
