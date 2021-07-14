package com.jobs.domain;

public class Employee extends AbsStaffMember {

	private final static int PAYMENTS = 14; // nº de pagaments a l'any
	private final static double BONUS = 0.1;
	private double irpf;
	protected double grossSalaryPerMonth;
	protected double grossSalaryPerYear;
	protected double netSalaryPerMonth;
	protected double netSalaryPerYear;
	protected double bonus;
	protected IPaymentRate paymentRate;

	public Employee(String name, String address, String phone, double grossSalaryPerMonth, IPaymentRate paymentRate,
			double IRPF) throws Exception {
		super(name, address, phone);
		if (grossSalaryPerMonth < 0)
			throw new Exception();
		if (paymentRate == null)
			throw new Exception();

		this.paymentRate = paymentRate;
		this.irpf = IRPF;
		this.grossSalaryPerMonth = grossSalaryPerMonth;
		this.grossSalaryPerYear = grossSalaryPerMonth * PAYMENTS;
		this.netSalaryPerMonth = grossSalaryPerMonth - (grossSalaryPerMonth * (irpf / 100));
		netSalaryPerYear = grossSalaryPerYear - (grossSalaryPerYear * (irpf / 100));
		bonus = 0;
	}

	@Override
	public void addBonus() {
		bonus = grossSalaryPerYear * BONUS;
	}

	@Override
	public void pay() {
		if (bonus > 0) {
			totalPaid = paymentRate.pay(netSalaryPerMonth) + bonus;
		} else {
			totalPaid = paymentRate.pay(netSalaryPerMonth);
		}
	}

	@Override
	public String toString() {
		String result = "\n";
		result += "Emp ";
		result += "[name=" + name + ", ";
		result += "address=" + address + ", ";
		result += "phone=" + phone + ", ";
		result += "grossSalaryPerMonth=" + grossSalaryPerMonth + ", ";
		result += "grossSalaryPerYear=" + grossSalaryPerYear + ", ";
		result += "netSalaryPerMonth=" + netSalaryPerMonth + ", ";
		result += "netSalaryPerYear=" + netSalaryPerYear + ", ";
		if (bonus > 0) {
			result += "bonus=" + bonus + ", ";
		}
		result += "totalP=" + totalPaid + "]";
		return result;
	}

}
