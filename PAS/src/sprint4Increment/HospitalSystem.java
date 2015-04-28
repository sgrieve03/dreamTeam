package sprint4Increment;


/**
 * 
 * @author Shauna-Marie
 * This is a very vague copy of what the Hospital System might look like beyond
 *  the ER PAS. We will build an adapter which will enable us to deal with patients
 *  as they exit ER if they are not completely discharged.
 */
public abstract class HospitalSystem {

	//a patient object to handle the patient being passed through the system
	Patient patient;
	//a Action object to handle the action to be taken on the patient
	Action referredTo ;
	
	/**
	 * Constructor with args used to passed the patient through the system
	 * @param patient
	 * @param action
	 */
	HospitalSystem(Patient patient, Action action){
		this.patient=patient;
		referredTo=action;
		
		//call the selection action method which will process the patient
		selectAction();
		
	}//end constructor with args
	
	/**
	 * this method is used to push the patient through the system 
	 * selecting the appropriate course of action as chosen by the calling
	 * health care professional
	 */
	public void selectAction(){
		
		//Switch on the action to be taken i.e referral made by health care professional
		switch(this.referredTo){
		case DECEASED:
			isDeceased();
			break;
		case GP:
			referToGP();
			break;
		case OUTPATIENT:
			sendToOutpatients();
			break;
		case SOCIALSERVICES:
			referToSocialServices();
			break;
		case SPECIALIST:
			referToSpecialist();
			break;
		case WARD:
			sendToWard();
			break;
		case DISCHARGED:
			discharge();
		default:
			break;
		
		}//end switch
	}//end selectAction
	/**
	 * 
	 */
	public void discharge(){
		//bays.removeFirstInstanceOf(patient);
	}
	/**
	 * method to send patient to appropriate ward
	 */
	public void sendToWard(){
		//here is some code that we do not have access to
	}
	
	/**
	 * method to send patient to appropriate specialist
	 */
	public void referToSpecialist(){
		//here is some code that we do not have access to
	}
	
	/**
	 * method to send patient to social services
	 */
	public void referToSocialServices(){
		//here is some code that we do not have access to
	}
	
	/**
	 * method to send patient to outpatients
	 */
	public void sendToOutpatients(){
		//here is some code that we do not have access to
	}
	
	/**
	 * method to send patient to GP
	 */
	public void referToGP(){
		//here is some code that we do not have access to
	}
	
	/**
	 * method to send patient to mortuary/send appropriate files
	 */
	public void isDeceased(){
		//here is some code that we do not have access to
	}
}
