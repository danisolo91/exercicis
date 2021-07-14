package com.jobs.domain;

public class MidEmployee extends Employee {

	public MidEmployee(String name, String address, String phone, double salaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, salaryPerMonth, paymentRate);
		if (salaryPerMonth <= 1800 || salaryPerMonth >= 2500) {
			throw new Exception("Un Mid ha de cobrar més de 1800 pero menys de 2500€");
		}
	}

}
