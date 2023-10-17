package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
	private static final int EDAD_DEFECTO = 18;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEdad;
	private JButton btnAlta;
	private JComboBox<?> cboxProvincia;
	private JButton btnVolver;
	private PantallaPrincipal pantallaPrincipal;
	private JLabel lblInfoClienteAlta;

	/**
	 * Create the dialog.
	 */

	public AltaCliente(PantallaPrincipal padre, boolean modal) {
		getContentPane().setBackground(new Color(255, 255, 128));

		pantallaPrincipal = padre;

		setTitle("Alta cliente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		initializeLabels();

		initializeTextFields();

		initializeComboBoxes();

		initializeButtons();

	}

	/**
	 * Inicia los botones
	 */
	private void initializeButtons() {
		btnAlta = new JButton("Dar de alta");
		btnAlta.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnAlta.setBackground(new Color(255, 255, 255));
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnAltaClientePressed();

			}
		});
		btnAlta.setBounds(324, 227, 100, 23);
		getContentPane().add(btnAlta);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVolverPressed();
			}
		});
		btnVolver.setBounds(10, 227, 89, 23);
		getContentPane().add(btnVolver);
	}

	/**
	 * Inicia los combo boxes
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeComboBoxes() {
		cboxProvincia = new JComboBox();
		cboxProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cboxProvincia.setModel(new DefaultComboBoxModel(
				new String[] { "Málaga", "Granada", "Sevilla", "Córdoba", "Cádiz", "Huelva", "Jaén", "Almería" }));
		cboxProvincia.setBounds(179, 89, 245, 22);
		getContentPane().add(cboxProvincia);
	}

	/**
	 * Inicia los text fields
	 */
	private void initializeTextFields() {
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtNombre.setBounds(179, 8, 245, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(179, 33, 245, 20);
		getContentPane().add(txtApellidos);

		txtEdad = new JTextField();
		txtEdad.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtEdad.setColumns(10);
		txtEdad.setBounds(179, 58, 245, 20);
		getContentPane().add(txtEdad);
	}

	/**
	 * Inicia los labels
	 */
	private void initializeLabels() {
		JLabel lblNewLabel = new JLabel("Nombre del cliente:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 124, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblApellidosDelCliente = new JLabel("Apellidos del cliente:");
		lblApellidosDelCliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblApellidosDelCliente.setBounds(10, 36, 124, 14);
		getContentPane().add(lblApellidosDelCliente);

		JLabel lblEdadElCliente = new JLabel("Edad el cliente:");
		lblEdadElCliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblEdadElCliente.setBounds(10, 61, 124, 14);
		getContentPane().add(lblEdadElCliente);

		lblInfoClienteAlta = new JLabel(" ");
		lblInfoClienteAlta.setBounds(10, 150, 414, 14);
		getContentPane().add(lblInfoClienteAlta);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblProvincia.setBounds(10, 93, 124, 14);
		getContentPane().add(lblProvincia);
	}

	/**
	 * Acciones realizadas al pulsar el botón de volver
	 */
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
		int edadCliente;
		try {
			edadCliente = Integer.parseInt(txtEdad.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			edadCliente = EDAD_DEFECTO;
		}
		String provinciaCliente = (String) cboxProvincia.getSelectedItem();

		// Creo cliente con datos
		Cliente cliente = new Cliente(nombreCliente, apellidosCliente, edadCliente, provinciaCliente);

		ClienteManagement.addCliente(cliente);

		lblInfoClienteAlta.setText("Dado de alta: " + cliente.getDatosCliente());

	}

}
