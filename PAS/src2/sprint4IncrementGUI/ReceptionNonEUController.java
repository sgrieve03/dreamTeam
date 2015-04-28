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
 * @author Oracle blog https://blogs.oracle.com/jmxetc/entry/
 *         connecting_scenebuilder_edited_fxml_to
 * @author Alex Kidston controller for ReceptionEU fxml
 *
 */
public class ReceptionNonEUController implements Initializable {
	/**
	 * hashmap storing names from database NOTE: replace with method to pass
	 * database values to a instance of Patient class
	 */
	HashMap<String, String> names;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textPostCode;
	/**
	 * button for notification of event
	 */
	@FXML
	private Button buttonLogOut;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textFirstName;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textTelephone11;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textTelephone;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textTelephone1;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textLastName;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textHeight;
	/**
	 * button for notification of event
	 */
	@FXML
	private Button buttonAdmitPatient;
	/**
	 * button for notification of event
	 */
	@FXML
	private Button buttonNHSNumber;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textAddress2;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textNHSNumber;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textSearchNHSNumber;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textAddress1;
	/**
	 * button for notification of event
	 */
	@FXML
	private Button buttonSwitchUser;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textDOB;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textWeight;
	/**
	 * text field for text entry / values from database
	 */
	@FXML
	private TextField textBloodType;

	@Override
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		assert buttonNHSNumber != null : "fx:id=\"buttonNHSNUmber\" was not injected: check your FXML file 'Reception.fxml'.";
		assert buttonAdmitPatient != null : "fx:id=\"buttonAdmitPatient\" was not injected: check your FXML file 'Reception.fxml'.";
		assert buttonLogOut != null : "fx:id=\"buttonLogOut\" was not injected: check your FXML file 'Reception.fxml'.";
		assert buttonSwitchUser != null : "fx:id=\"buttonSwitchUser\" was not injected: check your FXML file 'Reception.fxml'.";
		// initialize your logic here: all @FXML variables will have been
		// injected

		Platform.runLater(() -> textSearchNHSNumber.requestFocus());

		buttonSwitchUser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				VistaNavigator.loadVista(VistaNavigator.VISTA_1);

				/*
				 * Stage stage0 = (Stage)
				 * buttonSwitchUser.getScene().getWindow(); FXMLLoader loader =
				 * new FXMLLoader();
				 * loader.setLocation(getClass().getResource("Login.fxml"));
				 * stage0.close(); Parent content = null; try { content =
				 * (Parent) loader.load(); } catch (IOException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); } Stage stage
				 * = new Stage(); stage.setScene(new Scene(content));
				 * stage.show();
				 */
			}

		});

		buttonLogOut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage0 = (Stage) buttonLogOut.getScene().getWindow();
				System.exit(0);
			}
		});

		/*buttonNHSNumber.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				names = alexSystem2.ORMManager.getNames();
				alexSystem2.ORMManager orm = new alexSystem2.ORMManager();
				orm.findPatientByNHSNumber(Integer.valueOf(textSearchNHSNumber
						.getText()));
				// int nhsNumber =
				// Integer.valueOf(textSearchNHSNumber.getText());
				textFirstName.setText(names.get("firstName"));
				textLastName.setText(names.get("lastName"));
				textPostCode.setText(names.get("postCode"));
				textNHSNumber.setText(textSearchNHSNumber.getText());

			}
		});*/
	}
}