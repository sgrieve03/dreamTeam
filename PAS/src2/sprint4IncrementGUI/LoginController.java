package sprint4IncrementGUI;

/**
 * FX libraries
 */
import jfx.messagebox.MessageBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sprint4Increment.DBConnection;
import sprint4Increment.HospitalBackup;
import sprint4Increment.Reception;

/**
 * @author Oracle blog https://blogs.oracle.com/jmxetc/entry/
 *         connecting_scenebuilder_edited_fxml_to
 * @author Alex Kidston controller for ReceptionEU fxml
 *
 */
public class LoginController implements Initializable {

	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private PasswordField pwPassWord;
	/**
	 * button for notification of event
	 */
	@FXML
	private Button buttonForgotPassword;
	/**
	 * button for notification of event
	 */
	@FXML
	private Button buttonHelp;
	/**
	 * button for notification of event
	 */
	@FXML
	private Button buttonLogin;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textUserName;

	/**
	 * nextPane method called to insert new fxml files into vistaHolder
	 * stackpane
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void nextPane(ActionEvent event) throws Exception {
		// vars for validation
		String userNameInput, passWordInput, staffCategory, resourceName = null;
		// assign text entered in userName & passWord text fields to
		// userName & passWord vars for searching database
		userNameInput = textUserName.getText();
		passWordInput = pwPassWord.getText();
		// instantiate DBConnection
		DBConnection staffDB = new DBConnection();
		staffCategory = staffDB.staffLogin(userNameInput, passWordInput);
		// switch statement validates allowed users
		// reception, triage, treatment & manager
		// NOTE: replace with access to staff table in database
		Stage stage0 = (Stage) buttonLogin.getScene().getWindow();
		// TRACE: System.out.println(staffCategory);
		switch (staffCategory) {
		case "receptionist":
			VistaNavigator.loadVista(VistaNavigator.VISTA_2);
			break;
		case "nurse":
			VistaNavigator.loadVista(VistaNavigator.VISTA_6);
			break;
		case "doctor":
			VistaNavigator.loadVista(VistaNavigator.VISTA_7);
			break;
		case "systemadmin":
			VistaNavigator.loadVista(VistaNavigator.VISTA_8);
			break;
		default:
			resourceName = "error";
		}
		// if statement uses MessageBox dialog
		// to warn user of invalid login
		if (resourceName != null) {
			MessageBox.show(stage0, "Invalid user", "Try again.",
					MessageBox.ICON_INFORMATION | MessageBox.OK);
			textUserName.clear();
			pwPassWord.clear();
		}
	}
	
	@FXML
	void onEnter() throws Exception{
		
		// vars for validation
				String userNameInput, passWordInput, staffCategory, resourceName = null;
				// assign text entered in userName & passWord text fields to
				// userName & passWord vars for searching database
				userNameInput = textUserName.getText();
				passWordInput = pwPassWord.getText();
				// instantiate DBConnection
				DBConnection staffDB = new DBConnection();
				staffCategory = staffDB.staffLogin(userNameInput, passWordInput);
				// switch statement validates allowed users
				// reception, triage, treatment & manager
				// NOTE: replace with access to staff table in database
				Stage stage0 = (Stage) buttonLogin.getScene().getWindow();
				switch (staffCategory) {
				case "receptionist":
					VistaNavigator.loadVista(VistaNavigator.VISTA_2);
					break;
				case "nurse":
					VistaNavigator.loadVista(VistaNavigator.VISTA_6);
					break;
				case "doctor":
					VistaNavigator.loadVista(VistaNavigator.VISTA_7);
					break;
				case "manager":
					VistaNavigator.loadVista(VistaNavigator.VISTA_8);
					break;
				default:
					resourceName = "error";
				}
				// if statement uses MessageBox dialog
				// to warn user of invalid login
				if (resourceName != null) {
					MessageBox.show(stage0, "Invalid user", "Try again.",
							MessageBox.ICON_INFORMATION | MessageBox.OK);
					textUserName.clear();
					pwPassWord.clear();
				}
	}

	@Override
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		assert buttonLogin != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
		assert buttonForgotPassword != null : "fx:id=\"forgotPassword\" was not injected: check your FXML file 'login.fxml'.";
		assert buttonHelp != null : "fx:id=\"helpButton\" was not injected: check your FXML file 'login.fxml'.";

	
		// forgotPassword button logic
		buttonForgotPassword.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				VistaNavigator.loadVista(VistaNavigator.VISTA_9);
			}
		});

		// help button logic
		buttonHelp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

			}
		});

	}

}
