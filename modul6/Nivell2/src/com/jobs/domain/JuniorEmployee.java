package com.jobs.domain;

public class JuniorEmployee extends Employee {

	public JuniorEmployee(String name, String address, String phone, double salaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, salaryPerMonth, paymentRate);
		if (salaryPerMonth <= 900 || salaryPerMonth >= 1600) {
			throw new Exception("Un Junior ha de cobrar més de 900 pero menys de 1600€");
		}
	}

}
