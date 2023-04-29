package RiskPackage;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Continents extends JPanel {

	public Continents(ArrayList<Territory> territories, int width, int height) {
		
		this.setLayout(new BorderLayout());
		
		this.setSize(width,height);
		for (Territory territory : territories) {
			this.add(territory);
		}
		this.setOpaque(true);
        this.setFocusable(true);
        this.setEnabled(true);
        this.setVisible(true);
		
		
		
		
	}

}
