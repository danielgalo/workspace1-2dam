package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.Cliente;
import dao.Reserva;
import utils.ReservasManagement;

/**
 * Pantalla para registrar una clase
 */
public class PantallaAddClase extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreClase;
	private JTextField txtProfesor;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnMorning;
	private JRadioButton rdbtnTarde;
	private JButton btnEnviar;
	Cliente cliente;

	/**
	 * Create the dialog.
	 */
	public PantallaAddClase(Cliente cliente) {
		this.cliente = cliente;
		setResizable(false);

		setBounds(100, 100, 450, 337);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelSuperiro = new JPanel();
			panelSuperiro.setBackground(new Color(0, 128, 255));
			contentPanel.add(panelSuperiro, BorderLayout.NORTH);
			{
				JLabel imagenLogo = new JLabel("Nueva Clase");
				imagenLogo.setForeground(new Color(255, 255, 255));
				imagenLogo.setFont(new Font("Tahoma", Font.BOLD, 20));
				panelSuperiro.add(imagenLogo);
			}
		}
		{
			JPanel panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			panelCentral.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Nombre");
				lblNewLabel.setBounds(56, 51, 101, 14);
				panelCentral.add(lblNewLabel);
			}
			{
				txtNombreClase = new JTextField();
				txtNombreClase.setBounds(221, 48, 149, 27);
				panelCentral.add(txtNombreClase);
				txtNombreClase.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("Profesor");
				lblNewLabel.setBounds(56, 100, 101, 14);
				panelCentral.add(lblNewLabel);
			}
			{
				txtProfesor = new JTextField();
				txtProfesor.setColumns(10);
				txtProfesor.setBounds(221, 86, 149, 27);
				panelCentral.add(txtProfesor);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Turno");
				lblNewLabel_1.setBounds(56, 146, 46, 14);
				panelCentral.add(lblNewLabel_1);
			}

			rdbtnMorning = new JRadioButton("Mañana");
			buttonGroup.add(rdbtnMorning);
			rdbtnMorning.setBounds(229, 142, 109, 23);
			panelCentral.add(rdbtnMorning);

			rdbtnTarde = new JRadioButton("Tarde");
			buttonGroup.add(rdbtnTarde);
			rdbtnTarde.setBounds(229, 170, 109, 23);
			panelCentral.add(rdbtnTarde);

			btnEnviar = new JButton("Enviar");
			btnEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnEnviarPressed();
				}
			});
			btnEnviar.setBounds(221, 219, 149, 23);
			panelCentral.add(btnEnviar);
		}
	}

	protected void btnEnviarPressed() {

		String nombreReserva = txtNombreClase.getText();
		String profesor = txtProfesor.getText();

		String turno = "";

		if (rdbtnMorning.isSelected()) {
			turno = "Mañana";
		} else if (rdbtnTarde.isSelected()) {
			turno = "Tarde";
		}

		Reserva reserva = new Reserva(nombreReserva, profesor, turno, cliente);

		ReservasManagement.addReserva(reserva);

		this.setVisible(false);
	}
}
