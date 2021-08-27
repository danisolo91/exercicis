package com.example.simplehttpservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplehttpservice.entity.Employee;
import com.example.simplehttpservice.entity.Job;
import com.example.simplehttpservice.repository.IEmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(int id) {
		return employeeRepository.findById(id);
	}

	@Override
	public List<Employee> getEmployeesByJob(Job job) {
		return employeeRepository.findByJob(job);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}

}
