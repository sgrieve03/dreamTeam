package sprint4IncrementGUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import sprint4Increment.DBConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

public class ForgotPasswordController implements Initializable {

	@FXML
	private Button buttonSecurity;
	
    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonResetPassword;

    @FXML
    private TextField textUserName;
    
    @FXML
    private TextField textSecurityQ;

    @FXML
    private PasswordField textSecurityA;

	@Override
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		assert buttonResetPassword != null : "fx:id=\"buttonResetPassword\" was not injected: check your FXML file 'ForgotPassword.fxml'.";
		assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'ForgotPassword.fxml'.";
		assert buttonSecurity != null : "fx:id=\"buttonSecurity\" was not injected: check your FXML file 'ForgotPassword.fxml'.";
		
	// buttonSecurity button logic
	buttonSecurity.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			DBConnection staffSecurityQ = new DBConnection();
			try {
				textSecurityQ.setText(staffSecurityQ.staffSecurityQ(textUserName.getText()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});

		
		// forgotPassword button logic
		buttonResetPassword.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage0 = (Stage) buttonResetPassword.getScene().getWindow();
				String userSecurityAnswer, userStoredAnswer = null;
				userSecurityAnswer = textSecurityA.getText();
				DBConnection staffSecurityA = new DBConnection();
			try {
				userStoredAnswer = staffSecurityA.staffSecurityA(textUserName.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (userSecurityAnswer.equalsIgnoreCase(userStoredAnswer)) {
				VistaNavigator.loadVista(VistaNavigator.VISTA_1);
			} else if (userSecurityAnswer!=userStoredAnswer) {
				MessageBox.show(stage0, "Invalid answer", "Try again.",
						MessageBox.ICON_INFORMATION | MessageBox.OK);
				textUserName.clear();
				textSecurityQ.clear();
				textSecurityA.clear();
				
			}
			}
		});

		// help button logic
		buttonCancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				VistaNavigator.loadVista(VistaNavigator.VISTA_1);
			}
		});

	}

}
