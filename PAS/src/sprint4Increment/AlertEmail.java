package sprint4Increment;
import java.util.LinkedList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class is generic and enables anyone within the application to send
 * emails To staff registered within the hospital database. It can also be used
 * to send automated email alerts
 * 
 * @author Shauna-Marie Grieve 40010493
 *
 */
public class AlertEmail extends Communication{

	/**
	 * final String to repesent the username of the admin account
	 */
	private final String USERNAME = "cafejava@hotmail.co.uk";
	/**
	 * final String for the admin password - my actual password needs to be substituted
	 */
	private final String PASSWORD = "shauna's password";
	/**
	 * final String for the address of the admin account
	 */
	private final String FROM = "cafejava@hotmail.co.uk";
	/**
	 * linkedList of email address to which emails will be sent
	 */
	private LinkedList<String> addresses = new LinkedList<String>();
	/**
	 * String representing email subject line must be less than 14 characters
	 */
	private String subject;
	/**
	 * String representing email must be less than 300 characters, not null and
	 * not empty
	 */
	private String body;

	/**
	 * Constructor with args
	 * 
	 * @param addresses linkedList
	 * @param Subject String - not null, not empty, <14chars
	 * @param body String - not null, not empty, <300chars
	 * @throws Exception
	 */
	public AlertEmail(LinkedList<String> addresses, String subject, String body ) throws Exception {

		setAddresses(addresses);
		setSubject(subject);
		setBody(body);
		sendCommunication();
	}// end sendEmailConstructor

	/**
	 * method used to create an email to send
	 * 
	 * @throws MessagingException
	 */
	public void createMail() throws MessagingException {

		/*
		 * Set up properties and enable the port
		 */
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.smtp.port", "25");

		// create session & authenticate username and password
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});// end create session

		// Create a new message object and pass the session as a param
		Message message = new MimeMessage(session);
		// Set up where the message will be sent from
		message.setFrom(new InternetAddress(FROM));

		/*
		 * Set up the recipients, this could be one or many - hence using the
		 * linked list
		 */
		while (!addresses.isEmpty()) {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses.removeFirst()));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
		}// end while
	}// end createMail

	/**
	 * getter for the addresses linked list
	 * 
	 * @return
	 */
	public LinkedList<String> getAddresses() {
		return addresses;
	}//end of getAddress

	/**
	 * setter for the addresses linkedList
	 * 
	 * @param addresses
	 * @throws Exception
	 */
	public void setAddresses(LinkedList<String> addresses) throws Exception {
		if (!addresses.isEmpty() && !addresses.equals(null)) {
			this.addresses = addresses;
		} else {
			throw new Exception("Addresses not found");
		}
	}//end setAddress

	/**
	 * getter for the body of the email
	 * 
	 * @return
	 */
	public String getBody() {
		return body;
	}//end getBody

	/**
	 * setter for the body of the email must not be empty, null or greater than
	 * 300 characters
	 * 
	 * @param body
	 * @throws Exception
	 */
	public void setBody(String body) throws Exception {
		if (!body.isEmpty() && !body.equals(null) && body.length() < 300) {
			this.body = body;
		} else {
			throw new Exception("Body required");
		}
	}//end setBody

	/**
	 * getter for email subject
	 * 
	 * @return
	 */
	public String getSubject() {
		return subject;
	}//end getSubject

	/**
	 * setter for subject must not be null, empty or greater than 14 characters
	 * 
	 * @param subject
	 * @throws Exception
	 */
	public void setSubject(String subject) throws Exception {
		if (subject.length() <= 30 && !subject.isEmpty() && !subject.equals(null)) {
			this.subject = subject;
		} else
			throw new Exception("Subject between 1 and 14 characters");
	}// end setSubject

	@Override
	public void sendCommunication() throws MessagingException {
		createMail();
		
	}

}

