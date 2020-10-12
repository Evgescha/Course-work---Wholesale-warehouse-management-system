package controller;

import javax.swing.JFrame;

public class StandartFrameOperation {
	JFrame frame;

	public StandartFrameOperation(JFrame frame) {
		this.frame = frame;
	}

	public void showMain() {
		frame.setVisible(true);
	}

	public void hideMain() {
		frame.setVisible(false);
	}
}
