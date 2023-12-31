package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/views/Pantalla.fxml"));

			Pane ventana = (Pane) loader.load();

			Scene scene = new Scene(ventana);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			// Icono aplicacion
			Image icon = new Image("resources/imagen.png");
			primaryStage.getIcons().add(icon);

			// Título de la ventana
			primaryStage.setTitle("Título de la ventana");

			// Pantalla completa
			primaryStage.setFullScreen(false);
			primaryStage.setFullScreenExitHint("Presiona Q para salir de pantalla completa");
			primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
