package cst8284.asgmt4.roomScheduler;

import cst8284.asgmt4.room.ComputerLab;

public class RoomSchedulerLauncher {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new RoomScheduler(new ComputerLab("B119")).launch();

			}
		});
	}
}
