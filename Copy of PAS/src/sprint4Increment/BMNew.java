package sprint4Increment;
/*package sprint4Increment;

import java.util.Iterator;
import java.util.LinkedList;

*//**
 * 
 * @author Shauna-Marie Grieve
 *
 *//*
public class BMNew {

	*//**
	 * this adapter links the bays to the larger hospital system which deals
	 * with treating patients after they are finished in the ER department. They
	 * can be discharged, refered, admitted, or be pronounced dead in ER but the
	 * er pas does not deal with these actions
	 * 
	 *//*
	BayManagerAdapter processedPatient;

	QueueSorter sorter = new QueueSorter();
	DBConnection db = new DBConnection();

	// list to hold patients in bays
	LinkedList<Patient> patientListBays = new LinkedList<Patient>();
	Patient patient;

	*//**
	 * non-argument based constructor
	 *//*
	public BayManager() {

	}

	public void treatPatient(Patient patient) throws InterruptedException {
		this.patient = patient;
		
		System.out.println("baylength in treatpatient"+patientListBays.size());
		
		// System.out.println(this.patient);
		
		System.out.println(this.patient.getFirstName());
		
		this.patient.setTimeEnteredBays(TimeHandler.now());
		
		this.patient.setWaitingTime();
		
		System.out
				.println(this.patient.getFirstName()+" entered bay = " + patient.getTimeEnteredBays());
		System.out.println(this.patient.getFirstName() + " waited "
				+ (this.patient.getWaitingTime() + " minutes"));
		this.patient.setTreatmentTime();
		
		
		this.patient.setTimeDischarged(TimeHandler.addTreatmentTime(
				patient.getTimeEnteredBays(), this.patient.getPatientTreatmentTime()));
		System.out.println("patient will be discharged at: " + this.patient.getTimeDischarged());
		System.out.println("Treatment will last: "
				+ this.patient.getPatientTreatmentTime() + " minutes");
		Thread.sleep(2000);
		System.out.println("BayLength in treatpatient"+patientListBays.size());
		

	}

	public void processBaysQue() {
	
		
		if(!this.patientListBays.isEmpty()){
		for(int i = 0; i<patientListBays.size(); i++) {
			if (patientListBays.get(i).getTimeDischarged()
					.equals(TimeHandler.now())) {
				this.dischargePatients(Action.DISCHARGED);
				System.out.println(patientListBays.getFirst().getFirstName()
						+ " discharged");
			}
		}
		}

	}

	*//**
	 * getter for patientListBays
	 * 
	 * @return list of patients
	 *//*
	public LinkedList<Patient> getPatientListBays() {
		return this.patientListBays;
	}// end getPatients

	*//**
	 * A method that checks the bays.
	 * 
	 * @return boolean - true if there are free bays, false if all bays are
	 *         filled.
	 *//*
	public boolean checkBays() {
		
		 * There are 5 bays - so if the size of patientListBays is less than 5,
		 * there must be an available bay.
		 
		if (this.patientListBays.size() < 5) {
			return true;
		} else {
			return false;
		}
	}// end checkBays

	*//**
	 * This method assigns a patient to a bay.
	 * 
	 * @param p
	 * @throws InterruptedException
	 *//*
	public void assignBays(Patient patient) throws InterruptedException {
	
		this.patient = patient;
		patientListBays.add(this.patient);
		this.treatPatient(this.patient);
		
	}// end assignBays

	public void dischargePatients(Action action) {
		// db.insertPatientDB(patient);
		
		
		processedPatient = new BayManagerAdapter(patientListBays.getFirst() ,Action.DISCHARGED);
		patientListBays.remove(patientListBays.getFirst());
		
		
		System.out.println("BayLength end discharge"+patientListBays.size());
	}

	*//**
	 * This method checks the bays for non-emergency patients, removes them from
	 * the bays, and returns them back to the queue.
	 * 
	 * @return LinkedList<Patient>, a list containing the removed patients.
	 * @throws InterruptedException
	 *//*
	public void removeNonEmergencyPatients(TriageManager triage,
			Patient emergencyPatient) throws InterruptedException {
		sorter.displaySortedQueue(patientListBays);
		if (patientListBays.getLast().getCategory() > 0) {
			triage.addFromBays(patientListBays.getLast());
			assignBays(emergencyPatient);
		}

	}

}
*/