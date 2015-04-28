package sprint4Increment;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
		Collections.sort(list);

		Iterator<Patient> it, it2;

		int num = 0;
		int num2 = 0;

		Patient temp = new Patient();

		it = list.iterator();
		it2 = list.iterator();
		boolean placed = false;
		int minutesTotal;

		if (list.size() > 1) {
			while (it.hasNext() && num < list.size()) {
				minutesTotal = TimeHandler.minutesTotal(list.get(num)
						.getWaitingTime());
				if (minutesTotal >= 25) {
					while (it2.hasNext() && !placed && num2 < list.size()) {
						if (TimeHandler.minutesTotal(list.get(num2)
								.getWaitingTime()) < minutesTotal) {
							placed = true;
						} else {
							num2++;
						}
					}

					list.add(num2, temp);

					list.removeLastOccurrence(temp);
				}
				num++;
			}
		}

		return list;

	}
}
