package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Aplicación principal
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/PantallaPrincipal.fxml"));
			Pane ventana = (Pane) loader.load();

			Scene scene = new Scene(ventana);

			String urlCss = getClass().getResource("application.css").toExternalForm();

			scene.getStylesheets().add(urlCss);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
