package sprint4Increment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DBConnection {
	private PatientIDGenerator patientIDGen;
	private static final String USERNAME = "40137653";
	private static final String PASSWORD = "ASH8646";
	private static final String url = "jdbc:mysql://web2.eeecs.qub.ac.uk/"+USERNAME; 
	Connection connection = null;
	
	//loading the driver
	public void loadDriver(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			} catch(java.lang.ClassNotFoundException e) {
				System.err.print("ClassNotFoundException: "); 
				System.err.println(e.getMessage());
			}
	}
	// method to connect to the database
	public void getConnection() {
		try {
			if (connection == null){
				connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// method to close the connection to the database
	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			// do nothing
		}
	}	
	public String staffLogin(String username, String password) throws SQLException {
		loadDriver();
		getConnection();
		ResultSet rs = null;
		String staffCategory = "";
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT * FROM staff WHERE stf_username = ? AND stf_password = ?");
	        preparedStatement.setString(1, username);
	        preparedStatement.setString(2, password);
	        rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	staffCategory = (rs.getString("stf_category"));
	        }
	        // System.out.println(staffCategory);
	        preparedStatement.close();
	        
		} catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
		}
		closeConnection();
		// System.out.println("connection closed");
		return staffCategory;
	}
	
	public String staffSecurityQ(String userName) throws SQLException {
		String securityQQuery = "SELECT stf_security_q FROM staff WHERE stf_username = '"+userName+"'";
		loadDriver();
		getConnection();
		ResultSet rs = null;
		String staffSecurityQ = "";
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(securityQQuery);
	        rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	staffSecurityQ = (rs.getString("stf_security_q"));
	        }
	        // System.out.println(staffCategory);
	        preparedStatement.close();
	        
		} catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
		}
		closeConnection();
		// System.out.println("connection closed");
		return staffSecurityQ;
	}
	
	
	public String staffSecurityA(String userName) throws SQLException {
		String securityAQuery = "SELECT stf_security_a FROM staff WHERE stf_username = '"+userName+"'";
		loadDriver();
		getConnection();
		ResultSet rs = null;
		String staffSecurityA = "";
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(securityAQuery);
	        rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	staffSecurityA = (rs.getString("stf_security_a"));
	        }
	        // System.out.println(staffCategory);
	        preparedStatement.close();
	        
		} catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
		}
		closeConnection();
		// System.out.println("connection closed");
		return staffSecurityA;
	}
	
	public Patient convertResultSetToPatient(ResultSet returnedPatient) {
		Patient patient = new Patient();
		try{
			while (returnedPatient.next()){
				String NHSNumber = (returnedPatient.getString("NHS_number"));
				patient.setNHSNumber(NHSNumber);
				String firstName = (returnedPatient.getString("pat_first_name"));
				patient.setFirstName(firstName);
				String lastName = (returnedPatient.getString("pat_surname"));
				patient.setLastName(lastName);
				String postCode = (returnedPatient.getString("pat_postcode"));
				patient.setPostCode(postCode);
				String dateOfBirth = (returnedPatient.getString("pat_dob"));
				patient.setDateOfBirth(dateOfBirth);
				String bloodGroup = (returnedPatient.getString("pat_blood_group"));
				patient.setBloodGroup(bloodGroup);
				String allergies = (returnedPatient.getString("pat_allergies"));
				patient.setAllergies(allergies);
				String title = (returnedPatient.getString("pat_title"));
				patient.setTitle(title);
				String existingCon = (returnedPatient.getString("pat_existing_con"));
				patient.setExistingConditions(existingCon);
				String telephone = (returnedPatient.getString("pat_telephone"));
				patient.setTelephone(telephone);
				String gender = (returnedPatient.getString("pat_gender"));
				patient.setGender(gender);
				String dob = (returnedPatient.getString("pat_dob"));
				patient.setDateOfBirth(dob);
			}
		}catch(SQLException e){
			  e.printStackTrace();  
		} 
		return patient;
	}
	public void insertPatientDB(String NHSNumber, String title, String firstName, String lastName, String postcode, char gender, String dateOfBirth, String telephone, String bloodGroup, String allergies, String existingConditions){
		loadDriver();
		getConnection();
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("INSERT INTO patient VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, NHSNumber);
	        preparedStatement.setString(2, title);
	        preparedStatement.setString(3, firstName);
	        preparedStatement.setString(4, lastName);
	        preparedStatement.setString(5, postcode);
	        preparedStatement.setString(6, String.valueOf(gender));
	        preparedStatement.setString(7, dateOfBirth);
	        preparedStatement.setString(8, telephone);
	        preparedStatement.setString(9, bloodGroup);
	        preparedStatement.setString(10, allergies);
	        preparedStatement.setString(11, existingConditions);
	        preparedStatement.executeUpdate();
	        //preparedStatement.close();
		} catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
		}
		closeConnection();
	}
	/**
	 * This method works!
	 * @param columnHeading
	 * @param newValue
	 * @param NHSNumber
	 */
	public void updatePatientDetails(String columnHeading, String newValue, String NHSNumber) {
		loadDriver();
		getConnection();
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("UPDATE patient SET "+columnHeading+" = ? WHERE NHS_number = ?");
	        //preparedStatement.setString(1, columnHeading);
	        preparedStatement.setString(1, newValue);
	        preparedStatement.setString(2, NHSNumber);
	        preparedStatement.executeUpdate();
	        //preparedStatement.close();
		} catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
		}
		closeConnection();
	}
	/**
	 * This method works!
	 * @param NHSNumber
	 * @return
	 */
	public Patient searchPatientByNHSNumber(String NHSNumber){
		loadDriver();
		getConnection();
		ResultSet returnedPatient = null;
		Patient patient = new Patient();
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT * FROM patient WHERE NHS_number = ?");
	        preparedStatement.setString(1, NHSNumber);
	        returnedPatient = preparedStatement.executeQuery();    
	        //preparedStatement.close();
		} catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
		}
		patient = convertResultSetToPatient(returnedPatient);
		patient.setPatientID(patientIDGen.INSTANCE.getNextID());
		//closeConnection();
		return patient;
	}
	/**
	 * This method works!
	 * @param firstName
	 * @param lastName
	 * @param postCode
	 * @param dateOfBirth
	 * @return
	 */
	public Patient searchPatientByFirstNameSurnamePostCodeDOB(String firstName, String lastName, String postCode, String dateOfBirth) {
		loadDriver();
		getConnection();
		ResultSet returnedPatient = null;
		Patient patient = new Patient();
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT * FROM patient WHERE pat_first_name = ? AND pat_surname = ? AND pat_postcode = ? AND pat_dob = ?");
	        preparedStatement.setString(1, firstName);
	        preparedStatement.setString(2, lastName);
	        preparedStatement.setString(3, postCode);
	        preparedStatement.setString(4, dateOfBirth);
	        returnedPatient = preparedStatement.executeQuery();
	        //preparedStatement.close();
		} catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
		}
		patient = convertResultSetToPatient(returnedPatient);
		closeConnection();
		return patient;
	}

}
