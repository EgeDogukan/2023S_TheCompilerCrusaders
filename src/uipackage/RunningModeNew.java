package uipackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.xml.namespace.QName;

import java.awt.*;
import uipackage.WorldMap;
import javax.imageio.ImageIO;

import RiskPackage.GameControllerNew;
import RiskPackage.PlayerNew;
import animationPackage.CardAnimationClass;
import armyCardPackage.ArmyCardFactory;
import armyCardPackage.IArmyCard;
import armyCardPackage.InfantryArmyCard;
import chanceCardPackage.ChanceCardFactory;
import databasePackage.ISaveLoadAdapter;
import databasePackage.TerritoryDBDatabase;
import databasePackage.TerritoryJSONDBDatabase;
import territoryCardPackage.TerritoryCard;


public class RunningModeNew extends JFrame {


    public static ArrayList<PlayerNew> players;
	private static JButton turn = new JButton();
	int numberOfAIPlayer;
	int numberOfHumanPlayer;
    static int turnCounter=0;
	public static boolean isContinue = true;
	public static int databaseChooser=0;
    public ArrayList<Shape> shapelist = new ArrayList<>();
	public static boolean isInBuildingMode = true;

	private int counter = 0;
	public static JLabel stage = new JLabel("Deploy");
	public static String whichStage= new String();
	
	public static WorldMap worldMap;
	
	public CardAnimationClass cardAnimationClass;
	
	public static ArrayList<IArmyCard> armyCards = new ArrayList<>();
	public ArmyCardFactory armyCardFactory = new ArmyCardFactory();
	
	public static ArrayList<TerritoryCard> territoryCards = new ArrayList<>();
	public static boolean isGameOver=false;
	//JPanel panel = new JPanel();



	public RunningModeNew(ArrayList<PlayerNew> players, WorldMap worldMap, int numberOfCompPlayer) {
		this.players=players;
		numberOfHumanPlayer=players.size()-numberOfCompPlayer;
		numberOfAIPlayer=numberOfCompPlayer;
		this.worldMap=worldMap;
		this.initializeArmyCards();
		this.initializeTerritoryCards();
		this.initializeTerritoryCards();
		initGame(players);
	}
	

	
	public void initializeArmyCards() {
		for (int i=0;i<players.size();i++) {
			
			for (int j=0;j<3;j++) {armyCards.add(armyCardFactory.createArmyCard(0));}
			for (int j=0;j<2;j++) {armyCards.add(armyCardFactory.createArmyCard(1));}
			for (int j=0;j<1;j++) {armyCards.add(armyCardFactory.createArmyCard(2));}
		}
	}
	
	public void initializeTerritoryCards() {
		for (Shape shape : worldMap.shapeList) {
			territoryCards.add(new TerritoryCard(worldMap.getShapeIndex(shape)));
		}
	}
	

    public static int getTurn() {
		return RunningModeNew.turnCounter;
	}
	public Color getTurnColor(){
		return this.players.get(getTurn()-1).getColor();
	}

	
	public Color getPlayer(int id){
		return this.players.get(id).getColor();
	}
	

	public ArrayList<PlayerNew> getPlayer(){
		return players;
	}
	
	public ArrayList<PlayerNew> getPlayers() {
		return this.players;
	}
	
	public Boolean getIsInBuildingMode() {
		return isInBuildingMode;
	}



	public void initGame(ArrayList<PlayerNew> players){
		
		JLayeredPane layeredPane = new JLayeredPane();

		String cwd = System.getProperty("user.dir");
		ImageIcon imageIcon = new ImageIcon(cwd + "/src/uipackage/runningMode.png");
        JLabel panel = new JLabel(imageIcon);
        panel.setBounds(-160, -90, 1920, 1080);
		
        //layeredPane.add(panel, 0);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1920, 1080));
		this.setLayout(null); // Use BorderLayout for the JFrame
	
		
		shapelist=this.worldMap.getShapeList();
		ArrayList<JTextField> textLabels = worldMap.getTextLabels();
		for (JTextField curField : textLabels) {
			panel.add(curField);
		}

		this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setUndecorated(true); 

		//panel.setBounds(0, 0, 1920, 1080);
		
		JPanel worldPanel = (JPanel) worldMap.getUI();
		worldPanel.setBounds(450, 200, worldPanel.getPreferredSize().width, worldPanel.getPreferredSize().height);
		panel.add(worldPanel); // Add the map panel to the center of the JFrame
		
		
		JButton saveButtonMongo = new JButton("SAVE MONGO");
		saveButtonMongo.setBounds(1500, 500, 150, 50);
		saveButtonMongo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TerritoryDBDatabase database = new TerritoryDBDatabase();
				try {
					database.empty();
					database.saveAll();
					System.out.println("MONGO IS SAVED");
					RunningModeNew.this.dispose();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		panel.add(saveButtonMongo);
		
		
		JButton saveButtonJSON = new JButton("SAVE JSON");
		saveButtonJSON.setBounds(1500, 600, 150, 50);
		saveButtonJSON.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TerritoryJSONDBDatabase database = new TerritoryJSONDBDatabase();
				try {
					database.empty();
					database.saveAll();
					System.out.println("JSON IS SAVED");
					RunningModeNew.this.dispose();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		panel.add(saveButtonJSON);

		JButton pickArmyCard = new JButton("trade army card");
		pickArmyCard.setBounds(1300, 750, 150, 50);
		pickArmyCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame attackFrame = new JFrame("attack");
				JPanel attackPanel = new JPanel();

				attackFrame.setSize(500, 100);
				attackFrame.setLocationRelativeTo(null);
				attackFrame.setTitle("RunningModedeploy");

				ArrayList<Integer> armyCardsInfo = BuildingModeNew.playerList.get(RunningModeNew.getTurn()).getArmyCardsInfo();

				attackPanel.add(new JLabel("Infantry"));
				attackPanel.add(new JLabel("Cavalry"));
				attackPanel.add(new JLabel("Artillery"));

				// Create two JComboBoxes to allow the player to select two items
				JComboBox<Integer> comboBox1 = new JComboBox<>(armyCardsInfo.toArray(new Integer[0]));
				JComboBox<Integer> comboBox2 = new JComboBox<>(armyCardsInfo.toArray(new Integer[0]));
				attackPanel.add(comboBox1); // Add the JComboBoxes to the panel
				attackPanel.add(comboBox2);

				attackFrame.getContentPane().add(attackPanel); // Add the panel to the frame
				attackFrame.setVisible(true); // Make the frame visible

				
				

				/******************************* */
				JButton tradeButton = new JButton("Trade");
				attackPanel.add(tradeButton);
				tradeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Integer selectedItem1 = (Integer) comboBox1.getSelectedItem();
					Integer selectedItem2 = (Integer) comboBox2.getSelectedItem();
					
					// Get the current counts of cards
					int infantryCount = armyCardsInfo.get(0);
					int cavalryCount = armyCardsInfo.get(1);
					int artilleryCount = armyCardsInfo.get(2);
					
					// Check the selected options and perform the trades
					if (selectedItem1 == 3 && selectedItem2 == 0 && infantryCount >= 3) {
						infantryCount -= 3;
						cavalryCount += 1;
					} else if (selectedItem1 == 2 && selectedItem2 == 1 && infantryCount >= 2 && cavalryCount >= 1) {
						infantryCount -= 2;
						cavalryCount += 1;
					} else if (selectedItem1 == 2 && selectedItem2 == 2 && infantryCount >= 2 && artilleryCount >= 1) {
						infantryCount -= 2;
						artilleryCount += 2;
					} else if (selectedItem1 == 1 && selectedItem2 == 1 && infantryCount >= 1 && cavalryCount >= 2) {
						infantryCount -= 1;
						cavalryCount -= 1;
						artilleryCount += 1;
					} else if (selectedItem1 == 2 && selectedItem2 == 2 && artilleryCount >= 1 && cavalryCount >= 2) {
						artilleryCount += 3;
						cavalryCount -= 2;
					} else {
						// Invalid trade, handle as needed
						System.out.println("Invalid trade, please select again");
						infantryCount += 8;
						cavalryCount += 9;
						artilleryCount += 7;
					}
					
					// Update armyCardsInfo
					armyCardsInfo.set(0, infantryCount);
					armyCardsInfo.set(1, cavalryCount);
					armyCardsInfo.set(2, artilleryCount);
					for(int i = 0; i< 3;i++){
						System.out.println(armyCardsInfo.get(i));
					}
					
					// Refresh the combo boxes
					comboBox1.setSelectedIndex(0);
					comboBox2.setSelectedIndex(0);
					
					// Refresh the armyCardsInfo on the UI, if needed
				}
			});

				/******************************** */
				attackFrame.pack();
        		attackFrame.setVisible(true);
			}
		});
		panel.add(pickArmyCard);

		
		JButton useArmyCard = new JButton("use army card");
		useArmyCard.setBounds(1500, 750, 150, 50);
		useArmyCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame useCardFrame = new JFrame("Use Army Card");
				JPanel useCardPanel = new JPanel();

				useCardFrame.setSize(500, 100);
				useCardFrame.setLocationRelativeTo(null);
				useCardFrame.setTitle("RunningModedeploy");

				ArrayList<Integer> armyCardsInfo = BuildingModeNew.playerList.get(RunningModeNew.getTurn()).getArmyCardsInfo();

				useCardPanel.add(new JLabel("Army Card"));

				JComboBox<Integer> comboBox = new JComboBox<>(armyCardsInfo.toArray(new Integer[0]));
				useCardPanel.add(comboBox);

				PlayerNew currentPlayer = BuildingModeNew.playerList.get(RunningModeNew.getTurn());
				
				JComboBox<Integer> playerIndicies = new JComboBox<>(currentPlayer.getShapeIndices().toArray(new Integer[0]));
				useCardPanel.add(playerIndicies);



				useCardFrame.getContentPane().add(useCardPanel);
				useCardFrame.setVisible(true);

				JButton useButton = new JButton("Use");
				useCardPanel.add(useButton);
				useButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Integer selectedItem = (Integer) comboBox.getSelectedItem();
						Integer selectedIndex = (Integer) playerIndicies.getSelectedItem();

						// Get the current count of the selected army card
						for(int i=0; i<armyCardsInfo.size();i++){
							System.out.println("******************"+armyCardsInfo.get(i));
						} 
						int selectedCardCount = armyCardsInfo.get(2);

						// Check if there are any cards of the selected type
						if (selectedCardCount > 0) {

							if (selectedItem == 3){
								WorldMap.armyList[selectedIndex][2] = WorldMap.armyList[selectedIndex][2]+17;
							}
							if (selectedItem == 2){
								WorldMap.armyList[selectedIndex][1] = WorldMap.armyList[selectedIndex][1]+13;
							}
							if (selectedItem == 1){
								WorldMap.armyList[selectedIndex][0] = WorldMap.armyList[selectedIndex][0]+11;
							}
							else{
								System.out.println("ınvalid selection for use army card");
							}
							// Perform the transformation and decrease the card count
							// (Update this part according to your game logic)
							
							selectedCardCount--;
							
							
							// Update armyCardsInfo
							armyCardsInfo.set(selectedItem, selectedCardCount);

							// Print the updated armyCardsInfo for testing
							for (int i = 0; i < 3; i++) {
								System.out.println(armyCardsInfo.get(i));
							}

							// Refresh the combo box
							comboBox.setSelectedIndex(0);

							// Refresh the armyCardsInfo on the UI, if needed
						} else {
							// No cards of the selected type available, handle as needed
							System.out.println("No cards of the selected type available");
						}
					}
				});

				useCardFrame.pack();
				useCardFrame.setVisible(true);
			}
		});
		panel.add(useArmyCard);

		



		
		
		JButton pickChanceCard = new JButton("pick chance card");
		pickChanceCard.setBounds(700, 750, 150, 50);
		pickChanceCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Random random = new Random();
		        int randomNumber = random.nextInt(5);
				//comment it out when the players are initialised.
		        players.get(turnCounter).addChanceCard(randomNumber);
				ChanceCardFactory factory = new ChanceCardFactory();
				
				System.out.println("card with index "+randomNumber+" which is "+factory.createCard(randomNumber).getClass().getName()+"added to the players' list with id: " +(turnCounter+1));
				cardAnimationClass = new CardAnimationClass();
			}
		});
		panel.add(pickChanceCard);
		
		JLabel turn = new JLabel("Turn: Player "+ players.get(turnCounter).getId());
		turn.setBounds(500, 680, 150, 100);
		
		panel.add(turn);
		
		JPanel turnPanel = new JPanel();
		turnPanel.setBounds(500,820,150,50);
		
		stage.setBounds(350, 765, 150, 100);
		whichStage="Deploy";

		JButton nextStage = new JButton("Next Stage");
		nextStage.setBounds(300, 750, 150, 50);
		nextStage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			counter ++;

			if(counter == 1){
				stage.setText("Attack");
				whichStage="Attack";

			}
			else if(counter==2){
				stage.setText("Fortify");
				whichStage="Fortify";
			}
			
			else if(counter>2){
				counter=0;
				stage.setText("Deploy");
				whichStage="Deploy";
			}
			}
		});

		//****************************************************************** */


		//********************************************************************* */
		
		nextStage.setVisible(true);
		panel.add(stage);
		panel.add(nextStage);

		JButton nextButton = new JButton("next turn");
		nextButton.setBounds(500,750,150,50);
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				turnCounter++;
				turn.setText("Turn: Player "+players.get(turnCounter-1).getId());
				if (turnCounter==numberOfAIPlayer+numberOfHumanPlayer)
						turnCounter=0;
				turnPanel.setBackground(players.get(turnCounter).getColor());
				
				
				
			}
		});
		panel.add(nextButton);
		panel.add(turnPanel);


		
		String[] cardTypes = {"Revolt Card", "Reinforcement Card", "Coup Card", "Revolution Card", "Nuclear Strike Card"};
        JComboBox<String> cardComboBox = new JComboBox<String>(cardTypes);
        cardComboBox.setBounds(900,750, 150, 50);
        panel.add(cardComboBox);
        
		
        
		JButton useCardButton = new JButton("use card");
		useCardButton.setBounds(1100, 750, 150, 50);
		useCardButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("card with index "+cardComboBox.getSelectedIndex()+"which is "+cardComboBox.getSelectedItem()+"used by the player with id: " +(turnCounter+1));
				
				players.get(turnCounter).useCard(cardComboBox.getSelectedIndex());
				
				if (cardComboBox.getSelectedItem().equals("Reinforcement Card")) {
					
					
					JFrame optionFrame = new JFrame();
                    optionFrame.setSize(500, 100);
                    optionFrame.setLocationRelativeTo(null);
                    optionFrame.setTitle("ID Choose");
                    JPanel optionPanel = new JPanel();
                    JTextField ID = new JTextField();
                    ID.setPreferredSize(new Dimension(100,50));
                    
                    JButton closeButton = new JButton("Close");
                    
                    closeButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String IDonArea = ID.getText();
							players.get(turnCounter).useReinforcementCard(Integer.parseInt(IDonArea));
							optionFrame.dispose();
							
						}
					});
                    
                    
                    optionPanel.add(ID);
                    optionPanel.add(closeButton);
                    optionFrame.add(optionPanel);
                    optionFrame.setVisible(true);
					
					
				}
				
				else if (cardComboBox.getSelectedItem().equals("Nuclear Strike Card")) {
					

					JFrame optionFrame = new JFrame();
                    optionFrame.setSize(500, 100);
                    optionFrame.setLocationRelativeTo(null);
                    optionFrame.setTitle("ID Choose");
                    JPanel optionPanel = new JPanel();
                    
                    JTextField ID = new JTextField();
                    ID.setPreferredSize(new Dimension(100,50));
                    
                    JTextField ID2 = new JTextField();
                    ID2.setPreferredSize(new Dimension(100,50));
                    
                    JButton closeButton = new JButton("Close");
                    
                    closeButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String IDonArea = ID.getText();
							String IDonArea2 = ID2.getText();
							players.get(turnCounter).useNuclearStrikeCard(Integer.parseInt(IDonArea), Integer.parseInt(IDonArea2));
							optionFrame.dispose();
							
						}
					});
                    
                    
                    optionPanel.add(ID);
                    optionPanel.add(ID2);
                    optionPanel.add(closeButton);
                    optionFrame.add(optionPanel);
                    optionFrame.setVisible(true);
					
					
					
				}
				
				
				else if (cardComboBox.getSelectedItem().equals("Revolt Card")) {
					

					JFrame optionFrame = new JFrame();
                    optionFrame.setSize(500, 100);
                    optionFrame.setLocationRelativeTo(null);
                    optionFrame.setTitle("ID Choose");
                    JPanel optionPanel = new JPanel();
                    
                    JTextField ID = new JTextField();
                    ID.setPreferredSize(new Dimension(100,50));
                    
                    JTextField ID2 = new JTextField();
                    ID2.setPreferredSize(new Dimension(100,50));
                    
                    JButton closeButton = new JButton("Close");
                    
                    closeButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String IDonArea = ID.getText();
							String IDonArea2 = ID2.getText();
							players.get(turnCounter).useRevoltCard(Integer.parseInt(IDonArea), Integer.parseInt(IDonArea2));
							optionFrame.dispose();
							
						}
					});
                    
                    
                    optionPanel.add(ID);
                    optionPanel.add(ID2);
                    optionPanel.add(closeButton);
                    optionFrame.add(optionPanel);
                    optionFrame.setVisible(true);
					
					
					
				}
				
				
				
				
				else if (cardComboBox.getSelectedItem().equals("Revolution Card")) {
					

					JFrame optionFrame = new JFrame();
                    optionFrame.setSize(500, 100);
                    optionFrame.setLocationRelativeTo(null);
                    optionFrame.setTitle("ID Choose");
                    JPanel optionPanel = new JPanel();
                    
                    JTextField ID = new JTextField();
                    ID.setPreferredSize(new Dimension(100,50));
                    
                    JButton closeButton = new JButton("Close");
                    
                    closeButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String IDonArea = ID.getText();
							players.get(turnCounter).useRevolutionCard(Integer.parseInt(IDonArea));
							optionFrame.dispose();
							
						}
					});
                    
                    
                    optionPanel.add(ID);
                    optionPanel.add(closeButton);
                    optionFrame.add(optionPanel);
                    optionFrame.setVisible(true);
					
					
					
				}
				
				else if (cardComboBox.getSelectedItem().equals("Coup Card")) {
					

					JFrame optionFrame = new JFrame();
                    optionFrame.setSize(500, 100);
                    optionFrame.setLocationRelativeTo(null);
                    optionFrame.setTitle("ID Choose");
                    JPanel optionPanel = new JPanel();
                    
                    JTextField ID = new JTextField();
                    ID.setPreferredSize(new Dimension(100,50));
                    
                    JButton closeButton = new JButton("Close");
                    
                    closeButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String IDonArea = ID.getText();
							players.get(turnCounter).useCoupCard(Integer.parseInt(IDonArea));
							optionFrame.dispose();
							
						}
					});
                    
                    optionPanel.add(ID);
                    optionPanel.add(closeButton);
                    optionFrame.add(optionPanel);
                    optionFrame.setVisible(true);
					
					
					
				}
				
				
				
			}
		});
		panel.add(useCardButton);
		
		
		JButton exitButton = new JButton("Exit!");
		exitButton.setBounds(1600, 900, 150, 50);
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RunningModeNew.this.dispose();
				
			}
		});
		panel.add(exitButton);
		
		JButton pauseButton = new JButton("Pause");
		pauseButton.setBounds(1400, 900, 150, 50);
		
		pauseButton.setOpaque(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setText("");

        ImageIcon backgroundImage9 = new ImageIcon(cwd+"/src/uipackage/pauseButton.png");
        pauseButton.setIcon(backgroundImage9);

		ImageIcon backgroundImage10 = new ImageIcon(cwd+"/src/uipackage/resumeButton.png");
        

		pauseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (pauseButton.getIcon().equals(backgroundImage9)){
					pauseButton.setIcon(backgroundImage10);
					exitButton.setVisible(false);
					useCardButton.setVisible(false);
					cardComboBox.setVisible(false);
					saveButtonMongo.setVisible(false);
					saveButtonJSON.setVisible(false);
					pickChanceCard.setVisible(false);
					turnPanel.setVisible(false);
					nextButton.setVisible(false);
					worldPanel.setVisible(false);
					nextStage.setVisible(false);
					pickArmyCard.setVisible(false);
					useArmyCard.setVisible(false);
					stage.setVisible(false);
					turn.setVisible(false);
					for (JTextField curField : textLabels) 
						curField.setVisible(false);
					
					
				}
				else {
					pauseButton.setIcon(backgroundImage9);
					exitButton.setVisible(true);
					useCardButton.setVisible(true);
					cardComboBox.setVisible(true);
					saveButtonMongo.setVisible(true);
					saveButtonJSON.setVisible(true);
					pickChanceCard.setVisible(true);
					turnPanel.setVisible(true);
					nextButton.setVisible(true);
					worldPanel.setVisible(true);
					turn.setVisible(true);
					nextStage.setVisible(true);
					pickArmyCard.setVisible(true);
					useArmyCard.setVisible(true);
					stage.setVisible(true);
					for (JTextField curField : textLabels) 
						curField.setVisible(true);
				}
				
				
			}
		});
		panel.add(pauseButton);
		



		pickChanceCard.setOpaque(false);
        pickChanceCard.setContentAreaFilled(false);
        pickChanceCard.setBorderPainted(false);
        pickChanceCard.setText("");

        ImageIcon backgroundImage = new ImageIcon(cwd+"/src/uipackage/chanceCardButton.png");
        pickChanceCard.setIcon(backgroundImage);



		nextStage.setOpaque(false);
        nextStage.setContentAreaFilled(false);
        nextStage.setBorderPainted(false);
        nextStage.setText("");

        ImageIcon backgroundImage1 = new ImageIcon(cwd+"/src/uipackage/nextStageButton.png");
        nextStage.setIcon(backgroundImage1);


		nextButton.setOpaque(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);
        nextButton.setText("");

        ImageIcon backgroundImage2 = new ImageIcon(cwd+"/src/uipackage/nextTurnButton.png");
        nextButton.setIcon(backgroundImage2);
		
		useCardButton.setOpaque(false);
        useCardButton.setContentAreaFilled(false);
        useCardButton.setBorderPainted(false);
        useCardButton.setText("");

        ImageIcon backgroundImage3 = new ImageIcon(cwd+"/src/uipackage/useCardButton.png");
        useCardButton.setIcon(backgroundImage3);
		

		saveButtonJSON.setOpaque(false);
        saveButtonJSON.setContentAreaFilled(false);
        saveButtonJSON.setBorderPainted(false);
        saveButtonJSON.setText("");

        ImageIcon backgroundImage4 = new ImageIcon(cwd+"/src/uipackage/saveJsonButton.png");
        saveButtonJSON.setIcon(backgroundImage4);
		
		saveButtonMongo.setOpaque(false);
        saveButtonMongo.setContentAreaFilled(false);
        saveButtonMongo.setBorderPainted(false);
        saveButtonMongo.setText("");

        ImageIcon backgroundImage5 = new ImageIcon(cwd+"/src/uipackage/saveMongoButton.png");
        saveButtonMongo.setIcon(backgroundImage5);
		
		exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setText("");

        ImageIcon backgroundImage6 = new ImageIcon(cwd+"/src/uipackage/exitButton.png");
        exitButton.setIcon(backgroundImage6);


		pickArmyCard.setOpaque(false);
        pickArmyCard.setContentAreaFilled(false);
        pickArmyCard.setBorderPainted(false);
        pickArmyCard.setText("");

        ImageIcon backgroundImage7 = new ImageIcon(cwd+"/src/uipackage/tradeArmyButton.png");
        pickArmyCard.setIcon(backgroundImage7);

		useArmyCard.setOpaque(false);
        useArmyCard.setContentAreaFilled(false);
        useArmyCard.setBorderPainted(false);
        useArmyCard.setText("");

        ImageIcon backgroundImage8 = new ImageIcon(cwd+"/src/uipackage/useArmyButton.png");
        useArmyCard.setIcon(backgroundImage8);

		Color color = new Color(171,70,48);
		cardComboBox.setBackground(color);

		panel.setVisible(true);
		this.add(panel);
	
		pack();
		setLocationRelativeTo(null); // Center the JFrame on the screen
		setVisible(true);
	}
	
	
	
	
}

