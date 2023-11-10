package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Persona;

public class Controller {

	@FXML
	private Button btnAddPersona;

	@FXML
	private TableColumn<Persona, String> columnaApellidos;

	@FXML
	private TableColumn<Persona, Integer> columnaEdad;

	@FXML
	private TableColumn<Persona, String> columnaNombre;

	@FXML
	private Label lblApellidos;

	@FXML
	private Label lblEdad;

	@FXML
	private Label lblNombre;

	@FXML
	private AnchorPane panePrincipal;

	@FXML
	private TableView<Persona> tablePersona;

	@FXML
	private TextField txtApellidos;

	@FXML
	private TextField txtEdad;

	@FXML
	private TextField txtNombre;

	ObservableList<Persona> datos;

	@FXML
	void initialize() {

		datos = FXCollections.observableArrayList();

		// Asignar las propiedades de los objetos Person a las celdas de la tabla
		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		columnaApellidos.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		columnaEdad.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
	}

	@FXML
	void btnAddPressed(MouseEvent event) {

		datos.add(new Persona(txtNombre.getText(), txtApellidos.getText(), Integer.parseInt(txtEdad.getText())));

		tablePersona.setItems(datos);

	}

}