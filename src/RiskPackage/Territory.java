package RiskPackage;

import java.awt.Polygon;
import java.awt.Color;
import java.util.ArrayList;

public class Territory extends Polygon {
	 private String name;
	    private int xCoordinate;
	    private int yCoordinate;
	    private Color color;
	    private ArrayList<Territory> neighbors;
	    private int armies;
	    public Territory(int[] xpoints, int[] ypoints, int npoints, String name, Color color, int initialArmies) {
	        super(xpoints, ypoints, npoints);
	        this.name = name;
	        this.color = color;
	        this.neighbors = new ArrayList<Territory>();
	        this.armies = initialArmies;
	    }

	    public String getName() {
	        return name ;
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
		 public int getArmies() {
		        return armies;
		    }

		 public void setArmies(int armies) {
			 this.armies = armies;
		    }

		public void removeArmy() {
			
			this.armies--;
		}

		

}
