package sprint4Increment;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * This class is used to treat patients in bays and monitor their process
 * through out. The patient's records can be updated and sent back to the
 * database. Its serializable so that an object of this type can be sent to file
 * every 60 seconds so that the classes do not remain volatile
 * 
 * @author Shauna-Marie Grieve 40010493
 *
 */
public class BayManager implements Serializable {

	/**
	 * This adapter links the bays to the larger hospital system which deals
	 * with treating patients after they are finished in the ER department. They
	 * can be discharged, refered, admitted, or be pronounced dead in ER but the
	 * er pas does not deal with these actions
	 * 
	 */
	BayManagerAdapter processedPatient;

	/**
	 * We use this object of type QueueSorter to arrange the patients in the
	 * linked list in order of priority. This is so we can easily determine how
	 * many priority cases we have, and who can be swapped out of bays back into
	 * triage if necessary
	 */
	QueueSorter sorter = new QueueSorter();
	DBConnection db = new DBConnection();

	/**
	 * This is our list of patients currently in bays - static so that if
	 * multiple users are working
	 */
	public static LinkedList<Patient> patientListBays = new LinkedList<Patient>();

	/**
	 * non-argument based constructor
	 */
	public BayManager() {

	}

	/**
	 * This method is used to process patients in triage - it throws an
	 * interrupted exception because there is a call to the email and sms
	 * classes
	 * 
	 * @param patient
	 * @throws InterruptedException
	 */

	public void treatPatient(Patient patient)  {

		// print lines are for testing and will be removed prior to release
		System.out
				.println("baylength in treatpatient" + patientListBays.size());
		System.out.println(patient.getPatientID());
		System.out.println(patient.getFirstName());
		System.out.println(patient.getTimeEnteredTriage());
		System.out.println(patient.getTimeEnteredBays());

		// set the time the patient entered bays to the current time. Comparing
		// this time
		// with the time they entered triage will allow us to calculate their
		// overall waiting time
		patient.setTimeEnteredBays(TimeHandler.now());

		// set the patients waiting time so that it can be sent out in the
		// patient object
		// to the database so that it can be used to calculate statistics about
		// the
		// Efficiencies within the ER
		patient.setWaitingTime();
		System.out.println(patient.getWaitingTime());
		// Again printlns for testing and will be removed
		System.out.println(patient.getFirstName() + " entered bay = "
				+ patient.getTimeEnteredBays());
		System.out.println(patient.getFirstName() + " waited "
				+ (patient.getWaitingTime() + " minutes"));

		// set the length of time the patient will be in treatment (currently
		// 10mins as per spec)
	}
	public void setTreatmentTime(Patient patient){

		// set the time the patient should be discharged, this can be
		// incremented by 5 mins
		// if the patients treatment will take longer. This can happen
		// infinitely. The time handler
		// class is used to change the string data/time var to ints so they can
		// be mathmatically
		// augmented, and then converts the result back to string.
		patient.setTimeDischarged(TimeHandler.addTreatmentTime(
				patient.getTimeEnteredBays(), patient.getPatientTreatmentTime()));

		// more print tests
		System.out.println("patient will be discharged at: "
				+ patient.getTimeDischarged());
		System.out.println("Treatment will last: "
				+ patient.getPatientTreatmentTime() + " minutes");
		
		System.out
				.println("BayLength in treatpatient" + patientListBays.size());

	}// end treatPatient

	/**
	 * this method checks through the linked list (patients in bays) and checks
	 * the patients discharge time and compares it to the time now. if they are
	 * the same, the patient is discharged (removed from the bays list), if not
	 * they remain for a further minute
	 * 
	 * @throws InterruptedException
	 */
	public void processBaysQue() throws InterruptedException {
		int time=0;
		// if bays isnt empty
		if (!patientListBays.isEmpty()) {
			// move through the patients in bay
			for (int i = 0; i < patientListBays.size(); i++) {
				// check if their discharge time is now
				
				if (patientListBays.get(i).getTimeDischarged()
						.equals(TimeHandler.now())) {
					System.out.println(patientListBays.get(i).getFirstName()
							+ " discharged");
					// if it is remove the patient from bays and send them to
					// the discharge method
					dischargePatients(patientListBays.remove(i),
							Action.DISCHARGED);
				}
			}
		}
		writeList();

	}// end processBayQueue

	/**
	 * A method that checks the bays.
	 * 
	 * @return boolean - true if there are free bays, false if all bays are
	 *         filled.
	 */
	public void checkBays() {
		/*
		 * There are 5 bays - so if the size of patientListBays is less than 5,
		 * there must be an available bay.
		 */
		boolean bayFree = false;

		if (patientListBays.size() < 5) {
			bayFree = true;
		} else {
			bayFree = false;
		}
		HospitalBackup.writeBoolean(bayFree, "checkBays");
		
	}// end checkBays

	/**
	 * This method assigns a patient to a bay.
	 * 
	 * @param p
	 * @throws InterruptedException
	 */
	public void assignBays(Patient patient) throws InterruptedException {
		patient.setTimeEnteredBays(TimeHandler.now());
		// take the patient from the parenthesis and add to bay
		patientListBays.add(patient);
		// start treating this patient
		treatPatient(patient);
		writeList();

	}// end assignBays

	/**
	 * This method is used to remove patients from the ER. A variety of things
	 * can happen to the patient at this stage. The ennum action provides a list
	 * of possible actions which must be selected
	 * 
	 * @param patient
	 * @param action
	 */
	public void dischargePatients(Patient patient, Action action) {

		// Assigning the patient to the baymanageradapter so they can be
		// processed by the main hospital system
		processedPatient = new BayManagerAdapter(patient, Action.DISCHARGED);
		writeList();
		// println for testing
		System.out.println("BayLength end discharge" + patientListBays.size()
				+ patient.getFirstName());
	}// end discharePatient

	public void writeList() {
		try {
			HospitalBackup.writeToFile(patientListBays, "bayList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}// end BayManager
