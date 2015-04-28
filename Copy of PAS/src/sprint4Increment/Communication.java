package sprint4Increment;
import javax.mail.MessagingException;

/**
 * This is an abstract communication class which ensures that all types of communication have a sendCommuniction method.
 * It also provides an entry point into the class hierarchy of communication types for the staff Adapter.
 * @author Shauna-Marie Grieve 40010493
 *
 */
public abstract class Communication {

	/**
	 * Abstract method to be included in all inheriting classes
	 * @throws MessagingException
	 */
	public abstract void sendCommunication() throws MessagingException ;
		
}
