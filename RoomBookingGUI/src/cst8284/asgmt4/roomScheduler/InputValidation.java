package cst8284.asgmt4.roomScheduler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cst8284.asgmt4.gui.RoomBookingDialog;

/**
 *
 * check input validation
 * 
 * @author Lei Kong
 * @version 1.00
 * 
 *
 */
public class InputValidation {
	/**
	 * check if the start time preceded end time
	 * 
	 * @param startTime booking event start time
	 * 
	 * @param endTime   booking event end time
	 * 
	 * @return true if start time does not precede end time
	 * @throws Exception
	 * 
	 * @throws BadRoomBookingException if the start time preceded end time
	 */
	public static boolean checkStartEndPrecede(Calendar startTime, Calendar endTime) {
		if (startTime.getTimeInMillis() > endTime.getTimeInMillis()) {
			throw new BadRoomBookingException("End time precedes start time",
					"The room booking start time must occur before the end time of the room booking.",
					RoomBookingDialog.getStart());
		}

		return true;
	}

	/**
	 * check if the start time is same as the end time
	 * 
	 * @param startTime booking event start time
	 * @param endTime   booking event end time
	 * @return true if event duration is one hour or longer
	 * @throws Exception
	 * @throws BadRoomBookingException if the start time is same as end time
	 */
	public static boolean checkStartEndEqual(Calendar startTime, Calendar endTime) {
		if (startTime.getTimeInMillis() == endTime.getTimeInMillis()) {
			throw new BadRoomBookingException("Zero time room booking",
					"Start and end time of the room booking are the same. The minimum time for a room booking is one hour.",
					RoomBookingDialog.getStart());
		}
		return true;
	}

	/**
	 * check the data if follows the format "ddMMyyyy"
	 * 
	 * @param date the date including only day, month and year
	 * @return true if the date format is valid
	 * @throws Exception
	 * @throws BadRoomBookingException if the date format is wrong
	 */
	public static boolean checkDateFormat(String date) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date);
		} catch (ParseException ex) {
			throw new BadRoomBookingException("Bad Calendar format",
					"Bad calendar date was entered. The correct format is DDMMYYYY.", RoomBookingDialog.getDate());
		}
		return true;
	}

	/**
	 * check if the name contains illegal characters
	 * 
	 * @param string name of the person
	 * @return true if the name is valid
	 * @throws Exception
	 * @throws BadRoomBookingException if the names contains illegal characters
	 */
	public static boolean checkNameChar(String string) {
		if (!string.matches("[a-zA-Z-\\.']*")) {
			throw new BadRoomBookingException("Name contains illegal characters",
					"name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (').",
					RoomBookingDialog.getFullName());
		}
		return true;
	}

	/**
	 * check if the length of name is valid
	 * 
	 * @param string name of the contact person
	 * @return true if the length of the name is within the limit
	 * @throws Exception
	 * @throws BadRoomBookingException if the length of the name is over the limit
	 */
	public static boolean checkNameLength(String string) {
		if (string.length() > 30) {
			throw new BadRoomBookingException("Name length exceeded",
					"The first or last name exceeds the 30 character maximum allowed.",
					RoomBookingDialog.getFullName());
		}
		return true;
	}

	/**
	 * check is the phone number is valid
	 * 
	 * @param phoneNumber phone number of the contact person
	 * @return true if the phone number is valid
	 * @throws Exception
	 * @throws BadRoomBookingException if the phone number is not valid
	 */
	public static boolean checkPhoneNumber(String phoneNumber) {
		if (!phoneNumber.matches("[2-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]")) {
			throw new BadRoomBookingException("Bad telephone number",
					"The telephone number must be a 10-digit number, separated by ?-? in the form, e.g. 613-555-1212.",
					RoomBookingDialog.getPhoneNumber());
		}
		return true;
	}

	/**
	 * check if the user input contains whitespace or nothing
	 * 
	 * @param string user general input
	 * @return true if the input is valid
	 * @throws Exception
	 * @throws BadRoomBookingException if the user input contains only whitespace or
	 *                                 nothing
	 */
	public static boolean checkEmpty(String string) {
		if (string.trim().isEmpty()) {
			throw new BadRoomBookingException("Missing value", "Missing an input value.", null);
		}
		return true;
	}

	/**
	 * check if the user input passes a null
	 * 
	 * @param string general user input
	 * @return ture if no null is passed
	 * @throws Exception
	 * @throws BadRoomBookingException if a null is passed by input
	 */
	public static boolean checkNull(String string) {
		if (string == null) {
			throw new BadRoomBookingException("Null value entered",
					"An attempt was made to pass a null value to a variable.", null);
		}

		return true;
	}

	/**
	 * check if the full name is input in right way
	 * 
	 * @param fullName full name of the contact person
	 * @return true if the full name is typed right
	 * @throws Exception
	 * @throws BadRoomBookingException if full name is missing either first name or
	 *                                 last name or both
	 */
	public static boolean checkFullName(String[] fullName) {
		if (fullName.length < 2) {
			throw new BadRoomBookingException("Missing value", "Missing an input value.",
					RoomBookingDialog.getFullName());
		}

		return true;
	}

	/**
	 * check if the input for the menu selection is valid
	 * 
	 * @param string input for menu selection
	 * @return true if input for the menu selection is valid
	 * @throws Exception
	 * @throws BadRoomBookingException if the input does not match the menu
	 *                                 selection
	 */

}
