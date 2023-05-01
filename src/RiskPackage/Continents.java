package RiskPackage;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Continents extends JPanel {

	private Color color;
	private String name;
	public boolean isIncluded;
	private int width;
	private int height;
	private ArrayList<Territory> territories;

	public Continents(String name,ArrayList<Territory> territories, int width, int height, Color color) {

		this.color = color;
		this.name = name;
		this.width = width;
		this.height = height;
		this.isIncluded = true;
		this.territories = territories;
		this.setBackground(color);
		this.setLayout(new BorderLayout());
		
		JLabel nameLabel = new JLabel(name);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(nameLabel, BorderLayout.NORTH);
		
		this.setSize(width,height);
		for (Territory terr : territories) {
			
			this.add(terr);
		}
		this.setColor(color);
		this.setOpaque(true);
        this.setFocusable(true);
        this.setEnabled(true);
        this.setVisible(true);

		this.addMouseListener(new MouseAdapter() {
				private Color oldColor;
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("continent clicked!");
					JOptionPane.showMessageDialog(null, "continent"+ Continents.this.getLocation()+"w"+Continents.this.width
					+"h"+Continents.this.height);
					Continents.this.setColor(Color.GRAY);
					
					Continents.this.isIncluded = false;
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// oldColor = getColor();
					// setColor(Color.RED);
					// repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// setColor(oldColor);
					// repaint();
				}
			});
		
		
		
	}

	
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
		this.setBackground(color);
	}

	public ArrayList<Territory> getTerritories() {
	   	return territories;
	}

		public void addTerritory(Territory territory) {
	    territories.add(territory);
				
	 }

}
