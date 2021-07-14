package com.jobs.view;

import com.jobs.application.JobsController;

public class Main {

	private static JobsController controller = new JobsController();

	public static void main(String[] args) throws Exception {

		controller.createBossEmployee("Pepe Boss", "Dirección molona", "666666666", 8500);
		controller.createManagerEmployee("Pedro Employee", "Dirección molona 2", "665226666", 4500);
		controller.createSeniorEmployee("Pedro Senior", "Dirección molona 2", "665266666", 3000);
		controller.createMidEmployee("Laura Mid", "Dirección molona 3", "625266666", 2000);
		controller.createJuniorEmployee("Cristina Junior", "Dirección molona 3", "625266666", 1000);
		controller.createVolunteer("Juan Volunteer", "Dirección molona 4", "614266666", 0);

		controller.payAllEmployeers();

		String allEmployees = controller.getAllEmployees();

		System.out.println("EMPLOYEES: " + allEmployees + " \n");

	}

}
