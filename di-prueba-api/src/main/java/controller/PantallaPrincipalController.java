package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Pelicula;
import utils.TMDBApi;

public class PantallaPrincipalController {

	@FXML
	private Button btnBuscar;

	@FXML
	private Label lblTitulo;

	@FXML
	private ImageView imgPelicula;

	@FXML
	private TextArea txtAreaDescripcionPelicula;

	@FXML
	private TextField txtPelicula;

	@FXML
	private TextArea txtAreaFechaEstreno;

	@FXML
	private Button btnSiguiente;

	@FXML
	private Button btnAnterior;

	@FXML
	private TextArea txtAreaTituloPelicula;

	int posicionPelicula = 0;

	@FXML
	void initialize() {
		txtAreaDescripcionPelicula.setWrapText(true);
		assert btnBuscar != null
				: "fx:id=\"btnBuscar\" was not injected: check your FXML file 'PantallaPrincipal.fxml'.";
		assert imgPelicula != null
				: "fx:id=\"imgPelicula\" was not injected: check your FXML file 'PantallaPrincipal.fxml'.";
		assert txtAreaDescripcionPelicula != null
				: "fx:id=\"txtAreaDescripcionPelicula\" was not injected: check your FXML file 'PantallaPrincipal.fxml'.";
		assert txtAreaFechaEstreno != null
				: "fx:id=\"txtAreaFechaEstreno\" was not injected: check your FXML file 'PantallaPrincipal.fxml'.";
		assert txtPelicula != null
				: "fx:id=\"txtPelicula\" was not injected: check your FXML file 'PantallaPrincipal.fxml'.";

	}

	/**
	 * Cambia a la siguiente pelicula
	 * 
	 * @param event
	 */
	@FXML
	void btnSiguientePressed(MouseEvent event) {

		if (posicionPelicula >= TMDBApi.getResultsLength() || TMDBApi.getResultsLength() == 0) {
			txtAreaDescripcionPelicula.setText("No hay pelicula siguiente.");
		} else {
			posicionPelicula++;
			setPelicula();
		}

	}

	/**
	 * Cambia a la pelicula anterior
	 */
	@FXML
	void btnAnteriorPressed(MouseEvent event) {

		if (posicionPelicula > 0) {
			posicionPelicula--;
			setPelicula();
		} else {
			txtAreaDescripcionPelicula.setText("No hay pelicula anterior");
		}

	}

	/**
	 * Muestra la primera pelicula
	 * 
	 * @param event
	 */
	@FXML
	void btnBuscarPressed(MouseEvent event) {

		lblTitulo.setText(txtPelicula.getText());
		// posicionPelicula = 0;
		// setPelicula();

	}

	/**
	 * Obtiene una pelicula de la API con el titulo introducido
	 */
	private void setPelicula() {
		// Consigo el titulo introducido
		String tituloPelicula = txtPelicula.getText();
		// Obtengo pelicula
		Pelicula pelicula = TMDBApi.getPelicula(tituloPelicula, posicionPelicula);

		if (pelicula != null) {
			txtAreaDescripcionPelicula.setText(pelicula.getOverview());
			txtAreaFechaEstreno.setText(pelicula.getReleaseDate());
			txtAreaTituloPelicula.setText(pelicula.getTitulo());
			InputStream stream = null;
			try {
				stream = new URL(pelicula.getImg()).openStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image image = new Image(stream);

			// Mostrar la imagen en un ImageView
			imgPelicula.setImage(image);
		}

	}

}
