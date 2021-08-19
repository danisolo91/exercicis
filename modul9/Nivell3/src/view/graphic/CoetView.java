package view.graphic;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import domain.Coet;

public class CoetView {
	private JFrame marc;
	private Pantalla pantalla;
	private Controls controls;
	private static int counter = 1;

	public CoetView(Coet coet) {

		marc = new JFrame();

		marc.setBounds(200, 100 * counter, 670, 200); // coord, size
		marc.setTitle("Panell de control del coet " + coet.getId());
		marc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		marc.setVisible(true);

		pantalla = new Pantalla(coet);
		controls = new Controls(coet, pantalla);

		marc.add(pantalla, BorderLayout.CENTER);
		marc.add(controls, BorderLayout.SOUTH);

		counter++;
	}
}
