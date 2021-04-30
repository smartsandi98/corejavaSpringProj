package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import dao.rowmapper.EmployeeRowMapper;
import model.Employee;

public class EmployeeDao implements DAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public long addEmployee(Employee employeeDetails) {
		try {
			final String INSERT_SQL = "INSERT INTO EMPLOYEE(EMPLOYEEID,EMPLOYEENAME,EMPLOYEEAGE,EMPLOYEEADDRESS) VALUES(:EMPLOYEEID, :EMPLOYEENAME, :EMPLOYEEAGE, :EMPLOYEEADDRESS)";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("EMPLOYEEID", employeeDetails.getEmployeeId());
			parameters.put("EMPLOYEENAME", employeeDetails.getEmployeeName());
			parameters.put("EMPLOYEEAGE", employeeDetails.getEmployeeAge());
			parameters.put("EMPLOYEEADDRESS", employeeDetails.getEmployeeAddress());
			namedParameterJdbcTemplate.update(INSERT_SQL, parameters);

			return employeeDetails.getEmployeeId();

		} catch (Exception e) {
			System.out.println("Can not insert details error code " + e);

		}
		return 0;
	}

	public Employee getEmployeeById(long id) {

		try {
			final String GET_EMPLOYEE_BY_ID = "select * from EMPLOYEE where EMPLOYEEID = :EMPLOYEEID";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("EMPLOYEEID", id);
			List<Employee> results = namedParameterJdbcTemplate.query(GET_EMPLOYEE_BY_ID, parameters,
					new EmployeeRowMapper());
			if (null != results && !results.isEmpty()) {
				return results.get(0);
			}
		} catch (Exception e) {
			System.out.println("Error In Searching...");
		}
		return null;
	}

	public boolean deleteEmployee(long id) {
		final String DELETE_EMPLOYEE = "delete from EMPLOYEE where EMPLOYEEID = :EMPLOYEEID";
		try {

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("EMPLOYEEID", id);
			namedParameterJdbcTemplate.update(DELETE_EMPLOYEE, parameters);
			return true;
		} catch (Exception e) {
			System.out.println("Error In Deletion...");
		}
		return false;
	}

	public long createId() {
		final String GET_LAST_ID = "select * from EMPLOYEE where rownum=1 order by EMPLOYEEID desc";
		try {
			List<Employee> results = namedParameterJdbcTemplate.query(GET_LAST_ID, new EmployeeRowMapper());
			if (null != results && !results.isEmpty()) {
				return results.get(0).getEmployeeId();
			} else {
				return 1200;
			}

		} catch (Exception e) {
			System.out.println("Error Occurred During Id Creation");
			e.printStackTrace();
		}
		return 0;
	}

	public boolean updateEmployeeName(String name, long id) {
		try {
			final String UPDATE_EMPLOYEE_NAME_SQL = "update EMPLOYEE set EMPLOYEENAME = :EMPLOYEENAME where EMPLOYEEID = :EMPLOYEEID";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("EMPLOYEENAME", name);
			parameters.put("EMPLOYEEID", id);
			namedParameterJdbcTemplate.update(UPDATE_EMPLOYEE_NAME_SQL, parameters);
			return true;

		} catch (Exception e) {
			System.out.println("Error During Employee Name Update...");
		}
		return false;
	}

	public boolean updateEmployeeAge(int age, long id) {
		try {
			final String UPDATE_EMPLOYEE_AGE_SQL = "update EMPLOYEE set EMPLOYEEAGE = :EMPLOYEEAGE where EMPLOYEEID = :EMPLOYEEID";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("EMPLOYEEAGE", age);
			parameters.put("EMPLOYEEID", id);
			namedParameterJdbcTemplate.update(UPDATE_EMPLOYEE_AGE_SQL, parameters);
			return true;
		} catch (Exception e) {
			System.out.println("Error During Employee Age Update...");
		}
		return false;
	}

	public boolean updateEmployeeAddress(String address, long id) {
		try {
			final String UPDATE_EMPLOYEE_ADDRESS_SQL = "update EMPLOYEE  set empAddress = :empAddress where EMPLOYEEID = :EMPLOYEEID";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("EMPLOYEEADDRESS", address);
			parameters.put("EMPLOYEEID", id);
			namedParameterJdbcTemplate.update(UPDATE_EMPLOYEE_ADDRESS_SQL, parameters);
			return true;

		} catch (Exception e) {
			System.out.println("Error During Update Address...");
		}
		return false;
	}

	public List<Employee> getEmployee() {

		final String GET_ALL_EMPLOYEE = "select * from EMPLOYEE ORDER BY EMPLOYEEID ASC";
		try {

			List<Employee> employeeList = jdbcTemplate.query(GET_ALL_EMPLOYEE, new EmployeeRowMapper());
			return employeeList;
			
		} catch (Exception e) {
			System.out.println("Error In Showing Details");
			return null;
		}
		

	}

}
