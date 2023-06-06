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
import java.awt.*;
import uipackage.WorldMap;
import javax.imageio.ImageIO;

import RiskPackage.GameControllerNew;
import RiskPackage.PlayerNew;
import animationPackage.CardAnimationClass;
import chanceCardPackage.ChanceCardFactory;
import databasePackage.ISaveLoadAdapter;
import databasePackage.TerritoryDBDatabase;
import databasePackage.TerritoryJSONDBDatabase;


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



	public RunningModeNew(ArrayList<PlayerNew> players, WorldMap worldMap) {
		this.players=players;
		numberOfHumanPlayer=players.size();
		numberOfAIPlayer=0;
		this.worldMap=worldMap;
		initGame(players);
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
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1920, 1080));
		this.setLayout(null); // Use BorderLayout for the JFrame
	
		
		shapelist=this.worldMap.getShapeList();
		ArrayList<JTextField> textLabels = worldMap.getTextLabels();
		for (JTextField curField : textLabels) {
			this.add(curField);
		}
		

		JPanel worldPanel = (JPanel) worldMap.getUI();
		worldPanel.setBounds(0, 0, worldPanel.getPreferredSize().width, worldPanel.getPreferredSize().height);
		this.add(worldPanel); // Add the map panel to the center of the JFrame
		
		
		JButton saveButtonMongo = new JButton("SAVE MONGO");
		saveButtonMongo.setBounds(1100, 300, 100, 100);
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
		this.add(saveButtonMongo);
		
		
		JButton saveButtonJSON = new JButton("SAVE JSON");
		saveButtonJSON.setBounds(1300, 300, 100, 100);
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
		this.add(saveButtonJSON);
		
		JButton pickChanceCard = new JButton("pick chance card");
		pickChanceCard.setBounds(200, 550, 100, 100);
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
		this.add(pickChanceCard);
		
		JLabel turn = new JLabel("Turn: Player "+ players.get(turnCounter).getId());
		turn.setBounds(300, 650, 100, 100);
		
		this.add(turn);
		
		JPanel turnPanel = new JPanel();
		turnPanel.setBounds(600,700,100,100);
		
		stage.setBounds(380, 620, 100, 100);
		whichStage="Deploy";

		JButton nextStage = new JButton("Next Stage");
		nextStage.setBounds(350, 550, 100, 100);
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
		this.add(stage);
		this.add(nextStage);

		JButton nextButton = new JButton("next turn");
		nextButton.setBounds(500,550,100,100);
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
		this.add(nextButton);
		this.add(turnPanel);


		
		String[] cardTypes = {"Draft Chance Card", "Reinforcement Card", "Coup Card", "Revolution Card", "Nuclear Strike Card"};
        JComboBox<String> cardComboBox = new JComboBox<String>(cardTypes);
        cardComboBox.setBounds(900,550, 100, 100);
        this.add(cardComboBox);
        
		
        
		JButton useCardButton = new JButton("use card");
		useCardButton.setBounds(700, 550, 100, 100);
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
		this.add(useCardButton);
		
		
		JButton exitButton = new JButton("Exit!");
		exitButton.setBounds(1200, 700, 100, 100);
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RunningModeNew.this.dispose();
				
			}
		});
		this.add(exitButton);
		
		JButton pauseButton = new JButton("Pause");
		pauseButton.setBounds(1200, 600, 100, 100);
		pauseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (pauseButton.getText().equals("Pause")){
					pauseButton.setText("Resume");
					exitButton.setVisible(false);
					useCardButton.setVisible(false);
					cardComboBox.setVisible(false);
					saveButtonMongo.setVisible(false);
					saveButtonJSON.setVisible(false);
					pickChanceCard.setVisible(false);
					turnPanel.setVisible(false);
					nextButton.setVisible(false);
					worldPanel.setVisible(false);
					turn.setVisible(false);
					for (JTextField curField : textLabels) 
						curField.setVisible(false);
					
					
				}
				else {
					pauseButton.setText("Pause");
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
					for (JTextField curField : textLabels) 
						curField.setVisible(true);
				}
				
				
			}
		});
		this.add(pauseButton);
		
		
	
		
		
		
		
	
		pack();
		setLocationRelativeTo(null); // Center the JFrame on the screen
		setVisible(true);
	}
}

