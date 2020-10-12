package controller;

import javax.swing.JFrame;

public class MainController extends StandartFrameOperation{

	public MainController(JFrame frame) {
		super(frame);
		startApplication();
	}
	public void startApplication() {
		frame.setVisible(true);
	}

}
