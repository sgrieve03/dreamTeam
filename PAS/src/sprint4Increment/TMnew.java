package sprint4Increment;
/**
 * 
 *//*
package sprint4Increment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import javafx.fxml.FXMLLoader;
import sprint4IncrementGUI.Main;
import sprint4IncrementGUI.ReceptionUKController;
import sprint4IncrementGUI.TriageController;

*//**
 * @author laura Triage Manager class which will implement: alertEmail,
 *         AlertSMS, UpdateTime, SearchQueue, AdjustQueue interfaces
 *//*
public class TriageManager implements IPatientCategory {
	
	private TriageController tC = new TriageController();
	
	public static HashMap<Integer, Integer> triageQueue;
	
	private Main main;
	*//**
	 * 
	 *//*
	private final int EMERGENCY = 0;
	*//**
	 * 
	 *//*
	private final int URGENT = 1;
	*//**
	 * 
	 *//*
	private final int SEMIURGENT = 2;
	*//**
	 * 
	 *//*
	private final int NONURGENT = 3;
	*//**
	 * Constant array of categories
	 *//*
	private final int[] PatientCategories = { EMERGENCY, URGENT, SEMIURGENT,
			NONURGENT };
	private TriageManager triage;
	public static LinkedList<Patient> triageList = new LinkedList<Patient>();
	private BayManager bay;
	private QueueSorter sorted = new QueueSorter();
	Random rand = new Random();
	int pickedNumber;
	private Patient patient;

	*//**
	 * to accept patient from reception
	 * 
	 * @throws InterruptedException
	 *//*
	public void patientHandler(Patient patient, BayManager bay, TriageManager triage) throws InterruptedException{
		int x = 0;
		this.bay=bay;
		this.triage=triage;
		this.patient=patient;
		
		System.out.println("Name: "+this.triage.patient.getFirstName()+" Patient ID: "+this.triage.patient.getPatientID());
		this.patient.setCategory(rand.nextInt(3));
		System.out.println("Patient category: "+this.patient.getCategory());
		this.triage.addToTriageList(this.patient);
		this.patient.setTimeEnteredTriage(TimeHandler.now());
		System.out.println("Time entered Triage: "+this.patient.getTimeEnteredTriage());	
		this.patient.setWaitingTime();
		sorted.displaySortedQueue(this.triage.triageList);
		System.out.println("Next into bays: "+this.triage.triageList.getFirst().getFirstName());
		
		this.triage.processQue();
		
		triageQueue = new HashMap<Integer, Integer>();
		
		for (Patient p : triageList) {
			triageQueue.put(p.getPatientID(), p.getPatientTreatmentTime());
		}
		
		Thread.sleep(2000);
		
	}

	*//**
	 * @return the triageList
	 *//*
	public LinkedList<Patient> getTriageList() {
		return this.triage.triageList;
	}

	*//**
	 * @param triageList
	 *            the triageList to set
	 * @throws Exception
	 *//*
	// public void setTriageList(LinkedList<Object> triageList) {
	// this.triageList = triageList;
	// }

	@Override
	public void setDateOfAccident(Calendar dateOfAccident) {
		patient.setDateOfAccident(dateOfAccident);
	}

	@Override
	public void setTimeOfAccident(Calendar timeOfAccident) {
		patient.setTimeOfAccident(timeOfAccident);

	}

	@Override
	public void setNatureOfAccident(String natureOfAccident) {
		patient.setNatureOfAccident(natureOfAccident);

	}

	@Override
	public void setSymptoms(String symptoms) {
		patient.setSymptoms(symptoms);

	}

	@Override
	public void setAdditionalNotes(String additionalNotes) {
		patient.setAdditionalNotes(additionalNotes);

	}

	@Override
	public void setPatientCategory(int patientCategory) {
		for (int P : PatientCategories) {
			if (P == patientCategory) {
				this.setPatientCategory(patientCategory);
			} else {
				this.setPatientCategory(NONURGENT);
			}
		}
	}

	public void addFromBays(Patient patient) {
		this.patient = patient;
		this.triage.triageList.addFirst(patient);
	}

	public void addToTriageList(Patient patient) {
		this.patient = patient;
		// needs validation
		// adds person to linked list
		this.triage.triageList.add(patient);
		triageList.add(patient);
	}

	public boolean checkTriageList() {
		boolean spaceAvailable;
		if (this.triageList.size() < 10) {
			spaceAvailable = true;
		} else {
			spaceAvailable = false;
		}
		return spaceAvailable;
	}

	public String nextInQueue() {

		return this.triageList.getFirst().getFirstName();
	}

	public void processQue() throws InterruptedException {
		if (this.triageList!=null){
		if (bay.checkBays() && !this.triageList.isEmpty()) {
			bay.assignBays(this.triageList.poll());
		} else {
			bay.processBaysQue();
		}
		}

	}

	public ArrayList<Integer> triageListIDArray() {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for(Patient p : triageList) {
			idList.add(p.getPatientID());
		}
		return idList;
	}
}
*/