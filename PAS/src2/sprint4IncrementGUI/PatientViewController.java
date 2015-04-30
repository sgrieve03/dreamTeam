package sprint4IncrementGUI;
import java.net.URL;
import java.util.ResourceBundle;

import sprint4Increment.BayManager;
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

public class PatientViewController implements Initializable {
	
	TriageManager tm = new TriageManager();
	Patient patient;
	
	private String firstName;
	
	private String lastName;
	
	@FXML
	private TextField nextPatient;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		nextPatient.setText(tm.nextInQueue());
		
	}
		

}
