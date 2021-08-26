package com.example.simplehttpservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplehttpservice.entity.Employee;
import com.example.simplehttpservice.entity.Job;
import com.example.simplehttpservice.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.getEmployees();
	}

	@Override
	public Optional<Employee> getEmployeeById(int id) {
		return employeeRepository.getEmployeeById(id);
	}
	
	@Override
	public List<Employee> getEmployeesByJob(Job job) {
		return employeeRepository.getEmployeesByJob(job);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.addEmployee(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.updateEmployee(employee);
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.deleteEmployee(id);
	}

}
