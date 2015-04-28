/**
 * 
 */
package sprint4Increment;

import java.util.Calendar;
import java.util.Date;

/**
 * @author laura
 * This interface will be implemented by triage and possibly person
 *
 */
public interface IPatientCategory {
	
	public void setDateOfAccident(Date dateOfAccident);
	
	public void setTimeOfAccident(Date timeOfAccident);
	
	public void setNatureOfAccident(String natureOfAccident);
	
	public void setSymptoms(String symptoms);
	
	public void setAdditionalNotes(String additionalNotes);
	
	public void setPatientCategory(Patient patient, int patientCategory);

}
