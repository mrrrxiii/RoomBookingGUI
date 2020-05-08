package cst8284.asgmt4.roomScheduler;

import java.io.Serializable;

/**
 *
 * 
 * @author Lei Kong
 * @version 1.00
 * 
 *
 */

public class RoomBooking implements Serializable {
	public static final long serialVersionUID = 1L;
	private ContactInfo contactInfo;
	private Activity activity;
	private TimeBlock timeBlock;

	/**
	 * construct RoomBooking with specified event information
	 * 
	 * @param timeBlock   the given TimeBlock
	 * @param contactInfo the given ContactInfo
	 * @param activity    the given Activity
	 */
	public RoomBooking(TimeBlock timeBlock, ContactInfo contactInfo, Activity activity) {
		setTimeBlock(timeBlock);
		setContactInfo(contactInfo);
		setActivity(activity);
	}

	/**
	 * get the contact information
	 * 
	 * @return contact information
	 */
	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	/**
	 * set the contact information
	 * 
	 * @param contactInfo contact information
	 *
	 */
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	 * get the activity information
	 * 
	 * @return the activity information
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * set the activity information
	 * 
	 * @param activity activity information
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * get the TimeBlock contains start and end time
	 * 
	 * @return TimeBlock contains start and end time
	 * 
	 */
	public TimeBlock getTimeBlock() {
		return timeBlock;
	}

	/**
	 * set TimeBlock contains start and end time
	 * 
	 * @param timeBlock TimeBlock contains start and end time
	 */
	public void setTimeBlock(TimeBlock timeBlock) {
		this.timeBlock = timeBlock;

	}

	/**
	 * roombing information including start and end time, event category and details
	 * and contact information
	 * 
	 * @return all room booking information
	 */
	@Override
	public String toString() {
		return "---------------\n" + getTimeBlock().toString() + "\n" + getActivity().toString() + "\n"
				+ getContactInfo().toString() + "---------------";
	}

}
