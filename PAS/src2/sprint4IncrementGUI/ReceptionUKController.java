package sprint4IncrementGUI;

/**
 * import FX libraries
 */

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;

import sprint4Increment.DBConnection;
import sprint4Increment.HospitalRunner;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

/**
 * @author Oracle blog https://blogs.oracle.com/jmxetc/entry/
 *         connecting_scenebuilder_edited_fxml_to
 * @author Alex Kidston controller for ReceptionEU fxml
 *
 */
public class ReceptionUKController implements Initializable {

	private static final String RED_BAR = "red-bar";
	private static final String YELLOW_BAR = "yellow-bar";
	private static final String ORANGE_BAR = "orange-bar";
	private static final String GREEN_BAR = "green-bar";
	private static final String[] barColorStyleClasses = { RED_BAR, ORANGE_BAR,
			YELLOW_BAR, GREEN_BAR };

	private HospitalRunner runner;

	@FXML
	private Label labelTriageProgress;

	@FXML
	private ProgressBar progressTriageList;

	@FXML
	private TableView<Patient> tableTriageQueue;

	@FXML
	private Button buttonUpdateTriage;

	@FXML
	private TextField textPatientID;

	private Main main;
	/**
	 * patient entering system from reception
	 */
	public static Patient patient;
	/**
	 * linked list of patients added to from GUI and sent to Reception class
	 */
	private static LinkedList<Patient> patientList;
	/**
	 * database connection
	 */
	DBConnection dbConnection = new DBConnection();

	/**
	 * patient title
	 */
	@FXML
	private TextField textTitle;
	/**
	 * patient blood group
	 */
	@FXML
	private TextField textBloodGroup;
	/**
	 * patient allergies
	 */
	@FXML
	private TextField textAllergies;
	/**
	 * patent existing conditions
	 */
	@FXML
	private TextField textExistingCon;
	/**
	 * patient gender
	 */
	@FXML
	private TextField textGender;
	/**
	 * patient post code
	 */
	@FXML
	private TextField textPostCode;
	/**
	 * button to leave system
	 */
	@FXML
	private Button buttonLogOut;
	/**
	 * patient first name
	 */
	@FXML
	private TextField textFirstName;
	/**
	 * patient telephone number
	 */
	@FXML
	private TextField textTelephone;
	/**
	 * patient last name
	 */
	@FXML
	private TextField textLastName;
	/**
	 * button searches for patient by NHS number
	 */
	@FXML
	private Button buttonSearchNHSNumber;
	/**
	 * button searches for patient by first name, last name, post code & date of
	 * birth
	 */
	@FXML
	private Button buttonSearchFNLNPCDOB;
	/**
	 * patient NHS number
	 */
	@FXML
	private TextField textNHSNumber;
	/**
	 * button to switch system users
	 */
	@FXML
	private Button buttonSwitchUser;
	/**
	 * patient date of birth
	 */
	@FXML
	private TextField textDOB;

	@FXML
	private Button buttonAdmitUnconsciousPatient;

	@FXML
	private TextField textGenderUnconscious;

	@FXML
	private Button buttonAdmitConsciousPatient;

	@FXML
	public TextArea textTriageQueue;

	@FXML
	private Button buttonAdmitUnconsciousPatient1;

	@FXML
	private Button buttonClearGenderUnconscious;

	@FXML
	private Button buttonClearAllConscious;

	@FXML
	private TextArea textBayQueue;

	public ReceptionUKController() {

	}

	@Override
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		
			assert buttonSearchNHSNumber != null : "fx:id=\"buttonNHSNUmber\" was not injected: check your FXML file 'Reception.fxml'.";
			assert buttonSearchFNLNPCDOB != null : "fx:id=\"buttonSearchFNLNPCDOB\" was not injected: check your FXML file 'Reception.fxml'.";
			assert buttonAdmitConsciousPatient != null : "fx:id=\"buttonAdmitConsciousPatient\" was not injected: check your FXML file 'Reception.fxml'.";
			assert buttonAdmitUnconsciousPatient != null : "fx:id=\"buttonAdmitUnconsciousPatient\" was not injected: check your FXML file 'Reception.fxml'.";
			assert buttonLogOut != null : "fx:id=\"buttonLogOut\" was not injected: check your FXML file 'Reception.fxml'.";
			assert buttonSwitchUser != null : "fx:id=\"buttonSwitchUser\" was not injected: check your FXML file 'Reception.fxml'.";
			// initialize your logic here: all @FXML variables will have been
			// injected

			Platform.runLater(() -> textNHSNumber.requestFocus());

			TableColumn<Patient, Integer> patientIDCol = new TableColumn<Patient, Integer>(
					"Patient ID");
			patientIDCol.setCellValueFactory(new PropertyValueFactory(
					"patientID"));
			TableColumn<Patient, String> waitingTimeCol = new TableColumn<Patient, String>(
					"Waiting Time");
			waitingTimeCol.setCellValueFactory(new PropertyValueFactory(
					"waitingTime"));
			tableTriageQueue.getColumns().setAll(patientIDCol, waitingTimeCol);

			// progress bar indicates triage list status
			int triageStatus = ((Integer) TriageManager.triageList.size());
			int triageBar = 0;
			if (triageStatus <= 3) {
				triageBar = 1;
			} else if ((triageStatus >= 4) && (triageStatus <= 6)) {
				triageBar = 2;
			} else if ((triageStatus >= 7) && (triageStatus <= 10)) {
				triageBar = 3;
			}

			labelTriageProgress.setText("Triage List at " + (triageStatus * 10)
					+ "% capacity");
			switch (triageBar) {
			case 1:
				setBarStyleClass(progressTriageList, GREEN_BAR);
				break;
			case 2:
				setBarStyleClass(progressTriageList, YELLOW_BAR);
				break;
			case 3:
				setBarStyleClass(progressTriageList, RED_BAR);
				break;
			default:
			}
			progressTriageList.setProgress(triageStatus);

			buttonUpdateTriage.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
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
					waitingTimeCol
							.setCellValueFactory(new PropertyValueFactory(
									"waitingTime"));
					tableTriageQueue.getColumns().setAll(patientIDCol,
							waitingTimeCol);

					// progress bar indicates triage list status
					int triageStatus = ((Integer) TriageManager.triageList
							.size());
					int triageBar = 0;
					if (triageStatus <= 3) {
						triageBar = 1;
					} else if ((triageStatus >= 4) && (triageStatus <= 6)) {
						triageBar = 2;
					} else if ((triageStatus >= 7) && (triageStatus <= 10)) {
						triageBar = 3;
					}

					labelTriageProgress.setText("Triage List at "
							+ (triageStatus * 10) + "% capacity");
					switch (triageBar) {
					case 1:
						setBarStyleClass(progressTriageList, GREEN_BAR);
						break;
					case 2:
						setBarStyleClass(progressTriageList, YELLOW_BAR);
						break;
					case 3:
						setBarStyleClass(progressTriageList, RED_BAR);
						break;
					default:
					}
					progressTriageList.setProgress(triageStatus);
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

			buttonSearchFNLNPCDOB.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Stage stage0 = (Stage) buttonSearchFNLNPCDOB.getScene()
							.getWindow();
					patient = dbConnection
							.searchPatientByFirstNameSurnamePostCodeDOB(
									textFirstName.getText(),
									textLastName.getText(),
									textPostCode.getText(), textDOB.getText());
					if (patient.getNHSNumber() != null) {
						setAll();
					} else if (patient.getNHSNumber() == null) {
						MessageBox.show(stage0, "Patient not found",
								"Try again.", MessageBox.ICON_INFORMATION
										| MessageBox.OK);
						clearAll();
					}
				}
			});

			buttonSearchNHSNumber.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Stage stage0 = (Stage) buttonSearchNHSNumber.getScene()
							.getWindow();
					patient = dbConnection
							.searchPatientByNHSNumber(textNHSNumber.getText());
					if (patient.getNHSNumber() != null) {
						setAll();
					} else if (patient.getNHSNumber() == null) {
						MessageBox.show(stage0, "Patient not found",
								"Try again.", MessageBox.ICON_INFORMATION
										| MessageBox.OK);
						clearAll();
					}
				}
			});

			buttonClearAllConscious
					.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							clearAll();
						}
					});

			buttonClearGenderUnconscious
					.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							textGenderUnconscious.clear();

						}
					});

			buttonAdmitConsciousPatient
					.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							if (TriageController.triageAvailable) {

								TriageController.triageAvailable = false;
								try {
									Reception.reception.sendToTriage(patient,
											runner);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

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
								patientIDCol
										.setCellValueFactory(new PropertyValueFactory(
												"patientID"));
								TableColumn<Patient, String> waitingTimeCol = new TableColumn<Patient, String>(
										"Waiting Time");
								waitingTimeCol
										.setCellValueFactory(new PropertyValueFactory(
												"waitingTime"));
								tableTriageQueue.getColumns().setAll(
										patientIDCol, waitingTimeCol);
								clearAll();

							}// end while triageNurseFree
						}

					});

			buttonAdmitUnconsciousPatient
					.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							Stage stage0 = (Stage) buttonAdmitUnconsciousPatient
									.getScene().getWindow();
							if ((textGenderUnconscious.getText()
									.equalsIgnoreCase("m"))
									|| (textGenderUnconscious.getText()
											.equalsIgnoreCase("f"))) {
								patient = Reception.reception
										.createPatientUnconscious(textGenderUnconscious
												.getText());
							} else if ((!textGenderUnconscious.getText()
									.equalsIgnoreCase("m"))
									|| (!textGenderUnconscious.getText()
											.equalsIgnoreCase("f"))) {

								MessageBox.show(stage0, "Patient not found",
										"Try again.",
										MessageBox.ICON_INFORMATION
												| MessageBox.OK);
								textGenderUnconscious.clear();

							}

							patient = Reception.reception
									.createPatientUnconscious(textGenderUnconscious
											.getText());

						}
					});
		
	}

	public static LinkedList<Patient> getPatientList() {
		return patientList;
	}

	public static void setPatientList(LinkedList<Patient> patientList) {
		ReceptionUKController.patientList = patientList;
	}

	public void clearAll() {

		textFirstName.clear();
		textLastName.clear();
		textPostCode.clear();
		textDOB.clear();
		textGender.clear();
		textAllergies.clear();
		textBloodGroup.clear();
		textTelephone.clear();
		textTitle.clear();
		textExistingCon.clear();
		textNHSNumber.clear();
	}

	public void setAll() {
		textFirstName.setText(patient.getFirstName());
		textLastName.setText(patient.getLastName());
		textPostCode.setText(patient.getPostCode());
		textGender.setText(String.valueOf(patient.getGender()));
		textAllergies.setText(patient.getAllergies());
		textBloodGroup.setText(patient.getBloodGroup());
		textTelephone.setText(patient.getTelephone());
		textTitle.setText(patient.getTitle());
		textExistingCon.setText(patient.getExistingConditions());
		textDOB.setText(patient.getDateOfBirth());
		textPatientID.setText(String.valueOf(patient.getPatientID()));
	}

	private void setBarStyleClass(ProgressBar bar, String barStyleClass) {
		bar.getStyleClass().removeAll(barColorStyleClasses);
		bar.getStyleClass().add(barStyleClass);
	}

}