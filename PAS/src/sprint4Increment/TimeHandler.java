package sprint4Increment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 * @author Shauna-Marie
 * 
 *         Error (difference in time) picked up by shauna and fixed by James Carlisle
 *         
 *         Code merged by Shauna - errors with merge handled
 * 
 *         This class handles time as used within the ER PAS
 *
 */
public class TimeHandler {

	/**
	 * A final string which is the format for time
	 */
	public static final String TIME_FORMAT_NOW = "HH:mm";
	
	/**
	 * A final string which is the format the date
	 */
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";

	/**
	 * 
	 * @return String which is the formatted current time
	 */
	public static String now() {
		// create an instance of calendar
		Calendar cal = Calendar.getInstance();
		// create an instance of simpleDateFormat and pass our predefined time
		// format as a parameter
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_NOW);
		// return the correctly formatted current time
		return sdf.format(cal.getTime());
		
	}// end now
	
	public static String time(String time){
		// create an instance of simpleDateFormat and pass our predefined time
		// format as a parameter
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_NOW);
		// return the correctly formatted current time
		return sdf.format(time);
	}
	
	public static String dateNow(){
		// create an instance of calendar
				Calendar cal = Calendar.getInstance();
				// create an instance of simpleDateFormat and pass our predefined time
				// format as a parameter
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				// return the correctly formatted current time
				return sdf.format(cal.getTime());
	}
	
	public static String formatDate(String date){
		// create an instance of simpleDateFormat and pass our predefined time
		// format as a parameter
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		// return the correctly formatted current time
		return sdf.format(date);
	}

	/**
	 * This method takes time as a string, removes the hr portion and converts
	 * the minutes to an int so it can be used in calculations
	 * 
	 * @param String
	 *            time
	 * @return minutes as an int
	 */
	public static int nowMinutes(String time) {
		// var to hold the minutes portion of time
		int minutes = 0;
		// remove all but the last two digits from the time string
		time = time.substring(3);
		// change this substring to an int
		minutes = Integer.parseInt(time);
		// return the int minutes
		return minutes;
	}// end nowMinutes

	/**
	 * This method takes time as a string and removes the minutes portion. It
	 * then converts the hrs portion to an int so that calulations can be
	 * carried out on it
	 * 
	 * @param String
	 *            time
	 * @return int hours
	 */
	public static int nowHours(String time) {
		// var to hold the int hours
		int hrs = 0;
		// remove all but the first two characters of time
		time = time.substring(0, 2);
		// convert substring to int
		hrs = Integer.parseInt(time);
		// return hours as int
		return hrs;
	}// end nowHours
	
	public static int minutesTotal(String time){
		return ((nowMinutes(time))+((nowHours(time)*60)));
		
	}

	/**
	 * This method takes hours and minutes as ints and converts them to a string
	 * and correctly formats the time
	 * 
	 * @param int hrs
	 * @param int minutes
	 * @return String time
	 */
	public static String formatTime(int hrs, int minutes) {
		// var to hold time as String
		String time;
		// if the number of minutes or hours is less than 10
		if (minutes < 10 && hrs < 10) {
			// add a zero before the minutes and hours
			time = ("0" + hrs + ":0" + minutes);
			// if the number of hours is less than 10 but the number of minutes
			// is greater than or equal to 10
		} else if (hrs < 10 && minutes >= 10) {
			// add a zero before the hours only
			time = ("0" + hrs + ":" + minutes);
			// if the number of hrs is greater than or equal to 10 and the
			// number of minutes is less than 10
		} else if (hrs >= 10 && minutes < 10) {
			// add a zero before the minutes only
			time = (hrs + ":0" + minutes);
			// otherwise
		} else {
			// do not add a zero before either hours or minutes
			time = (hrs + ":" + minutes);
		}
		// return String time
		return time;

	}// end formatTime

	/**
	 * This method takes two times as string and calculates the difference in
	 * time between them
	 * 
	 * @param String
	 *            time1
	 * @param String
	 *            time2
	 * @return String difference in time (dif)
	 */
	public static String differenceInTime(String time1, String time2) {
		// A string to hold the difference in time
		String dif;
		// An int to hold the hour of the first time put into this method
		int hrsTime1 = nowHours(time1);
		// An int to hold the minutes of the first time put into this method
		int minutesTime1 = nowMinutes(time1);
		// An int to hold the hour of the second time put into this method
		int hrsTime2 = nowHours(time2);
		// An int to hold the minutes of the second time put into this method
		int minutesTime2 = nowMinutes(time2);

		// An int to hold the difference in the hours between the two times
		int difHrs = hrsTime2 - hrsTime1;

		// If the difference in hours is negative, the first time is before
		// midnight,
		// and the second time is after midnight. Therefore add 24 hours.
		// Example: first time is 23:00, second time is 01:00.
		// Hence the difference in hours is 1 - 23 = -22.
		// The difference in hours should be 2, equivalent to -22 + 24.
		if (difHrs < 0) {
			difHrs = difHrs + 24;
		}

		// An int to hold the difference in minutes between the two times
		int difMinutes = minutesTime2 - minutesTime1;

		// If the difference in minutes is negative, then the two times do not
		// belong to the same hour. Therefore add 60 minutes. An hour must
		// also be deducted from difHrs, as there is not necessarily a full hour
		// between the two times.
		// Example: first time is 22:50, second time is 23:10.
		// Hence the difference in minutes is 10 - 50 = -40.
		// The difference in minutes should be 20, equivalent to -40 + 60.
		// difHours must also be deducted by 1, as there is not a full hour's
		// difference.
		if (difMinutes < 0) {
			difHrs--;
			difMinutes = difMinutes + 60;
		}

		// Returning the string
		return formatTime(difHrs,difMinutes );
	}

	/**
	 * This method is used to add minutes of treatment to the patients expected
	 * discharge time which will enable them to be treated and discharged
	 * 
	 * @param String time
	 * @param int minutesForTreatment
	 * @return time as a string - this will be used as the patient expected
	 *         discharge time. It can be changed at anytime by recalling this
	 *         method and adding further minutes.
	 */
	public static String addTreatmentTime(String time, int minutesForTreatment) {
		//vars to hold the current time as ints so they can be used in calculations
		int hrs = nowHours(time);
		int minutes = nowMinutes(time);

		//if the minutes for treatment is greater than 60
		if (minutesForTreatment > 60) {
			
			//this is to hold the number of minutes and hours to be added to time
			int timeFactor = 0;

			//the hours to be added is calculated by dividing the minutes of treatment by 60
			timeFactor = minutesForTreatment / 60;
			//then add the result to the number if hrs
			hrs += timeFactor;
			
			//the number of minutes to be added is calculated by finding the modulus of the minutes divided by 60
			timeFactor = minutesForTreatment % 60;
			//the minutes for treatment becomes this timefactor
			minutesForTreatment = timeFactor;
		}
		
		//add the minutes for treatment to minutes
		minutes += minutesForTreatment;
		

		//if the number of minutes is greater than 59
		if (minutes > 59) {
			//add 1 to hours
			hrs += 1;
			//remove 60 from minutes
			minutes -= 60;
		}

		//if hours is greater than 23 (i.e next day)
		if (hrs > 23) {
			//remove 24 from hrs - this represents passing midnight
			hrs -= 24;
		}

		//reformat time to be a string
		time = formatTime(hrs, minutes);

		//return time as string
		return time;
	} // end treatment time

}
