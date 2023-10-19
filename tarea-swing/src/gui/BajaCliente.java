package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.Cliente;
import utils.ClienteManagement;

public class BajaCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombreElim;
	private JTextField txtApellidoElim;
	private JButton btnEliminar;
	private JButton btnVolver;
	private JLabel lblInfoClienteElim;
	PantallaPrincipal pantallaPrincipal;
	Cliente cliente;

	/**
	 * Create the dialog.
	 */
	public BajaCliente(PantallaPrincipal padre, boolean modal) {
		getContentPane().setBackground(new Color(255, 255, 128));

		pantallaPrincipal = padre;
		setTitle("Baja cliente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		initializeLabels();

		initializeTextFields();

		initializeButtons();

	}

	/**
	 * Inicia los botones
	 */
	private void initializeButtons() {
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnEliminaClientePressed();

			}
		});
		btnEliminar.setBounds(335, 227, 89, 23);
		getContentPane().add(btnEliminar);

		btnVolver = new JButton("Volver");
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
	 * Inicia los text fields
	 */
	private void initializeTextFields() {
		txtNombreElim = new JTextField();
		txtNombreElim.setBounds(202, 8, 222, 20);
		getContentPane().add(txtNombreElim);
		txtNombreElim.setColumns(10);

		txtApellidoElim = new JTextField();
		txtApellidoElim.setColumns(10);
		txtApellidoElim.setBounds(202, 33, 222, 20);
		getContentPane().add(txtApellidoElim);
	}

	/**
	 * Inicia los labels
	 */
	private void initializeLabels() {
		JLabel lblNewLabel = new JLabel("Nombre del cliente a eliminar:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 182, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblApellidosDelCliente = new JLabel("Apellidos del cliente a eliminar:");
		lblApellidosDelCliente.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblApellidosDelCliente.setBounds(10, 36, 182, 14);
		getContentPane().add(lblApellidosDelCliente);

		lblInfoClienteElim = new JLabel("  ");
		lblInfoClienteElim.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblInfoClienteElim.setBounds(10, 108, 414, 14);
		getContentPane().add(lblInfoClienteElim);
	}

	/**
	 * Acciones realizadas al presionar el botón de volver
	 */
	protected void btnVolverPressed() {
		pantallaPrincipal.vaciaTextAreaClientes();
		pantallaPrincipal.actualizaTextAreaClientes();
		setVisible(false);
	}

	/**
	 * Acciones realizadas al presionar el botón de eliminar clientes
	 */
	protected void btnEliminaClientePressed() {

		darBajaCliente();

	}

	/**
	 * Da de baja a un cliente de la lista de clientes, tomando como referencia sus
	 * nombres y apellidos
	 */
	private void darBajaCliente() {

		String nombreEliminar = txtNombreElim.getText();
		String apellidosEliminar = txtApellidoElim.getText();

		ClienteManagement.eliminaClientes(nombreEliminar, apellidosEliminar);
		JOptionPane.showMessageDialog(null, "Cliente eliminado.");

	}
}
