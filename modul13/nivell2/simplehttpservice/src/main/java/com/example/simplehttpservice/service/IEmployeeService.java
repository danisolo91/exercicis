package com.example.simplehttpservice.service;

import java.util.List;
import java.util.Optional;

import com.example.simplehttpservice.entity.Employee;
import com.example.simplehttpservice.entity.Job;

public interface IEmployeeService {

	public List<Employee> getEmployees();

	public List<Employee> getEmployeesByJob(Job job);

	public Optional<Employee> getEmployeeById(int id);

	public Employee addEmployee(Employee employee);

	public void updateEmployee(Employee employee);

	public void deleteEmployee(int id);

}
