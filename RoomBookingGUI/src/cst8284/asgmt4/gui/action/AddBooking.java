package cst8284.asgmt4.gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cst8284.asgmt4.gui.RoomBookingDialog;
import cst8284.asgmt4.roomScheduler.RoomScheduler;

public class AddBooking implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		RoomBookingDialog addBooking = new RoomBookingDialog(RoomScheduler.getRoom().toString());
		addBooking.getSearch().setEnabled(false);
		addBooking.getChange().setEnabled(false);
		addBooking.getDelete().setEnabled(false);

	}

}
