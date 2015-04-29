/**
 * 
 */
package sprint4Increment;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author laura Triage Manager class which will implement: alertEmail,
 *         AlertSMS, UpdateTime, SearchQueue, AdjustQueue interfaces
 * 
 * @author Shauna-Marie Grieve 40010493 this class is used in triage to assess
 *         the patients initial condition assign them a catagory and hold them
 *         in a queue waiting to go into bays for treatment
 */
public class TriageManager implements Serializable {
	
	public static HashMap<Integer, Integer> triageQueue;
	
	/**
	 * this final var is used to store the emergency catagory i.e 0
	 */
	private final int EMERGENCY = 0;
	/**
	 * this var is used to store the urgent but non emergency catagory i.e 1
	 */
	private final int URGENT = 1;
	/**
	 * this var is use to store the semi-urgent catagory i.e 2
	 */
	private final int SEMIURGENT = 2;
	/**
	 * this is the non-urgent var i.e 3
	 */
	private final int NONURGENT = 3;
	/**
	 * Constant array of categories
	 */
	private final int[] PatientCategories = { EMERGENCY, URGENT, SEMIURGENT,
			NONURGENT };
	/**
	 * instantiate the bayManager class
	 */
	public static BayManager bayManager = new BayManager();
	/**
	 * instantiate the triage class
	 */
	public static TriageManager triageManager = new TriageManager();
	/**
	 * store the number of time the triage list maximum capacity is breached
	 */
	int breach = 0;
	/**
	 * This string array is used to keep track of the time oncall were called,
	 * the time they arrived and the time they left
	 */
	private String[] onCallTime = new String[3];
	
	/**
	 * list of patients in triage
	 */
	public static LinkedList<Patient> triageList = new LinkedList<Patient>();

	/**
	 * Used to sort the queue in order of priority and also by time if the
	 * patienst have waited over 25 mins, the gain highest priority
	 */
	private QueueSorter sorted = new QueueSorter();
	/**
	 * a random number generator
	 */
	Random rand = new Random();
	/**
	 * object of type patient used to store the current patient coming into
	 * triage
	 */
	private Patient patient=new Patient();
	/**
	 * used to determine is oncall have been called
	 */
	boolean onCallCalled = false;
	/**
	 * used to determine if oncall are in
	 */
	boolean onCallIn = false;
	/**
	 * used to determine if oncall are busy treating patients
	 */
	boolean onCallBusy = false;
	/**
	 * used to store the current patient that the oncall team are treating
	 */
	Patient onCallPatient= new Patient();
	/**
	 * bayManager adapter object used to discharge patients via oncall insitue
	 * in bays
	 */
	BayManagerAdapter bayAdapt;

	/**
	 * used to create alert emails and sms's and send them to the appropriate
	 * staff
	 */
	CommunicationAdapter com;
	
	public boolean triageAvailable=true;
	
	public TriageManager(){};
	
	
	
	/**
	 * this method returns true if the triage list can accept more patients this
	 * method return false if it cann ot accept further patients
	 * 
	 * @return
	 */
	public boolean checkTriageList() {
		// used to track if sapces are avaliable
		readList();
		boolean spaceAvailable;
		// if oncall are busy they are working with one patient insitue
		// this results in only 9 spaces being available in the queue
		if (onCallBusy) {
			if (triageList.size() < 9) {
				spaceAvailable = true;
			} else {
				spaceAvailable = false;
			}
			// if oncall are not busy this means there will be space for 10
			// patients in the queue
		} else {
			if (triageList.size() < 10) {
				spaceAvailable = true;
			} else {
				spaceAvailable = false;
			}
		}
		return spaceAvailable;
	}// end check triageList

	
	/**
	 * This method is used to
	 * 
	 * @param patient
	 * @param bay
	 * @param triage
	 * @throws Exception
	 */
	public void patientHandler() throws Exception {
		
		
		patient=(Patient)HospitalBackup.readFromFile("patientReception");
		// set the patients waiting time to now
		patient.setTimeEnteredTriage(TimeHandler.now());
		System.out.println("patientReception in patient handler"+patient.getFirstName());	
		for (int i=0; i<triageList.size(); i++){
			System.out.println("Setting waiting times");
			triageList.get(i).setWaitingTime();
		}
		System.out.println();
		bayManager.checkBays();
		boolean bayFree=HospitalBackup.readBoolean("bayFree");
		if(bayFree){
			try {
				System.out.println("Is there space in bays?");
				patient.setWaitingTime();
				bayManager.assignBays(patient);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if 
		// check if patient is emergency, if so and there is a non emergency in
		// bays swap them round
		
		((!bayFree) && (swappable(patient))
				&& (patient.getCategory() == EMERGENCY)) {
			System.out.println("Is the patient swappable?");
			// do the swap
			onCallIn = false;
			onCallCalled = false;
			patient.setTimeEnteredBays(TimeHandler.now());
			patient.setWaitingTime();
			swap(BayManager.patientListBays.removeLast(), patient);
			
		}

		else{
			// adds person to linked list
			patient.setWaitingTime();
			triageManager.placeInQueue(patient);
			System.out.println("Pateint in queue" +patient.getFirstName());
			
			
		}
		// IF ON CALL ARE CALLED AND THEY HAVE ARRIVED SET ONCALLIN TO TRUE
		if ((onCallCalled) && (getOnCallTime(1).equals(TimeHandler.now()))) {
			onCallIn = true;
			System.out.println("Oncall called in");
		}
		// IF ON CALL ARE IN AND THEY ARE NOT BUSY
		if (onCallIn && !onCallBusy) {
			// set onCall to true
			onCallBusy = true;
			System.out.println("Oncall busy");
			onCallPatient = triageList.removeFirst();
			// send the first person in the list to oncall
			OnCallTreatPatient();
			// else if oncall are busy
		} else if (onCallBusy && triageList.getFirst().getCategory() == EMERGENCY) {
			
			System.out.println("send alert");
			// send an alert to the manager to let them know the oncall team can
			// not treat an emergency patient

			// com = new CommunicationAdapter(Alert.ONCALLPREOCCUPIED);

		}
		if (onCallBusy) {
			triageManager.dischargeViaOncall();
			System.out.println("Patient discharged via oncall");
		}

		
		
		// triage queue report
		triageQueue = new HashMap<Integer, Integer>();

		for (Patient p : triageList) {
			triageQueue.put(p.getPatientID(), p.getPatientTreatmentTime());
		}
		writeList();
		
	

	}
	
	
	
	
	
	
	
	
	

	

	/**
	 * gettter for oncalltime 0 - the time they were called
	 * 
	 * @param index
	 * @return
	 */
	public String getOnCallTime(int index) {
		return this.onCallTime[0];
	}

	/**
	 * Oncall treating patients insitu
	 */
	public void OnCallTreatPatient() {

		onCallIn = true;
		onCallPatient.setTimeEnteredBays(TimeHandler.now());
		onCallPatient.setTimeDischarged(TimeHandler.addTreatmentTime(
				TimeHandler.now(), 10));
		System.out.println("Oncall patient will be discharged at"+onCallPatient.getTimeDischarged());
	}

	/**
	 * Oncall discharge Patients in list
	 */
	public void dischargeViaOncall() {

		// if the time the patient is to be discharged equals now
		if (TimeHandler.now().equals(onCallPatient.getTimeDischarged())) {
			// use the bayAdapt object to discharge the patient
			bayAdapt = new BayManagerAdapter(onCallPatient, Action.DISCHARGED);
			System.out.println("Patient discharged");
			// set oncall busy to false as they are now free to treat another
			// patient
			onCallBusy = false;
		}
	}

	/**
	 * Place patient in triage queue
	 */
	public void placeInQueue(Patient patient) {
		
		// add this patient to this list
		triageList.add(patient);
		
		// sort the queue on catagory first and if the waiting time is greater
		// than 25mins bump to from of queue
		triageList=sorted.displaySortedQueue(triageList);
		writeList();
		

	}




	/**
	 * swap non-emergency patient out of bays and put emergency into bays
	 * 
	 * @param Patient
	 *            non-emergency coming from bays
	 * @param Patient
	 *            emergency - coming from reception
	 * @throws InterruptedException
	 */

	public void swap(Patient nonEmergency, Patient emergency) throws InterruptedException {
		// add the non-emergency patient to triage from bays
		emergency.setWaitingTime();
		triageManager.addFromBays(nonEmergency);
		// add the emergency patient to bays from triage
		bayManager.assignBays(emergency);
		writeList();
		bayManager.writeList();
	}// end swap

	/**
	 * set the time the oncall started working to now
	 */
	public void setOnCallTimeToStartWork() {
		onCallTime[2] = TimeHandler.now();
	}// end set on call time to start work

	/**
	 * Set the time the oncall are called to now
	 * 
	 * @param index
	 * @param time
	 */
	public void setOncallTimeCalled(int index, String time) {
		// set the time on call called to now
		onCallTime[index] = time;
		// set the time the oncall will start working to a random number between
		// 1 and 15 mins
		// in the future
		onCallTime[1] = TimeHandler.addTreatmentTime(onCallTime[0],
				rand.nextInt(15) + 1);
	}

	/**
	 * check if non-emergencies are in bays and if current patient is emergency
	 * so they can be swapped
	 * 
	 * @param Patient
	 * @throws Exception
	 */
	public boolean swappable(Patient patient) throws Exception {
		boolean swap = false;
		// if there is no space in bays
		// sort bays in order ascending from high priorty to low
		BayManager.patientListBays=sorted.displaySortedQueue(BayManager.patientListBays);
		// if bays are full and the lowest priority patient is not high priority
		// and this patient is high priority
		if (BayManager.patientListBays.getLast().getCategory() > EMERGENCY) {
			// they can be swapped
			swap = true;
			onCallCalled = false;
		} else {
			// otherwise bays is full of emergencies and Oncall and management
			// should be alerted
			alertBaysFullOfEmergencies();
			// set the time on call where called to now
			setOncallTimeCalled(0, TimeHandler.now());
		}
		// return true or false depending on if swappable or no
		return swap;
	}

	/**
	 * If there are no spaces left in bays for an emergency patient alert onCall
	 * 
	 * @throws Exception
	 */
	public void alertBaysFullOfEmergencies() throws Exception {
		// instantiate the communication adapter object, and pass in the alert
		// that is required
		// com = new CommunicationAdapter(Alert.BAYSFULL);
		// set oncallcalled to true
		onCallCalled = true;
	}
	/**
	 * This method is used to add a patient to the triage list when they come
	 * from bays
	 * 
	 * @param patient
	 */
	public void addFromBays(Patient patient) {
		// get the triage list and add the patient
		triageList.addLast(patient);
		// for all the patients in the list update their waiting times
		
		for (int i=0; i<triageList.size(); i++){
			triageList.get(i).setWaitingTime();
		}
	writeList();
	}



	/**
	 * This method simple returns the name of the next patient waiting to go
	 * into bays
	 * 
	 * @return
	 */
	public String nextInQueue() {
		// sort the queue in order of priority
		triageList=sorted.displaySortedQueue(triageList);
		// return the name of the first patient in the queue
		if(triageList.size()>0){
		return (triageList.getFirst().getFirstName()+" "+triageList.getFirst().getLastName()) ;
		}
		else return "No patients waiting";
	}// end of nextInQueue

	/**
	 * This method is used to move patients from triage to bays when it is their
	 * turn
	 * 
	 * @throws InterruptedException
	 */
	public void processQue() throws InterruptedException {

		boolean bayFree=false;
		try {
			bayFree = (boolean)HospitalBackup.readBoolean("bayFree");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// if there is space in bays and the triage list is not empty
		if (bayFree && !triageList.isEmpty()) {

			// assign the first person from triage to bays, removing them from
			// triage
			bayManager.assignBays(triageList.poll());
			// otherwise process the patients current in bays
			writeList();
		} else {
			bayManager.processBaysQue();

		}

	}
	
	public void readList(){
		try {
			triageList=(LinkedList<Patient>)HospitalBackup.readFromFile("triageList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeList(){
		try {
			HospitalBackup.writeToFile(triageList, "triageList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
