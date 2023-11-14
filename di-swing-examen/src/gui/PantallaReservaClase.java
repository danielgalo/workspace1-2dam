package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PantallaReservaClase extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox cboxClase;
	private JComboBox cboxTurno;
	private JButton btnReservar;

	/**
	 * Create the dialog.
	 */
	public PantallaReservaClase() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 255, 255));
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("Reservar Clases");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblNewLabel.setForeground(new Color(255, 255, 255));
				panel.add(lblNewLabel);
			}
		}
		{
			JPanel panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			panelCentral.setLayout(null);
			{
				JLabel lblNewLabel_1 = new JLabel("Clase");
				lblNewLabel_1.setBounds(10, 54, 141, 14);
				panelCentral.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Turno");
				lblNewLabel_1.setBounds(10, 121, 141, 14);
				panelCentral.add(lblNewLabel_1);
			}
			{
				cboxClase = new JComboBox();
				cboxClase.setModel(new DefaultComboBoxModel(new String[] { "Spinning", "Zumba" }));
				cboxClase.setBounds(227, 38, 187, 46);
				panelCentral.add(cboxClase);
			}
			{
				cboxTurno = new JComboBox();
				cboxTurno.setModel(new DefaultComboBoxModel(new String[] { "Mañana", "Tarde" }));
				cboxTurno.setBounds(227, 95, 187, 46);
				panelCentral.add(cboxTurno);
			}
			{
				btnReservar = new JButton("New button");
				btnReservar.setBounds(227, 162, 187, 46);
				panelCentral.add(btnReservar);
			}
		}
	}

}
