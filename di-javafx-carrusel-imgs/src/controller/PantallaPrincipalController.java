package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Controlador de la pantalla principal
 */
public class PantallaPrincipalController {

	// Imagenes a usar
	Image imagen1 = new Image("resources/supramk3.jfif");
	Image imagen2 = new Image("resources/supramk4.jfif");
	Image imagen3 = new Image("resources/supramk5.jfif");

	// Array con las imágenes a usar
	Image[] imagenes = { imagen1, imagen2, imagen3 };

	// Posición actual en el array de la imagen mostrada en pantalla
	int posicionImg = 0;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnAnterior;

	@FXML
	private Button btnSiguiente;

	@FXML
	private ImageView imgCarrusel;

	@FXML
	private Label lblImagen;

	@FXML
	void btnAnteriorPressed(MouseEvent event) {

		// Disminuir la posición de la imagen
		posicionImg--;

		// Si es menor a 0, ponerla al máximo
		if (posicionImg == -1) {
			posicionImg = imagenes.length - 1;
		}

		// Cambiar la imagen
		imgCarrusel.setImage(imagenes[posicionImg]);
		lblImagen.setText("Imagen " + (posicionImg + 1));
	}

	@FXML
	void btnSiguientePressed(MouseEvent event) {

		// Aumentar la posición de la imagen
		posicionImg++;

		// Si es mayor al mayor índice del array, devolverla a 0
		if (posicionImg == imagenes.length) {
			posicionImg = 0;
		}

		// Cambiar imagen
		imgCarrusel.setImage(imagenes[posicionImg]);
		lblImagen.setText("Imagen " + (posicionImg + 1));
	}

	@FXML
	void initialize() {

		// Inicia con la primera imagen
		lblImagen.setText("Imagen 1");
		imgCarrusel.setImage(imagenes[0]);

	}

}
