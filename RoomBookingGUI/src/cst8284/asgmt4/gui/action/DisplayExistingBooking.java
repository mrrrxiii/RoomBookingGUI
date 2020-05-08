package cst8284.asgmt4.gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cst8284.asgmt4.gui.RoomBookingDialog;
import cst8284.asgmt4.roomScheduler.RoomScheduler;

public class DisplayExistingBooking implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		RoomBookingDialog display = new RoomBookingDialog(RoomScheduler.getRoom().toString());
		display.getDelete().setEnabled(false);
		display.getSave().setEnabled(false);
		display.getChange().setEnabled(false);

		RoomBookingDialog.getStart().setEnabled(false);
		RoomBookingDialog.getEnd().setEnabled(false);
		RoomBookingDialog.getFullName().setEnabled(false);
		RoomBookingDialog.getPhoneNumber().setEnabled(false);
		RoomBookingDialog.getOrganization().setEnabled(false);
		RoomBookingDialog.getEvent().setEnabled(false);
		RoomBookingDialog.getDescription().setEnabled(false);

	}

}
