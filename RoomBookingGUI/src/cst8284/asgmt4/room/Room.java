package cst8284.asgmt4.room;

/**
 * 
 * 
 * @author Dave Houtman
 * @version 1.10
 *
 */
public abstract class Room {

	private static final String DEFAULT_ROOM_NUMBER = "unknown room number";
	private String roomNumber;

	/**
	 * no-arg constructor chained to the one arg constructor with default room
	 * number
	 */
	protected Room() {
		this(DEFAULT_ROOM_NUMBER);
	}

	/**
	 * one-arg constructor set the room number
	 * 
	 * @param roomNum room number
	 */
	protected Room(String roomNum) {
		setRoomNumber(roomNum);
	}

	/**
	 * set the room number
	 * 
	 * @param roomNum room number
	 * 
	 */
	public void setRoomNumber(String roomNum) {
		roomNumber = roomNum;
	}

	/**
	 * get the room number
	 * 
	 * 
	 * @return room number
	 */
	public String getRoomNumber() {
		return roomNumber;
	}

	/**
	 * abstract method to get the room type
	 * 
	 * @return room type
	 */
	protected abstract String getRoomType();

	/**
	 * abstract method to get the number of seats
	 * 
	 * @return number of seats
	 */
	protected abstract int getSeats();

	/**
	 * abstract method to get the details of room
	 * 
	 * @return details of room
	 */
	protected abstract String getDetails();

	/**
	 * information of the room
	 * 
	 * @return information of the room including room number, room type, number of
	 *         seat and details of room
	 */
	public String toString() {
		return getRoomNumber() + " (" + getRoomType() + " / " + getSeats() + " / " + getDetails() + ")";
	}
}
