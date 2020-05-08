package cst8284.asgmt4.roomScheduler;

import java.util.Comparator;

/**
 *
 * 
 * @author Lei Kong
 * @version 1.00
 * 
 *
 */
public class SortRoomBookingsByCalendar implements Comparator<RoomBooking> {
	/**
	 * make the RoomBooking comparable by comparing the start time between two
	 */
	@Override
	public int compare(RoomBooking rm1, RoomBooking rm2) {
		// TODO Auto-generated method stub
		int x = 0;
		if (rm1.getTimeBlock().getStartTime().getTimeInMillis() > rm2.getTimeBlock().getStartTime().getTimeInMillis()) {
			x = 1;
		}
		if (rm1.getTimeBlock().getStartTime().getTimeInMillis() < rm2.getTimeBlock().getStartTime().getTimeInMillis()) {
			x = -1;
		}

		return x;
	}

}
