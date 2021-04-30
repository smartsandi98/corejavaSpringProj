package services;

import java.util.List;

import dao.EmployeeDao;
import model.Employee;

public class EmployeeServicesImpl implements EmployeeServices {
	private EmployeeDao dao;
	private long idGenarator;

	public void setDao(EmployeeDao dao) {
		this.dao = dao;
	}

	private long idGenarator() {
		this.idGenarator = dao.createId();
		return ++idGenarator;
	}

	public long addEmployee(Employee employee) {
		employee.setEmployeeId(idGenarator());
		return dao.addEmployee(employee);
	}

	public Employee searchEmployeeByID(long employeeId) {

		Employee foundEmployee = dao.getEmployeeById(employeeId);
		return foundEmployee;
	}

	public void showEmployeeDetails() {

	}

	public EmployeeDao getDao() {
		return dao;
	}

	public boolean removeEmployeeDetails(long employeeID) {
		boolean deletionStatus = dao.deleteEmployee(employeeID);
		if (deletionStatus) {
			return true;
		} else {
			return false;

		}

	}

	public boolean updateEmployeeName(String name, long id) {
		boolean status = dao.updateEmployeeName(name, id);
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateEmployeeAge(int age, long id) {
		boolean status = dao.updateEmployeeAge(age, id);
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateEmployeeAddress(String address, long id) {

		boolean status = dao.updateEmployeeAddress(address, id);
		if (status) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isEmployeeExists(long id) {
		Employee foundEmployee = dao.getEmployeeById(id);
		if (foundEmployee == null) {
			return false;
		} else {
			return true;
		}
	}

	public List<Employee> getAllEmployees() {
		return dao.getEmployee();
	}

}
