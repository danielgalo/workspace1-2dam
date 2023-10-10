package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
		pantallaPrincipal = padre;
		setTitle("Baja cliente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre del cliente a eliminar:");
		lblNewLabel.setBounds(10, 11, 182, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblApellidosDelCliente = new JLabel("Apellidos del cliente a eliminar:");
		lblApellidosDelCliente.setBounds(10, 36, 182, 14);
		getContentPane().add(lblApellidosDelCliente);

		txtNombreElim = new JTextField();
		txtNombreElim.setBounds(202, 8, 222, 20);
		getContentPane().add(txtNombreElim);
		txtNombreElim.setColumns(10);

		txtApellidoElim = new JTextField();
		txtApellidoElim.setColumns(10);
		txtApellidoElim.setBounds(202, 33, 222, 20);
		getContentPane().add(txtApellidoElim);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnEliminaClientePressed();

			}
		});
		btnEliminar.setBounds(335, 227, 89, 23);
		getContentPane().add(btnEliminar);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnVolverPressed();

			}
		});
		btnVolver.setBounds(10, 227, 89, 23);
		getContentPane().add(btnVolver);

		lblInfoClienteElim = new JLabel("  ");
		lblInfoClienteElim.setBounds(10, 108, 414, 14);
		getContentPane().add(lblInfoClienteElim);

	}

	protected void btnVolverPressed() {
		pantallaPrincipal.vaciaTextAreaClientes();
		pantallaPrincipal.actualizaTextAreaClientes();
		setVisible(false);
	}

	protected void btnEliminaClientePressed() {

		darBajaCliente();
		pantallaPrincipal.actualizaTextAreaClientes();

	}

	private void darBajaCliente() {

		String nombreEliminar = txtNombreElim.getText();
		String apellidosEliminar = txtApellidoElim.getText();

		ClienteManagement.eliminaClientes(nombreEliminar, apellidosEliminar);

	}
}
