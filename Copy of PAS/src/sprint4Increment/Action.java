package sprint4Increment;
/**
 * 
 * @author Shauna-Marie Grieve 40010493
 * This enum is used to determine what further actions will be taken on a 
 * patient when they are finished in ER but not yet fully discharged from the hospital
 */
public enum Action {
	
	 //indicating the patient will be sent to a ward	
	 WARD, 
	 
	 //indicating the patient will be sent to a specialist
	 SPECIALIST, 
	 
	 //indicating the patient will be referred to social services
	 SOCIALSERVICES, 
	 
	 //indicating the patient will be sent to out patients
	 OUTPATIENT,
	 
	 //indicating the patient will be sent to GP
	 GP, 
	 
	 //Indicating the patient is deceased and will be sent to mortgue
	 DECEASED,
	 
	//indicating the patient is discharged
	 DISCHARGED
}
