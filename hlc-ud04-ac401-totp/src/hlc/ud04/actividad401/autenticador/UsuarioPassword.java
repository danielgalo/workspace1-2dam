package hlc.ud04.actividad401.autenticador;

public class UsuarioPassword {

	private String nombre;
	private String password;
	private Long id;

	/**
	 * @param nombre
	 * @param password
	 * @param id
	 */
	public UsuarioPassword(String nombre, String password, Long id) {
		super();
		this.nombre = nombre;
		this.password = password;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
