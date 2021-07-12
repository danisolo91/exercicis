package com.jobs.domain;

public class Volunteer extends AbsStaffMember {

	protected String description;

	public Volunteer(String name, String address, String phone, String description) throws Exception {
		super(name, address, phone);
		this.description = description;
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
		result += "descripció=" + description + ", ";
		result += "totalP=" + totalPaid + "]";
		return result;
	}

}
