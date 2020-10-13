package controller;

import forms.ClientFrame;
import forms.DeliveryFrame;
import forms.EmployeeFrame;
import forms.MainFrame;
import forms.OrderFrame;
import forms.ProductFrame;
import forms.ProviderFrame;
import forms.WarehouseFrame;

public class ApplicationController {
	
	public static MainController mainController = new MainController(new MainFrame());
	public static ClientController clientController = new ClientController(new ClientFrame());
	public static OrderController orderController = new OrderController(new OrderFrame());
	public static EmployeeController employeeController = new EmployeeController(new EmployeeFrame());
	public static ProductController productController = new ProductController(new ProductFrame());
	public static ProviderController providerController = new ProviderController(new ProviderFrame());
	public static DeliveryController deliveryController = new DeliveryController(new DeliveryFrame());
	public static WarehouseController warehouseController = new WarehouseController(new WarehouseFrame());
	
	public static void main(String[] args) throws Exception {
		mainController.showFrame();
	}
	

}
