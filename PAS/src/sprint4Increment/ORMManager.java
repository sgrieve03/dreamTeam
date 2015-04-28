package sprint4Increment;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author laura
 *
 */
public class ORMManager {
	/**
	 * instantiating the database connector class
	 */
	DBConnection db = new DBConnection();
	/**
	 * Creating a patient object
	 */
	Patient patient = new Patient();
	/**
	 * Login method
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public void login(String username, String password) throws SQLException{
		String staffCategory = db.staffLogin(username, password);
		try {
			if (staffCategory.equalsIgnoreCase("receptionist")){
				//trace statement
				System.out.println("recpetionist logged in");
			} else if (staffCategory.equalsIgnoreCase("nurse")){
				//login to system as triage nurse
				//trace statement
				System.out.println("triage nurse logged in");
			}else if (staffCategory.equalsIgnoreCase("doctor")){
				//login to system as doctor
				//trace statement
				System.out.println("doctor logged in");
			} else {
				//dont log in
				//trace statement
				System.out.println("you may not log in");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * creating a patient from the result set of the database search
	 * @param returnedPatient
	 * @param isConscious
	 * @return
	 */
	public Patient createPatientConscious(ResultSet returnedPatient) throws SQLException {
		return db.convertResultSetToPatient(returnedPatient);
	}
	/**
	 * Creating an unconscious patient object with only a first name and surname
	 * @param gender
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Patient createPatientUnconscious(String gender) throws IllegalArgumentException{
		Patient patient = new Patient();
		if (gender.equalsIgnoreCase("m")){ 
			patient.setFirstName("John");
			patient.setLastName("Doe");
		} else if (gender.equalsIgnoreCase("f")){
			patient.setFirstName("Jane");
			patient.setLastName("Doe");;
		} else if((!gender.equalsIgnoreCase("m"))
				|| (!gender.equalsIgnoreCase("f"))) {
			throw new IllegalArgumentException();
		}
		return patient;
	}
	/**
	 * Adds patient to db, and creates patient object of the patient added to register them in A&E
	 * @param NHSNumber
	 * @param title
	 * @param firstName
	 * @param lastName
	 * @param postcode
	 * @param gender
	 * @param dateOfBirth
	 * @param bloodGroup
	 * @param allergies
	 * @param existingConditions
	 * @return
	 */
	public void addPatientToDataBase(String NHSNumber, String title, String firstName, String lastName, String postcode, char gender, String dateOfBirth, String telephone, String bloodGroup, String allergies, String existingConditions ) throws SQLException {
		db.insertPatientDB(NHSNumber, title, firstName, lastName, postcode, gender, dateOfBirth, telephone, bloodGroup, allergies, existingConditions);
	}
	/**
	 * Update a patient's details in the database
	 * @param columnHeading - the name of the attribute
	 * @param newValue - the new value to be input to the attribute (columnHeading)
	 * @param NHSNumber - must know NHS number to be able to update
	 * @throws SQLException
	 */
	public void updatePatientDetails(String columnHeading, String newValue, String NHSNumber) throws SQLException{
		//validation needed to ensure parameters are valid  
		 db.updatePatientDetails(columnHeading, newValue, NHSNumber);
	}
	/**
	 * Method to return a patient when they know their nhsNumber
	 * @param nHSNumber
	 * @return patient 
	 */
	public Patient searchForPatient(String NHSNumber){
		return db.searchPatientByNHSNumber(NHSNumber);
	}
	public Patient searchPatientByFirstNameLastNamePostCodeDOB(String firstName, String lastName, String postCode, String dateOfBirth) throws SQLException{
		return (db.searchPatientByFirstNameSurnamePostCodeDOB(firstName, lastName, postCode, dateOfBirth));
	}
}