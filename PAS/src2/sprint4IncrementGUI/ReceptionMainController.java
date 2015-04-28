package sprint4IncrementGUI;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

/**
 *  @author Oracle blog https://blogs.oracle.com/jmxetc/entry/
 *         connecting_scenebuilder_edited_fxml_to
 * @author Alex Kidston controller for ReceptionEU fxml
 *
 */
public class ReceptionMainController implements Initializable {
	/**
	 * button for notification of event
	 */
    @FXML
    private Button buttonAllOther;
	/**
	 * button for notification of event
	 */
    @FXML
    private Button buttonLogOut;
	/**
	 * button for notification of event
	 */
    @FXML
    private Button buttonSwitchUser;
	/**
	 * button for notification of event
	 */
    @FXML
    private Button buttonEU;
	/**
	 * button for notification of event
	 */
    @FXML
    private Button buttonUK;
    
	@Override
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		assert buttonUK != null : "fx:id=\"buttonUK\" was not injected: check your FXML file 'ReceptionMain.fxml'.";
		assert buttonEU != null : "fx:id=\"buttonEU\" was not injected: check your FXML file 'ReceptionMain.fxml'.";
		assert buttonAllOther != null : "fx:id=\"buttonAllOther\" was not injected: check your FXML file 'ReceptionMain.fxml'.";
		assert buttonSwitchUser != null : "fx:id=\"buttonSwitchUser\" was not injected: check your FXML file 'ReceptionMain.fxml'.";		
		assert buttonLogOut != null : "fx:id=\"buttonLogOut\" was not injected: check your FXML file 'ReceptionMain.fxml'.";
		// initialize your logic here: all @FXML variables will have been
		// injected

		
		buttonUK.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
		        VistaNavigator.loadVista(VistaNavigator.VISTA_3);
			}
		});

		buttonEU.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			
				VistaNavigator.loadVista(VistaNavigator.VISTA_4);
				
			}
			
		});

		buttonAllOther.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			
				VistaNavigator.loadVista(VistaNavigator.VISTA_5);
				

			}
			
		});

		buttonSwitchUser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				VistaNavigator.loadVista(VistaNavigator.VISTA_1);
			}

		});

		buttonLogOut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage0 = (Stage) buttonLogOut.getScene().getWindow();
				System.exit(0);
			}
		});
	
		
		
	
	}
	
	
}