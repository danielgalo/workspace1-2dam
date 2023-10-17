package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import dto.Producto;
import utils.ProductoManagement;

public class ListadoProductos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton btnVolver;
	private JTextArea textAreaProductos;
	private PantallaPrincipal pantallaPrincipal;
	private JScrollPane scrollPane;

	/**
	 * Create the dialog.
	 */
	public ListadoProductos(PantallaPrincipal padre, boolean modal) {
		getContentPane().setBackground(new Color(255, 255, 128));

		pantallaPrincipal = padre;

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Listado de productos");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 414, 14);
		getContentPane().add(lblNewLabel);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
			}
		});
		btnVolver.setBounds(173, 227, 89, 23);
		getContentPane().add(btnVolver);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 414, 170);
		getContentPane().add(scrollPane);

		textAreaProductos = new JTextArea();
		textAreaProductos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		scrollPane.setViewportView(textAreaProductos);
		textAreaProductos.setEditable(false);

	}

	/**
	 * Acutaliza la lista de productos
	 */
	public void actualizaLista() {
		for (Producto p : ProductoManagement.listaProductos) {

			textAreaProductos.setText(textAreaProductos.getText() + p.getDatosProducto() + "\n");
		}
	}
}
