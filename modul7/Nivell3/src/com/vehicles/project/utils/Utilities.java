package com.vehicles.project.utils;

import java.util.Calendar;
import java.util.Date;

public abstract class Utilities {

	/**
	 * Mètode per restar o sumar anys a una data. Si el valor de 'years' está en
	 * positiu es suma a 'date', si està en negatiu resta.
	 * 
	 * @param date
	 * @param years
	 * @return Date
	 */
	public static Date addSubstractYears(Date date, int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}

}
