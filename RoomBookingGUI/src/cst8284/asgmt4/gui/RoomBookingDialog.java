package cst8284.asgmt4.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import cst8284.asgmt4.gui.action.changeRoomBooking;
import cst8284.asgmt4.gui.action.deleteRoomBooking;
import cst8284.asgmt4.gui.action.exitRoomBooking;
import cst8284.asgmt4.gui.action.saveRoomBooking;
import cst8284.asgmt4.gui.action.searchRoomBooking;
import cst8284.asgmt4.roomScheduler.BadRoomBookingException;
import cst8284.asgmt4.roomScheduler.Changemesseger;
import cst8284.asgmt4.roomScheduler.RoomBooking;
import cst8284.asgmt4.roomScheduler.RoomScheduler;

public class RoomBookingDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagConstraints labelConstraints = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
			GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 20), 0, 0);
	private GridBagConstraints textConstraints = new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 1, 1, // gridx,
																													// gridy,
																													// gridwidth,
																													// gridheight,
																													// weightx,
																													// weighty
			GridBagConstraints.EAST, 0, new Insets(5, 5, 5, 10), 20, 20); // anchor, fill, insets, ipadx, ipady
	private GridBagConstraints btnConstraints = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 2, 1, 0.5, 0.5,
			GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 20), 0, 0);

	private Panel cp = new Panel();
	private int labelWidth = 24;
	private Font defaultFont = new Font("SansSerif", Font.PLAIN, 20);
	private FlowLayout btnRow = new FlowLayout();
	private JPanel btnPanel = new JPanel();

	private static JTextField date;
	private static JTextField start;
	private static JTextField end;
	private static JTextField fullName;
	private static JTextField phoneNumber;
	private static JTextField organization;
	private static JTextField event;
	private static JTextField description;

	private JButton search;
	private JButton save;
	private JButton change;
	private JButton delete;
	private JButton exit;

	public RoomBookingDialog(String string) {
		super(string);
		cp.setLayout(new GridBagLayout());

		// Set the first seven rows with Label/TextField
		date = setRow("Booking Date (DDMMYYYY):", 'e');
		start = setRow("Booking Start Time (2 p.m. or 14:00):", 'a');
		end = setRow("Booking End Time (2 p.m. or 14:00):", 'n');
		fullName = setRow("Client Name (FirstName LastName):", 'n');
		phoneNumber = setRow("Phone Number (e.g. 613-555-1212):", 'p');
		organization = setRow("Organization (optional):", 'o');
		event = setRow("Event Category:", 'c');
		description = setRow("Description:", 'd');

		// Load the buttons across the bottom
		btnPanel.setLayout(btnRow);

		search = setBtnRow("   Search   ", 'r', new searchRoomBooking());
		search.addActionListener(e -> {

			try {
				RoomScheduler.displayDayBookings(
						RoomScheduler.makeCalendarFromUserInput(null, false, getDate(), getStart()));

				this.dispose();
			} catch (BadRoomBookingException ex) {
				// TODO Auto-generated catch block
				hi(ex.getText());
				JOptionPane.showMessageDialog(null, ex.getHeader() + "\n" + ex.getMessage());

			}
			RoomSchedulerDialog.reloadJTextArea(null);
			RoomSchedulerDialog.reloadJTextArea(RoomScheduler.getMessage());
		});

		save = setBtnRow("    Save    ", 's', new saveRoomBooking());
		save.addActionListener(e -> {

			try {
				RoomScheduler.saveRoomBooking(RoomScheduler.makeBookingFromUserInput(getDate(), getStart(), getEnd(),
						getFullName(), getPhoneNumber(), getOrganization(), getEvent(), getDescription()));
				this.dispose();
			} catch (BadRoomBookingException ex) {
				// TODO Auto-generated catch block
				hi(ex.getText());
				JOptionPane.showMessageDialog(null, ex.getHeader() + "\n" + ex.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			RoomSchedulerDialog.reloadJTextArea(null);
			RoomSchedulerDialog.reloadJTextArea(RoomScheduler.getMessage());
		});

		change = setBtnRow("   change  ", 'h', new changeRoomBooking());
		change.addActionListener(e -> {
			try {
				RoomScheduler.setMessage("");
				RoomBooking change = RoomScheduler
						.displayBooking(RoomScheduler.makeCalendarFromUserInput(null, true, getDate(), getStart()));
				String oldone = "";
				if (change != null) {
					oldone = change.toString();
					Changemesseger.setRm(change);
					new NewTimeDialog(this, "Set up new time", true);
					if (Changemesseger.getReceiveSignal() == 1) {
						change.setTimeBlock(Changemesseger.getTb());
						RoomScheduler.setMessage(oldone + "\nhas been changed to\n" + change.toString());
					} else if (Changemesseger.getReceiveSignal() == -1) {
						RoomScheduler.setMessage(RoomScheduler.getMessage() + "\nno change is proccessed");
					}

				}
				this.dispose();
			} catch (BadRoomBookingException ex) {
				// TODO Auto-generated catch block
				hi(ex.getText());
				JOptionPane.showMessageDialog(null, ex.getHeader() + "\n" + ex.getMessage());
			}

			RoomSchedulerDialog.reloadJTextArea(null);

			RoomSchedulerDialog.reloadJTextArea(RoomScheduler.getMessage());

		});

		delete = setBtnRow("   Delete   ", 'l', new deleteRoomBooking());
		delete.addActionListener(e -> {

			try {
				RoomScheduler.deleteBooking(RoomScheduler.makeCalendarFromUserInput(null, true, getDate(), getStart()));
				this.dispose();
			} catch (BadRoomBookingException ex) {
				// TODO Auto-generated catch block
				hi(ex.getText());
				JOptionPane.showMessageDialog(null, ex.getHeader() + "\n" + ex.getMessage());
			}
			RoomSchedulerDialog.reloadJTextArea(null);

			RoomSchedulerDialog.reloadJTextArea(RoomScheduler.getMessage());

		});

		exit = setBtnRow("    Exit    ", 'x', new exitRoomBooking());
		exit.addActionListener(e -> {
			this.dispose();
		});

		cp.add(btnPanel, btnConstraints);
		add(cp);
		pack();

		// Close dialog
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});

		setVisible(true);
	}

	private JTextField setRow(String label, char keyboardShortcut) {
		JLabel l;
		JTextField t;
		cp.add(l = new JLabel(label, SwingConstants.RIGHT), labelConstraints);
		l.setFont(defaultFont);
		l.setDisplayedMnemonic(keyboardShortcut);
		cp.add(t = new JTextField(labelWidth), textConstraints);
		t.setFont(defaultFont);
		t.setFocusAccelerator(keyboardShortcut);
		return t;
	}

	private JButton setBtnRow(String label, char keyboardShortcut, ActionListener act) {
		JButton btn = new JButton(label);
		btn.setFont(defaultFont);
		btn.setMnemonic(keyboardShortcut);
		btnPanel.add(btn);
		btn.addActionListener(act);
		return btn;
	}

	public static JTextField getDate() {
		return date;
	}

	public static JTextField getStart() {
		return start;
	}

	public static JTextField getEnd() {
		return end;
	}

	public static JTextField getFullName() {
		return fullName;
	}

	public static JTextField getPhoneNumber() {
		return phoneNumber;
	}

	public static JTextField getOrganization() {
		return organization;
	}

	public static JTextField getEvent() {
		return event;
	}

	public static JTextField getDescription() {
		return description;
	}

	public JButton getSearch() {
		return search;
	}

	public JButton getSave() {
		return save;
	}

	public JButton getChange() {
		return change;
	}

	public JButton getDelete() {
		return delete;
	}

	public JButton getExit() {
		return exit;
	}

	private void hi(JTextField text) {
		if (text != null) {
			Highlighter highlighter = text.getHighlighter();
			highlighter.removeAllHighlights();
			HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
			int p0 = 0;
			int p1 = text.getText().length();
			try {
				highlighter.addHighlight(p0, p1, painter);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
