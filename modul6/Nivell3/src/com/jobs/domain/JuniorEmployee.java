package com.jobs.domain;

public class JuniorEmployee extends Employee {

	private final static int IRPF = 2;

	public JuniorEmployee(String name, String address, String phone, double grossSalaryPerMonth,
			IPaymentRate paymentRate) throws Exception {
		super(name, address, phone, grossSalaryPerMonth, paymentRate, IRPF);
		if (grossSalaryPerMonth <= 900 || grossSalaryPerMonth >= 1600) {
			throw new Exception("Un Junior ha de cobrar més de 900 pero menys de 1600€");
		}
	}

}
