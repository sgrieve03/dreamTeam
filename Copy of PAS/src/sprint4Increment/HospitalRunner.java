package sprint4Increment;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class HospitalRunner implements Serializable {

	static Reception reception = new Reception();
	static HospitalRunner runner = new HospitalRunner();

	public static void main(String[] args) throws Exception {
		Boolean saved = true;
		if (saved) {
			runner = (HospitalRunner) HospitalBackup.readFromFile("runner");
			reception = (Reception) HospitalBackup.readFromFile("reception");
		}
		
	}// end main
}// end class

/*
 * import java.io.IOException; import java.util.LinkedList;
 * 
 * import javax.swing.text.html.HTMLDocument.Iterator;
 * 
 * public class HospitalRunner implements Runnable {
 * 
 * BayManager bay = new BayManager(); TriageManager triage = new
 * TriageManager(); Reception reception; DBConnection database = new
 * DBConnection(); LinkedList<Patient> patients = new LinkedList<Patient>();
 * String[] nhsNumbers = { "100400281", "284433382", "784082739", "294738739",
 * "514452153", "597587498", "626748389", "713673701", "713673729", "729938482",
 * "734683675", "776546567", "776725372", "783673429" };
 * 
 * public HospitalRunner() throws IOException, InterruptedException { }
 * 
 * public void run() {
 * 
 * try { Thread.sleep(20000); } catch (InterruptedException e1) { // TODO
 * Auto-generated catch block e1.printStackTrace(); }
 * 
 * for (int loop = 0; loop < 14; loop++) {
 * patients.add(database.searchPatientByNHSNumber(nhsNumbers[loop])); }
 * database.closeConnection();
 * 
 * try { reception = new Reception(triage, patients, bay);
 * reception.sendToTriage(); } catch (InterruptedException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); }
 * 
 * }
 * 
 * public void addToList(Patient patient) { patients.add(patient); try {
 * reception = new Reception(triage, patients, bay); reception.sendToTriage(); }
 * catch (InterruptedException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } }
 * 
 * }
 */