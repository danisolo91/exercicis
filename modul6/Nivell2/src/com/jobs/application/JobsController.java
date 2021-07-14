package com.jobs.application;

import com.jobs.domain.AbsStaffMember;
import com.jobs.domain.BossEmployee;
import com.jobs.domain.JuniorEmployee;
import com.jobs.domain.ManagerEmployee;
import com.jobs.domain.MidEmployee;
import com.jobs.domain.SeniorEmployee;
import com.jobs.domain.Volunteer;
import com.jobs.persistence.EmployeeRepository;

public class JobsController {

	private EmployeeRepository repository;

	public JobsController() {
		this.repository = new EmployeeRepository();
	}

	public void createBossEmployee(String name, String address, String phone, double salaryPerMonth) throws Exception {
		BossEmployee boss = new BossEmployee(name, address, phone, salaryPerMonth,
				PaymentFactory.createPaymentRateBoss());
		repository.addMember(boss);
	}

	public void createManagerEmployee(String name, String address, String phone, double salaryPerMonth)
			throws Exception {
		ManagerEmployee manager = new ManagerEmployee(name, address, phone, salaryPerMonth,
				PaymentFactory.createPaymentRateManager());
		repository.addMember(manager);
	}

	public void createSeniorEmployee(String name, String address, String phone, double salaryPerMonth)
			throws Exception {
		SeniorEmployee employee = new SeniorEmployee(name, address, phone, salaryPerMonth,
				PaymentFactory.createPaymentRateSeniorEmployee());
		repository.addMember(employee);
	}

	public void createMidEmployee(String name, String address, String phone, double salaryPerMonth) throws Exception {
		MidEmployee employee = new MidEmployee(name, address, phone, salaryPerMonth,
				PaymentFactory.createPaymentRateMidEmployee());
		repository.addMember(employee);
	}

	public void createJuniorEmployee(String name, String address, String phone, double salaryPerMonth)
			throws Exception {
		JuniorEmployee employee = new JuniorEmployee(name, address, phone, salaryPerMonth,
				PaymentFactory.createPaymentRateJuniorEmployee());
		repository.addMember(employee);
	}

	public void createVolunteer(String name, String address, String phone, double salaryPerMonth) throws Exception {
		Volunteer volunteer = new Volunteer(name, address, phone, salaryPerMonth);
		repository.addMember(volunteer);
	}

	public void payAllEmployeers() {
		for (AbsStaffMember member : repository.getAllMembers()) {
			member.pay();
		}
	}

	public String getAllEmployees() {
		return repository.getAllMembers().toString();
	}

}
