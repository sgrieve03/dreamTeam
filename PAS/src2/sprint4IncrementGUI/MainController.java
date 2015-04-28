package sprint4IncrementGUI;

/**
 * import FX libraries
 */
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * @author JewelSea (StackOverflow)
 * @author Alex Kidston Main controller class for the entire application
 */
public class MainController {

	/**
	 * StackPane holder of a switchable vista.
	 * 
	 */
	@FXML
	private StackPane vistaHolder;

	/**
	 * Replaces the vista displayed in the vista holder with a new vista.
	 *
	 * @param node
	 *            the vista node to be swapped in.
	 */
	public void setVista(Node node) {
		vistaHolder.getChildren().setAll(node);
	}

}