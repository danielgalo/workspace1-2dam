package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PantallaSecundaria extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private PantallaPrincipal pantallaPrincipal;

	/**
	 * Create the dialog.
	 */
	public PantallaSecundaria(PantallaPrincipal parent, boolean modal) {
		super(parent, modal);
		pantallaPrincipal = parent;

		setTitle("Pantalla Secundaria");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnVolverPressed(e);

			}
		});
		btnVolver.setBounds(10, 227, 89, 23);
		getContentPane().add(btnVolver);

		JLabel lblIntroduceNombre = new JLabel("Introduce tu nombre:");
		lblIntroduceNombre.setBounds(10, 11, 125, 14);
		getContentPane().add(lblIntroduceNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(165, 8, 184, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 75, 326, 120);
		getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

	}

	protected void btnVolverPressed(ActionEvent e) {

		pantallaPrincipal.escribeSaludo(txtNombre.getText());

		setVisible(false);

	}
}
