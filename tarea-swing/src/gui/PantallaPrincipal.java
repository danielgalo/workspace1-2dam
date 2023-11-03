package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import dto.Cliente;
import utils.ClienteManagement;

public class PantallaPrincipal extends JFrame {

	/** Serial version */
	private static final long serialVersionUID = 1L;

	/** Jpanel */
	private JPanel contentPane;

	/** Text area donde se plasma los clientes */
	private JTextArea textAreaClientes;

	/** Menu alta cliente */
	private JMenuItem mntmAltaCliente;

	/**
	 * Objeto ListadoProductos como variable de clase para que al abrirlo desde aquí
	 * se actualizen los datos introducidos en AltaProducto
	 */
	ListadoProductos lp = null;

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {

		lp = new ListadoProductos(this, false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		initializeMenu();

		JLabel lblNewLabel = new JLabel("Información de clientes");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 44, 188, 14);
		contentPane.add(lblNewLabel);

		initializeTextAreaClientes();

		initializeButtons();
	}

	/**
	 * Initializes all the buttons
	 */
	private void initializeButtons() {
	}

	/**
	 * Inicia el textArea
	 */
	private void initializeTextAreaClientes() {

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 414, 181);
		contentPane.add(scrollPane);
		textAreaClientes = new JTextArea();
		textAreaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		scrollPane.setViewportView(textAreaClientes);
		textAreaClientes.setEditable(false);
	}

	/**
	 * Inicia los elementos del menu
	 */
	private void initializeMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(10, 11, 143, 22);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Clientes");
		menuBar.add(mnNewMenu);

		mntmAltaCliente = new JMenuItem("Dar de alta");
		mntmAltaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				menuAltaClientePressed();

			}
		});
		mnNewMenu.add(mntmAltaCliente);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Dar de baja");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				menuBajaClientePressed();

			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_1 = new JMenu("Productos");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Alta producto");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				menuAltaProductoPressed();

			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listar productos");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVerProductosPressed();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
	}

	/**
	 * Acciones realizadas al presionar el menú de ver productos
	 */
	protected void btnVerProductosPressed() {

		lp.setVisible(true);

	}

	/**
	 * Acciones realizadas al presionar el menú de alta de productos
	 */
	protected void menuAltaProductoPressed() {
		AltaProducto ap = new AltaProducto(this, true);
		ap.setVisible(true);
	}

	/**
	 * Acciones realizadas al presionar el menú de baja de clientes
	 */
	protected void menuBajaClientePressed() {
		BajaCliente bc = new BajaCliente(this, true);
		bc.setVisible(true);
	}

	/**
	 * Acciones realizadas al presionar el menú de alta de clientes
	 */
	protected void menuAltaClientePressed() {
		AltaCliente ac = new AltaCliente(this, true);
		ac.setVisible(true);
	}

	/**
	 * Añade datos de un cliente al textArea de la pantalla principal
	 * 
	 * @param cliente
	 */
	public void actualizaTextAreaClientes() {

		for (Cliente c : ClienteManagement.listaClientes) {

			textAreaClientes.setText(textAreaClientes.getText() + c.getDatosCliente() + "\n");
		}

	}

	/**
	 * Vacía el textArea
	 */
	public void vaciaTextAreaClientes() {
		textAreaClientes.setText("");
	}

}
