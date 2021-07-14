package com.jobs.domain;

public class SeniorEmployee extends Employee {

	public SeniorEmployee(String name, String address, String phone, double salaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, salaryPerMonth, paymentRate);
		if (salaryPerMonth <= 2700 || salaryPerMonth >= 4000) {
			throw new Exception("Un Senior ha de cobrar més de 2700 pero menys de 4000€");
		}
	}

}
