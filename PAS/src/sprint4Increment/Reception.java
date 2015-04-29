package sprint4Increment;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author laura
 *
 */
public class Reception {
	/**
	 * create the linked list for the patients
	 */
	LinkedList<Patient> patientList;
	/**
	 * 
	 */
	Patient patient = new Patient();
	/**
	 * instantiate the bayManager class
	 */
	public static BayManager bayManager = new BayManager();
	/**
	 * instantiate the triage class
	 */
	public static TriageManager triageManager = new TriageManager();
	/**
	 * Instantiate the ormManager class
	 */
	ORMManager ormManager = new ORMManager();
	
	DBConnection db = new DBConnection();
	
	public static Reception reception=new Reception();
	
	HospitalRunner hospitalRunner;
	
	QueueSorter sort = new QueueSorter();
	
	/**
	 * default constructor
	 */
	public Reception() {
	}
	/**
	 * constructor
	 * @throws InterruptedException 
	 */
	public Reception( LinkedList<Patient> patientList) throws InterruptedException {
	
		this.patientList = patientList;
			
	}
	/**
	 * Method to login to the system
	 * @param staff member's username
	 * @param staff member's password
	 * @throws SQLException
	 */
	public void login(String username, String password) throws SQLException {
		ormManager.login(username,password);
	}
	/**
	 * Method to create a patient object
	 * @param returnedPatient
	 * @return
	 * @throws SQLException
	 */
	public Patient createPatientConscious(ResultSet returnedPatient) throws SQLException {
		 this.patient = ormManager.createPatientConscious(returnedPatient);
		 return patient;
	}
	/**
	 * Method to create a patient object if they are unconscious
	 * @param gender
	 * @return
	 */
	public Patient createPatientUnconscious(String gender){
		this.patient = ormManager.createPatientUnconscious(gender);
		return patient;
	}
	/**
	 * Adds a patient to the DB
	 * @param NHSNumber
	 * @param title
	 * @param firstName
	 * @param lastName
	 * @param postcode
	 * @param gender
	 * @param dateOfBirth
	 * @param telephone
	 * @param bloodGroup
	 * @param allergies
	 * @param existingConditions
	 * @throws SQLException
	 */
	public void addPatientToDataBase(String NHSNumber, String title, String firstName, String lastName, String postcode, char gender, String dateOfBirth, String telephone, String bloodGroup, String allergies, String existingConditions)throws SQLException {
		ormManager.addPatientToDataBase(NHSNumber, title, firstName, lastName, postcode, gender, dateOfBirth, telephone, bloodGroup, allergies, existingConditions);
	}
	/**
	 * Updates a patients details, based on knowing their NHS Number
	 * @param columnHeading
	 * @param newValue
	 * @param NHSNumber
	 * @throws SQLException
	 */
	public void updatePatientDetails(String columnHeading, String newValue, String NHSNumber) throws SQLException {
		ormManager.updatePatientDetails(columnHeading, newValue, NHSNumber);
	}
	/**
	 * Search for a patient who doesnt know their NHS number
	 * @param firstName
	 * @param lastName
	 * @param postCode
	 * @param dateOfBirth
	 * @return
	 * @throws SQLException
	 */
	public Patient searchForPatientByFirstNameLastNamePostCodeDOB(String firstName, String lastName, String postCode, String dateOfBirth) throws SQLException{
				this.patient = ormManager.searchPatientByFirstNameLastNamePostCodeDOB(firstName, lastName, postCode, dateOfBirth);
		return patient;
	}
	/**
	 * Search for a patient who knows their NHS number
	 * @param nHSNumber
	 * @return
	 * @throws SQLException
	 */
	public Patient searchForPatient(String nHSNumber) throws SQLException{
		this.patient =  ormManager.searchForPatient(nHSNumber);
		return patient;
	}
	
	
	/**
	* This method is used to register patients with reception and then to send them to triage if there is a place available
	* @param HospitalRunner runner
	* @param Reception reception
	* @throws Exception
	*/
	public void sendToTriage(Patient patient, HospitalRunner runner) throws Exception {
		
		this.hospitalRunner = runner;
		
		
		if (triageManager.checkTriageList()) {
			
			HospitalBackup.writeToFile(patient, "patientReception");
			
	
			triageManager.visitTriageNurse();
		}
	System.out.println(patient.getPatientID());
		HospitalBackup.writeToFile(runner, "Runner");
		HospitalBackup.writeToFile(reception, "Reception");
	}
}

