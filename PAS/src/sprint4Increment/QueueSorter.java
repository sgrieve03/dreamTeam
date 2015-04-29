package sprint4Increment;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class QueueSorter implements Serializable {
	public QueueSorter() {
	}

	// A method that sorts and displays the queue,
	// first by triage and then by time
	public LinkedList<Patient> displaySortedQueue(LinkedList<Patient> list) {
		// sort the list by triage
		// Collections lets you sort objects
		// It will check the "compareTo" method in Patient for this.
		// Here it will sort the list in ascending order of triage
		System.out.println("Justbeforesort"+list.size());
		Collections.sort(list);
System.out.println("just after sort"+list.size());
		// iterators for running through the linked lists
		Iterator<Patient> it, it2;

		// two ints
		int num = 0;
		int num2 = 0;

		// create a temporary patient to store the patient temporarily
		Patient temp = new Patient();

		// instantiating the iterators
		it = list.iterator();
		it2 = list.iterator();

		// a variable to store whether or not the person has been placed in the
		// list after being stored in temp
		boolean placed = false;
		// variable to store the minutes the current patient has been waiting
		int minutesTotal;

		// if there is more than one person in the list
		if (list.size() > 1) 
		{

			// while there is a next person in the list and num is less than the
			// size of the list
			while (it.hasNext() && num <list.size()) 
			{
				// calculate the minutes the current patient has been waiting
				minutesTotal = TimeHandler.minutesTotal(list.get(num)
						.getWaitingTime());

				// if this time is greater than 25
				if (minutesTotal >= 25) 
				{
					// store the current patients in temp
					temp = list.get(num);

					// go to the start of the list and iterate to the end
					while (it2.hasNext() && (!placed) && num2 < list.size()) 
					{
						// if the time of the person in the current position is
						// less than the temp waiting time
						if (TimeHandler.minutesTotal(list.get(num2).getWaitingTime()) < minutesTotal) 
						{
							placed = true;
						} 
						else
						{
						num2++;
						}
					} 
					
					// add the temporary person to the list in the current
					// position
					list.add(num2, temp);
					// remove the last occurrance of this person from the list as it
					// will be currently duplicated
					 list.removeLastOccurrence(temp);
				}
				
			num++;
			
			} // end of while
		}

		return list;
	}
}
