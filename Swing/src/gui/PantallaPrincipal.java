package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnHolaMundo;
	private JLabel lblSalida;
	private JButton btnPantSec;
	private JLabel lblTuNombre;
	private JComboBox<Object> cbProvincia;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnSi;
	private JTable table;

	// ABSOLUTE LAYOUT

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		setTitle("Pantalla principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblSalida = new JLabel("");
		lblSalida.setBounds(138, 11, 177, 14);
		contentPane.add(lblSalida);

		btnHolaMundo = new JButton("Pulsa");
		btnHolaMundo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				holaMundo(e);

			}
		});
		btnHolaMundo.setBounds(185, 81, 89, 23);
		contentPane.add(btnHolaMundo);

		btnPantSec = new JButton("Ir a secundaria");
		btnPantSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnPantSecPressed(e);

			}

		});
		btnPantSec.setBounds(185, 129, 118, 23);
		contentPane.add(btnPantSec);

		lblTuNombre = new JLabel(" ");
		lblTuNombre.setBounds(120, 187, 249, 14);
		contentPane.add(lblTuNombre);

		cbProvincia = new JComboBox<Object>();
		cbProvincia.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Granada", "Sevilla", "Málaga", "Jaén", "Córdoba", "Cádiz" }));
		cbProvincia.setBounds(10, 81, 95, 23);
		contentPane.add(cbProvincia);

		rdbtnSi = new JRadioButton("SI");
		rdbtnSi.setBounds(10, 129, 109, 23);
		contentPane.add(rdbtnSi);

		rdbtnNo = new JRadioButton("NO");
		rdbtnNo.setBounds(10, 157, 109, 23);
		contentPane.add(rdbtnNo);

		ButtonGroup rbGroup = new ButtonGroup();
		rbGroup.add(rdbtnSi);
		rbGroup.add(rdbtnNo);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1696888800000L), null, null, Calendar.DAY_OF_YEAR));
		spinner.setBounds(10, 208, 95, 20);
		contentPane.add(spinner);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, },
				new String[] { "New column", "New column", "New column" }));
		table.setBounds(319, 36, 105, 140);
		contentPane.add(table);

	}

	protected void holaMundo(ActionEvent e) {
		lblSalida.setText("Hola Mundo");
	}

	protected void btnPantSecPressed(ActionEvent e) {
		PantallaSecundaria ps = new PantallaSecundaria(this, true);
		ps.setVisible(true);
	}

	public void escribeSaludo(String mensaje) {
		lblTuNombre.setText("Hola " + mensaje);
	}
}
