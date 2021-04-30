package model;


public class Employee {
	private long employeeId;
	private String employeeName;
	private int employeeAge;
	private String employeeAddress;

	public Employee(long employeeId, String employeeName, int employeeAge, String employeeAddress) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
		this.employeeAddress = employeeAddress;
	}

	public static Employee createEmployeeDetails(long employeeID, String name, int age, String address) {

		return new Employee(employeeID, name, age, address);

	}

	public long getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	@Override
	public String toString() {
		return ("Employee Id: " + employeeId 
				+ ", Employee Name: " + employeeName
				+ ", Employee age: " + employeeAge
				+ ", Employee Address: " + employeeAddress);

	}
	
	

}

