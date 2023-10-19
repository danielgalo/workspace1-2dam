package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dto.Producto;
import utils.ProductoManagement;

public class AltaProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final double PRECIO_DEFECTO = 0;
	private JTextField txtNombreProd;
	private JTextField txtPrecio;
	private JButton btnAddProducto;
	private JButton btnVolver;
	private JComboBox cboxPerecedero;
	private JLabel lblInfo;
	private PantallaPrincipal pantallaPrincipal;
	private ListadoProductos listadoProductos;

	/**
	 * Create the dialog.
	 */
	public AltaProducto(PantallaPrincipal padre, boolean modal) {
		getContentPane().setBackground(new Color(255, 255, 128));

		pantallaPrincipal = padre;

		setTitle("Alta de producto");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		initializeLabels();

		initializeTextFields();

		initializeComboBox();

		initializeButtons();

	}

	/**
	 * Inicia los botones
	 */
	private void initializeButtons() {
		btnAddProducto = new JButton("Añadir");
		btnAddProducto.setBackground(new Color(255, 255, 255));
		btnAddProducto.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAddProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnAltaProductoPressed();

			}
		});
		btnAddProducto.setBounds(335, 227, 89, 23);
		getContentPane().add(btnAddProducto);

		btnVolver = new JButton("Volver");
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnVolverPressed();

			}
		});
		btnVolver.setBounds(10, 227, 89, 23);
		getContentPane().add(btnVolver);
	}

	/**
	 * Inicia los combobox
	 */
	private void initializeComboBox() {
		cboxPerecedero = new JComboBox();
		cboxPerecedero.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboxPerecedero.setModel(new DefaultComboBoxModel(new String[] { "Sí", "No" }));
		cboxPerecedero.setBounds(176, 57, 49, 22);
		getContentPane().add(cboxPerecedero);
	}

	/**
	 * Inicia los text fields
	 */
	private void initializeTextFields() {
		txtNombreProd = new JTextField();
		txtNombreProd.setBounds(176, 8, 248, 20);
		getContentPane().add(txtNombreProd);
		txtNombreProd.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(176, 33, 248, 20);
		getContentPane().add(txtPrecio);
	}

	/**
	 * Inicia los labels
	 */
	private void initializeLabels() {
		JLabel lblNewLabel = new JLabel("Nombre del producto:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 156, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblPrecioUnitario = new JLabel("Precio unitario:");
		lblPrecioUnitario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPrecioUnitario.setBounds(10, 36, 156, 14);
		getContentPane().add(lblPrecioUnitario);

		JLabel lblPerecedero = new JLabel("Perecedero:");
		lblPerecedero.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPerecedero.setBounds(10, 61, 156, 14);
		getContentPane().add(lblPerecedero);

		lblInfo = new JLabel(" ");
		lblInfo.setBounds(10, 128, 414, 14);
		getContentPane().add(lblInfo);
	}

	/**
	 * Acciones realizadas al presionar el botón de volver
	 */
	protected void btnVolverPressed() {

		pantallaPrincipal.lp.actualizaLista();

		setVisible(false);
	}

	/**
	 * Acciones realizadas al presionar el botón de alta producto
	 */
	protected void btnAltaProductoPressed() {

		altaProducto();

		for (Producto p : ProductoManagement.listaProductos) {
			System.out.println(p.getDatosProducto());
		}

		System.out.println("TAMAÑO " + ProductoManagement.listaProductos.size());

	}

	/**
	 * Dar de alta un producto
	 */
	private void altaProducto() {

		String nombreProd = txtNombreProd.getText();
		double precioProd;
		try {
			precioProd = Double.parseDouble(txtPrecio.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			precioProd = PRECIO_DEFECTO;
		}

		String perecederoStr = (String) cboxPerecedero.getSelectedItem();

		boolean perecedero;

		if (perecederoStr.equalsIgnoreCase("Sí")) {
			perecedero = Boolean.TRUE;
		} else {
			perecedero = Boolean.FALSE;
		}

		Producto producto = new Producto(nombreProd, precioProd, perecedero);

		ProductoManagement.addProducto(producto);

		lblInfo.setText("Producto dado de alta: " + producto.getDatosProducto());

	}
}
