package dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

		return Employee.createEmployeeDetails(rs.getLong(1), rs.getString(2), rs.getInt(3), rs.getString(4));
	
	}

}
