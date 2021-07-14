package com.jobs.domain;

public class SeniorEmployee extends Employee {

	private final static int IRPF = 24;

	public SeniorEmployee(String name, String address, String phone, double grossSalaryPerMonth,
			IPaymentRate paymentRate) throws Exception {
		super(name, address, phone, grossSalaryPerMonth, paymentRate, IRPF);
		if (grossSalaryPerMonth <= 2700 || grossSalaryPerMonth >= 4000) {
			throw new Exception("Un Senior ha de cobrar més de 2700 pero menys de 4000€");
		}
	}

}
