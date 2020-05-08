package cst8284.asgmt4.roomScheduler;

public class Changemesseger {
	private static TimeBlock tb;
	private static int receiveSignal = 0;
	private static RoomBooking rm;

	public static TimeBlock getTb() {
		return tb;
	}

	public static int getReceiveSignal() {
		return receiveSignal;
	}

	public static void setTb(TimeBlock tb) {
		Changemesseger.tb = tb;
	}

	public static void setReceiveSignal(int receiveSignal) {
		Changemesseger.receiveSignal = receiveSignal;
	}

	public static RoomBooking getRm() {
		return rm;
	}

	public static void setRm(RoomBooking roomBooking) {
		Changemesseger.rm = roomBooking;
	}

}
