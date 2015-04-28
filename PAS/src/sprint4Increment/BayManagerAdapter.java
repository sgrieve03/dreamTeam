package sprint4Increment;


/**
 * 
 * @author Shauna-Marie
 * This class is used to join the er system to the hospital system
 *
 */
public class BayManagerAdapter extends HospitalSystem {

	
	/**
	 * This is the adapter contructor which passes the patient object
	 * and the actions to be taken on the patient to the main hospital system.
	 * @param patient
	 * @param action
	 */
	BayManagerAdapter(Patient patient, Action action) {
		//call to Hospital System
		super(patient, action);		
	}//end constructor with args
}//end class
