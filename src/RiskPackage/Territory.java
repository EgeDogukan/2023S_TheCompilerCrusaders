package RiskPackage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;

import RiskPackage.GameController;
import cardPackage.ArmyCardFactory;
import cardPackage.TerritoryCard;

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
		private int Cnumber;
		private int Anumber;
		private int Inumber;
		private Army armyOnTerritory;
		private boolean isBuilding=false;
	    
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

						JFrame territoryPromptjFrame = new JFrame(Territory.this.getName());
						territoryPromptjFrame.setLayout(null);
						territoryPromptjFrame.setVisible(true);
						territoryPromptjFrame.setPreferredSize(new Dimension(300, 300
						));
						territoryPromptjFrame.setLocationRelativeTo(null);
						JPanel territoryPromptJPanel = new JPanel();
						territoryPromptJPanel.setBackground(Color.GREEN);
						territoryPromptJPanel.setSize(500, 500);
						
						//
						territoryPromptJPanel.setOpaque(true);
	        			territoryPromptJPanel.setFocusable(true);
	        			territoryPromptJPanel.setEnabled(true);
	        			
						JLabel presentArmyJLabel = new JLabel("Present Armies: " + Territory.this.Anumber + " Artillery, " + Territory.this.Cnumber +" Cavalary, "
						+ Territory.this.Inumber +" Infantry.");
						JLabel power = new JLabel("Total power: "+ "\n" + Territory.this.armyOnTerritory.calculateStrength());
						
						String[] terrToAttack = new String[Territory.this.getNeighbors().size()];
						//for(Territory t : Territory.this.getNeighbors()){
						for(int t=0; t< Territory.this.getNeighbors().size();t++){

						JLabel neig = new JLabel(Territory.this.getNeighbors().get(t).getName() + " territory power : " + Territory.this.getNeighbors().get(t).armyOnTerritory.calculateStrength());
						territoryPromptJPanel.add(neig);
						terrToAttack[t]=Territory.this.getNeighbors().get(t).getName();
						System.out.println(terrToAttack[t]);



								
								JLabel ppp = new JLabel("Present Armies: " + Territory.this.Anumber + " Artillery, " + Territory.this.Cnumber +" Cavalary, "
								+ Territory.this.Inumber +" Infantry.");
								territoryPromptjFrame.add(ppp);
								

							}

						presentArmyJLabel.setLocation(500, 500);
						territoryPromptJPanel.add(presentArmyJLabel);
						territoryPromptJPanel.add(power);

						territoryPromptjFrame.add(territoryPromptJPanel);
						territoryPromptjFrame.setContentPane(territoryPromptJPanel);
						//JComboBox<String> cardComboBox = new JComboBox<String>(CardsOfCurrentPlayer);
						JComboBox<String> chooseToAttackBox = new JComboBox<String>(terrToAttack);
						
						territoryPromptjFrame.pack();

						

						

						if(Territory.this.getOwnerID() == GameController.getCurrentTurnPlayerID()) {
							System.out.println("sahip");
							JOptionPane.showMessageDialog(null, Territory.this.getName() + Territory.this.xCoordinate + 
						"y"+Territory.this.yCoordinate + "--------" + Territory.this.getOwnerID() + GameController.getCurrentTurnPlayerID());
						JButton attackButton = new JButton("Attack");
						String name = (String) chooseToAttackBox.getSelectedItem();
						territoryPromptJPanel.add(attackButton);
						attackButton.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								Territory destination = null;
								for(Territory t : Territory.this.getNeighbors()){
									if(name.equals(t.getName())){
										destination = t;
										
										break;
									}
								}
							
								if(destination.armyOnTerritory.calculateStrength() >= Territory.this.armyOnTerritory.calculateStrength()){
									int tempc = Territory.this.Cnumber;
									int tempi = Territory.this.Inumber;
									int tempa = Territory.this.Anumber;
									destination.decreaseArmy(Territory.this);
									
									Territory.this.decreaseArmy(tempa, tempc, tempi);
									Random rant = new Random();
									int i = rant.nextInt(3) + 1;
									int k = rant.nextInt(2);
									if(k == 0) {	
										if(i == 1) {
											Territory.this.increaseArmy(1, 0, 0);
											System.out.println("Artillary army card drawn.");
										}
										else if(i == 2) {
											Territory.this.increaseArmy(0, 1, 0);
											System.out.println("Cavalry army card drawn.");
										}
										else if(i == 3) {
											Territory.this.increaseArmy(0, 0, 1);
											System.out.println("Infantry army card drawn.");
										}
									}
									else if( k == 1) {
										//pick territory card
									}

									
									JLabel ppp = new JLabel("Present Armies: " + Territory.this.Anumber + " Artillery, " + Territory.this.Cnumber +" Cavalary, "
						+ Territory.this.Inumber +" Infantry.");
						territoryPromptjFrame.add(ppp);
						
						setColor(Territory.this.getColor());
						JOptionPane.showMessageDialog(null, "Attack successful!");
						territoryPromptjFrame.dispose();
								}
							}
						});
						territoryPromptJPanel.add(chooseToAttackBox);
						territoryPromptJPanel.setVisible(true);

						territoryPromptjFrame.pack();

						}
						else {
							System.out.println("sahip degil");
							JOptionPane.showMessageDialog(null, Territory.this.getName() + Territory.this.xCoordinate + 
						"y"+Territory.this.yCoordinate);
						}
							
							
						}
						
					
					
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					if (isBuilding){
						oldColor = getColor();
						setColor(Color.RED);
						repaint();
					}
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					if (isBuilding) {
						setColor(oldColor);
						repaint();
					}
					
				}
			});
			
			this.setFocusable(true);
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


}
