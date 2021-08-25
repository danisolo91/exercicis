package com.example.simplehttpservice.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.simplehttpservice.entity.Employee;
import com.example.simplehttpservice.entity.Job;

@Repository
public class EmployeeRepository {

	private static int counter = 0;
	private List<Employee> employees = new ArrayList<Employee>();

	public List<Employee> getEmployees() {
		return employees;
	}
	
	public List<Employee> getEmployeesByJob(Job job) {
		return employees.stream().filter(e -> e.getJob().equals(job)).collect(Collectors.toList());
	}

	public Optional<Employee> getEmployeeById(int id) {
		return employees.stream().filter(e -> e.getId() == id).findFirst();
	}

	public Employee addEmployee(Employee employee) {
		counter++;
		employee.setId(counter);
		employees.add(employee);
		return employee;
	}

	public void updateEmployee(Employee employee) {
		Iterator<Employee> it = employees.iterator();
		while (it.hasNext()) {
			Employee e = it.next();
			if (e.getId() == employee.getId()) {
				e.setName(employee.getName());
				e.setJob(employee.getJob());
				e.setSalary(employee.getJob().getSalary());
				break;
			}
		}
	}

	public void deleteEmployee(int id) {
		Iterator<Employee> it = employees.iterator();
		while (it.hasNext()) {
			Employee e = it.next();
			if (e.getId() == id) {
				it.remove();
				break;
			}
		}
	}
}
