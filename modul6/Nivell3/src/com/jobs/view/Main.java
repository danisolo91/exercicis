package com.jobs.view;

import com.jobs.application.JobsController;

public class Main {

	private static JobsController controller = new JobsController();

	public static void main(String[] args) throws Exception {

		controller.createBossEmployee("Pepe Boss", "Dirección molona", "666666666", 8500);
		controller.createManagerEmployee("Pedro Manager", "Dirección molona 2", "665226666", 4800);
		controller.createSeniorEmployee("Pedro Senior", "Dirección molona 3", "665266666", 3000);
		controller.createMidEmployee("Laura Mid", "Dirección molona 4", "625266666", 2000);
		controller.createJuniorEmployee("Cristina Junior", "Dirección molona 5", "625266666", 1000);
		controller.createVolunteer("Juan Volunteer", "Dirección molona 6", "614266666", 0);
		controller.createVolunteer("Adrián Volunteer", "Dirección molona 7", "614266666", 0, 230);

		controller.addBonus(); // Opcional

		controller.payAllEmployeers();

		String allEmployees = controller.getAllEmployees();

		System.out.println("EMPLOYEES: " + allEmployees + " \n");

	}

}
