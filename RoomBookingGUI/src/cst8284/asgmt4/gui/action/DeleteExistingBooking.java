package cst8284.asgmt4.gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cst8284.asgmt4.gui.RoomBookingDialog;
import cst8284.asgmt4.roomScheduler.RoomScheduler;

public class DeleteExistingBooking implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		RoomBookingDialog deleteBooking = new RoomBookingDialog(RoomScheduler.getRoom().toString());
		deleteBooking.getSearch().setEnabled(false);
		deleteBooking.getSave().setEnabled(false);
		deleteBooking.getChange().setEnabled(false);

		RoomBookingDialog.getEnd().setEnabled(false);
		RoomBookingDialog.getFullName().setEnabled(false);
		RoomBookingDialog.getPhoneNumber().setEnabled(false);
		RoomBookingDialog.getOrganization().setEnabled(false);
		RoomBookingDialog.getEvent().setEnabled(false);
		RoomBookingDialog.getDescription().setEnabled(false);

	}

}
