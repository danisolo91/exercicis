package com.jobs.domain;

public class MidEmployee extends Employee {

	private final static int IRPF = 15;

	public MidEmployee(String name, String address, String phone, double grossSalaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, grossSalaryPerMonth, paymentRate, IRPF);
		if (grossSalaryPerMonth <= 1800 || grossSalaryPerMonth >= 2500) {
			throw new Exception("Un Mid ha de cobrar més de 1800 pero menys de 2500€");
		}
	}

}
