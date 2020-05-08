package cst8284.asgmt4.roomScheduler;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * 
 * @author Lei Kong
 * @version 1.00
 * 
 *
 */
public class TimeBlock implements Serializable {
	public static final long serialVersionUID = 1L;
	private Calendar startTime;
	private Calendar endTime;

	/**
	 * construct TimeBlock with default start and end time
	 * 
	 * @throws Exception
	 */
	public TimeBlock() {
		this(new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 8).build(),
				new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 11).build());
	}

	/**
	 * construct TimeBlock with given start and end time
	 * 
	 * @param startTime given start time
	 * @param endTime   given end time
	 * @throws Exception
	 */
	public TimeBlock(Calendar startTime, Calendar endTime) {
		if (InputValidation.checkStartEndPrecede(startTime, endTime)
				& InputValidation.checkStartEndEqual(startTime, endTime)) {
			setStartTime(startTime);
			setEndTime(endTime);
		}
	}

	/**
	 * get the start time
	 * 
	 * @return Calendar the start time
	 */
	public Calendar getStartTime() {
		return startTime;
	}

	/**
	 * set the start time by given time
	 * 
	 * @param startTime given time
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * get the end time
	 * 
	 * @return Calendar the end time
	 */
	public Calendar getEndTime() {
		return endTime;
	}

	/**
	 * set the end time by give time
	 * 
	 * @param endTime given time
	 */
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	/**
	 * calculate the duration between start and end time
	 * 
	 * @return the duration between start and end time
	 */
	public int duration() {
		return getEndTime().get(Calendar.HOUR_OF_DAY) - getStartTime().get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * check if the two TimeBlock overlapped in term of start and end time
	 * 
	 * @param timeblock given TimeBlock
	 * @return true if two TimeBlock is overlapped, false otherwise
	 */
	public boolean overLaps(TimeBlock timeblock) {
		return !(this.getStartTime().getTimeInMillis() >= timeblock.getEndTime().getTimeInMillis()
				|| this.getEndTime().getTimeInMillis() <= timeblock.getStartTime().getTimeInMillis());

	}

	/**
	 * 
	 * @return start time and end time
	 */
	@Override
	public String toString() {
		return getStartTime().get(Calendar.HOUR_OF_DAY) + ":00 - " + getEndTime().get(Calendar.HOUR_OF_DAY) + ":00";
	}

}
