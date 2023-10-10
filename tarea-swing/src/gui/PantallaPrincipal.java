package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import dto.Cliente;
import dto.Producto;
import utils.ClienteManagement;

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textAreaClientes;
	private JMenuItem mntmAltaCliente;
	List<Cliente> listaClientes;
	List<Producto> listaProducto;

	List<String> contenido;

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		contenido = new ArrayList<String>();
		listaClientes = new ArrayList<Cliente>();
		listaProducto = new ArrayList<Producto>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
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
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listar productos");
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JLabel lblNewLabel = new JLabel("Información de clientes");
		lblNewLabel.setBounds(10, 44, 143, 14);
		contentPane.add(lblNewLabel);

		textAreaClientes = new JTextArea();
		textAreaClientes.setEditable(false);
		textAreaClientes.setBounds(10, 69, 414, 125);
		contentPane.add(textAreaClientes);

		JButton btnNewButton = new JButton("Ver productos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(150, 227, 128, 23);
		contentPane.add(btnNewButton);
	}

	protected void menuBajaClientePressed() {
		BajaCliente bc = new BajaCliente(this, false);
		bc.setVisible(true);
	}

	protected void menuAltaClientePressed() {
		AltaCliente ac = new AltaCliente(this, false);
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

	public void vaciaTextAreaClientes() {
		textAreaClientes.setText("");
	}

}
