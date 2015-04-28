package sprint4Increment;
/**
 * This enum is used to set the options for the various types of automated alerts that will be sent out by our system
 * @author Shauna-Marie Grieve 40010493
 *
 */
public enum Alert {
	
/**
 * if waiting times are exceeded both oncall and management must be alerted
 */
	WAITINGTIMESEXCEEDED("Both", "Waiting times Exceeded", "**Automated Alert**\n Please do not reply to this address\nPatients waiting times have been exceeded\n Oncall have been requested", "Please report for duty\n**Automated Alert**\n Please do not reply to this txt"), 
	/**
	 * if all bays are full with emergency patients both oncall and management must be alerted
	 */
	BAYSFULL("Both","Bays Full", "**Automated Alert**\n Please do not reply to this address\nBays are full with emergency patients", "Please report for duty\n**Automated Alert**\n Please do not reply to this txt"),
	/**
	 * if triage queue is full with both oncall and management must be alerted
	 */
	TRIAGEFULL("Both","TRIAGE Full", "**Automated Alert**\n Please do not reply to this address\nTriage is full, patients are being directed to the nearest ER", "Please report for duty\n**Automated Alert**\n Please do not reply to this txt"),
	/**
	 * if oncall are busy with a treatment for more than 15mins management must be alerted
	 */
	ONCALLPREOCCUPIED("Manager","Oncall Busy","**Automated Alert**\n Please do not reply to this address\nOncall present and preocuppied", "");
	
	/*
	 * String to hold the reipient of the alert - either manager, oncall or both
	 */
	String recipient;
	/*
	 * String to hold the title of the alert sent
	 */
	String title;
	/*
	 * String to hold the body of the alert message sent to oncall
	 */
	String bodyOncall;
	/*
	 * String to hold the body of the alert message sent to manager
	 */
	String bodyManager;
	
	
	/**
	 * Constructor with args
	 * @param recipient - String
	 * @param title - String
	 * @param Manager - String
	 * @param oncall - String
	 */
	Alert(String recipient, String title, String Manager, String oncall){
		this.recipient=recipient;
		this.title=title;
		this.bodyManager=Manager;
		this.bodyOncall=oncall;
	}//end constructor
}//end enum
