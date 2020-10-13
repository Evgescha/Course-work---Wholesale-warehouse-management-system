package controller;

import javax.swing.JFrame;

import defaultOperation.StandartFrameOperation;

public class MainController extends StandartFrameOperation {

	public MainController(JFrame frame) {
		super(frame);
	}

	public void actionOrderButton() {
		ApplicationController.orderController.switchVisible();
	}

	public void actionProductButton() {
		ApplicationController.productController.switchVisible();
	}

	public void actionProviderButton() {
		ApplicationController.providerController.switchVisible();
	}

	public void actionClientButton() {
		ApplicationController.clientController.switchVisible();
	}

	public void actionEmployeeButton() {
		ApplicationController.employeeController.switchVisible();
	}
	public void actionDeliveryButton() {
		ApplicationController.deliveryController.switchVisible();
	}
	public void actionWarehouseButton() {
		ApplicationController.warehouseController.switchVisible();
	}
}
