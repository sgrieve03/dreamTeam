package sprint4IncrementGUI;

/**
 * Imported FXML libraries
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sprint4Increment.HospitalRunner;


/**
 * @author JewelSea (StackOverflow)
 * @author Alex Kidston 
 * Main FX application class.
 */
public class Main extends Application {
	
	private static final int MYTHREADS = 300;
	private static String textTriageQueue = "Text";
	/**
	 * start method inherited from Application sets virtual stage for FX
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// setTextTriageQueue("Test");
		// stage title for entire application
		stage.setTitle("AnyHospital Trust A&E");
		stage.setScene(createScene(loadMainPane()));
		stage.show();
	}

	/**
	 * Loads the main fxml layout. Sets up the vista switching VistaNavigator.
	 * Loads the first vista into the fxml layout.
	 *
	 * @return the loaded pane.
	 * @throws IOException
	 *             if the pane could not be loaded.
	 */
	private Pane loadMainPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		// access FXML resource files
		// constants in VistaNavigator
		// MAIN = main.fxml
		Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(
				VistaNavigator.MAIN));
		// access main controller for entire application
		MainController mainController = loader.getController();
		VistaNavigator.setMainController(mainController);
		// loadVista method from VistaNavigator
		// VISTA_1 = Login.fxml
		VistaNavigator.loadVista(VistaNavigator.VISTA_1);
		return mainPane;
	}

	/**
	 * Creates the main application scene.
	 *
	 * @param mainPane
	 *            the main application layout.
	 *
	 * @return the created scene.
	 */
	private Scene createScene(Pane mainPane) {
		Scene scene = new Scene(mainPane);

		// CSS formatted stylesheet to adjust visual layout
		// for entire application
		scene.getStylesheets().setAll(
				getClass().getResource("vista.css").toExternalForm());

		return scene;
	}

	/**
	 * launch method inherited from Application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
			launch(args);
		
	}

	public String getTextTriageQueue() {
		return textTriageQueue;
	}

	public void setTextTriageQueue(String textTriageQueue) {
		this.textTriageQueue = textTriageQueue;
	}
}