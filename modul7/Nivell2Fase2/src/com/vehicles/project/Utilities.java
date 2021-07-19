package com.vehicles.project;

import java.util.Calendar;
import java.util.Date;

public abstract class Utilities {

	/**
	 * Mètode per restar anys a una data
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addSubstractYears(Date date, int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}

}
