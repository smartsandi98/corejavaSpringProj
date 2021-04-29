package services;

import dao.EmployeeDao;
import model.Employee;

public interface EmployeeServices {

	public void setDao(EmployeeDao dao);

	long addEmployee(Employee employee);

	Employee searchEmployeeByID(long employeeId);

	void getAllEmployees();

	boolean removeEmployeeDetails(long employeeID);

	boolean updateEmployeeName(String name, long id);

	boolean updateEmployeeAge(int age, long id);

	boolean updateEmployeeAddress(String address, long id);

	boolean isEmployeeExists(long id);
}
