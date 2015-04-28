package sprint4IncrementGUI;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Set;

import sprint4Increment.HospitalBackup;
import sprint4Increment.Patient;
import sprint4Increment.Reception;
import sprint4Increment.TriageManager;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Oracle blog https://blogs.oracle.com/jmxetc/entry/
 *         connecting_scenebuilder_edited_fxml_to
 * @author Alex Kidston controller for ReceptionEU fxml
 *
 */
public class TriageController implements Initializable {

	private String dateOfAccident;

	private String timeOfAccident;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

	private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

	private Patient patient = new Patient();

	ChangeListener<? super String> choiceCategorySelectionChangeListener;

	private TriageManager tM;
	
	public static boolean triageAvailable = true;

	

	@FXML
	private TextField textCategory;

	@FXML
	private TextField textPatient;

	@FXML
	private Button buttonUpdateTriage;

	@FXML
	private TableView<Patient> tableTriageQueue;

	@FXML
	private Button buttonUpdatePatient;

	@FXML
	private Button buttonUpdateQueue;

	@FXML
	private TextArea textNotes;

	@FXML
	private Button buttonAdmitUnconsciousPatient;

	@FXML
	private ChoiceBox<String> choiceCategory;

	@FXML
	private TextArea textSymptoms;

	@FXML
	private TextField textDateOfAcc;

	@FXML
	private ChoiceBox<String> choiceBayList;

	@FXML
	public TextArea textTriageQueue;

	@FXML
	private ChoiceBox<String> choiceTriageList;

	@FXML
	private TextField textTimeOfAcc;

	@FXML
	private TextField textNatureOfAcc;

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
		assert buttonUpdatePatient != null : "fx:id=\"buttonUpdatePatient\" was not injected: check your FXML file 'Triage.fxml'.";
		assert buttonLogOut != null : "fx:id=\"buttonLogOut\" was not injected: check your FXML file 'Triage.fxml'.";
		assert buttonSwitchUser != null : "fx:id=\"buttonSwitchUser\" was not injected: check your FXML file 'Triage.fxml'.";
		assert buttonUpdateQueue != null : "fx:id=\"buttonUpdateQueue\" was not injected: check your FXML file 'Triage.fxml'.";

		// initialize your logic here: all @FXML variables will have been
		// injected

		// set up the patient choicebox populated by Reception
		ArrayList<String> idList = new ArrayList<String>();
		idList.add(0, "Choose a Patient");
		
		this.patient=Reception.triageManager.getNextPatient();
		 
		idList.add(String.valueOf(patient.getFirstName()));
		
			
		triageAvailable = false;
		choiceTriageList.setItems(FXCollections.observableArrayList(idList));
		choiceTriageList.getSelectionModel().select(0);

		// bind patient choice to a text field
		textPatient.textProperty().bind(
				choiceTriageList.getSelectionModel().selectedItemProperty());

		// set up patient category choice box
		ArrayList<String> categoryList = new ArrayList<String>();
		categoryList.add(0, "Choose a category");
		categoryList.add("Emergency");
		categoryList.add("Urgent");
		categoryList.add("SemiUrgent");
		categoryList.add("NonUrgent");

		// bind category choice to a text field
		textCategory.textProperty().bind(
				choiceCategory.getSelectionModel().selectedItemProperty());

		choiceCategory
				.setItems(FXCollections.observableArrayList(categoryList));
		choiceCategory.getSelectionModel().select(0);

		// set up Triage report table
		ObservableList<Patient> triageReport = FXCollections
				.observableArrayList();

		for (Patient p : TriageManager.triageList) {
			if (!triageReport.contains(p)) {
				triageReport.add(p);
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
		tableTriageQueue.getColumns().setAll(patientIDCol, waitingTimeCol,
				categoryCol);
		


		// button event handler
		buttonUpdateQueue.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				try {
					Reception.triageManager.patientHandler(patient);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				triageAvailable=true;
				//if there is a new patient in reception display their name otherwise remove the processed patients name
				if(!Reception.triageManager.getNextPatient().equals(patient)){
					idList.add(Reception.triageManager.getNextPatient().getFirstName());
				}
				idList.remove(String.valueOf(patient.getFirstName()));
				choiceTriageList.setItems(FXCollections.observableArrayList(idList));
				choiceTriageList.getSelectionModel().select(0);	
				
				ObservableList<Patient> triageReport = FXCollections
						.observableArrayList();

				for (Patient p : TriageManager.triageList) {
					if (!triageReport.contains(p)) {
						triageReport.add(p);
					}
				}

				tableTriageQueue.setItems(triageReport);

				TableColumn<Patient, Integer> patientIDCol = new TableColumn<Patient, Integer>(
						"Patient ID");
				patientIDCol.setCellValueFactory(new PropertyValueFactory(
						"patientID"));
				TableColumn<Patient, String> waitingTimeCol = new TableColumn<Patient, String>(
						"Waiting Time");
				waitingTimeCol.setCellValueFactory(new PropertyValueFactory(
						"waitingTime"));
				TableColumn<Patient, String> categoryCol = new TableColumn<Patient, String>(
						"Category");
				categoryCol.setCellValueFactory(new PropertyValueFactory(
						"category"));
				tableTriageQueue.getColumns().setAll(patientIDCol,
						waitingTimeCol, categoryCol);

				choiceTriageList.getSelectionModel().select(0);
				choiceCategory.getSelectionModel().select(0);
				clearAll();

			}
		});
		
		

		// choicebox event handler
		choiceTriageList.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
					}
				});
		// choice box event handler
		choiceCategory.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
					}
				});

		// button event handler
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

		buttonUpdatePatient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				
				patient.setCategory(textCategory.getText());
				//patient.setDateOfAccident(textDateOfAcc.getText());
				//patient.setTimeOfAccident(textDateOfAcc.getText());
				patient.setSymptoms(textSymptoms.getText());
				patient.setAdditionalNotes(textNotes.getText());
				
			
				

			}

		});
	}
public void clearAll(){

	textCategory.clear();
	textDateOfAcc.clear();
	textTimeOfAcc.clear();
	textNatureOfAcc.clear();
	textSymptoms.clear();
	textNotes.clear();
}
}