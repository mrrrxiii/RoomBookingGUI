package cst8284.asgmt4.roomScheduler;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cst8284.asgmt4.gui.RoomSchedulerDialog;
import cst8284.asgmt4.room.Room;

/**
 *
 * 
 * @author Lei Kong
 * @version 1.00
 * 
 *
 */

public class RoomScheduler {

	private static ArrayList<RoomBooking> roomBookings = new ArrayList<>();
	private static String message;
	private static Room room;

	/**
	 * construct RoomScheduler with given room
	 * 
	 * @param room the given room
	 */
	public RoomScheduler(Room room) {
		setRoom(room);
		loadBookingsFromFile();
		Collections.sort(getRoomBookings(), new SortRoomBookingsByCalendar());

	}

	/**
	 * launch the booking system with displaying the menu
	 */
	public void launch() {
		Calendar current = Calendar.getInstance();
		String[] dateArray = current.getTime().toString().split(" ");
		new RoomSchedulerDialog("Room Bookings for " + getRoom().getRoomNumber() + " for " + dateArray[1] + ". "
				+ dateArray[2] + ", " + dateArray[5]);

	}

	/**
	 * display the menu and require the user input for selection
	 * 
	 * @return the selection number from user
	 */

	/**
	 * execute the corresponding menu selection
	 * 
	 * @param choice selection from the menu
	 */

	/**
	 * display the room information
	 */

	/**
	 * save given RoomBooking to the system this method displays all overlapped
	 * booking if exists not only the first occurrence, which provides better user
	 * experience
	 * 
	 * @param booking given RoomBooking to be saved into system
	 * @return true if save successfully or false if there is overlapped booking
	 */
	public static boolean saveRoomBooking(RoomBooking booking) {
		boolean save = true;
		message = "";
		Calendar div = new Calendar.Builder().setInstant(booking.getTimeBlock().getStartTime().getTime()).build();
		for (int i = 0; i < booking.getTimeBlock().duration(); i++) {
			RoomBooking unit = findBooking(div);
			if (unit != null) {
				if (save == true) {
					// change save to false only for once
					// so the alter message only show once before showing overlapped bookings
					save = false;
					message += "The room is already booked during that time and date!!!\nPlease see details below:";
				}
				message += unit.toString() + "\n";
				// i is increased corresponding to the endtime of the found overlapped booking
				i += unit.getTimeBlock().duration() - 1;
				// assign div startTime equal to the endtime of the found overlapped booking
				div.set(Calendar.HOUR_OF_DAY, unit.getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY));
			} else {
				div.add(Calendar.HOUR_OF_DAY, 1);
			}

		}
		// no overlapped booking, save stay same
		if (save == true) {
			getRoomBookings().add(booking);

			message = "Booking time and date saved.";
		}
		return save;

	}

	/**
	 * delete existing room booking by given start time
	 * 
	 * @param cal the given start time
	 * @return true if room booking is deleted or false otherwise
	 */
	public static boolean deleteBooking(Calendar cal) {
		message = "";
		RoomBooking del = displayBooking(cal);

		if (del != null) {
			// only print the confirmation when the booking exists
			int re = JOptionPane.showConfirmDialog(null, "Do you want to delete this booking?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (re == JOptionPane.YES_OPTION) {
				message = "Booking deleted";
				return getRoomBookings().remove(del);
			} else {
				// show cancel message when there is an invalid input for confirmation

			}
		}
		// show cancel message when the booking does not exist
		message += "Delete process is canceled";
		return false;

	}

	/**
	 * change the existing room booking by given start time and ask user input new
	 * start and end time to set up new event time
	 * 
	 * @param cal given start time
	 * @return true if room booking is changed or false otherwise
	 */

	/**
	 * display the room booking by given start time
	 *
	 * @param cal given start time
	 * @return RoomBooking overlapped with the given star time, or null if there is
	 *         no booking overlapped with the start time
	 */
	public static RoomBooking displayBooking(Calendar cal) {
		RoomBooking display = findBooking(cal);
		if (display == null) {
			message += "No booking scheduled between " + cal.get(Calendar.HOUR_OF_DAY) + ":00 and "
					+ (cal.get(Calendar.HOUR_OF_DAY) + 1) + ":00" + "\n";

		} else {
			message += display.toString() + "\n";
		}
		return display;
	}

	/**
	 * display all existing room bookings by given date
	 * 
	 * @param cal given date which is only used the field: year, month,day
	 */
	public static void displayDayBookings(Calendar cal) {
		message = "";

		cal.set(Calendar.HOUR_OF_DAY, 8);
		for (int i = 8; i < 23; i++) {
			RoomBooking display = displayBooking(cal);
			if (display == null) {
				cal.add(Calendar.HOUR_OF_DAY, 1);
			} else {
				// i is increased corresponding to the endtime of the found overlapped booking
				i += display.getTimeBlock().duration() - 1;
				// assign div startTime equal to the endtime of the found overlapped booking
				cal.set(Calendar.HOUR_OF_DAY, display.getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY));
			}

		}

	}

	/**
	 * save all the booking information into file if the file is missing, create new
	 * one
	 * 
	 * @return true if all booking information is stored into the file, false
	 *         otherwise
	 */
	public static boolean saveBookingsToFile() {
		message = "";
		boolean check = false;
		File file = new File("CurrentRoomBookings.book");
		if (!file.exists() || file.isDirectory()) {

			try {
				file.createNewFile();
				// System.out.println("CurrentRoomBookings.book is created");
			} catch (IOException e) {
				message = "Can not creat file CurrentRoomBookings.book";
			}
		}

		try (FileOutputStream f = new FileOutputStream(file); ObjectOutputStream o = new ObjectOutputStream(f);) {
			for (RoomBooking roomBooking : getRoomBookings()) {
				o.writeObject(roomBooking);
			}

			check = true;
			message = "Current room bookings backed up to file";
		} catch (IOException e) {
			message = "No File exists";

		}
		return check;
	}

	/**
	 * load all the booking information from the file if the file is missing, create
	 * a new one
	 * 
	 * @return ArrayList contains all booking information loaded from the file
	 */
	public static ArrayList<RoomBooking> loadBookingsFromFile() {
		getRoomBookings().clear();
		message = "";
		File file = new File("CurrentRoomBookings.book");
		if (!file.exists() || file.isDirectory()) {

			try {
				file.createNewFile();
				// System.out.println("CurrentRoomBookings.book is created");
			} catch (IOException e) {
				message = "Can not creat file CurrentRoomBookings.book";
			}
		}
		try (FileInputStream f = new FileInputStream(file); ObjectInputStream o = new ObjectInputStream(f);) {
			while (getRoomBookings().add((RoomBooking) o.readObject())) {
				// do nothing
			}
		} catch (EOFException e) {
			message = "Current room bookings loaded from file";

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			message = "No file exists";
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			message = "No file exists";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = "No roombooking found";
		}

		return getRoomBookings();
	}

	/**
	 * create room booking by user input
	 * 
	 * @return RoomBooking instantiated by the user input information
	 * @throws Exception
	 */
	public static RoomBooking makeBookingFromUserInput(JTextField dateInput, JTextField startTimeInput,
			JTextField endTimeInput, JTextField nameInput, JTextField phoneNumberInput, JTextField organizationInput,
			JTextField categoryInput, JTextField descriptionInput) throws Exception {

		ContactInfo contactInfo = null;
		Activity activity = null;
		TimeBlock timeBlock = null;
		String[] fullName = null;

		message = "\n";
		fullName = nameInput.getText().split(" ");

		String phoneNumber = phoneNumberInput.getText();
		String organization = organizationInput.getText();

		String category = categoryInput.getText();
		String description = descriptionInput.getText();

		Calendar startTime = makeCalendarFromUserInput(null, true, dateInput, startTimeInput);

		Calendar endTime = makeCalendarFromUserInput(new Calendar.Builder().setInstant(startTime.getTime()).build(),
				true, dateInput, endTimeInput);

		timeBlock = new TimeBlock(startTime, endTime);

		InputValidation.checkFullName(fullName);
		contactInfo = new ContactInfo(fullName[0], fullName[1], phoneNumber, organization);

		activity = new Activity(category, description);

		return new RoomBooking(timeBlock, contactInfo, activity);

	}

	/**
	 * create calendar time by user input
	 * 
	 * @param cal         given time
	 * @param requestHour indicating if the hours needed to be set or not
	 * @return Calendar created by user input
	 */
	public static Calendar makeCalendarFromUserInput(Calendar cal, boolean requestHour, JTextField dateInput,
			JTextField timeInput) {
		message = "";
		if (cal == null) {
			cal = new Calendar.Builder().build();

			String date = dateInput.getText();
			InputValidation.checkDateFormat(date);
			cal.set(Integer.parseInt(date.substring(4)), Integer.parseInt(date.substring(2, 4)) - 1,
					Integer.parseInt(date.substring(0, 2)));

			if (requestHour) {

				cal.set(Calendar.HOUR_OF_DAY, processTimeString(timeInput.getText()));

			}

		} else {
			if (requestHour) {

				cal.set(Calendar.HOUR_OF_DAY, processTimeString(timeInput.getText()));

			}
		}

		return cal;

	}

	/**
	 * convert user input hour time to Calendar compatible format
	 * 
	 * @param string user input hour time
	 * @return the hour of the day
	 */
	public static int processTimeString(String string) {
		// TODO Auto-generated method stub
		InputValidation.checkEmpty(string);
		InputValidation.checkNull(string);
		return ((string.contains(":") || string.contains("a"))
				? Integer.parseInt(string.substring(0, 2).trim().split(":")[0])
				: Integer.parseInt(string.substring(0, 2).trim()) % 12 + 12);

	}

	/**
	 * find the existing booking by given start time using binary search to find the
	 * existing booking index
	 * 
	 * @param cal given start time
	 * @return RoomBooking if there is existing RoomBooking overlapped with the
	 *         given start time, null if no existing booking
	 */
	public static RoomBooking findBooking(Calendar cal) {
		Collections.sort(getRoomBookings(), new SortRoomBookingsByCalendar());

		Calendar cal1 = new Calendar.Builder().setInstant(cal.getTime()).build();
		cal1.add(Calendar.HOUR_OF_DAY, 1);
		TimeBlock timeBlock = new TimeBlock(cal, cal1);
		RoomBooking cur = new RoomBooking(new TimeBlock(cal, cal1), new ContactInfo("TEST", "TEST", "999-999-9999"),
				new Activity("TEST", "TEST"));
		int x = Collections.binarySearch(getRoomBookings(), cur, new SortRoomBookingsByCalendar());
		if (x < -1) {
			x = -(x + 1) - 1;
			RoomBooking rm = getRoomBookings().get(x);
			if (rm.getTimeBlock().overLaps(timeBlock)) {
				return rm;
			}
			return null;

		}
		if (x == -1) {
			return null;
		}

		return getRoomBookings().get(x);

	}

	/**
	 * get all existing room bookings
	 * 
	 * @return ArrayList contains existing RoomBooking
	 */
	private static ArrayList<RoomBooking> getRoomBookings() {

		return roomBookings;
	}

	/**
	 * set the room to be booked by given room
	 * 
	 * @param room given room
	 */
	private void setRoom(Room rm) {
		room = rm;
	}

	/**
	 * get the room to be booked
	 * 
	 * @return Room the room to be booked
	 */
	public static Room getRoom() {
		return room;
	}

	public static String getMessage() {
		return message;
	}

	public static void setMessage(String string) {
		message = string;
	}

}
