package com.jobs.domain;

public class ManagerEmployee extends Employee {

	private final static double IRPF = 26;

	public ManagerEmployee(String name, String address, String phone, double grossSalaryPerMonth,
			IPaymentRate paymentRate) throws Exception {
		super(name, address, phone, grossSalaryPerMonth, paymentRate);
		if (grossSalaryPerMonth <= 3000 || grossSalaryPerMonth >= 5000) {
			throw new Exception("El Manager ha de cobrar més de 3000 però menys de 5000€ ");
		}
		grossSalaryPerYear = grossSalaryPerMonth * PAYMENTS;
		netSalaryPerMonth = grossSalaryPerMonth - (grossSalaryPerMonth * (IRPF / 100));
		netSalaryPerYear = grossSalaryPerYear - (grossSalaryPerYear * (IRPF / 100));
	}

}
