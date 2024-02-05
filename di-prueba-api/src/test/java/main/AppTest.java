package main;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Unit test for simple App.
 */
@ExtendWith(ApplicationExtension.class)
public class AppTest {

	private Button button;

	@Start
	private void start(Stage stage) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/views/PantallaPrincipal.fxml"));
			Pane ventana = (Pane) loader.load();

			Scene scene = new Scene(ventana);

			String urlCss = getClass().getResource("/styles/login-style.css").toExternalForm();

			scene.getStylesheets().add(urlCss);

			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param robot - Will be injected by the test runner.
	 */
	@Test
	void compruebaTextoBoton(FxRobot robot) {
		// or (lookup by css id):
		FxAssert.verifyThat("#btnBuscar", LabeledMatchers.hasText("Buscar"));
	}

	@Test
	public void testTexto() {
		FxRobot fxRobot = new FxRobot();

		fxRobot.clickOn("#txtPelicula");
		fxRobot.write("Hola");
		fxRobot.clickOn("#btnBuscar");
		FxAssert.verifyThat("#lblTitulo", LabeledMatchers.hasText("Hola"));
	}

}
