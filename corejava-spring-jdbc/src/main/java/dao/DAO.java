package dao;

import java.util.List;

import model.Employee;

public interface DAO {

	long addEmployee(Employee employeeDetails);

	Employee getEmployeeById(long id);

	boolean deleteEmployee(long id);

	long createId();

	boolean updateEmployeeName(String name, long id);

	public boolean updateEmployeeAge(int age, long id);

	boolean updateEmployeeAddress(String address, long id);

	List<Employee> getEmployee();

}