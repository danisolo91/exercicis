package com.jobs.domain;

public class BossEmployee extends Employee {

	private final static double IRPF = 32;

	public BossEmployee(String name, String address, String phone, double grossSalaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, grossSalaryPerMonth, paymentRate);
		if (grossSalaryPerMonth <= 8000) {
			throw new Exception("El Boss ha de cobrar més de 8000€ ");
		}
		grossSalaryPerYear = grossSalaryPerMonth * PAYMENTS;
		netSalaryPerMonth = grossSalaryPerMonth - (grossSalaryPerMonth * (IRPF / 100));
		netSalaryPerYear = grossSalaryPerYear - (grossSalaryPerYear * (IRPF / 100));
	}

}
