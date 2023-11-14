package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.Cliente;

public class PantallaPrincipalAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Cliente cliente;
	PantallaInicial inicio;

	/**
	 * Create the frame.
	 */
	public PantallaPrincipalAdmin(PantallaInicial inicio) {
		this.inicio = inicio;
		cliente = PantallaRegistro.getCliente();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelSuperiro = new JPanel();
		panelSuperiro.setBackground(new Color(128, 255, 255));
		contentPane.add(panelSuperiro, BorderLayout.NORTH);

		JLabel imagenLogo = new JLabel("GYM Picasso");
		imagenLogo.setIcon(new ImageIcon(PantallaPrincipalAdmin.class.getResource("/images/logoApp.png")));
		imagenLogo.setForeground(new Color(0, 64, 128));
		imagenLogo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelSuperiro.add(imagenLogo);

		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(new Color(128, 255, 255));
		contentPane.add(panelInferior, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("Daniel Galo Vega 14/11/2023");
		lblNewLabel.setForeground(new Color(0, 64, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelInferior.add(lblNewLabel);

		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(255, 255, 255));
		contentPane.add(panelCentral, BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(113, 11, 64, 64);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				iconoAddClasePressed();
			}
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setIcon(new ImageIcon(PantallaPrincipalAdmin.class.getResource("/images/addClase.png")));

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setBounds(301, 11, 64, 64);
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnVerReservasPressed();
			}
		});
		lblNewLabel_1_1.setIcon(new ImageIcon(PantallaPrincipalAdmin.class.getResource("/images/listarReservas.png")));

		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setBounds(113, 113, 64, 64);
		lblNewLabel_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				iconoVerClientesPressed();
			}
		});
		lblNewLabel_1_2.setIcon(new ImageIcon(PantallaPrincipalAdmin.class.getResource("/images/listarUsuarios.png")));

		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setBounds(301, 113, 64, 64);
		lblNewLabel_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				iconoExitPressed();
			}
		});
		lblNewLabel_1_1_1.setIcon(new ImageIcon(PantallaPrincipalAdmin.class.getResource("/images/cierreSesion.png")));

		JLabel lblNewLabel_2 = new JLabel("Añade clase");
		lblNewLabel_2.setBounds(84, 86, 118, 14);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_2_1 = new JLabel("Ver reservas");
		lblNewLabel_2_1.setBounds(275, 81, 107, 14);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_2_1_1 = new JLabel("Ver clientes");
		lblNewLabel_2_1_1.setBounds(84, 183, 118, 14);
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Cerrar sesión");
		lblNewLabel_2_1_1_1.setBounds(275, 183, 107, 14);
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.setLayout(null);
		panelCentral.add(lblNewLabel_2_1_1);
		panelCentral.add(lblNewLabel_2_1_1_1);
		panelCentral.add(lblNewLabel_1);
		panelCentral.add(lblNewLabel_1_2);
		panelCentral.add(lblNewLabel_2);
		panelCentral.add(lblNewLabel_1_1);
		panelCentral.add(lblNewLabel_1_1_1);
		panelCentral.add(lblNewLabel_2_1);
	}

	protected void btnVerReservasPressed() {

		PantallaListarReservas listadoReservas = new PantallaListarReservas();

		listadoReservas.setVisible(true);

	}

	protected void iconoVerClientesPressed() {
		PantallaListadoClientes listadoClientes = new PantallaListadoClientes();
		listadoClientes.setVisible(true);
	}

	protected void iconoExitPressed() {
		inicio.setVisible(true);
		this.setVisible(false);
	}

	protected void iconoAddClasePressed() {
		PantallaAddClase addClase = new PantallaAddClase(cliente);
		addClase.setVisible(true);
	}

}
