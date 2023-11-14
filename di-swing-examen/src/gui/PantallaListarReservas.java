package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.Reserva;
import utils.ReservasManagement;

public class PantallaListarReservas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtClaseFiltro;
	private JButton btnFiltrar;
	DefaultTableModel modelo;

	/**
	 * Create the dialog.
	 */
	public PantallaListarReservas() {
		setResizable(false);
		setBounds(100, 100, 572, 423);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelSuperior = new JPanel();
			panelSuperior.setBackground(new Color(0, 128, 255));
			contentPanel.add(panelSuperior, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("Listar Reservas");
				lblNewLabel.setForeground(new Color(255, 255, 255));
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
				panelSuperior.add(lblNewLabel);
			}
		}
		{
			JPanel panelCentral = new JPanel();
			contentPanel.add(panelCentral, BorderLayout.CENTER);
			panelCentral.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelCentral.add(scrollPane, BorderLayout.CENTER);
				{

					modelo = new DefaultTableModel(new Object[][] {},
							new String[] { "Nombre", "Apellidos", "Clase", "Turno" });

					for (Reserva r : ReservasManagement.reservas) {
						Object[] fila = { r.getCliente().getNombre(), r.getCliente().getApellidos(), r.getNombre(),
								r.getTurno() };
						modelo.addRow(fila);
					}

					Object[] fileEj = { "a", "a", "Spinning", "Mañana" };
					modelo.addRow(fileEj);
					table = new JTable();
					table.setModel(modelo);
					scrollPane.setViewportView(table);

				}
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			{
				JLabel lblNewLabel_1 = new JLabel("Clase:");
				panel.add(lblNewLabel_1);
			}
			{
				txtClaseFiltro = new JTextField();
				txtClaseFiltro.setText("");
				panel.add(txtClaseFiltro);
				txtClaseFiltro.setColumns(10);
			}
			{
				btnFiltrar = new JButton("Filtrar");
				panel.add(btnFiltrar);
			}
		}
	}

}
