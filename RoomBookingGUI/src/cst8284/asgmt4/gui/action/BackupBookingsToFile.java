package cst8284.asgmt4.gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cst8284.asgmt4.gui.RoomSchedulerDialog;
import cst8284.asgmt4.roomScheduler.RoomScheduler;

public class BackupBookingsToFile implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		RoomSchedulerDialog.reloadJTextArea(null);
		RoomScheduler.saveBookingsToFile();
		RoomSchedulerDialog.reloadJTextArea(RoomScheduler.getMessage());
	}

}
