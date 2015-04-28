package sprint4Increment;

import java.util.Calendar;
import java.util.LinkedList;

public class StaffManagementSystem {
	/**
	 * if we were to fully implement this class these would be objects
	 * and would all have their own classes and methods etc...
	 */
	LinkedList<String> oncall = new LinkedList<String>();
	LinkedList<String> managerAddress = new LinkedList<String>();
	LinkedList<String>managerNumber= new LinkedList<String>();
	
	

	
	/**
	 * Constructor without args
	 *
	 * This constructor takes the loginID and password of a member of staff
	 * and links the staff member to the staff managment system, allowing them
	 * to update the PAS
	 */
	StaffManagementSystem() {

		setOncall();
		setManagerNumber();
		setManagerAddress();
	}

	public LinkedList<String> getOncall() {
		return oncall;
	}

	public void setOncall() {
		 // oncall.add("7515489280");
		//oncall.add("7511404512");
		//oncall.add("7544110910");
		//oncall.add("7450225287");
		
	}

	public LinkedList<String> getManagerAddress() {
		return managerAddress;
	}

	public void setManagerAddress() {
		managerAddress.add("sgrieve03@qub.ac.uk");
		//managerAddress.add("Aiden.mcgowan@qub.ac.uk");
	}
	
	public LinkedList<String> getManagerNumber() {
		return managerNumber;
	}

	public void setManagerNumber() {
		//managerNumber.add("7515489280");
	}



	
	
	//lots of other methods
	
	
}

