package com.vehicles.project;

import java.util.Date;

public class DrivingLicense {

	private int id;
	private String type; // A (motos), B (cotxes), C (camions)
	private String fullname;
	private Date expiryDate;

	private static int COUNTER = 1;

	public DrivingLicense(String type, String fullname, Date expiryDate) throws Exception {
		if (expiryDate.before(new Date()))
			throw new Exception("La llicència de conduir ha d'estar en vigor.");

		this.type = type;
		this.fullname = fullname;
		this.expiryDate = expiryDate;
		id = COUNTER;
		COUNTER++;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
