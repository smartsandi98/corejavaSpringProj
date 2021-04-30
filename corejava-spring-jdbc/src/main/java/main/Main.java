package main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.EmployeeController;

public class Main {
	private static final String CONFIG_LOCATION = "applicationContext.xml";
	private EmployeeController controller;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
		Main main = context.getBean("main", Main.class);
		main.controller.HandleRequest();
		context.close();
	}

	public void setController(EmployeeController controller) {
		this.controller = controller;
	}
}
