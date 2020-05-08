package cst8284.asgmt4.room;

/**
 * 
 * 
 * @author Lei Kong
 * @version 1.00
 *
 */
public final class Boardroom extends Room {
	private int seat = 16;

	/**
	 * no-arg constructor chained to the superclass with default name A154
	 * 
	 * 
	 * 
	 */
	public Boardroom() {

		// TODO Auto-generated constructor stub
	}

	public Boardroom(String roomNumber) {
		super(roomNumber);

	}

	/**
	 * get the room type
	 * 
	 * @return room type: board room
	 * 
	 * 
	 */
	@Override
	protected String getRoomType() {
		// TODO Auto-generated method stub
		return "board room";
	}

	/**
	 * get the number of seat
	 * 
	 * @return the number of seats in the room
	 * 
	 * 
	 */
	@Override
	protected int getSeats() {
		// TODO Auto-generated method stub
		return seat;
	}

	/**
	 * get the details of the room
	 * 
	 * @return the details of the room
	 * 
	 * 
	 */
	@Override
	protected String getDetails() {
		// TODO Auto-generated method stub
		return "conference call enabled";
	}

}
