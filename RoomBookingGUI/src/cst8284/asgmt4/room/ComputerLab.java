package cst8284.asgmt4.room;

/**
 * 
 * 
 * @author Lei Kong
 * @version 1.00
 *
 */
public final class ComputerLab extends Room {
	private static final int DEFAULT_SEATS = 30;
	private int seats;

	/**
	 * no-arg constructor chained to the superclass with default name B119, set
	 * number of seat to default number
	 * 
	 * 
	 * 
	 */
	public ComputerLab() {

		this.seats = DEFAULT_SEATS;
		// TODO Auto-generated constructor stub
	}

	public ComputerLab(String roomNumber) {
		super(roomNumber);
		this.seats = DEFAULT_SEATS;
		// TODO Auto-generated constructor stub
	}

	/**
	 * get the type of room
	 * 
	 * @return room type: computer lab
	 * 
	 */
	@Override
	protected String getRoomType() {
		// TODO Auto-generated method stub
		return "computer lab";
	}

	/**
	 * get the number of seats
	 * 
	 * @return the number of seats
	 * 
	 */
	@Override
	protected int getSeats() {
		// TODO Auto-generated method stub
		return seats;
	}

	/**
	 * get the details of the room
	 * 
	 * @return details of the room
	 *
	 */
	@Override
	protected String getDetails() {
		// TODO Auto-generated method stub
		return "contains outlets for 30 laptops";
	}

}
