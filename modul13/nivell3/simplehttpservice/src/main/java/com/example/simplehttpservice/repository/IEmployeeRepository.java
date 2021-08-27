package com.example.simplehttpservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simplehttpservice.entity.Employee;
import com.example.simplehttpservice.entity.Job;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

	public List<Employee> findByJob(Job job);
	
}
