package sprint4Increment;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum PatientIDGenerator {
	
	INSTANCE;
	
	private Date curDate = new Date();
	private SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
	String DateToStr = format.format(curDate);
	private int count = Integer.valueOf(DateToStr)*1000;
	
	
	public synchronized int getNextID() {
		return count++;
	}
}
