package com.example.simplehttpservice.entity;

import java.math.BigDecimal;

public class Employee {

	private int id;
	private String name;
	private Job job;
	private BigDecimal salary;

	public Employee(int id, String name, Job job) {
		this.id = id;
		this.name = name;
		this.job = job;
		this.salary = job.getSalary();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	
}
