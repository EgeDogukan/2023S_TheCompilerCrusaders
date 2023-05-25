package RiskPackage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Random;

import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.plaf.synth.Region;

import RiskPackage.GameController;
import cardPackage.ArmyCardFactory;
import cardPackage.TerritoryCard;
import uipackage.AttackTerrUI;
import uipackage.RunningMode;
import uipackage.WorldMap;

public class Territory extends JPanel {
	 private String name;
	    private int xCoordinate;
	    private int yCoordinate;
	    private int width;
	    private int height;
		private boolean isFirst=true;
	    private Color color;
	    private Continents continent;
		private Continents c;
	    private ArrayList<Territory> neighbors;
		public int playerID;
		public int Cnumber;
		public int Anumber;
		public int Inumber;
		public Army armyOnTerritory;
		private boolean isBuilding=false;
		private boolean winner = false;


		private Region terr_Region;
		private static Shape clickedShape;
	    

	    public Territory(int xCoordinate, int yCoordinate,int width,int height, String name, Color color, Continents continent, int playerID) {
	    	this.isBuilding=false;
	    	this.setVisible(true);
	    	this.playerID = playerID;
			this.Inumber = 0;
			this.Cnumber = 0;
			this.Anumber = 0;
			this.armyOnTerritory = new Army(Cnumber, Anumber, Inumber);
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
					
				
					if (isBuilding) {
						System.out.println("Panel clicked!");

						
					if(GameController.isBuildingMode) {
						if(Territory.this.getOwnerID() == GameController.getCurrentTurnPlayerID()) {
							
							
							
							System.out.println("sahip");
							JOptionPane.showMessageDialog(null, Territory.this.getName() + Territory.this.xCoordinate + 
						"y"+Territory.this.yCoordinate + "--------" + Territory.this.getOwnerID() + GameController.getCurrentTurnPlayerID());
						
								
								AttackTerrUI at = new AttackTerrUI(Territory.this);
								System.out.println("birinci tur");

							
					
						}
					}
					else {
						if(RunningMode.isItFirstTour) {
							if(Territory.this.getOwnerID()+1 == GameController.getCurrentTurnPlayerID()) {
								
								
								
								System.out.println("sahip");
								JOptionPane.showMessageDialog(null, Territory.this.getName() + Territory.this.xCoordinate + 
							"y"+Territory.this.yCoordinate + "--------" + Territory.this.getOwnerID() + GameController.getCurrentTurnPlayerID());
							
									
									AttackTerrUI at = new AttackTerrUI(Territory.this);
									System.out.println("birinci tur");

								
						
							}
						}
						else {
							if(Territory.this.getOwnerID() == GameController.getCurrentTurnPlayerID()) {
								
								
								
								System.out.println("sahip");
								JOptionPane.showMessageDialog(null, Territory.this.getName() + Territory.this.xCoordinate + 
							"y"+Territory.this.yCoordinate + "--------" + Territory.this.getOwnerID() + GameController.getCurrentTurnPlayerID());
							
									
									AttackTerrUI at = new AttackTerrUI(Territory.this);
									System.out.println("birinci tur");

								
						
							}
						}
					}
					
						
					
					
				}
			}//Added after commanding the codes

				@Override
				public void mousePressed(MouseEvent e) {
					/*if (isBuilding){
						oldColor = getColor();
						setColor(Color.RED);
						repaint();
					}*/
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					/*if (isBuilding) {
						setColor(oldColor);
						repaint();
					}*/
					
				}
			});
			
			this.setFocusable(true);
	    }
	    
		private Point getCenter() {
			return new Point(getWidth() / 2, getHeight() / 2);
		}
		
		@Override
	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	        g.setColor(this.getColor());
	        g.fillRect(this.xCoordinate, this.yCoordinate, this.width, this.height);
	        g.setColor(Color.BLACK);
	        g.drawString(this.name, this.xCoordinate, this.yCoordinate); 
			
			

	    }
		public boolean Winner(){
			Territory destination = null;
								for(Territory t : Territory.this.getNeighbors()){
									if(name.equals(t.getName())){
										destination = t;
										
										break;
									}
								}
			
			if(destination.armyOnTerritory.calculateStrength() >= Territory.this.armyOnTerritory.calculateStrength()){
				winner = false;
			}
			else{
				winner = true;
			}
			
			
			return winner;
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

		public String neigName (Territory n){
			return n.getName();
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
		
		public void setIsBuilding(boolean status) {
			this.isBuilding=status;
		}

		public static void setClickedShape(Shape shape) {
			clickedShape = shape;
		}



		public void setArmy(int A, int C, int I) {
			this.armyOnTerritory.setArtillery(A);
			this.Anumber = A;
			this.armyOnTerritory.setCavalry(C);
			this.Cnumber = C;
			this.armyOnTerritory.setInfantry(I);
			this.Inumber = I;

		}

		public void increaseArmy(int A, int C, int I){
			this.armyOnTerritory.setArtillery(A + this.Anumber);
			this.Anumber += A;
			this.armyOnTerritory.setCavalry(C + this.Cnumber);
			this.Cnumber += C;
			this.armyOnTerritory.setInfantry(I + this.Inumber);
			this.Inumber += I;

		}
		
		public void decreaseArmy(int A, int C, int I) {
			this.armyOnTerritory.setArtillery(A - this.Anumber);
			this.Anumber -= A;
			this.armyOnTerritory.setCavalry(C - this.Cnumber);
			this.Cnumber -= C;
			this.armyOnTerritory.setInfantry(I - this.Inumber);
			this.Inumber -= I;
		}

		public void decreaseArmy(Territory destination){
			this.armyOnTerritory.setArtillery(this.Anumber - destination.Anumber);
			this.Anumber -= destination.Anumber;
			this.armyOnTerritory.setCavalry( this.Cnumber - destination.Cnumber);
			this.Cnumber -= destination.Cnumber;
			this.armyOnTerritory.setInfantry(this.Inumber - destination.Inumber);
			this.Inumber -= destination.Inumber;

		}
		
		public ArrayList<String> getList(){
			ArrayList<String> list = new ArrayList<String>();
			list.add(((Integer)xCoordinate).toString());
			list.add(((Integer)yCoordinate).toString());
			list.add(((Integer)width).toString());
			list.add(((Integer)height).toString());
			list.add(name);
			list.add(this.color.toString());
			list.add(c.getName());
			list.add(((Integer)playerID).toString());
			
			return list;
		}

		public int getAArmy(){
			return this.Anumber;
		}		
		public int getCArym(){
			return this.Cnumber;
		}
		public int getIArmy(){
			return this.Inumber;
		}




}
