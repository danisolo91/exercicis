package com.jobs.domain;

public class ManagerEmployee extends Employee {

	public ManagerEmployee(String name, String address, String phone, double salaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, salaryPerMonth, paymentRate);
		if(salaryPerMonth <= 3000 || salaryPerMonth >= 5000) {
			throw new Exception("El Manager ha de cobrar més de 3000 però menys de 5000€ ");
		}
	}

}
