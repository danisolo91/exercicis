package com.example.simplehttpservice.controller;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplehttpservice.entity.Employee;
import com.example.simplehttpservice.entity.Job;
import com.example.simplehttpservice.service.IEmployeeService;

@RestController
@RequestMapping("/v1")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping("/employees")
	public ResponseEntity<?> list(@RequestParam(required = false) String job) {
		if (job != null) {
			return ResponseEntity.ok(employeeService.getEmployeesByJob(Job.valueOf(job)));
		}
		return ResponseEntity.ok(employeeService.getEmployees());
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		Optional<Employee> employee = employeeService.getEmployeeById(id);

		if (employee.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(employee.get());
	}

	@PostMapping("/employees")
	public ResponseEntity<?> add(@RequestBody Employee e) {
		if (e.getName() == null) {
			return ResponseEntity.badRequest().build();
		}

		// Asignem el salari (depén del tipus de feina)
		e.setSalary(e.getJob().getSalary());

		Employee newEmployee = employeeService.addEmployee(e);

		return ResponseEntity.created(URI.create("/employees/" + newEmployee.getId()))
				.body(Collections.singleton("Employee created"));
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<?> update(@RequestBody Employee e, @PathVariable int id) {
		if (e.getName() == null) {
			return ResponseEntity.badRequest().build();
		}

		if (employeeService.getEmployeeById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		// Asignem l'ID del path
		e.setId(id);
		// Asignem el salari (depén del tipus de feina)
		e.setSalary(e.getJob().getSalary());

		employeeService.updateEmployee(e);

		return ResponseEntity.ok(Collections.singleton("Employee updated"));
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		if (employeeService.getEmployeeById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		employeeService.deleteEmployee(id);

		return ResponseEntity.ok(Collections.singleton("Employee deleted"));
	}

}
