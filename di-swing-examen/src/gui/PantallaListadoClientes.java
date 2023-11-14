package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.Cliente;
import utils.PersonaManagement;

public class PantallaListadoClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtApellidosFiltro;
	private JButton btnFiltrar;
	private JTable table;
	DefaultTableModel modelo;
	private TableRowSorter<DefaultTableModel> rowSorter;

	/**
	 * Create the dialog.
	 */
	public PantallaListadoClientes() {
		setResizable(false);
		setBounds(100, 100, 677, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(0, 128, 255));
		contentPanel.add(panelSuperior, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Listar Clientes");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		panelSuperior.add(lblNewLabel);

		JPanel panelCentral = new JPanel();
		contentPanel.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelCentral.add(scrollPane, BorderLayout.CENTER);

		iniciaTabla(scrollPane);

		JPanel panelInferior = new JPanel();
		contentPanel.add(panelInferior, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("Apellidos:");
		panelInferior.add(lblNewLabel_1);

		txtApellidosFiltro = new JTextField();
		panelInferior.add(txtApellidosFiltro);
		txtApellidosFiltro.setColumns(10);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFiltrarPressed();
			}
		});
		panelInferior.add(btnFiltrar);
	}

	/**
	 * @param scrollPane
	 */
	private void iniciaTabla(JScrollPane scrollPane) {

		table = new JTable();

		modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Nombre", "Apellidos", "Fecha de nacimiento", "Email" });
		rowSorter = new TableRowSorter<>(modelo);
		table.setRowSorter(rowSorter);
		for (Entry<Cliente, String> entry : PersonaManagement.clienteMap.entrySet()) {
			Cliente c = entry.getKey();

			Object[] fila = { c.getNombre(), c.getApellidos(), c.getFechaNacimiento(), c.getCorreoElectronico() };
			modelo.addRow(fila);
		}

		table.setModel(modelo);
		scrollPane.setViewportView(table);
	}

	/**
	 * 
	 */
	protected void btnFiltrarPressed() {
		RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter((String) txtApellidosFiltro.getText(),
				0);
		rowSorter.setRowFilter(rowFilter);
	}

}
