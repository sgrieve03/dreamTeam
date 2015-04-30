package sprint4IncrementGUI;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sprint4Increment.BayManager;
import sprint4Increment.DBConnection;
import sprint4Increment.HospitalRunner;
import sprint4Increment.Patient;
import sprint4Increment.Reception;
import sprint4Increment.TriageManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

/**
 * @author Oracle blog https://blogs.oracle.com/jmxetc/entry/
 *         connecting_scenebuilder_edited_fxml_to
 * @author Alex Kidston controller for ReceptionEU fxml
 *
 */
public class SysAdminController implements Initializable {


	private static final int MYTHREADS = 300;
	private TriageManager tM;

	@FXML
	private Button buttonInitialisePAS;

	@FXML
	private TextArea textTriageQueue;

	@FXML
	private TextArea textBayQueue;

	@FXML
	private Button buttonLogOut;
	/**
	 * button for notification of event
	 */
	@FXML
	private Button buttonSwitchUser;

	@Override
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		assert buttonLogOut != null : "fx:id=\"buttonLogOut\" was not injected: check your FXML file 'Manager.fxml'.";
		assert buttonSwitchUser != null : "fx:id=\"buttonSwitchUser\" was not injected: check your FXML file 'Manager.fxml'.";

		// initialize your logic here: all @FXML variables will have been
		// injected

		
		buttonSwitchUser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				VistaNavigator.loadVista(VistaNavigator.VISTA_1);
			}

		});

		buttonInitialisePAS.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
				
				Runnable system;
				system = new InitialisePAS();
				executor.execute(system);
				
			}	
					   
		});
}
	}
