package controller;

import forms.Client;
import forms.Employee;
import forms.Main;
import forms.Order;
import forms.Product;
import forms.Provider;

public class ApplicationController {
	
	public static MainController mainController = new MainController(new Main());
	public static ClientController clientController = new ClientController(new Client());
	public static OrderController orderController = new OrderController(new Order());
	public static EmployeeController employeeController = new EmployeeController(new Employee());
	public static ProductController productController = new ProductController(new Product());
	public static ProviderController providerController = new ProviderController(new Provider());
	
	public static void main(String[] args) {
		mainController.showFrame();
	}

}
