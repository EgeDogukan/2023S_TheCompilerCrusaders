package RiskPackage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Territory extends JPanel {
	 private String name;
	    private int xCoordinate;
	    private int yCoordinate;
	    private int width;
	    private int height;
	    private Color color;
	    private ArrayList<Territory> neighbors;
	    
	    public Territory(int xCoordinate, int yCoordinate,int width,int height, String name, Color color) {
	    	this.setVisible(true);
	        this.xCoordinate = xCoordinate;
	        this.yCoordinate = yCoordinate;
	        this.width = width;
	        this.height = height;
	        
	        this.setPreferredSize(new Dimension(width,height));
			this.setLocation(xCoordinate, yCoordinate);
			
	        this.setBackground(color);
	        this.setColor(color);
	        this.name = name;
	        this.color = color;
	        this.neighbors = new ArrayList<Territory>();
	        
	        //this.setOpaque(false);
	        this.setName(name);
	        
			this.addMouseListener(new MouseAdapter() {
				private Color oldColor;
				@Override
				public void mouseClicked(MouseEvent e) {
					
					JOptionPane.showMessageDialog(null, "successfully clicked");
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					oldColor = getColor();
					setColor(Color.RED);
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					setColor(oldColor);
					repaint();
				}
			});        
	    }
	    
	    
		
		@Override
	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	        g.setColor(this.getColor());
	        g.fillRect(this.xCoordinate, this.yCoordinate, this.width, this.height);
	        g.setColor(Color.BLACK);
	        g.drawString(this.name, this.xCoordinate, this.yCoordinate); 
	    }
	    
	    public int getWidth() {
			return this.width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return this.height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getxCoordinate() {
			return this.xCoordinate;
		}

		public void setxCoordinate(int xCoordinate) {
			this.xCoordinate = xCoordinate;
		}

		public int getyCoordinate() {
			return this.yCoordinate;
		}

		public void setyCoordinate(int yCoordinate) {
			this.yCoordinate = yCoordinate;
		}

	    public String getName() {
	        return this.name;
	    }
		   
	    public ArrayList<Territory> getNeighbors() {
	        return this.neighbors;
	    }

	    public void addNeighbor(Territory neighbor) {
	        this.neighbors.add(neighbor);
	    }

		public boolean contains(int x, int y) {
			return super.contains(x,y);
		}
		
		public Color getColor() {
			return this.color;
		}

		public Color setColor(Color color) {
			return this.color = color;
		}
}
