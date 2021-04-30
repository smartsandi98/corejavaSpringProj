package controller;

import java.util.List;
import java.util.Scanner;

import model.Employee;
import services.EmployeeServices;

public class EmployeeController {
	private EmployeeServices services;
	private Scanner scanner = new Scanner(System.in);

	public void setServices(EmployeeServices services) {
		this.services = services;
	}

	public void HandleRequest() {
		boolean exit = false;
		printInstruction();
		while (!exit) {
			System.out.print("\nEnter action: (Press 6 to show available options)" + "\nPress: ");
			String choice = scanner.nextLine();
			try {
				int intChoice = Integer.parseInt(choice);
				switch (intChoice) {
				case 0:
					System.out.println("Exiting the applications...\n" + "Exiting successful");
					exit = true;
					break;
				case 1:
					addEmployee();
					break;
				case 2:
					showEmployeeDetails();
					break;
				case 3:
					updateEmployeeDetails();
					break;

				case 4:
					deleteEmployeeDetails();
					break;

				case 5:
					searchEmployeeDetails();
					break;

				case 6:
					printInstruction();
					break;
				}

			} catch (Exception e) {
				System.out.println(
						"Plear enter 'Number' input.\nTo start from begining Press '1'\n" + "To terminate enter '0'");
				System.out.print("\nPress: ");
				String newChoice = scanner.nextLine();
				try {
					int newIntChoice = Integer.parseInt(newChoice);
					switch (newIntChoice) {
					case 0:
						exit = true;
						break;
					case 1:
						exit = false;
						break;
					}

				} catch (Exception e2) {
					exit = true;
				}
			}
		}

	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public void addEmployee() {
		try {
			System.out.print("Enter the employee name: ");
			String name = scanner.nextLine();
			System.out.print("Enter employee age: ");
			String age = scanner.nextLine();
			int integerAge = Integer.parseInt(age);
			System.out.print("Enter employee address: ");
			String address = scanner.nextLine();
			Employee newEmployee = Employee.createEmployeeDetails(0, name, integerAge, address);
			long id = services.addEmployee(newEmployee);
			System.out.print("Genarated ID " + id);
		} catch (Exception e) {
			System.out.println("Error in addition process");
		}

	}

	public void deleteEmployeeDetails() {
		try {
			System.out.print("Please enter the id: ");
			String id = scanner.nextLine();
			int integerID = Integer.parseInt(id);
			if (services.isEmployeeExists(integerID)) {
				Employee recordEmployee = services.searchEmployeeByID(integerID);

				if (recordEmployee.getEmployeeId() <= 0) {
					System.out.println("Record does not exists for ID: " + integerID);
				} else {
					boolean status = services.removeEmployeeDetails(integerID);
					if (!status) {
						System.out.println("Deletion Error");
					} else {
						System.out.println("Deletion Successful");
					}
				}
			} else {
				System.out.println("Employee Details Not Found In The Database For ID " + integerID);
			}

		} catch (Exception e) {
			System.out.println("Error found in deletion" + e);
		}

	}

	public void searchEmployeeDetails() {
		try {
			System.out.print("Please enter the id: ");
			String id = scanner.nextLine();
			int integerID = Integer.parseInt(id);
			Employee recordEmployee = services.searchEmployeeByID(integerID);

			if (recordEmployee == null) {
				System.out.println("Record does not exists for ID: " + integerID);
			} else {
				System.out.println("Employee Id: " + recordEmployee.getEmployeeId() + ", Employee Name: "
						+ recordEmployee.getEmployeeName() + ", Employee age: " + recordEmployee.getEmployeeAge()
						+ ", Employee Address: " + recordEmployee.getEmployeeAddress());

			}
		} catch (Exception e) {
			System.out.println("Error in Search");

		}

	}

	public void showEmployeeDetails() {
		List<Employee> employeeList = services.getAllEmployees();
		if (employeeList != null) {
			for (int i = 0; i < employeeList.size(); i++) {
				System.out.println("Employee Id: " + employeeList.get(i).getEmployeeId() + ", Employee Name: "
						+ employeeList.get(i).getEmployeeName() + ", Employee age: "
						+ employeeList.get(i).getEmployeeAge() + ", Employee Address: "
						+ employeeList.get(i).getEmployeeAddress());
			}
		} else {
			System.out.println("No Value");
		}

	}

	public void printInstruction() {
		System.out.println("Press\n" + "Enter 0 to exit\n" + "Enter 1 to add employee details\n"
				+ "Enter 2 to show employee details\n" + "Enter 3 to update details\n"
				+ "Enter 4 to remove employee details\n" + "Enter 5 to search employee details by id\n"
				+ "Enter 6 to show options");
	}

	private void updateEmployeeName() {
		long employeeID = 0;
		System.out.print("Please Enter the Employee Id to Update : ");
		String id = scanner.nextLine();
		try {
			employeeID = Long.parseLong(id);
		} catch (Exception e) {
			System.out.println("Please Enter Number Input");
		}
		if (services.isEmployeeExists(employeeID)) {
			System.out.print("Please Enter updated name : ");
			String name = scanner.nextLine();
			boolean status = services.updateEmployeeName(name, employeeID);
			if (status) {
				System.out.println("Employee name updated for " + employeeID);
			} else {
				System.out.println("Cannot Update Employee Name");
			}

		} else {
			System.out.println("No Data Exist For ID " + employeeID);
		}

	}

	private void updateEmployeeAge() {
		long employeeID = 0;
		int intAge = 0;
		System.out.print("\nEnter updated age :- ");
		String age = scanner.nextLine();
		try {
			intAge = Integer.parseInt(age);
		} catch (Exception e) {
			System.out.println("Please Enter Valid Age");
		}
		System.out.print("Please Enter the Employee Id to Update : ");
		String id = scanner.nextLine();
		try {
			employeeID = Long.parseLong(id);
		} catch (Exception e) {
			System.out.println("Please Enter Number Input");
		}
		if (services.isEmployeeExists(employeeID)) {
			boolean status = services.updateEmployeeAge(intAge, employeeID);
			if (status) {
				System.out.println("Employee name updated for " + employeeID);
			} else {
				System.out.println("Cannot Update Age");
			}
		} else {
			System.out.println("No Data Exist For ID " + employeeID);
		}

	}

	private void updateEmployeeAddress() {
		long employeeID = 0;
		System.out.print("\nEnter updated address :- ");
		String address = scanner.nextLine();
		System.out.print("Please Enter the Employee Id to Update : ");
		String id = scanner.nextLine();
		try {
			employeeID = Long.parseLong(id);
		} catch (Exception e) {
			System.out.println("Please Enter Number Input");
		}
		if (services.isEmployeeExists(employeeID)) {
			boolean status = services.updateEmployeeAddress(address, employeeID);
			if (status) {
				System.out.println("Employee name updated for " + employeeID);
			} else {
				System.out.println("Error...Can Not Update Address ");
			}
		} else {
			System.out.println("No Data Exist For ID " + employeeID);
		}

	}

	public void updateEmployeeDetails() {
		System.out.println("-------- Update Employee Details --------\n");
		boolean flag = true;
		while (flag) {
			System.out.println("Select The Field You Want To Update\n");
			System.out.println("Press 1 To Update Name\n" + "Press 2 To Update Age\n" + "Press 3 To Update Address\n"
					+ "Press 0 To Go Back In Main Menu\n");
			System.out.print("\nPress: ");
			String Choice = scanner.nextLine();
			System.out.println("You entered the choice " + Choice);
			int intChoice;
			try {
				intChoice = Integer.parseInt(Choice);

				switch (intChoice) {
				case 1:
					updateEmployeeName();
					break;
				case 2:
					updateEmployeeAge();
					break;
				case 3:
					updateEmployeeAddress();
				case 0:
					flag = false;
					break;
				default:
					break;
				}
			} catch (Exception ex) {
				System.out.println("You have Entered an Invalid Input." + "\nTo go Back Enter 1"
						+ "\n Enter 0 To Go Back To Main Menu");
				System.out.println("\nPress: ");
				Choice = scanner.nextLine();
				int newIntChoice;
				try {
					newIntChoice = Integer.parseInt(Choice);
					switch (newIntChoice) {
					case 1:
						flag = true;
						break;
					case 0:
						flag = false;
						break;
					default:
						flag = false;
						break;
					}
				} catch (Exception exp) {
					flag = false;
				}

			}
		}
	}
}
