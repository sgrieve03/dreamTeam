package sprint4Increment;

/**
 * This class acts as an adapter between the external staff management system
 * and the erPAS
 * 
 * @author Shauna-Marie
 *
 */
public class StaffAdapter extends StaffManagementSystem {

	/**
	 * create a communication object to create and send various communication
	 * alerts
	 */
	Communication com;

	/**
	 * Constructor with arguments
	 * 
	 * @param alert
	 *            - Alert
	 * @throws Exception
	 */
	StaffAdapter(Alert alert) throws Exception {

		// if the alert is not null send the correct email/sms
		if (!alert.equals(null)) {

			/*
			 * if the alert must be sent to both manager and oncall- send
			 * management an email and sms, send oncall an sms
			 */
			if (alert.recipient.equals("Both")) {
				com = new AlertEmail(getManagerAddress(), alert.title, alert.bodyManager);
				com = new AlertSMS(getManagerNumber(), alert.bodyManager);
				com = new AlertSMS(getOncall(), alert.bodyOncall);
				/*
				 * if the alert is to be sent to the manager - send an email and
				 * sms
				 */
			} else if (alert.recipient.equals("Manager")) {
				com = new AlertEmail(getManagerAddress(), alert.title, alert.bodyManager);
				com = new AlertSMS(getManagerNumber(), alert.bodyManager);
				/*
				 * if the alert is for oncall - send an sms - this isnt an
				 * option in our system currently but is included in case needs
				 * change in the future
				 */
			} else if (alert.recipient.equals("Oncall")) {
				com = new AlertSMS(getOncall(), alert.bodyOncall);
			}
			//if no alert is provided throw an exception
		} else {
			throw new Exception("No Alert Specified");
		}

	}

}