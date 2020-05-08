package cst8284.asgmt4.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cst8284.asgmt4.gui.action.AddBooking;
import cst8284.asgmt4.gui.action.BackupBookingsToFile;
import cst8284.asgmt4.gui.action.ChangeExistingBooking;
import cst8284.asgmt4.gui.action.DeleteExistingBooking;
import cst8284.asgmt4.gui.action.DisplayExistingBooking;
import cst8284.asgmt4.gui.action.Exit;
import cst8284.asgmt4.gui.action.LoadBookingsFromFile;

public class RoomSchedulerDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Toolkit tk = Toolkit.getDefaultToolkit();
	private static final Dimension screenSize = tk.getScreenSize();
	private static final JTextArea scrollText = new JTextArea();
	private static ArrayList<String> message = null;

	public RoomSchedulerDialog(String roomNumber) {
		super(roomNumber);

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int screenX = (int) screenSize.getWidth() / 2;
		int screenY = (int) (7 * screenSize.getHeight() / 8);

		add(getWestPanel(), BorderLayout.WEST);
		add(getCenterPanel(scrollText, screenY), BorderLayout.CENTER);

		setPreferredSize(new Dimension(screenX, screenY));

		pack();
		setVisible(true);

	}

	public static JPanel getCenterPanel(JTextArea jta, int height) {
		JScrollPane centerPane = new JScrollPane(jta);
		centerPane.setPreferredSize(new Dimension(400, 7 * height / 8));
		JPanel jp = new JPanel();
		jp.add(centerPane);
		return jp;
	}

	public static JPanel getWestPanel() {
		JPanel controlPanel = new JPanel(new GridLayout(7, 1));
		JPanel westPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weighty = 1;

		/*
		 * Uncomment each of the lines below as you create and test your ActionListener
		 * classes according to the instructions for TODO #3. Then delete this comment,
		 * load the long word list, test your code by clicking the buttons, and demo
		 * your lab to the professor
		 */
		controlPanel.add(setWestPanelBtns(" Add Booking ", new AddBooking()));
		controlPanel.add(setWestPanelBtns(" Display Existing Booking ", new DisplayExistingBooking()));
		controlPanel.add(setWestPanelBtns(" Change Existing Booking ", new ChangeExistingBooking()));
		controlPanel.add(setWestPanelBtns(" Delete Existing Booking ", new DeleteExistingBooking()));
		controlPanel.add(setWestPanelBtns(" Backup Bookings to File ", new BackupBookingsToFile()));
		controlPanel.add(setWestPanelBtns(" Load Bookings from File ", new LoadBookingsFromFile()));
		controlPanel.add(setWestPanelBtns(" Exit ", new Exit()));

		westPanel.add(controlPanel, gbc);
		return westPanel;
	}

	private static JButton setWestPanelBtns(String btnLabel, ActionListener act) {
		final Font font = new Font("SansSerif", Font.PLAIN, 20);
		JButton btn = new JButton(btnLabel);
		btn.setFont(font);
		btn.addActionListener(act);
		return btn;
	}

	public static ArrayList<String> getMessage() {
		return message;
	}

	public static void reloadJTextArea(String string) {
		scrollText.setText(string);
	}

}
