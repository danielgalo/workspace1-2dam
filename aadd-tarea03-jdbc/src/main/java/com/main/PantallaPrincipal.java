package com.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import utils.SakilaManagement;

public class PantallaPrincipal {

	private JFrame frmPelculas;
	private JTextArea txtAId;
	private JTextArea txtATitulo;
	private JTextArea txtADesc;
	private JTextArea txtALanzam;
	private JTextArea txtAIDIdioma;
	private JTextArea txtAIDIdiomaOrig;
	private JTextArea txtADuracionAlq;
	private JTextArea txtACosteAlq;
	private JTextArea txtADuracionPeli;
	private JTextArea txtACosteReemplazo;
	private JTextArea txtARating;
	private JTextArea txtACaract;
	private JTextArea txtAUltimaAct;
	private JButton btnPrimero;
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private JButton btnUltimo;
	private JButton btnNuevo;
	private JButton btnGuardar;

	private static ResultSet peliculas;
	private static SakilaManagement sm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal window = new PantallaPrincipal();
					window.frmPelculas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaPrincipal() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		iniciaFrame();

		iniciaTextAreas();

		iniciaLabels();

		iniciaBotones();

		sm = new SakilaManagement();

		sm.getAllPeliculas();

		peliculas = sm.getResultSetPeliculas();
		siguientePelicula();

	}

	private void siguientePelicula() {

		try {
			if (peliculas.next()) {
				setCampos();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Rellena las text areas con datos del ResultSet de peliculas
	 * 
	 * @throws SQLException
	 */
	private void setCampos() throws SQLException {
		txtAId.setText(String.valueOf(peliculas.getInt("film_id")));
		txtATitulo.setText(peliculas.getString("title"));
		txtADesc.setText(peliculas.getString("description"));
		txtALanzam.setText(String.valueOf(peliculas.getInt("release_year")));
		txtAIDIdioma.setText(String.valueOf(peliculas.getInt("language_id")));
		txtAIDIdiomaOrig.setText(String.valueOf(peliculas.getInt("original_language_id")));
		txtADuracionAlq.setText(String.valueOf(peliculas.getInt("rental_duration")));
		txtACosteAlq.setText(String.valueOf(peliculas.getDouble("rental_rate")));
		txtADuracionPeli.setText(String.valueOf(peliculas.getInt("length")));
		txtACosteReemplazo.setText(String.valueOf(peliculas.getDouble("replacement_cost")));
		txtARating.setText(peliculas.getString("rating"));
		txtACaract.setText(peliculas.getString("special_features"));
		txtAUltimaAct.setText(peliculas.getString("last_update"));

	}

	private void peliculaAnterior() {

		try {
			if (peliculas.previous()) {
				setCampos();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	private void iniciaFrame() {
		frmPelculas = new JFrame();
		frmPelculas.getContentPane().setBackground(new Color(0, 0, 64));
		frmPelculas.setTitle("Películas");
		frmPelculas.setBounds(100, 100, 648, 618);
		frmPelculas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPelculas.getContentPane().setLayout(null);
	}

	/**
	 * 
	 */
	private void iniciaTextAreas() {

		txtAId = new JTextArea();
		txtAId.setBounds(225, 49, 397, 22);
		frmPelculas.getContentPane().add(txtAId);

		txtATitulo = new JTextArea();
		txtATitulo.setBounds(225, 84, 397, 22);
		frmPelculas.getContentPane().add(txtATitulo);

		txtADesc = new JTextArea();
		txtADesc.setBounds(225, 117, 397, 22);
		frmPelculas.getContentPane().add(txtADesc);

		txtALanzam = new JTextArea();
		txtALanzam.setBounds(225, 150, 397, 22);
		frmPelculas.getContentPane().add(txtALanzam);

		txtAIDIdioma = new JTextArea();
		txtAIDIdioma.setBounds(225, 183, 397, 22);
		frmPelculas.getContentPane().add(txtAIDIdioma);

		txtAIDIdiomaOrig = new JTextArea();
		txtAIDIdiomaOrig.setBounds(225, 216, 397, 22);
		frmPelculas.getContentPane().add(txtAIDIdiomaOrig);

		txtADuracionAlq = new JTextArea();
		txtADuracionAlq.setBounds(225, 249, 397, 22);
		frmPelculas.getContentPane().add(txtADuracionAlq);

		txtADuracionPeli = new JTextArea();
		txtADuracionPeli.setBounds(225, 315, 397, 22);
		frmPelculas.getContentPane().add(txtADuracionPeli);

		txtACosteAlq = new JTextArea();
		txtACosteAlq.setBounds(225, 282, 397, 22);
		frmPelculas.getContentPane().add(txtACosteAlq);

		txtACosteReemplazo = new JTextArea();
		txtACosteReemplazo.setBounds(225, 348, 397, 22);
		frmPelculas.getContentPane().add(txtACosteReemplazo);

		txtARating = new JTextArea();
		txtARating.setBounds(225, 381, 397, 22);
		frmPelculas.getContentPane().add(txtARating);

		txtACaract = new JTextArea();
		txtACaract.setBounds(225, 414, 397, 22);
		frmPelculas.getContentPane().add(txtACaract);

		txtAUltimaAct = new JTextArea();
		txtAUltimaAct.setBounds(225, 447, 397, 22);
		frmPelculas.getContentPane().add(txtAUltimaAct);
	}

	/**
	 * 
	 */
	private void iniciaBotones() {
		btnPrimero = new JButton("Primero");
		btnPrimero.setBounds(10, 492, 89, 23);
		frmPelculas.getContentPane().add(btnPrimero);

		btnUltimo = new JButton("Último");
		btnUltimo.setBounds(533, 492, 89, 23);
		frmPelculas.getContentPane().add(btnUltimo);

		btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				peliculaAnterior();

			}
		});
		btnAnterior.setBounds(182, 492, 89, 23);
		frmPelculas.getContentPane().add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				siguientePelicula();

			}
		});
		btnSiguiente.setBounds(373, 492, 89, 23);
		frmPelculas.getContentPane().add(btnSiguiente);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(10, 545, 89, 23);
		frmPelculas.getContentPane().add(btnNuevo);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(182, 545, 89, 23);
		frmPelculas.getContentPane().add(btnGuardar);
	}

	private void iniciaLabels() {

		JLabel lblNewLabel_1_1 = new JLabel("Título: ");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(10, 89, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("PELÍCULAS");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 612, 14);
		frmPelculas.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Id de la película:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 54, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Descripción: ");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setBounds(10, 122, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Año de lanzamiento: ");
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setBounds(10, 155, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_3 = new JLabel("Id del idioma: ");
		lblNewLabel_1_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_1_3.setBounds(10, 188, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1_3);

		JLabel lblNewLabel_1_1_4 = new JLabel("ID del idioma original: ");
		lblNewLabel_1_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_1_4.setBounds(10, 221, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1_4);

		JLabel lblNewLabel_1_1_5 = new JLabel("Duración del alquiler: ");
		lblNewLabel_1_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_1_5.setBounds(10, 254, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1_5);

		JLabel lblNewLabel_1_1_6 = new JLabel("Coste del alquiler:");
		lblNewLabel_1_1_6.setForeground(Color.WHITE);
		lblNewLabel_1_1_6.setBounds(10, 287, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1_6);

		JLabel lblNewLabel_1_1_7 = new JLabel("Duración de la película:");
		lblNewLabel_1_1_7.setForeground(Color.WHITE);
		lblNewLabel_1_1_7.setBounds(10, 320, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1_7);

		JLabel lblNewLabel_1_1_8 = new JLabel("Coste de reemplazo: ");
		lblNewLabel_1_1_8.setForeground(Color.WHITE);
		lblNewLabel_1_1_8.setBounds(10, 353, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1_8);

		JLabel lblNewLabel_1_1_9 = new JLabel("Rating: ");
		lblNewLabel_1_1_9.setForeground(Color.WHITE);
		lblNewLabel_1_1_9.setBounds(10, 386, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1_9);

		JLabel lblNewLabel_1_1_10 = new JLabel("Características especiales: ");
		lblNewLabel_1_1_10.setForeground(Color.WHITE);
		lblNewLabel_1_1_10.setBounds(10, 419, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1_10);

		JLabel lblNewLabel_1_1_11 = new JLabel("Última actualización: ");
		lblNewLabel_1_1_11.setForeground(Color.WHITE);
		lblNewLabel_1_1_11.setBounds(10, 452, 192, 14);
		frmPelculas.getContentPane().add(lblNewLabel_1_1_11);
	}
}
