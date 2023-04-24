package RiskPackage;

import java.awt.Polygon;

import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Territory extends JPanel implements MouseListener {
	 private String name;
	    private int xCoordinate;
	    private int yCoordinate;
	    private int width;
	    private int height;
	    private Color color;
	    private ArrayList<Territory> neighbors;
	    //private Army army;

	 

	    
	    public Territory(int xCoordinate, int yCoordinate,int width,int height, String name, Color color) {
	        this.setLayout(null);
	        this.xCoordinate = xCoordinate;
	        this.yCoordinate = yCoordinate;
	        this.width = width;
	        this.height = height;
	        this.setBounds(xCoordinate, yCoordinate, width, height);
	        
	        this.setColor(color);
	        this.name = name;
	        this.color = color;
	        this.neighbors = new ArrayList<Territory>();
	        this.setOpaque(false);
	        addMouseListener(this);
	        this.setName(name);
	        	
	        
	    }
	    
	    
	    
	    
	    public int getWidth() {
			return width;
		}




		public void setWidth(int width) {
			this.width = width;
		}




		public int getHeight() {
			return height;
		}




		public void setHeight(int height) {
			this.height = height;
		}




		@Override
	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);

	       
	            // Draw the territory
	            g.setColor(this.getColor());
	            g.fillOval(this.getXCoordinate(), this.getYCoordinate(), this.getWidth(), this.getHeight());

	            // Draw the territory name
	            g.setColor(Color.BLACK);
	            g.drawString(this.getName(), this.getBounds().x + this.getBounds().width / 2, this.getBounds().y + this.getBounds().height / 2);
	            
	            
	            
	        
	    }

		public int getxCoordinate() {
			return xCoordinate;
		}




		public void setxCoordinate(int xCoordinate) {
			this.xCoordinate = xCoordinate;
		}




		public int getyCoordinate() {
			return yCoordinate;
		}




		public void setyCoordinate(int yCoordinate) {
			this.yCoordinate = yCoordinate;
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
		// public Army getArmies() {
		  //      return this.army;
		    //}

		 //public void setArmies(Army army) {
		//	 this.army = army;
		  //  }




		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}




		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}




		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}




		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}




		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		


}
