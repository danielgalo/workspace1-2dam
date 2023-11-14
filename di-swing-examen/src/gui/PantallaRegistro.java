package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import dao.Administrador;
import dao.Cliente;
import utils.PersonaManagement;

public class PantallaRegistro extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreReg;
	private JTextField txtApellidosReg;
	private JTextField txtEmailReg;
	private JDateChooser dateFechaNac;
	private JButton btnEnviar;
	PantallaInicial pantallaInicial;
	private JComboBox<Object> cboxPerfil;
	private JTextField txtPasswordReg;
	private JTextField txtPasswordRepeat;
	public static Cliente cliente;

	/**
	 * Create the dialog.
	 */
	public PantallaRegistro(PantallaInicial pantallaInicial) {
		setResizable(false);
		this.pantallaInicial = pantallaInicial;

		setBounds(100, 100, 380, 544);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 128, 255));
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("REGISTRO");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel.setForeground(new Color(255, 255, 255));
				panel.add(lblNewLabel);
			}
		}
		{
			JPanel panelCampos = new JPanel();
			contentPanel.add(panelCampos, BorderLayout.CENTER);
			panelCampos.setLayout(null);
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre");
				lblNewLabel_1.setBounds(10, 44, 134, 14);
				panelCampos.add(lblNewLabel_1);
			}
			{
				txtNombreReg = new JTextField();
				txtNombreReg.setBounds(210, 41, 134, 20);
				panelCampos.add(txtNombreReg);
				txtNombreReg.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Apellidos");
				lblNewLabel_1.setBounds(10, 99, 134, 14);
				panelCampos.add(lblNewLabel_1);
			}
			{
				txtApellidosReg = new JTextField();
				txtApellidosReg.setColumns(10);
				txtApellidosReg.setBounds(210, 96, 134, 20);
				panelCampos.add(txtApellidosReg);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Fecha de Nacimiento");
				lblNewLabel_1.setBounds(10, 153, 134, 14);
				panelCampos.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Perfil");
				lblNewLabel_1.setBounds(10, 214, 134, 14);
				panelCampos.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Email");
				lblNewLabel_1.setBounds(10, 266, 134, 14);
				panelCampos.add(lblNewLabel_1);
			}
			{
				txtEmailReg = new JTextField();
				txtEmailReg.setColumns(10);
				txtEmailReg.setBounds(210, 267, 134, 20);
				panelCampos.add(txtEmailReg);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Contraseña");
				lblNewLabel_1.setBounds(10, 326, 134, 14);
				panelCampos.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Repite contraseña");
				lblNewLabel_1.setBounds(10, 388, 134, 14);
				panelCampos.add(lblNewLabel_1);
			}
			{
				btnEnviar = new JButton("Enviar");
				btnEnviar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnEnviarPressed();
					}
				});
				btnEnviar.setBounds(255, 437, 89, 23);
				panelCampos.add(btnEnviar);
			}

			dateFechaNac = new JDateChooser();
			dateFechaNac.setBounds(210, 153, 134, 20);
			panelCampos.add(dateFechaNac);

			cboxPerfil = new JComboBox<Object>();
			cboxPerfil.setModel(new DefaultComboBoxModel<Object>(new String[] { "Cliente", "Administrador" }));
			cboxPerfil.setBounds(210, 210, 134, 22);
			panelCampos.add(cboxPerfil);
			{
				txtPasswordReg = new JTextField();
				txtPasswordReg.setColumns(10);
				txtPasswordReg.setBounds(210, 323, 134, 20);
				panelCampos.add(txtPasswordReg);
			}
			{
				txtPasswordRepeat = new JTextField();
				txtPasswordRepeat.setColumns(10);
				txtPasswordRepeat.setBounds(210, 385, 134, 20);
				panelCampos.add(txtPasswordRepeat);
			}
		}
	}

	/**
	 * 
	 */
	protected void btnEnviarPressed() {

		String perfil = (String) cboxPerfil.getSelectedItem();

		String nombre = txtNombreReg.getText();
		String apellidos = txtApellidosReg.getText();
		String email = txtEmailReg.getText();
		Date fechaNacimiento = dateFechaNac.getDate();

		String password = txtPasswordReg.getText();
		String passwordConfirm = txtPasswordRepeat.getText();

		if (password.equals(passwordConfirm)) {

			registraPersona(perfil, nombre, apellidos, email, fechaNacimiento, password);

			// abrir pantalla inicial
			pantallaInicial.setVisible(true);
			// cerrar esta pantalla
			this.setVisible(false);

		} else {
			// mostrar dialogo error
			WarningPasswords warning = new WarningPasswords();
			warning.setVisible(true);
		}

	}

	/**
	 * @param perfil
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param fechaNacimiento
	 * @param password
	 */
	private void registraPersona(String perfil, String nombre, String apellidos, String email, Date fechaNacimiento,
			String password) {
		// Si es cliente
		if (perfil.equalsIgnoreCase("Cliente")) {

			cliente = new Cliente(nombre, apellidos, fechaNacimiento, email);

			PersonaManagement.addCliente(cliente, password);

			// Si es admin
		} else if (perfil.equalsIgnoreCase("Administrador")) {

			Administrador admin = new Administrador(nombre, apellidos, fechaNacimiento, email);

			PersonaManagement.addAdmin(admin, password);
		}
	}

	public static Cliente getCliente() {
		return cliente;
	}
}
