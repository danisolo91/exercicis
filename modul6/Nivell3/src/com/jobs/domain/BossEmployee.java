package com.jobs.domain;

public class BossEmployee extends Employee {

	private final static int IRPF = 32;

	public BossEmployee(String name, String address, String phone, double grossSalaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, grossSalaryPerMonth, paymentRate, IRPF);
		if (grossSalaryPerMonth <= 8000) {
			throw new Exception("El Boss ha de cobrar més de 8000€ ");
		}
	}

}
