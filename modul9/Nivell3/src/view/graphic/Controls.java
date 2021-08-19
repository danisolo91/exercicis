package view.graphic;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Coet;

public class Controls extends JPanel {

	private static final long serialVersionUID = 7174635941983566411L;
	private JLabel etiqueta1;
	private JLabel etiqueta2;
	private JLabel etiqueta3;
	private JTextField velocitatActual;
	private JTextField velocitatObjectiu;
	private JTextField pas;
	private JButton boto;

	public Controls(Coet coet, Pantalla pantalla) {
		etiqueta1 = new JLabel("Velocitat actual: ");
		etiqueta2 = new JLabel("Velocitat objectiu: ");
		etiqueta3 = new JLabel("Pas: ");
		velocitatActual = new JTextField(5);
		velocitatObjectiu = new JTextField(5);
		pas = new JTextField("1", 3);
		boto = new JButton("Cambia velocitat");

		boto.addActionListener(new CambiaVelocitatListener(velocitatActual, velocitatObjectiu, pantalla, coet, pas));

		add(etiqueta1);
		add(velocitatActual);
		add(etiqueta2);
		add(velocitatObjectiu);
		add(etiqueta3);
		add(pas);
		add(boto);

		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
	}

}
