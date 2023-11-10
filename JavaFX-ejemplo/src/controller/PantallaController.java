package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PantallaController {

	@FXML
	private Button bntHola;

	@FXML
	private CheckBox chckBox;

	@FXML
	private Label lblHola;

	@FXML
	private PasswordField txtPassword;

	@FXML
	void btnHolaPressed(MouseEvent event) {
		lblHola.setText("Hola mundo");
		// Mensajes
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("illu");
		alert.setHeaderText("header");
		alert.showAndWait();
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("resources/imagen.png")); // To add an icon
		alert.showAndWait();
	}

	@FXML
	void cboxpressed(ActionEvent event) {
		if (chckBox.isSelected()) {
			lblHola.setText("a");
		} else {
			lblHola.setText("b");
		}
	}

	@FXML
	void btnPswdPressed(MouseEvent event) {
		lblHola.setText(txtPassword.getText());
	}

}
