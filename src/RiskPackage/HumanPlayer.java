package RiskPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

public class HumanPlayer extends Player{

	public HumanPlayer(int id, Color color, String name, ArrayList<Territory> territories) {
		super(id, color, name, territories);
		JLabel nameLabel = new JLabel("aaa");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
		this.territories = territories;
		System.out.println("FROM HUMAN");
	}
	
	

}
