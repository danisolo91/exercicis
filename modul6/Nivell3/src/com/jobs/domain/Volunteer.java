package com.jobs.domain;

public class Volunteer extends AbsStaffMember {

	protected double salaryPerMonth;
	protected double governmentAid;

	public Volunteer(String name, String address, String phone, double salaryPerMonth) throws Exception {
		super(name, address, phone);
		if (salaryPerMonth < 0)
			throw new Exception();
		if (salaryPerMonth > 0)
			throw new Exception("El Volunteer no cobra!");
		this.salaryPerMonth = salaryPerMonth;
		this.governmentAid = 0;
	}

	public Volunteer(String name, String address, String phone, double salaryPerMonth, double governmentAid)
			throws Exception {
		super(name, address, phone);
		if (salaryPerMonth < 0)
			throw new Exception();
		if (governmentAid < 0)
			throw new Exception();
		if (salaryPerMonth > 0)
			throw new Exception("El Volunteer no cobra!");
		if (governmentAid > 300)
			throw new Exception("L'ajuda guvernamental ha de ser fins a 300€!");
		this.salaryPerMonth = salaryPerMonth;
		this.governmentAid = governmentAid;
	}

	@Override
	public void pay() {
		if (governmentAid > 0) {
			totalPaid = governmentAid;
		} else {
			totalPaid = 0;
		}
	}

	@Override
	public void addBonus() {
		// Els voluntaris no reben bonus
	}

	@Override
	public String toString() {
		String result = "\n";
		result += "Vol ";
		result += "[name=" + name + ", ";
		result += "address=" + address + ", ";
		result += "phone=" + phone + ", ";
		result += "salaryPerMonth=" + salaryPerMonth + ", ";
		if (governmentAid > 0) {
			result += "governmentAid=" + governmentAid + ", ";
		}
		result += "totalP=" + totalPaid + "]";
		return result;
	}

}
