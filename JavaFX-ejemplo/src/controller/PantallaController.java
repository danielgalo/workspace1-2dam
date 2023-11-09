package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

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
