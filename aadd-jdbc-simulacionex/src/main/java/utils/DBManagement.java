package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Categorias;
import dao.Productos;
import dao.Proveedores;

/**
 * Clase usada para hacer tareas de BBDD para la simulación del examen de AADD
 */
public class DBManagement {

	private static final String TITULO_PRODUCTOS = "PRODUCTOS";
	private static final String TITULO_PROVEEDORES = "PROVEEDORES";
	private static final String TITULO_CATEGORIA = "CATEGORIA";
	private static final String SELECT_CATEGORIAS = "SELECT * FROM categorias where id = ?";
	private static final String SELECT_PROVEEDORES = "SELECT id, empresa, contacto FROM proveedores WHERE id = ?";
	private static final String CAMPOS_CATEGORIA = "id;categoria;descripcion";
	private static final String CAMPOS_PROVEEDORES = "id;empresa;contacto";
	private static final String CAMPOS_PRODUCTO = "id;producto;proveedor_id;categoria_id;cantidad_por_unidad;precio_unidad;unidades_existencia;unidades_pedido;nivel_nuevo;pedido;suspendido";
	private static final String DROP_TABLE_USUARIO = "drop table if exists bd_neptuno2.usuario";
	private static final String SQL_SELECT_PRODUCTOS_CATID = "SELECT * FROM productos WHERE categoria_id = ?";
	private static final String SQL_CREATE_USUARIOS = "CREATE TABLE IF NOT EXISTS Usuario (IdUser int auto_increment, Nombre varchar(200), Apellidos varchar(200), User char(8), primary key(IdUser))";
	private static final String SQL_INSERT_USUARIOS = "INSERT INTO Usuario(Nombre, Apellidos, User) VALUES (?, ?, ?)";

	private static List<Productos> listaProductos;
	private static List<Proveedores> listaProveedores;
	private static Categorias categoria;

	/**
	 * Crea la tabla usuarios e inserta datos de un fichero en ella
	 * 
	 * @param fichero
	 */
	public static void crearTablaSuperUsuarios(String fichero) {

		Connection con = Conexion.conectar();

		try {

			// Borrar tabla si existe
			dropTableUsuario(con);

			// Crear tabla
			createTableUsuarios(SQL_CREATE_USUARIOS, con);

			// Conseguir datos de usuario
			List<String[]> listaUsuarios = getListaUsuarios(fichero);

			// Insertar ususarios
			insertaUsuarios(listaUsuarios, con);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			closeConnection(con);
		}
	}

	/**
	 * Elimina la tabla usuario si existe
	 * 
	 * @param con
	 */
	private static void dropTableUsuario(Connection con) {

		try {
			Statement sentencia = con.createStatement();
			sentencia.executeUpdate(DROP_TABLE_USUARIO);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Consulta en la bbdd bd_neptuno2 los campos necesarios de los productos con
	 * esa categoría y los almacena en una lista de productos.
	 * 
	 * Consulta la información también de la Categoría y crea un objeto con ella.
	 * 
	 * 
	 * En la lista de Productos también tendremos el proveedor (id_proveedor) de
	 * cada producto, por lo que consultaremos la información (id, empresa,
	 * contacto) de esos proveedores y los almacenaremos en una lista de
	 * Proveedores, pero controlando que no se repitan.
	 * 
	 * Una vez que tengamos la lista de Productos, la Categoría y la lista de
	 * Proveedores, los almacenaremos en un fichero de texto con el nombre que le
	 * hayamos indicado como segundo parámetro.
	 * 
	 * @param id
	 * @param fichero
	 */
	public static void almacenarProductosEnFichero(int idCategoria, String fichero) {

		Connection con = Conexion.conectar();

		try {

			// Añadir productos a lista
			addProductosToList(idCategoria, con);

			// Conseguir categoria con el id
			categoria = getCategoria(idCategoria, con);

			// Añadir los proveedores de los productos a una lista
			addProveedoresToList(con);

			// Escribir en fichero
			try {

				escribeEnFichero(fichero);

			} catch (IOException e) {

				System.err.println("Error accediendo o escribiendo al archivo " + fichero + e.getMessage());

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	/**
	 * Escribe los datos de la categoria, proveedores y productos en un fichero
	 * 
	 * @param fichero
	 * @throws IOException
	 */
	private static void escribeEnFichero(String fichero) throws IOException {
		PrintWriter writer = new PrintWriter(new FileWriter(fichero));

		writer.println(TITULO_PRODUCTOS);
		writer.println(CAMPOS_PRODUCTO);
		for (Productos p : listaProductos) {
			writer.println(p.getDatosProducto());
		}

		writer.println();
		writer.println(TITULO_PROVEEDORES);
		writer.println(CAMPOS_PROVEEDORES);

		for (Proveedores p : listaProveedores) {
			writer.println(p.getDatosProveedor());
		}
		writer.println();

		writer.println(TITULO_CATEGORIA);
		writer.println(CAMPOS_CATEGORIA);
		writer.println(categoria.getDatosCategoria());

		writer.close();

		System.out.println("Fichero OK");
	}

	/**
	 * Lee la información del fichero y almacene su información en las tablas
	 * Categorias_new, Proveedores_new, Productos_new.
	 * 
	 * @param fichero
	 */
	public static void ficheroATablas(String fichero) {

		BufferedReader reader = null;
		Connection con = Conexion.conectar();

		try {

			con.setAutoCommit(false);

			// Crea las nuevas tablas
			createNewTables(con);

			reader = new BufferedReader(new FileReader(fichero));

			String linea;

			// Leo fichero
			while ((linea = reader.readLine()) != null) {

				String titulo = "";

				// Si la linea es un titulo asignarlo a la variable titulo
				if (linea.equals(TITULO_CATEGORIA) || linea.equals(TITULO_PRODUCTOS)
						|| linea.equals(TITULO_PROVEEDORES)) {

					titulo = linea;

					// Si la linea no son campos de la tabla, insertar la linea
				} else if (!linea.equals(CAMPOS_CATEGORIA) && !linea.equals(CAMPOS_PRODUCTO)
						&& !linea.equals(CAMPOS_PROVEEDORES)) {

					switch (titulo) {

					case TITULO_CATEGORIA:

						insertCategoria(con, linea);

						break;

					case TITULO_PRODUCTOS:
						System.out.println("produ");
						break;

					case TITULO_PROVEEDORES:
						System.out.println("prov");
						break;
					}

				}

			}

		} catch (IOException e) {

			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void insertCategoria(Connection con, String linea) throws SQLException {
		String[] datos = linea.split(";");
		PreparedStatement insert = con.prepareStatement("INSERT INTO categorias_new VALUES(?,?,?)");

		insert.setString(1, datos[0]);
		insert.setString(2, datos[1]);
		insert.setString(3, datos[2]);

		insert.executeUpdate();

	}

	/**
	 * @param con
	 * @throws SQLException
	 */
	private static void createNewTables(Connection con) throws SQLException {
		Statement create = con.createStatement();

		create.addBatch(
				"CREATE TABLE IF NOT EXISTS categorias_new(id int primary key, categoria varchar(200), descripcion varchar(200))");

		create.addBatch(
				"CREATE TABLE IF NOT EXISTS proveedores_new(id int primary key, empresa varchar(200), contacto varchar(200))");
		create.addBatch("CREATE TABLE IF NOT EXISTS productos_new(" + "id INT PRIMARY KEY," + "producto VARCHAR(200),"
				+ "proveedor_id INT," + "categoria_id INT," + "cantidad_por_unidad VARCHAR(200),"
				+ "precio_unidad DOUBLE(6,2)," + "unidades_existencia INT," + "unidades_pedido INT,"
				+ "nivel_nuevo_pedido INT," + "suspendido INT)");
		create.executeBatch();

		con.commit();

		System.out.println("Tablas nuevas OK");
	}

	/**
	 * 
	 * @param linea
	 * @param titulos
	 * @return
	 */
	private static boolean isLineaTitulo(String linea) {
		return linea.equals(TITULO_CATEGORIA) || linea.equals(TITULO_PRODUCTOS) || linea.equals(TITULO_PROVEEDORES);
	}

	/**
	 * @param linea
	 * @return
	 */
	private static boolean isLineaDatos(String linea, String campos, String titulo) {
		return !linea.equals(campos) && !linea.equals(titulo) && !linea.isBlank();
	}

	/**
	 * Añade a la lista de proveedores los proveedores que tengan el id del producto
	 * 
	 * @param con
	 * @param listaProductos
	 * @param listaProveedores
	 * @throws SQLException
	 */
	private static void addProveedoresToList(Connection con) throws SQLException {

		listaProveedores = new ArrayList<Proveedores>();

		PreparedStatement sentenciaProveedor = con.prepareStatement(SELECT_PROVEEDORES);

		for (Productos p : listaProductos) {
			sentenciaProveedor.setInt(1, p.getProveedorId());

			ResultSet rsetProv = sentenciaProveedor.executeQuery();

			while (rsetProv.next()) {
				listaProveedores.add(new Proveedores(rsetProv.getInt("id"), rsetProv.getString("empresa"),
						rsetProv.getString("contacto")));
			}

		}
	}

	/**
	 * Devuelve la categoria especifica con el id dado
	 * 
	 * @param idCategoria
	 * @param con
	 * @return la categoria
	 * @throws SQLException
	 */
	private static Categorias getCategoria(int idCategoria, Connection con) throws SQLException {
		Categorias categoria = null;
		PreparedStatement sentencia = con.prepareStatement(SELECT_CATEGORIAS);
		sentencia.setInt(1, idCategoria);

		ResultSet rsetCat = sentencia.executeQuery();

		while (rsetCat.next()) {
			categoria = new Categorias(rsetCat.getInt("id"), rsetCat.getString("categoria"),
					rsetCat.getString("descripcion"));
		}

		return categoria;
	}

	/**
	 * Añade los productos con el id especificado a la lista de productos
	 * 
	 * @param idCategoria
	 * @param con
	 * @param listaProductos
	 * @throws SQLException
	 */
	private static void addProductosToList(int idCategoria, Connection con) throws SQLException {

		listaProductos = new ArrayList<Productos>();

		PreparedStatement sentencia = con.prepareStatement(SQL_SELECT_PRODUCTOS_CATID);
		sentencia.setInt(1, idCategoria);

		ResultSet resProds = sentencia.executeQuery();

		while (resProds.next()) {
			listaProductos.add(new Productos(resProds.getInt("id"), resProds.getString("producto"),
					resProds.getInt("proveedor_id"), resProds.getInt("categoria_id"),
					resProds.getString("cantidad_por_unidad"), resProds.getDouble("precio_unidad"),
					resProds.getInt("unidades_existencia"), resProds.getInt("unidades_pedido"),
					resProds.getInt("nivel_nuevo_pedido"), resProds.getInt("suspendido")));
		}

	}

	/**
	 * Inserta los usuarios de un fichero en la tabla usuarios
	 * 
	 * @param listaUsuarios
	 * @param con
	 * @throws SQLException
	 */
	private static void insertaUsuarios(List<String[]> listaUsuarios, Connection con) throws SQLException {

		con.setAutoCommit(Boolean.FALSE);

		PreparedStatement sentencia = con.prepareStatement(SQL_INSERT_USUARIOS);

		// Conseguir lista de los códigos de usuario
		List<String> user = getUser(listaUsuarios);

		int contador = 0;

		for (String[] usuario : listaUsuarios) {

			sentencia.setString(1, usuario[1]);
			sentencia.setString(2, usuario[0]);
			sentencia.setString(3, user.get(contador));
			sentencia.addBatch();

			contador++;

			// Ejecutar por lotes de 10
			if (contador % 10 == 0) {
				sentencia.executeBatch();
				sentencia.clearBatch();
			}

		}

		// Ejecutar sobrantes
		sentencia.executeBatch();
		con.commit();

		System.out.println("Insert usuarios ok");

	}

	/**
	 * Devuelve una lista con los códigos de los usuarios
	 * 
	 * @param listaUsuarios
	 * @return
	 */
	private static List<String> getUser(List<String[]> listaUsuarios) {

		String prefijo = "2DAM";

		List<String> listaUsers = new ArrayList<String>();

		for (String[] usuario : listaUsuarios) {
			String primeraLetraNombre = String.valueOf(usuario[1].charAt(0));
			String[] apellidos = usuario[0].split(" ");

			String letrasApellidos = "";

			for (int i = 0; i < apellidos.length; i++) {
				letrasApellidos += String.valueOf(apellidos[i].charAt(0));
			}

			listaUsers.add(prefijo + primeraLetraNombre + letrasApellidos);
		}

		return listaUsers;
	}

	/**
	 * Devuelve una lista con un array que contiene el nombre y los apellidos del
	 * usuario
	 * 
	 * @param fichero
	 * @return
	 */
	private static List<String[]> getListaUsuarios(String fichero) {

		List<String[]> listaUsuarios = new ArrayList<String[]>();
		BufferedReader reader = null;

		try {

			reader = new BufferedReader(new FileReader(fichero));

			String lineaLeida;

			// Leer archivo, guardar datos en la lista
			while ((lineaLeida = reader.readLine()) != null) {
				listaUsuarios.add(lineaLeida.split(","));
			}

			// Eliminar los espacios del nombre
			eliminaEspaciosNombre(listaUsuarios);

		} catch (FileNotFoundException e) {

			System.err.println("Error, no se encuentra el archivo o no es accesible: " + fichero);

		} catch (IOException e) {

			System.err.println("Error leyendo el archivo: " + e.getMessage());

		} finally {

			closeReader(reader);

		}

		return listaUsuarios;
	}

	/**
	 * Elimina los espacios del nombre del usuario
	 * 
	 * @param listaUsuarios
	 */
	private static void eliminaEspaciosNombre(List<String[]> listaUsuarios) {
		for (String[] arr : listaUsuarios) {
			arr[arr.length - 1] = arr[arr.length - 1].replaceAll("\\s+", "");
		}
	}

	/**
	 * Cierra el buffer
	 * 
	 * @param reader
	 */
	private static void closeReader(BufferedReader reader) {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea una tabla en una base de datos
	 * 
	 * @param fichero
	 * @param con
	 * @throws SQLException
	 */
	private static void createTableUsuarios(String sqlCreate, Connection con) throws SQLException {
		Statement sentencia = con.createStatement();
		sentencia.execute(sqlCreate);

		System.out.println("Tabla usuario OK");
	}

	/**
	 * Cierra la conexión
	 * 
	 * @param con
	 */
	private static void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the listaProductos
	 */
	public static List<Productos> getListaProductos() {
		return listaProductos;
	}

	/**
	 * @param listaProductos the listaProductos to set
	 */
	public static void setListaProductos(List<Productos> listaProductos) {
		DBManagement.listaProductos = listaProductos;
	}

	/**
	 * @return the listaProveedores
	 */
	public static List<Proveedores> getListaProveedores() {
		return listaProveedores;
	}

	/**
	 * @param listaProveedores the listaProveedores to set
	 */
	public static void setListaProveedores(List<Proveedores> listaProveedores) {
		DBManagement.listaProveedores = listaProveedores;
	}

}
