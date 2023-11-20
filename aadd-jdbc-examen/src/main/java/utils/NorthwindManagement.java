package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Products;

/**
 * Clase usada para gestionar métodos en la base de datos Northwind
 */
public class NorthwindManagement {

	private static final String SQL_CREATE_PRODUCTS = "CREATE TABLE IF NOT EXISTS `ProductosNoVenta` (\r\n"
			+ "    `ProductID` INTEGER NOT NULL,\r\n" + "    `ProductName` VARCHAR(40) NOT NULL,\r\n"
			+ "    `SupplierID` INTEGER,\r\n" + "    `CategoryID` INTEGER,\r\n"
			+ "    `QuantityPerUnit` VARCHAR(20),\r\n" + "    `UnitPrice` DECIMAL(10,4) DEFAULT 0,\r\n"
			+ "    `UnitsInStock` SMALLINT(2) DEFAULT 0,\r\n" + "    `UnitsOnOrder` SMALLINT(2) DEFAULT 0,\r\n"
			+ "    `ReorderLevel` SMALLINT(2) DEFAULT 0,\r\n" + "    `Discontinued` BIT NOT NULL DEFAULT 0,\r\n"
			+ "    CONSTRAINT `PK_Products` PRIMARY KEY (`ProductID`)\r\n" + ");";

	private static final String SQL_SELECT_DISCONTINUED_PRODUCTS = "SELECT * FROM Products WHERE discontinued = 1";

	private static final String SQL_INSERT_DISCONTINUED_PRODUCTS = "INSERT INTO productosnoventa (productid, categoryid, supplierid, productname, quantityperunit, unitPrice, unitsInStock,unitsOnOrder, reorderLevel, discontinued)VALUES(?,?,?,?,?,?,?,?,?,?)";

	private static final String SQL_SELECT_PRODUCTS_CAT = "SELECT Products.* FROM Products INNER JOIN categories ON products.categoryid = categories.categoryid WHERE categories.categoryname = ? AND products.discontinued = 0;\r\n"
			+ "";

	private static List<Products> productosNoVenta = new ArrayList<>();

	private static List<Products> productosCat = new ArrayList<>();

	private static List<Products> productosBin = new ArrayList<>();

	/**
	 * Crea una tabla ProductosNoventa en la que inserta datos de la tabla productos
	 * los cuales son descontinuados
	 */
	public static void crearTablaProductosNoVenta() {

		Connection con = Conexion.conectar();

		try {
			// Crear tabla productosnoventa
			createTableProductosNoVenta(con);

			// Buscar en productos datos que tengan el campo discontinued=1
			ResultSet rsetProducts = getResultSetProductosNoVenta(con);

			// Insertar datos
			insertProductos(productosNoVenta, con, rsetProducts);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	/**
	 * Inserta los datos de un fichero csv a la tabla ProductosNoVenta
	 * 
	 * @param ficheroCsv debe ser un fichero existente con formato CSV
	 */
	public static void cargarProductos(String ficheroCsv) {

		Connection con = Conexion.conectar();
		List<String[]> datos = new ArrayList<String[]>();

		// Si el fichero existe y es csv
		if (isFicheroExtension(ficheroCsv, ".csv")) {

			BufferedReader reader = null;

			try {
				reader = new BufferedReader(new FileReader(ficheroCsv));

				// Saltar la cabecera
				reader.readLine();

				String lineRead;

				// Leer archivo, guardar filas
				while ((lineRead = reader.readLine()) != null) {
					datos.add(lineRead.split(","));
				}

				// Quitar comillas
				for (String[] filas : datos) {
					for (String str : filas) {
						str.replaceAll("\"", "");
					}
				}

				for (String[] strings : datos) {
					for (int i = 0; i < strings.length; i++) {
						System.out.print(strings[i] + " | ");
					}
					System.out.println();
				}

				// Insertar productos en lotes
				insertaProductosBatch(con, datos);

			} catch (IOException | SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnection(con);
			}

		} else {
			System.err.println("El archivo proporcionado no existe o no es CSV.");
		}

	}

	/**
	 * Se almacenan los productos de la tabla Products que tengan el campo
	 * discontinued=0, y que sean de la categoría indicada como parámetro.
	 * 
	 * @param nombreCategoria nombre de la categoria del producto
	 * @param ficheroTexto    si es true se crea archivo CSV. Si es false, se crea
	 *                        archivo dat
	 */
	public static void productosToFichero(String nombreCategoria, boolean ficheroTexto) {

		Connection con = Conexion.conectar();
		BufferedWriter writerCSV = null;
		ObjectOutputStream oos = null;
		String nombreFichero = "productos_cat_" + nombreCategoria;

		try {

			// Consulta
			PreparedStatement select = con.prepareStatement(SQL_SELECT_PRODUCTS_CAT);
			select.setString(1, nombreCategoria);
			ResultSet resultado = select.executeQuery();

			// Guarda consulta en lista
			resultSetToList(productosCat, resultado);

			if (ficheroTexto) {
				// Crear fichero csv e insertarle los datos
				writerCSV = creaFicheroCSV(nombreFichero, nombreCategoria, con, writerCSV, resultado);
			} else {
				// Crear archivo dat
				oos = creaFicheroBinario(nombreFichero);

			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			closeBuffer(writerCSV);
			closeObjectStream(oos);
			closeConnection(con);
		}

	}

	/**
	 * Procesa el fichero binario e inserta sus líneas en la tabla ProductosNoVenta
	 * 
	 * @param ficheroBinario debe ser un fichero binario
	 */
	public static void cargarProductosBinario(String ficheroBinario) {

		Connection con = Conexion.conectar();

		// Comprobar que el fichero sea binario
		if (isFicheroExtension(ficheroBinario, ".dat")) {
			ObjectInputStream ois = null;

			try {
				ois = new ObjectInputStream(new FileInputStream(ficheroBinario));

				while (true) {
					Object objeto = ois.readObject();
					// Si es un producto, guardarlo en lista de productos binario
					if (objeto instanceof Products) {
						Products producto = (Products) objeto;
						productosBin.add(producto);

					} else if (objeto == null) {
						break;
					}
				}

				// Insertar los datos obtenidos
				con.setAutoCommit(Boolean.FALSE);
				PreparedStatement insert = con.prepareStatement(SQL_SELECT_DISCONTINUED_PRODUCTS);

				int contador = 0;

				for (Products product : productosBin) {

					product.imprimirAtributos();

					insert.setInt(1, product.getProductId());
					insert.setInt(2, product.getCategoryId());
					insert.setInt(3, product.getSupplierId());
					insert.setString(4, product.getProductName());
					insert.setString(5, product.getQuantityPerUnit());
					insert.setBigDecimal(6, product.getUnitPrice());
					insert.setShort(7, product.getUnitsInStock());
					insert.setShort(8, product.getUnitsOnOrder());
					insert.setShort(9, product.getReorderLevel());
					insert.setBoolean(10, product.isDiscontinued());

					contador++;

					// Ejecutar por lotes de 10
					if (contador % 10 == 0) {
						insert.executeBatch();
						insert.clearBatch();
					}

					// Ejecutar sobrantes
					insert.executeBatch();
					con.commit();
				}

			} catch (IOException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnection(con);
			}

		} else {
			System.err.println("Error, el archivo no es accesible o no es binario");
		}
	}

	/**
	 * Crea un fichero binario con los datos de los productos de la lista de
	 * productosCat
	 * 
	 * @param nombreFichero
	 * @return un ObjectOutputStream
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static ObjectOutputStream creaFicheroBinario(String nombreFichero)
			throws IOException, FileNotFoundException {
		ObjectOutputStream oos;
		nombreFichero += ".dat";
		oos = new ObjectOutputStream(new FileOutputStream(nombreFichero));

		// Escribir producto
		for (Products product : productosCat) {
			oos.writeObject(product);
		}
		return oos;
	}

	/**
	 * @param oos
	 */
	private static void closeObjectStream(ObjectOutputStream oos) {
		if (oos != null) {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Crea un fichero CSV con los datos de una lista
	 * 
	 * @param nombreCategoria
	 * @param con
	 * @param writerCSV
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	private static BufferedWriter creaFicheroCSV(String nombreFichero, String nombreCategoria, Connection con,
			BufferedWriter writerCSV, ResultSet resultado) throws SQLException, IOException {
		// Crear archivo csv
		if (nombreCategoria != null) {
			// Formato del nombre del fichero
			nombreFichero += ".csv";

			// Hacer consulta, insertar en fichero

			// Crear fichero
			writerCSV = new BufferedWriter(new FileWriter(nombreFichero));

			// Escribir cabecera
			writerCSV.write(
					"productId,categoryId,supplierId,productName,quantityPerUnit,unitPrice,unitsInStock,unitsOnOrder,reorderLevel,discontinued");
			writerCSV.newLine();
			// Escribir datos de los productos
			for (Products product : productosCat) {
				writerCSV.write(product.getProductId() + ";" + product.getCategoryId() + ";" + product.getSupplierId()
						+ product.getProductName() + ";" + product.getQuantityPerUnit() + ";" + product.getUnitPrice()
						+ ";" + product.getUnitsInStock() + ";" + product.getUnitsOnOrder() + ";"
						+ product.getReorderLevel() + ";" + product.isDiscontinued());
				writerCSV.newLine();
			}

		} else {
			System.err.println("Error, el nombre de categoría no puede ser nulo.");
		}
		return writerCSV;
	}

	/**
	 * Inserta los productos en lotes de 10 de una lista de filas de datos
	 * 
	 * @param con
	 * @param datos
	 * @throws SQLException
	 */
	private static void insertaProductosBatch(Connection con, List<String[]> datos) throws SQLException {
		con.setAutoCommit(false);

		// Insertar datos
		PreparedStatement stmntInsert = con.prepareStatement(SQL_INSERT_DISCONTINUED_PRODUCTS);

		int contador = 0;

		// Obtengo el máximo id ya que no es autoincremental y no aparece en el fichero
		Statement selectMaxId = con.createStatement();
		ResultSet resultadoId = selectMaxId.executeQuery("SELECT MAX(productid) FROM products");

		int maxId = 0;

		if (resultadoId.next()) {
			maxId = resultadoId.getInt(1);
		}

		for (String[] fila : datos) {
			stmntInsert.setInt(1, maxId);
			stmntInsert.setInt(2, Integer.valueOf(fila[0]));
			stmntInsert.setInt(3, Integer.valueOf(fila[1]));
			stmntInsert.setString(4, fila[2]);
			stmntInsert.setString(5, fila[3]);
			stmntInsert.setBigDecimal(6, new BigDecimal(fila[4]));
			stmntInsert.setShort(7, Short.valueOf(fila[5]));
			stmntInsert.setShort(8, Short.valueOf(fila[6]));
			stmntInsert.setShort(9, Short.valueOf(fila[7]));
			stmntInsert.setBoolean(10, Boolean.valueOf(fila[8]));

			maxId++;
			contador++;

			// Ejecutar por lotes de 10
			if (contador % 10 == 0) {
				stmntInsert.addBatch();
				stmntInsert.executeBatch();
				stmntInsert.clearBatch();
			}
		}

		// Ejecutar sobrantes
		stmntInsert.executeBatch();
		con.commit();
	}

	/**
	 * @param writer
	 */
	private static void closeBuffer(BufferedWriter writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Comprueba que un fichero tenga la extensión dada
	 * 
	 * @param nombreArchivo
	 * @param extension
	 * @return true si el fichero coincide con la extensión y existe
	 */
	private static boolean isFicheroExtension(String nombreArchivo, String extension) {

		File archivo = new File(nombreArchivo);

		// Comprobar si el archivo existe y si es un archivo
		if (archivo.exists() && archivo.isFile()) {
			return archivo.getName().toLowerCase().endsWith(extension);
		}

		return false;
	}

	/**
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
	 * 
	 * @param con
	 * @param rsetProducts
	 * @throws SQLException
	 */
	private static void insertProductos(List<Products> products, Connection con, ResultSet rsetProducts)
			throws SQLException {

		// Guardar los productos de la consulta en la lista
		resultSetToList(products, rsetProducts);

		// Insertarlos en la tabla
		PreparedStatement stmntInsert = con.prepareStatement(SQL_INSERT_DISCONTINUED_PRODUCTS);

		for (Products product : products) {

			product.imprimirAtributos();

			stmntInsert.setInt(1, product.getProductId());
			stmntInsert.setInt(2, product.getCategoryId());
			stmntInsert.setInt(3, product.getSupplierId());
			stmntInsert.setString(4, product.getProductName());
			stmntInsert.setString(5, product.getQuantityPerUnit());
			stmntInsert.setBigDecimal(6, product.getUnitPrice());
			stmntInsert.setShort(7, product.getUnitsInStock());
			stmntInsert.setShort(8, product.getUnitsOnOrder());
			stmntInsert.setShort(9, product.getReorderLevel());
			stmntInsert.setBoolean(10, product.isDiscontinued());

			stmntInsert.executeUpdate();
		}
	}

	/**
	 * @param products
	 * @param rsetProducts
	 * @throws SQLException
	 */
	private static void resultSetToList(List<Products> products, ResultSet rsetProducts) throws SQLException {
		while (rsetProducts.next()) {

			products.add(new Products(rsetProducts.getInt("ProductID"), rsetProducts.getInt("CategoryID"),
					rsetProducts.getInt("SupplierID"), rsetProducts.getString("ProductName"),
					rsetProducts.getString("QuantityPerUnit"), rsetProducts.getBigDecimal("UnitPrice"),
					rsetProducts.getShort("UnitsInStock"), rsetProducts.getShort("UnitsOnOrder"),
					rsetProducts.getShort("ReorderLevel"), rsetProducts.getBoolean("Discontinued")));
		}
	}

	/**
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private static ResultSet getResultSetProductosNoVenta(Connection con) throws SQLException {
		Statement stmntSelectProd = con.createStatement();
		ResultSet rsetProducts = stmntSelectProd.executeQuery(SQL_SELECT_DISCONTINUED_PRODUCTS);
		return rsetProducts;
	}

	/**
	 * @param con
	 * @throws SQLException
	 */
	private static void createTableProductosNoVenta(Connection con) throws SQLException {
		Statement stmntCreateProd = con.createStatement();
		stmntCreateProd.execute(SQL_CREATE_PRODUCTS);
	}

}
