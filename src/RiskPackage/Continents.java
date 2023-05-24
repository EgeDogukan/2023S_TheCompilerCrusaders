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
	public boolean isBuilding;
	private int width;
	private int height;
	private ArrayList<Territory> territories;

	public Continents(String name,ArrayList<Territory> territories, int width, int height, Color color) {

		this.color = color;
		this.name = name;
		this.width = width;
		this.height = height;
		this.isIncluded = true;
		this.isBuilding = false;
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
				private Color oldColor = getColor();
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("continent clicked!");
					JOptionPane.showMessageDialog(null, "continent"+ Continents.this.getLocation()+"w"+Continents.this.width
					+"h"+Continents.this.height);

					if(Continents.this.isIncluded == true) {
						Continents.this.setColor(Color.GRAY);
						repaint();
						Continents.this.isIncluded = false;
						Continents.this.isBuilding = true;
						
					} 
					else {
						Continents.this.setColor(oldColor);
						Continents.this.isIncluded = true;
						Continents.this.isBuilding = false;
					}
					
					
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

	
	public Continents() {
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		if (this.equals(null)) {
			return "tr";
		}
		else {
			return this.name;
		}
	}


	public void setName(String name) {
		this.name = name;
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
