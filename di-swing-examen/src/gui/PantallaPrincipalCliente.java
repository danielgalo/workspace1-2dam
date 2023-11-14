package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.Cliente;

public class PantallaPrincipalCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel iconoReservaClase;
	private JLabel iconoCierreSesion;
	private Cliente cliente;
	PantallaInicial inicio;

	/**
	 * Create the frame.
	 */
	public PantallaPrincipalCliente(Cliente cliente, PantallaInicial inicio) {
		this.inicio = inicio;
		this.cliente = cliente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("GYM Picasso");
		lblNewLabel.setForeground(new Color(0, 64, 128));
		lblNewLabel.setIcon(new ImageIcon(PantallaPrincipalCliente.class.getResource("/images/logoApp.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblNewLabel);

		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panelInferior, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("Daniel Galo Vega 14/11/2023");
		lblNewLabel_1.setForeground(new Color(0, 64, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelInferior.add(lblNewLabel_1);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);

		iconoReservaClase = new JLabel("");
		iconoReservaClase.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				iconReservarClasePressed();
			}
		});
		iconoReservaClase
				.setIcon(new ImageIcon(PantallaPrincipalCliente.class.getResource("/images/apuntaAClase.png")));
		iconoReservaClase.setBounds(83, 38, 71, 71);
		panelCentral.add(iconoReservaClase);

		iconoCierreSesion = new JLabel("");
		iconoCierreSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				iconoSalirPressed();
			}
		});
		iconoCierreSesion
				.setIcon(new ImageIcon(PantallaPrincipalCliente.class.getResource("/images/cierreSesion.png")));
		iconoCierreSesion.setBounds(281, 38, 71, 71);
		panelCentral.add(iconoCierreSesion);

		JLabel lblNewLabel_3 = new JLabel("Reservar clase");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(64, 120, 106, 14);
		panelCentral.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Cerrar sesión");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setBounds(254, 120, 106, 14);
		panelCentral.add(lblNewLabel_3_1);
	}

	protected void iconoSalirPressed() {
		inicio.setVisible(true);
	}

	protected void iconReservarClasePressed() {
		PantallaReservaClase reservaClase = new PantallaReservaClase();
		reservaClase.setVisible(true);
	}

	public Cliente getCliente() {
		return this.cliente;
	}

}
