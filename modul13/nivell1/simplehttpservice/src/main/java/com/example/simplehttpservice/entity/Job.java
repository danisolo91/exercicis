package com.example.simplehttpservice.entity;

import java.math.BigDecimal;

public enum Job {
	SOFTWARE_ENGINEER("Software Engineer", BigDecimal.valueOf(45000)),
	DATA_SCIENTIST("Data Scientist", BigDecimal.valueOf(47000)), 
	QA_TESTER("QA Tester", BigDecimal.valueOf(30000)),
	SUPPORT_SPECIALIST("Support Specialist", BigDecimal.valueOf(35000)),
	WEB_DEVELOPER("Web Developer", BigDecimal.valueOf(34000)),
	WEB_ADMINISTRATOR("Web Administrator", BigDecimal.valueOf(32000)),
	IT_TECHNICIAN("IT Technician", BigDecimal.valueOf(36000)), 
	UX_DESIGNER("UX Designer", BigDecimal.valueOf(31000)),
	DATABASE_ADMINISTRATOR("Database Administrator", BigDecimal.valueOf(40000)),
	IT_DIRECTOR("IT Director", BigDecimal.valueOf(67000)),
	CLOUD_SYSTEM_ENGINEER("Cloud System Engineer", BigDecimal.valueOf(55000));

	private String jobTitle;
	private BigDecimal salary;

	private Job(String jobTitle, BigDecimal salary) {
		this.jobTitle = jobTitle;
		this.salary = salary;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
}
