package view.graphic;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import domain.Coet;
import domain.Propulsor;

public class Pantalla extends JPanel {

	private static final long serialVersionUID = 2039828479508320089L;
	private JTextArea propulsorsTextArea;
	private JScrollPane propulsorsScroll;
	private JLabel missatgeLabel;
	private String propulsors;

	public Pantalla(Coet coet) {
		propulsorsTextArea = new JTextArea(8, 20);
		propulsorsTextArea.setLineWrap(true);
		propulsorsTextArea.setEditable(false);
		propulsorsScroll = new JScrollPane(propulsorsTextArea);
		updatePropulsors(coet);

		missatgeLabel = new JLabel();

		setLayout(new BorderLayout());
		add(propulsorsScroll, BorderLayout.CENTER);
		add(missatgeLabel, BorderLayout.SOUTH);
	}

	public void setMissatge(String missatge) {
		this.missatgeLabel.setText(missatge);
	}

	public synchronized void updatePropulsors(Coet coet) {
		this.propulsors = "";
		List<Propulsor> propulsorsList = coet.getPropulsors();

		for (int i = 0; i < propulsorsList.size(); i++) {
			Propulsor p = propulsorsList.get(i);
			this.propulsors += "Propulsor-" + i + " [ " + p.getPotenciaActual() + " " + p.getPotenciaObjectiu() + " "
					+ p.getPotenciaMaxima() + " ]\n";
		}

		this.propulsorsTextArea.setText(this.propulsors);
	}
}
