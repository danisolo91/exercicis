package com.jobs.domain;

public class BossEmployee extends Employee {

	public BossEmployee(String name, String address, String phone, double salaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, salaryPerMonth, paymentRate);
		if (salaryPerMonth <= 8000) {
			throw new Exception("El Boss ha de cobrar més de 8000€ ");
		}
	}

}
