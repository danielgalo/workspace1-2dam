package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import dao.Administrador;
import dao.Cliente;
import utils.PersonaManagement;

/**
 * Pantalla Inicial de la aplicacion
 */
public class PantallaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JButton btnLogin;
	private JButton btnRegister;
	private JTextField txtPassword;

	/**
	 * Create the frame.
	 */
	public PantallaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelLogo = new JPanel();
		panelLogo.setBackground(new Color(128, 255, 255));
		contentPane.add(panelLogo, BorderLayout.NORTH);

		JLabel imagenLogo = new JLabel("GYM Picasso");
		imagenLogo.setForeground(new Color(0, 64, 128));
		imagenLogo.setFont(new Font("Tahoma", Font.BOLD, 20));
		imagenLogo.setIcon(new ImageIcon(PantallaInicial.class.getResource("/images/logoApp.png")));
		panelLogo.add(imagenLogo);

		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(new Color(0, 128, 255));
		contentPane.add(panelInfo, BorderLayout.EAST);

		JLabel lblNewLabel = new JLabel("Bienvenido a la aplicación Gym Picasso");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(255, 255, 255));

		JPanel panelCampos = new JPanel();
		panelCampos.setBackground(new Color(128, 255, 255));
		GroupLayout gl_panelInfo = new GroupLayout(panelInfo);
		gl_panelInfo.setHorizontalGroup(gl_panelInfo.createParallelGroup(Alignment.LEADING)
				.addComponent(panelCampos, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING,
						gl_panelInfo.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel).addContainerGap()));
		gl_panelInfo.setVerticalGroup(gl_panelInfo.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panelInfo.createSequentialGroup().addGap(39).addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
						.addComponent(panelCampos, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)));

		JLabel lblNewLabel_2 = new JLabel("Usuario");
		lblNewLabel_2.setForeground(new Color(0, 64, 128));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 93, 69, 14);

		JLabel lblNewLabel_2_1 = new JLabel("Contraseña");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setForeground(new Color(0, 64, 128));
		lblNewLabel_2_1.setBounds(10, 119, 77, 14);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(113, 90, 130, 20);
		txtUsuario.setColumns(10);
		panelCampos.setLayout(null);
		panelCampos.add(lblNewLabel_2_1);
		panelCampos.add(lblNewLabel_2);
		panelCampos.add(txtUsuario);

		btnLogin = new JButton("Iniciar sesión");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginPressed();
			}
		});
		btnLogin.setBackground(new Color(0, 64, 128));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBounds(123, 147, 120, 23);
		panelCampos.add(btnLogin);

		btnRegister = new JButton("Pulsa aquí para registrarte");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegisterPressed();
			}
		});
		btnRegister.setBounds(10, 217, 233, 23);
		panelCampos.add(btnRegister);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(113, 117, 130, 20);
		panelCampos.add(txtPassword);
		panelInfo.setLayout(gl_panelInfo);

		JPanel panelImagenLogo = new JPanel();
		contentPane.add(panelImagenLogo, BorderLayout.WEST);
		panelImagenLogo.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PantallaInicial.class.getResource("/images/imgLogin.png")));
		panelImagenLogo.add(lblNewLabel_1);
	}

	/**
	 * 
	 */
	protected void btnRegisterPressed() {

		PantallaRegistro registro = new PantallaRegistro(this);
		registro.setVisible(Boolean.TRUE);

	}

	/**
	 * 
	 */
	protected void btnLoginPressed() {

		Map<Cliente, String> clientes = PersonaManagement.clienteMap;
		Map<Administrador, String> admins = PersonaManagement.adminMap;

		String user = txtUsuario.getText();
		String password = txtPassword.getText();

		boolean logeado = false;
		boolean isCliente = false;
		boolean isAdmin = false;

		if (clientes.containsValue(password)) {
			isCliente = true;
		}

		if (admins.containsValue(password)) {
			isAdmin = true;
		}

		Cliente cliente = PantallaRegistro.getCliente();

		// Si es admin
		if (isAdmin) {
			PantallaPrincipalAdmin pantallaAdmin = new PantallaPrincipalAdmin(this);
			pantallaAdmin.setVisible(true);
			// Si es cliente
		} else if (isCliente) {
			PantallaPrincipalCliente pantallaCliente = new PantallaPrincipalCliente(cliente, this);
			pantallaCliente.setVisible(true);
		}

	}
}
