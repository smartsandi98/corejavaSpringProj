package services;

import java.util.List;

import dao.DAOImpl;
import model.Employee;

public interface EmployeeServices {

	public void setDao(DAOImpl dao);

	long addEmployee(Employee employee);

	Employee searchEmployeeByID(long employeeId);

	List<Employee> getEmployee();

	boolean removeEmployeeDetails(long employeeID);

	boolean updateEmployeeName(String name, long id);

	boolean updateEmployeeAge(int age, long id);

	boolean updateEmployeeAddress(String address, long id);

	boolean isEmployeeExists(long id);
}
