package com.jobs.domain;

public class JuniorEmployee extends Employee {

	private final static double IRPF = 2;

	public JuniorEmployee(String name, String address, String phone, double grossSalaryPerMonth,
			IPaymentRate paymentRate) throws Exception {
		super(name, address, phone, grossSalaryPerMonth, paymentRate);
		if (grossSalaryPerMonth <= 900 || grossSalaryPerMonth >= 1600) {
			throw new Exception("Un Junior ha de cobrar més de 900 pero menys de 1600€");
		}
		grossSalaryPerYear = grossSalaryPerMonth * PAYMENTS;
		netSalaryPerMonth = grossSalaryPerMonth - (grossSalaryPerMonth * (IRPF / 100));
		netSalaryPerYear = grossSalaryPerYear - (grossSalaryPerYear * (IRPF / 100));
	}

}
