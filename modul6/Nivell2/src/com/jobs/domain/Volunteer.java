package com.jobs.domain;

public class Volunteer extends AbsStaffMember {

	protected double salaryPerMonth;

	public Volunteer(String name, String address, String phone, double salaryPerMonth) throws Exception {
		super(name, address, phone);
		if (salaryPerMonth < 0)
			throw new Exception();
		if (salaryPerMonth > 0)
			throw new Exception("El Volunteer no cobra!");
		this.salaryPerMonth = salaryPerMonth;
	}

	@Override
	public void pay() {
		totalPaid = 0;
	}

	@Override
	public String toString() {
		String result = "\n";
		result += "Vol ";
		result += "[name=" + name + ", ";
		result += "address=" + address + ", ";
		result += "phone=" + phone + ", ";
		result += "salaryPerMonth=" + salaryPerMonth + ", ";
		result += "totalP=" + totalPaid + "]";
		return result;
	}

}
