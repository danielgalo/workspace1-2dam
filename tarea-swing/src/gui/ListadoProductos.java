package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import dto.Producto;
import utils.ProductoManagement;

public class ListadoProductos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton btnVolver;
	private JTextArea textAreaProductos;
	private PantallaPrincipal pantallaPrincipal;

	/**
	 * Create the dialog.
	 */
	public ListadoProductos(PantallaPrincipal padre, boolean modal) {

		pantallaPrincipal = padre;

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Listado de productos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 414, 14);
		getContentPane().add(lblNewLabel);

		textAreaProductos = new JTextArea();
		textAreaProductos.setEditable(false);
		textAreaProductos.setBounds(10, 36, 414, 150);
		getContentPane().add(textAreaProductos);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
			}
		});
		btnVolver.setBounds(173, 227, 89, 23);
		getContentPane().add(btnVolver);

	}

	public void actualizaLista() {
		for (Producto p : ProductoManagement.listaProductos) {

			textAreaProductos.setText(textAreaProductos.getText() + p.getDatosProducto() + "\n");
		}
	}
}
