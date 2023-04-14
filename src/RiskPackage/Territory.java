package RiskPackage;

import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Territory extends Polygon {
	 private String name;
	    private int xCoordinate;
	    private int yCoordinate;
	    private Color color;
	    
	    private ArrayList<Territory> neighbors;
	    private Army army;
	    
	    public Territory(int[] xpoints, int[] ypoints, int npoints, String name, Color color) {
	        super(xpoints, ypoints, npoints);
	        this.name = name;
	        this.color = color;
	        this.neighbors = new ArrayList<Territory>();
	        
	  
	        
	    }
	  

		public String getName() {
	        return name;
	    }

	    public int getXCoordinate() {
	        return xCoordinate;
	    }

	    public int getYCoordinate() {
	        return yCoordinate;
	    }

	    public ArrayList<Territory> getNeighbors() {
	        return neighbors;
	    }

	    public void addNeighbor(Territory neighbor) {
	        neighbors.add(neighbor);
	    }

		public boolean contains(int x, int y) {
			return super.contains(x,y);
		}
		
		public Color getColor() {
			
			return color;
		}
		public Color setColor(Color color) {
			return this.color = color;
		}
		 public Army getArmies() {
		        return this.army;
		    }

		 public void setArmies(Army army) {
			 this.army = army;
		    }


}
