package gui;

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

		pantallaPrincipal = padre;

		setTitle("Alta de producto");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre del producto:");
		lblNewLabel.setBounds(10, 11, 156, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblPrecioUnitario = new JLabel("Precio unitario:");
		lblPrecioUnitario.setBounds(10, 36, 156, 14);
		getContentPane().add(lblPrecioUnitario);

		JLabel lblPerecedero = new JLabel("Perecedero:");
		lblPerecedero.setBounds(10, 61, 156, 14);
		getContentPane().add(lblPerecedero);

		txtNombreProd = new JTextField();
		txtNombreProd.setBounds(176, 8, 248, 20);
		getContentPane().add(txtNombreProd);
		txtNombreProd.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(176, 33, 248, 20);
		getContentPane().add(txtPrecio);

		cboxPerecedero = new JComboBox();
		cboxPerecedero.setModel(new DefaultComboBoxModel(new String[] { "Sí", "No" }));
		cboxPerecedero.setBounds(176, 57, 49, 22);
		getContentPane().add(cboxPerecedero);

		btnAddProducto = new JButton("Añadir");
		btnAddProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnAltaProductoPressed();

			}
		});
		btnAddProducto.setBounds(335, 227, 89, 23);
		getContentPane().add(btnAddProducto);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnVolverPressed();

			}
		});
		btnVolver.setBounds(10, 227, 89, 23);
		getContentPane().add(btnVolver);

		lblInfo = new JLabel(" ");
		lblInfo.setBounds(10, 128, 414, 14);
		getContentPane().add(lblInfo);

	}

	protected void btnVolverPressed() {

		pantallaPrincipal.lp.actualizaLista();

		setVisible(false);
	}

	protected void btnAltaProductoPressed() {

		altaProducto();

		for (Producto p : ProductoManagement.listaProductos) {
			System.out.println(p.getDatosProducto());
		}

		System.out.println("TAMAÑO " + ProductoManagement.listaProductos.size());

	}

	private void altaProducto() {

		String nombreProd = txtNombreProd.getText();
		double precioProd = Double.parseDouble(txtPrecio.getText());

		String perecederoStr = (String) cboxPerecedero.getSelectedItem();

		boolean perecedero;

		if (perecederoStr.equalsIgnoreCase("Sí")) {
			perecedero = Boolean.TRUE;
		} else {
			perecedero = Boolean.FALSE;
		}

		Producto producto = new Producto(nombreProd, precioProd, perecedero);

		ProductoManagement.addProducto(producto);

		lblInfo.setText("Producto dado de alta: ");

	}
}
