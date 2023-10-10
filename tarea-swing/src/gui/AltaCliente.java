package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.Cliente;
import utils.ClienteManagement;

public class AltaCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEdad;
	private JButton btnAlta;
	private JComboBox cboxProvincia;
	private JButton btnVolver;
	private PantallaPrincipal pantallaPrincipal;
	private JLabel lblInfoClienteAlta;

	/**
	 * Create the dialog.
	 */
	public AltaCliente(PantallaPrincipal padre, boolean modal) {

		pantallaPrincipal = padre;

		setTitle("Alta cliente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre del cliente:");
		lblNewLabel.setBounds(10, 11, 124, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblApellidosDelCliente = new JLabel("Apellidos del cliente:");
		lblApellidosDelCliente.setBounds(10, 36, 124, 14);
		getContentPane().add(lblApellidosDelCliente);

		JLabel lblEdadElCliente = new JLabel("Edad el cliente:");
		lblEdadElCliente.setBounds(10, 61, 124, 14);
		getContentPane().add(lblEdadElCliente);

		txtNombre = new JTextField();
		txtNombre.setBounds(179, 8, 245, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(179, 33, 245, 20);
		getContentPane().add(txtApellidos);

		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(179, 58, 245, 20);
		getContentPane().add(txtEdad);

		cboxProvincia = new JComboBox();
		cboxProvincia.setModel(new DefaultComboBoxModel(
				new String[] { "Málaga", "Granada", "Sevilla", "Córdoba", "Cádiz", "Huelva", "Jaén", "Almería" }));
		cboxProvincia.setBounds(179, 89, 245, 22);
		getContentPane().add(cboxProvincia);

		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 93, 124, 14);
		getContentPane().add(lblProvincia);

		btnAlta = new JButton("Dar de alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnAltaClientePressed();

			}
		});
		btnAlta.setBounds(324, 227, 100, 23);
		getContentPane().add(btnAlta);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVolverPressed();
			}
		});
		btnVolver.setBounds(10, 227, 89, 23);
		getContentPane().add(btnVolver);

		lblInfoClienteAlta = new JLabel(" ");
		lblInfoClienteAlta.setBounds(10, 150, 414, 14);
		getContentPane().add(lblInfoClienteAlta);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	}

	protected void btnVolverPressed() {

		pantallaPrincipal.actualizaTextAreaClientes();
		setVisible(false);
	}

	/**
	 * Acciones para hacer cuando se presiona el boton de alta
	 */
	protected void btnAltaClientePressed() {

		altaCliente();

		for (Cliente c : ClienteManagement.listaClientes) {
			System.out.println(c.getDatosCliente());
		}

		System.out.println("TAMAÑO " + ClienteManagement.listaClientes.size());
	}

	/**
	 * Da de alta en una lista al cliente con los datos rellenados
	 */
	private void altaCliente() {

		// Recojo datos
		String nombreCliente = txtNombre.getText();
		String apellidosCliente = txtApellidos.getText();
		int edadCliente = Integer.parseInt(txtEdad.getText());
		String provinciaCliente = (String) cboxProvincia.getSelectedItem();

		// Creo cliente con datos
		Cliente cliente = new Cliente(nombreCliente, apellidosCliente, edadCliente, provinciaCliente);

		ClienteManagement.addCliente(cliente);

		lblInfoClienteAlta.setText("Dado de alta: " + cliente.getDatosCliente());

	}

}
