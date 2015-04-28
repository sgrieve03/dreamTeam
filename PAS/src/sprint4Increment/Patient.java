package sprint4Increment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Patient implements Comparable<Patient> {
	
	String natureOfAccident;
	
	String symptoms;
	
	String additionalNotes;
	
	private String dateOfAccident;
	
	private String timeOfAccident;

	private int patientID;

	Random rand = new Random();

	/**
	 * Triage is the severity of the pataient's injury. 0 corresponds to
	 * emergency cases 1 corresponds to urgent cases 2 corresponds to
	 * semi-urgent cases 3 corresponds to non-urgent cases
	 */
	private int category;
	/**
	 * title
	 */
	private String title;
	/**
	 * The patient's first name;
	 */
	private String firstName;

	/**
	 * The patient's last name
	 */
	private String lastName;
	/**
	 * patient's telephone number
	 */
	private String telephone;
	/**
	 * The patient's post code
	 */
	private String postCode;
	/**
	 * patient's NHS number
	 */
	private String NHSNumber;
	/**
	 * patients date of birth
	 */
	private String dateOfBirth;
	private String bloodGroup;
	private String allergies;
	private String gender;
	private String existingConditions;
	private String medicalNotes;

	/**
	 * [0] Time patient enters triage [1] Time patient enters bays [3] Time
	 * patient leaves er
	 */
	private String[] time = new String[3];

	/**
	 * length of patients treatment in minutes - used to determine exit time
	 */
	private String waitingTime;
	private int lengthOfTreatmentInMinutes;

	/**
	 * constructor without args
	 */
	public Patient() {
	}

	public Patient(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * constructor for creating an unconscious patient
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Patient(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Patient(int patientID, String NHSNumber, String title,
			String firstName, String lastName, String postcode, String gender,
			String dateOfBirth, String bloodGroup, String allergies,
			String existingConditions, int category, Date dateOfAccident,
			Date timeOfAccident, String natureOfAccident, String symptoms,
			String additionalNotes) {
		this.setPatientID(patientID);
		this.NHSNumber = NHSNumber;
		this.setTitle(title);
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.allergies = allergies;
		this.bloodGroup = bloodGroup;
		this.gender = gender;
		this.setExistingConditions(existingConditions);
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(String category) {
		
		switch (category) {
		case "Emergency":
			this.category=0;
			break;
		case "Urgent":
			this.category=1;
			break;
		case "SemiUrgent":
			this.category=2;
			break;
		case "NonUrgent":
			this.category=3;
			break;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the nHSNumber
	 */
	public String getNHSNumber() {
		return NHSNumber;
	}

	/**
	 * @param NHSNumber
	 *            the nHSNumber to set needs validation
	 */
	public void setNHSNumber(String NHSNumber) {
		this.NHSNumber = NHSNumber;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set validation needed. string must be of
	 *            the form "yyyy-mm-dd"
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * validation needed for blood group - only 3 chars long
	 * 
	 * @param bloodGroup
	 *            the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * @return the allergies
	 */
	public String getAllergies() {
		return allergies;
	}

	/**
	 * @param allergies
	 *            the allergies to set
	 */
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * vaidation needed. can only be M or F
	 * 
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the existingConditions
	 */
	public String getExistingConditions() {
		return existingConditions;
	}

	/**
	 * @param existingConditions
	 *            the existingConditions to set
	 */
	public void setExistingConditions(String existingConditions) {
		this.existingConditions = existingConditions;
	}

	/**
	 * @return the medicalNotes
	 */
	public String getMedicalNotes() {
		return medicalNotes;
	}

	/**
	 * @param medicalNotes
	 *            the medicalNotes to set
	 */
	public void setMedicalNotes(String medicalNotes) {
		this.medicalNotes = medicalNotes;
	}

	public int setTreatmentTime() {
		this.lengthOfTreatmentInMinutes = rand.nextInt(10) + 1;
		String discharged = TimeHandler.addTreatmentTime(getTimeEnteredBays(),
				lengthOfTreatmentInMinutes);

		setTimeDischarged(discharged);

		return lengthOfTreatmentInMinutes;
	}

	public int getPatientTreatmentTime() {
		return this.lengthOfTreatmentInMinutes;
	}

	public String getTimeEnteredTriage() {
		return time[0];
	}

	public void setTimeEnteredTriage(String time) {
		this.time[0] = time;
	}

	public String getTimeEnteredBays() {
		return this.time[1];
	}

	public void setTimeEnteredBays(String time) {
		this.time[1] = time;
	}

	public String getTimeDischarged() {
		return this.time[2];
	}

	public void setTimeDischarged(String time) {
		this.time[2] = time;
	}

	public String getWaitingTime() {
		return this.waitingTime;
	}

	public void setWaitingTime() {
		this.waitingTime = TimeHandler.differenceInTime(getTimeEnteredTriage(),
				getTimeEnteredBays());
	}

	@Override
	public int compareTo(Patient patient) {
		int comparedTriage = patient.getCategory();

		if (this.category > comparedTriage) {
			return 1;
		} else if (this.category == comparedTriage) {
			return 0;
		} else {
			return -1;
		}
	}

	
	public String getDateOfAccident() {
		return dateOfAccident;
	}
	
	public void setDateOfAccident(String dateOfAccident) {
	
		if (!dateOfAccident.equals(null)){
			dateOfAccident=TimeHandler.formatDate(dateOfAccident);
		}
		
	}

	public void setTimeOfAccident(String timeOfAccident) {
		if(timeOfAccident!=null){
			this.timeOfAccident = TimeHandler.time(timeOfAccident);
		}
			}

	
	public String getTimeOfAccident() {
		return timeOfAccident;
	}

	public String getNatureOfAccident() {
		return natureOfAccident;
	}
	public void setNatureOfAccident(String natureOfAccident) {
		this.natureOfAccident = natureOfAccident;
	}

	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}
	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;

	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
}
