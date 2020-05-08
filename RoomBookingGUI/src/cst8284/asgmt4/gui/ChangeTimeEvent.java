package cst8284.asgmt4.gui;

import java.util.EventObject;

import cst8284.asgmt4.roomScheduler.Changemesseger;
import cst8284.asgmt4.roomScheduler.TimeBlock;

public class ChangeTimeEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TimeBlock timeblock;

	public ChangeTimeEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public ChangeTimeEvent(Object source, TimeBlock timeblock, int signal) {
		super(source);
		this.timeblock = timeblock;
		Changemesseger.setTb(timeblock);
		Changemesseger.setReceiveSignal(signal);
		// TODO Auto-generated constructor stub
	}

	public TimeBlock getTimeblock() {
		return timeblock;
	}

	public void setTimeblock(TimeBlock timeblock) {
		this.timeblock = timeblock;
	}

}
