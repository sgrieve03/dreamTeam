package sprint4Increment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 * Class for connecting to the Database
 * @author laura
 */
public class DBConnection {
	/**
	 * 
	 */
	private PatientIDGenerator patientIDGen;
	/**
	 * username for connecting to the db
	 */
	private static final String USERNAME = "40023058";
	/**
	 * password for connecting to the db
	 */
	private static final String PASSWORD = "BGF3974";
	/**
	 * url for database
	 */
	private static final String url = "jdbc:mysql://web2.eeecs.qub.ac.uk/"+USERNAME; 
	/**
	 * initialising the connection
	 */
	Connection connection = null;

	/**
	 * loading the driver
	 */
	public void loadDriver(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			} catch(java.lang.ClassNotFoundException e) {
				System.err.print("ClassNotFoundException: "); 
				System.err.println(e.getMessage());
			}
	}
	/**
	 *  method to connect to the database
	 */
	public void getConnection() {
		try {
			if (connection == null){
				connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  method to close the connection to the database
	 */
	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
		  // TODO Auto-generated catch block
		     e.printStackTrace();
		}
	}
	/**
	 * method to allow staff to login with valid username and password
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public String staffLogin(String username, String password) throws SQLException {
		loadDriver();
		getConnection();
		ResultSet rs = null;
		String staffCategory = null;
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
	/**
	 * Obtains security question from DB
	 * @param userName
	 * @return
	 * @throws SQLException
	 */
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
		/**
		 * Obtains security answer from DB
		 * @param userName
		 * @return
		 * @throws SQLException
		 */
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
		/**
		 * This method creates a patient from the details pulled into a result set
		 * The result set can be obtained through searching the db  
		 * @param returnedPatient
		 * @return
		 */
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
		/**
		 * Inserting a patient into the NHS DB
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
		 */
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
		 * This method updates the patients details in the DB
		 * @param columnHeading - Name of the attribute being changed
		 * @param newValue - new value being put in to the attribute
		 * @param NHSNumber - nhs number of the patient
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
		 * Searching the DB using the NHS number of the patient
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
		 * Searching the DB using the Firstname, surname, postcode and DB of the patient
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
		/**
		 * Inserts details into hospital_visits table
		 * called when the patient is discharged
		 * @param patientID (unique per visit)
		 * @param date
		 * @param waitingTimeMins - how long the patient waiting in the queue
		 * @param category - patient's final category (if it was changed at all)
		 */
		public void insertPatientStats(String patientID, String date, int waitingTimeMins, int category){
			loadDriver();
			getConnection();
			try{
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("INSERT INTO hospital_visits VALUES (?,?,?,?)");
				preparedStatement.setString(1, patientID);
				preparedStatement.setString(2, TimeHandler.dateNow());
				preparedStatement.setInt(3, waitingTimeMins);
				preparedStatement.setInt(4, category);
				preparedStatement.executeUpdate();
			} catch (SQLException e){
				e.printStackTrace();
			}
			closeConnection();
		}
		/**
		 * inserts details into hospital sms table
		 * @param date
		 * @param timeCalled
		 * @param recipient - can only be a member of the on call team or the hospital manager
		 */
		public void insertOnCallStats(String date, String timeCalled, String recipient){
			loadDriver();
			getConnection();
			try{
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("INSERT INTO hospital_SMS VALUES (?,?,?)");
				preparedStatement.setString(1, date);
				preparedStatement.setString(2, timeCalled);
				preparedStatement.setString(3, recipient);
				preparedStatement.executeUpdate();
			} catch (SQLException e){
				e.printStackTrace();
			}
			closeConnection();
		}
		/**
		 * inserts details into hospital manager stats
		 * When the hospital manager had to be alerted 
		 * @param date
		 * @param timeCalled
		 */
		public void insertHospitalManagerStats(String date, String timeCalled){
			loadDriver();
			getConnection();
			try{
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("INSERT INTO hospital_manager_email VALUES (?,?)");
				preparedStatement.setString(1, date);
				preparedStatement.setString(2, timeCalled);
				preparedStatement.executeUpdate();
			} catch (SQLException e){
				e.printStackTrace();
			}
			closeConnection();
		}
		/**
		 * returns a 2d array of details from hospital sms table
		 * @param date
		 * @return
		 */
		public String[][] checkOnCallAlerts(String date){
			loadDriver();
			getConnection();
			ResultSet rs = null;
			String[][] arr = null;
			try{
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT * FROM hospital_SMS WHERE date = ?");
				preparedStatement.setString(1, date);
				preparedStatement.executeQuery();
				rs = preparedStatement.executeQuery();
				rs.last();
	            int rowSize = rs.getRow();
	            rs.beforeFirst();
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int i = 0;
	            int columnSize = rsmd.getColumnCount();
				arr = new String[rowSize][columnSize];
				while(rs.next() && i < rowSize){
					for (int j=0; j<columnSize; j++){
						arr[i][j] = rs.getString(j+1);		
					}
					i++;
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
			closeConnection();
			
			return arr;
		}
		/**
		 * returns a 2d array of the date and time of alerts
		 * @param date
		 * @return
		 */
		public String[][] checkHospitalManagerAlert(String date){
			loadDriver();
			getConnection();
			ResultSet rs = null;
			String[][] arr = null;
			try{
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT * FROM hospital_manager_email WHERE date = ?");
				preparedStatement.setString(1, date);
				rs = preparedStatement.executeQuery();
				rs.last();
	            int rowSize = rs.getRow();
	            rs.beforeFirst();
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int i = 0;
	            int columnSize = rsmd.getColumnCount();
				arr = new String[rowSize][columnSize];
				while(rs.next() && i < rowSize){
					for (int j=0; j<columnSize; j++){
						arr[i][j] = rs.getString(j+1);		
					}
					i++;
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
			closeConnection();
			return arr;
			}
		/**
		 * this method returns an array of the result set for the date 
		 * date needs to be input in "yyyy-mm-dd" format
		 * @param date
		 * @return
		 */
		public String[][] checkPatientStats(String date){
			loadDriver();
			getConnection();
			ResultSet rs = null;
			String arr[][] = null;
			try{
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT * FROM hospital_visits WHERE visit_date = ?");
				preparedStatement.setString(1, date);
				rs = preparedStatement.executeQuery();
				rs.last();
	            int rowSize = rs.getRow();
	            rs.beforeFirst();
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int i = 0;
	            int columnSize = rsmd.getColumnCount();
				arr = new String[rowSize][columnSize];
				while(rs.next() && i < rowSize){
					for (int j=0; j<columnSize; j++){
						arr[i][j] = rs.getString(j+1);		
					}
					i++;
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
			closeConnection();
			
			return arr;
		}

}
