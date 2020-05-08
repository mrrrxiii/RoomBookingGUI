package cst8284.asgmt4.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cst8284.asgmt4.roomScheduler.BadRoomBookingException;
import cst8284.asgmt4.roomScheduler.Changemesseger;
import cst8284.asgmt4.roomScheduler.InputValidation;
import cst8284.asgmt4.roomScheduler.RoomBooking;
import cst8284.asgmt4.roomScheduler.RoomScheduler;
import cst8284.asgmt4.roomScheduler.TimeBlock;

public class NewTimeDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton changeBtn;
	private JButton cancelBtn;
	private JTextField newStartTextField;
	private JTextField newEndTextField;

	private ChangeTimeListener changeTimeListener;

	public NewTimeDialog(JFrame parent, String title, boolean mes) {
		super(parent, title, mes);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenX = (int) screenSize.getWidth() / 5;
		int screenY = (int) (7 * screenSize.getHeight() / 16);

		setPreferredSize(new Dimension(screenX, screenY));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		changeBtn = new JButton("Change");
		cancelBtn = new JButton("Cancel");
		JLabel newStartTimeLabel = new JLabel("New Start Time (2 p.m. or 14:00): ");
		JLabel newEndTimeLabel = new JLabel("New End Time (2 p.m. or 14:00): ");
		newStartTextField = new JTextField();
		newEndTextField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(newStartTimeLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		add(newStartTextField, c);
		c.gridx = 0;
		c.gridy = 1;
		add(newEndTimeLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		add(newEndTextField, c);
		c.gridx = 0;
		c.gridy = 2;
		add(changeBtn, c);
		c.gridx = 1;
		c.gridy = 2;
		add(cancelBtn, c);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		getCancelBtn().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();

			}

		});

		getChangeBtn().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				RoomBooking change = Changemesseger.getRm();
				String oldBooking = change.toString();
				Calendar newStart = new Calendar.Builder().setInstant(change.getTimeBlock().getStartTime().getTime())
						.build();
				Calendar newEnd = new Calendar.Builder().setInstant(change.getTimeBlock().getStartTime().getTime())
						.build();
				TimeBlock newTime = null;
				// TODO Auto-generated method stub
				RoomScheduler.setMessage("");
				String ns = getNewStartTextField().getText();
				String ne = getNewEndTextField().getText();

				boolean check = true;

				try {

					newStart.set(Calendar.HOUR_OF_DAY, RoomScheduler.processTimeString(ns));
					newEnd.set(Calendar.HOUR_OF_DAY, RoomScheduler.processTimeString(ne));

					InputValidation.checkStartEndEqual(newStart, newEnd);
					InputValidation.checkStartEndEqual(newStart, newEnd);
					newTime = new TimeBlock(newStart, newEnd);

					// check if the new time overlap with other existing bookings
					for (int i = 0; i < newTime.duration(); i++) {
						Calendar initialTime = new Calendar.Builder().setInstant(newStart.getTime()).build();
						RoomBooking unit = RoomScheduler.findBooking(initialTime);
						/* it will display all overlapped bookings, not only the first occurrence */
						if (unit != null && !unit.equals(change)) {
							if (check == true) {
								// change check to false only for once
								// so the alter message only show once before showing overlapped bookings
								check = false;
								RoomScheduler.setMessage(RoomScheduler.getMessage()
										+ "The room is already booked during that time and date!!!\nPlease see details below:\n");
								;
							}
							RoomScheduler.setMessage(RoomScheduler.getMessage() + unit.toString());

							// i is increased corresponding to the endtime of the found overlapped booking
							i += unit.getTimeBlock().duration() - 1;
							// assign newStart's startTime equal to the endtime of the found overlapped
							// booking
							initialTime.set(Calendar.HOUR_OF_DAY,
									unit.getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY));
						} else {
							initialTime.add(Calendar.HOUR_OF_DAY, 1);
						}

					}

					if (check == true) {
						// after finding, if there is no overlap, put the new booking back to the list
						// change is processed
						int re = JOptionPane.showConfirmDialog(null,
								"Do you want to change:\n" + oldBooking + "to\n" + newTime.toString(), "Confirm",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (re == JOptionPane.YES_OPTION) {
							ChangeTimeEvent changeEvent = new ChangeTimeEvent(this, newTime, 1);
							if (changeTimeListener != null) {
								changeTimeListener.changeEventOccurred(changeEvent);

							}

							// getRoomBookings().set(reservation, change);

							dispose();

						} else {
							ChangeTimeEvent changeEvent = new ChangeTimeEvent(this, newTime, -1);
							if (changeTimeListener != null) {
								changeTimeListener.changeEventOccurred(changeEvent);

							}
							dispose();
						}

					} else {
						// getRoomBookings().set(reservation, change);
						RoomScheduler.setMessage(RoomScheduler.getMessage() + "No change is proccessed");

						dispose();

					}
				} catch (BadRoomBookingException ex) {
					JOptionPane.showMessageDialog(null, ex.getHeader() + "\n" + ex.getMessage());
				}

			}

			// input new start and end time with the same date as requested booking
			// as the sample does not show the new date request, and there is an additional
			// "new" on the print
			// thus, here can not call makeCalenderFromUserInput() directly

		});

		setVisible(true);
	}

	public JButton getChangeBtn() {
		return changeBtn;
	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}

	public JTextField getNewStartTextField() {
		return newStartTextField;
	}

	public JTextField getNewEndTextField() {
		return newEndTextField;
	}

	public void setChangeBtn(JButton changeBtn) {
		this.changeBtn = changeBtn;
	}

	public void setCancelBtn(JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

	public void setNewStartTextField(JTextField newStartTextField) {
		this.newStartTextField = newStartTextField;
	}

	public void setNewEndTextField(JTextField newEndTextField) {
		this.newEndTextField = newEndTextField;
	}

	public ChangeTimeListener getChangeTimeListener() {
		return changeTimeListener;
	}

	public void setChangeTimeListener(ChangeTimeListener changeTimeListener) {
		this.changeTimeListener = changeTimeListener;
	}

}
