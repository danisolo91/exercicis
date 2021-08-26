package com.example.simplehttpservice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.simplehttpservice.entity.Employee;
import com.example.simplehttpservice.entity.Job;

@Repository
public class EmployeeRepository {

	private Connection connection;

	public EmployeeRepository() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String context = "jdbc:mysql://localhost:3306/modul13";
			this.connection = DriverManager.getConnection(context, "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();

		try {
			Statement s = connection.createStatement();
			String sql = "SELECT * FROM employees";
			s.execute(sql);
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				employees.add(new Employee(rs.getInt(1), rs.getString(2), Enum.valueOf(Job.class, rs.getString(3))));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return employees;
	}

	public List<Employee> getEmployeesByJob(Job job) {
		List<Employee> employees = new ArrayList<Employee>();

		try {
			Statement s = connection.createStatement();
			String sql = "SELECT * FROM employees WHERE job LIKE '" + job.toString() + "'";
			s.execute(sql);
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				employees.add(new Employee(rs.getInt(1), rs.getString(2), Enum.valueOf(Job.class, rs.getString(3))));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return employees;
	}

	public Optional<Employee> getEmployeeById(int id) {
		Employee employee = null;

		try {
			Statement s = connection.createStatement();
			String sql = "SELECT * FROM employees WHERE id=" + id;
			s.execute(sql);
			ResultSet rs = s.getResultSet();
			rs.next();
			employee = new Employee(rs.getInt(1), rs.getString(2), Enum.valueOf(Job.class, rs.getString(3)));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return Optional.of(employee);
	}

	public Employee addEmployee(Employee employee) {
		String query = "INSERT INTO employees (name, job, salary) values (?, ?, ?)";

		try {
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getJob().toString());
			statement.setBigDecimal(3, employee.getJob().getSalary());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return employee;
	}

	public void updateEmployee(Employee employee) {
		String query = "UPDATE employees SET name=?, job=?, salary=? WHERE id=?";

		try {
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getJob().toString());
			statement.setBigDecimal(3, employee.getJob().getSalary());
			statement.setInt(4, employee.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteEmployee(int id) {
		String query = "DELETE FROM employees WHERE id=" + id;

		try {
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
