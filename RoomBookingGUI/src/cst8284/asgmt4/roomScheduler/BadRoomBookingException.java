package cst8284.asgmt4.roomScheduler;

import javax.swing.JTextField;

/**
 *
 * exception contains header
 * 
 * @author Lei Kong
 * @version 1.00
 * 
 *
 */
public class BadRoomBookingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String header;
	private JTextField text;

	/**
	 * no-arg constructor chained to two args constructor with default header and
	 * message
	 * 
	 * @throws Exception
	 */

	/**
	 * two-args constructor set the header and message
	 * 
	 * @param header  summary of the exception
	 * @param message details of the exception
	 * @throws Exception
	 */
	public BadRoomBookingException(String header, String message, JTextField text) {
		super(message);
		setHeader(header);
		setText(text);

	}

	/**
	 * get the header
	 * 
	 * @return header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * set the header of the exception
	 * 
	 * @param header header of the exception
	 */
	private void setHeader(String header) {
		// TODO Auto-generated method stub
		this.header = header;
	}

	public JTextField getText() {
		return text;
	}

	public void setText(JTextField text) {
		this.text = text;

	}
}
