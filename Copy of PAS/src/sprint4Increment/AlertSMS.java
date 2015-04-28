package sprint4Increment;

import java.util.LinkedList;
import java.util.Properties;
import java.util.Date;
import javax.mail.*;
import javax.mail.internet.*;

	/**
	 * This class is generic and will send a text to anyone required via my email
	 * account using txtlocal as the communication server.
	 * 
	 * IMPORTANT ***My password and email address and txtlocal connection are
	 * private so please do not share this file.
	 * 
	 * @author Shauna-Marie Grieve 40010493
	 *
	 */
	public class AlertSMS extends Communication{
		/**
		 * This is the username of the account from which the text message will be
		 * sent
		 * 
		 * This is address of the account from which the text will be sent
		 */
		private final String USERNAME = "shaunamariegrieve@hotmail.co.uk";
		/**
		 * This is a private password used to access the above account - my actual email needs to be substituted
		 */
		private final String PASSWORD = "shauna's password";
		/**
		 * This is the host address from which the text will be sent
		 */
		private final String SMTPHOST = "smtp.live.com";
		/**
		 * This is transport object to transfer the message
		 */
		private Transport tr = null;
		/**
		 * This is the telephone number of the recipient
		 */
		private LinkedList<String>  to = new LinkedList<String>();
		/**
		 * This is the info contained within the text
		 */
		private String body;

		/**
		 * Constructor - empty - used to create text object There is no arg free
		 * constructor as this can not be used in this application
		 * 
		 * @author Shauna-Marie Grieve 40010493
		 * @throws Exception
		 *
		 */
		public AlertSMS(LinkedList<String> to, String body) throws Exception {
			setTo(to);
			setBody(body);
			sendCommunication();
		}// end SendSMS constructor

		/**
		 * This is a getter for the telephone number to which a text will be sent
		 */
		public String getTo() {
			return to.removeFirst().concat("@txtlocal.co.uk");
		}// end getTo

		/**
		 * This is a setter for the telephone numbers to which a text will be sent
		 * 
		 * @throws Exception
		 */
		public void setTo(LinkedList<String> to) throws Exception {
				this.to.addAll(to);
		}// end setTo

		/**
		 * This is a getter for the body of the text
		 */
		public String getBody() {
			return this.body;
		}// end getBody

		/**
		 * This is a setter for the body of the text
		 * 
		 * @throws Exception
		 */
		public void setBody(String body) throws Exception {
			/*
			 * The text can't be empty and it cant be longer than 100 characters Only
			 * because i have to pay for these text in real life they could be
			 * longer They could also be empty, but it would still cost so there would be
			 * no logic there!
			 */
			if ((!body.isEmpty()) && (!body.equals(null)) && (body.length() < 100)) {
				this.body = body.concat("##");
			} else {
				throw new Exception("No Message Content") {
				};
			}
		}// end setBody

		/**
		 * void method used to set up message and all its necessary variables
		 * @throws MessagingException 
		 * 
		 * @throws Exception
		 * @Override
		 */
		public void sendCommunication() throws MessagingException{

			/*
			 * create a properties object
			 */
			Properties properties = System.getProperties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			/*
			 * Get a Session object
			 */
			Session session = Session.getDefaultInstance(properties, null);

			/*
			 * Construct the message
			 */
			Message sms = new MimeMessage(session);

			/*
			 * Set message attributes
			 */
			sms.setFrom(new InternetAddress(USERNAME));
			
			/*
			 * This part of the method allows a message to be sent to one or more recipients
			 */
			while(!to.isEmpty()){
			InternetAddress[] address = { new InternetAddress(to.removeFirst().concat("@txtlocal.co.uk")) };	
			sms.setRecipients(Message.RecipientType.TO, address);
			sms.setText(body);
			sms.setSentDate(new Date());
			/*
			 * set up transport and connection
			 */
			tr = session.getTransport("smtp");
			tr.connect(SMTPHOST, USERNAME, PASSWORD);
			sms.saveChanges();

			/*
			 * Send message to recipients
			 */
			tr.sendMessage(sms, sms.getAllRecipients());
			}//end the while loop
			
			/*
			 * Importantly close resources
			 */
			tr.close();
		}// end sendCommunication
	}// end SendSMS

	


