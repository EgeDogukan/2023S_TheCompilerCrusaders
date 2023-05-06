package RiskPackage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import RiskPackage.GameController;

public class Territory extends JPanel {
	 private String name;
	    private int xCoordinate;
	    private int yCoordinate;
	    private int width;
	    private int height;
	    private Color color;
		private Continents c;
	    private ArrayList<Territory> neighbors;

		private int playerID;

	    
	    public Territory(int xCoordinate, int yCoordinate,int width,int height, String name, Color color, Continents continent, int playerID) {
	    	
	    	this.setVisible(true);
	    	this.playerID = playerID;
	        this.xCoordinate = xCoordinate;
	        this.yCoordinate = yCoordinate;
	        this.width = width;
	        this.height = height;
	        this.c = continent;
	        this.setSize(width, height);
			this.setLocation(xCoordinate, yCoordinate);
			
	        this.setBackground(color);
	        this.setColor(color);
			
	        this.name = name;
	        this.color = color;
	        this.neighbors = new ArrayList<Territory>();
	        
	        
			JLabel nameLabel = new JLabel(this.getName());
			System.out.println(this.getName());
        	nameLabel.setHorizontalAlignment(JLabel.CENTER);
        	this.add(nameLabel, BorderLayout.NORTH);
	        this.setOpaque(true);
	        this.setFocusable(true);
	        this.setEnabled(true);
	        
			this.addMouseListener(new MouseAdapter() {
				private Color oldColor;
				@Override
				public void mouseClicked(MouseEvent e) {

					System.out.println("Panel clicked!");

					JFrame territoryPromptjFrame = new JFrame(Territory.this.getName());
					territoryPromptjFrame.setVisible(true);
					territoryPromptjFrame.setSize(450, 300);
					//territoryPromptjFrame.setLocation(960, 540);
					JPanel territoryPromptJPanel = new JPanel();
					territoryPromptJPanel.setBackground(Color.GREEN);
					//
					
					territoryPromptJPanel.setOpaque(true);
        			territoryPromptJPanel.setFocusable(true);
        			territoryPromptJPanel.setEnabled(true);
        			territoryPromptJPanel.setVisible(true);
					JLabel presentArmyJLabel = new JLabel("Present Armies: ");
					presentArmyJLabel.setLocation(0, 0);
					territoryPromptJPanel.add(presentArmyJLabel);
					territoryPromptjFrame.add(territoryPromptJPanel);
					territoryPromptjFrame.setContentPane(territoryPromptJPanel);
					

					territoryPromptjFrame.pack();
					


					

					if(Territory.this.getOwnerID() == GameController.getCurrentTurnPlayerID()) {
						System.out.println("sahip");
						JOptionPane.showMessageDialog(null, Territory.this.getName() + Territory.this.xCoordinate + 
					"y"+Territory.this.yCoordinate + "--------" + Territory.this.getOwnerID() + GameController.getCurrentTurnPlayerID());
						
						
					}
					else {
						System.out.println("sahip değil");
						JOptionPane.showMessageDialog(null, Territory.this.getName() + Territory.this.xCoordinate + 
					"y"+Territory.this.yCoordinate);
					}
					
					
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
			
			this.setFocusable(true);
	    }
	    
	    
		
		@Override
	    public void paintComponent(Graphics g) {
	    	Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g);
	        g.setColor(this.getColor());
	        g.fillRect(this.xCoordinate, this.yCoordinate, this.width, this.height);
	        g.setColor(Color.BLACK);
	        g.drawString(this.name, this.xCoordinate, this.yCoordinate);
			g2.setColor(Color.BLACK); // Set the color of the line to black
			g2.setStroke(new BasicStroke(2)); 	
			for(Territory n : this.getNeighbors()){
				g2.draw(new Line2D.Double(n.getBounds().x, n.getBounds().y, this.getBounds().x/2, this.getBounds().y/2));
			}
			
			
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

		public void setName(String name) {
			this.name = name;
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

		public void setColor(Color color) {
			this.setBackground(color); 
		}

		public int getOwnerID() {
			return playerID;
		}

		public void setOwnerID(int ID) {
			this.playerID = ID;
		}

		public Continents getContinent() {
			return this.c;
		}
		
}
