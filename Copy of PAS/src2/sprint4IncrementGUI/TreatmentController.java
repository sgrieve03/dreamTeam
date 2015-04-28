package sprint4IncrementGUI;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import sprint4Increment.BayManager;
import sprint4Increment.Patient;
import sprint4Increment.Reception;
import sprint4Increment.TriageManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * @author Oracle blog https://blogs.oracle.com/jmxetc/entry/
 *         connecting_scenebuilder_edited_fxml_to
 * @author Alex Kidston controller for ReceptionEU fxml
 *
 */
public class TreatmentController implements Initializable {
	
	@FXML
	private TableView<Patient> tableBayQueue;
	
	@FXML
    private TextField nextFromTriage;

	/**
	 * button for notification of event
	 */
	@FXML
	private Button buttonLogOut;
	@FXML
	private Button buttonDeceased;
	/**
	 * button for notification of event
	 */
	@FXML
	private Button buttonAdmit;
	@FXML
	private Button buttonSwitchUser;
	/**
	 * button for notification of event
	 */
	@FXML
	private Button treatmentButton; // Value injected by FXMLLoader
	/**
	 * text area for output 
	 */
	@FXML
	private TextArea treatmentOutput;

	@Override
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		assert treatmentButton != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";
		assert buttonLogOut != null : "fx:id=\"buttonLogOut\" was not injected: check your FXML file 'Reception.fxml'.";
		assert buttonSwitchUser != null : "fx:id=\"buttonSwitchUser\" was not injected: check your FXML file 'Reception.fxml'.";
		assert buttonAdmit != null : "fx:id=\"buttonAdmit\" was not injected: check your FXML file 'Treatment.fxml'.";
		assert buttonDeceased != null : "fx:id=\"buttonDeceased\" was not injected: check your FXML file 'Treatment.fxml'.";
		
		// initialize your logic here: all @FXML variables will have been
		// injected
		nextFromTriage.setText(Reception.triageManager.nextInQueue());
		
		// set up Bay report table
		ObservableList<Patient> bayReport = FXCollections
				.observableArrayList();

		for (Patient p : BayManager.patientListBays) {
			if (!bayReport.contains(p)) {
				bayReport.add(p);
			}
		}

		TableColumn<Patient, Integer> patientIDCol = new TableColumn<Patient, Integer>(
				"Patient ID");
		patientIDCol.setCellValueFactory(new PropertyValueFactory("patientID"));
		TableColumn<Patient, String> waitingTimeCol = new TableColumn<Patient, String>(
				"Waiting Time");
		waitingTimeCol.setCellValueFactory(new PropertyValueFactory(
				"waitingTime"));
		TableColumn<Patient, String> categoryCol = new TableColumn<Patient, String>(
				"Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory("category"));
		tableBayQueue.getColumns().setAll(patientIDCol, waitingTimeCol,
				categoryCol);
		
		tableBayQueue.setItems(bayReport);
		

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

		buttonAdmit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		
		buttonDeceased.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
			}
		});

		OutputStream out = new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				appendText(String.valueOf((char) b));
			}
		};
		System.setOut(new PrintStream(out, true));
	}

	public void appendText(String str) {
		Platform.runLater(() -> treatmentOutput.appendText(str));
	}
}