package sprint4IncrementGUI;

import java.util.LinkedList;

import sprint4Increment.BayManager;
import sprint4Increment.DBConnection;
import sprint4Increment.HospitalRunner;
import sprint4Increment.Patient;
import sprint4Increment.Reception;
import sprint4Increment.TriageManager;

public class InitialisePAS implements Runnable {

	private HospitalRunner runner;
	private DBConnection database = new DBConnection();
	private LinkedList<Patient> patients = new LinkedList<Patient>();
	private String[] nhsNumbers = { "100400281", "284433382", "784082739",
			"294738739", "514452153", "597587498", "626748389", "713673701",
			"713673729", "729938482", "734683675", "776546567", "776725372",
			"783673429" };

	public InitialisePAS() {

	}

	@Override
	public void run() {

		
			
	}

}
