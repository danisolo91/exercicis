package view.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import controller.CoetController;
import domain.Coet;

public class CambiaVelocitatListener implements ActionListener {

	private Coet coet;
	private JTextField velocitatActual;
	private JTextField velocitatObjectiu;
	private JTextField pas;
	private Pantalla pantalla;
	private CoetController coetController;

	public CambiaVelocitatListener(JTextField velocitatActual, JTextField velocitatObjectiu, Pantalla pantalla,
			Coet coet, JTextField pas) {
		this.coet = coet;
		this.velocitatActual = velocitatActual;
		this.velocitatObjectiu = velocitatObjectiu;
		this.pantalla = pantalla;
		this.pas = pas;

		coetController = new CoetController();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pantalla.setMissatge(null);
		try {
			int vActual = Integer.parseInt(velocitatActual.getText());
			int vObjectiu = Integer.parseInt(velocitatObjectiu.getText());
			int vPas = Integer.parseInt(pas.getText());

			if (vPas <= 0)
				throw new Exception("--> El pas no pot ser negatiu <--");

			coetController.assolirVelocitat(vActual, vObjectiu, coet, pantalla, vPas);
		} catch (NumberFormatException nfe) {
			pantalla.setMissatge("--> Has d'introdïr nombres enters <--");
		} catch (Exception ex) {
			pantalla.setMissatge(ex.getMessage());
		}
	}

}
