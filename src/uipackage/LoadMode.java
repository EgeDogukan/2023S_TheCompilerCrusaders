package uipackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import RiskPackage.PlayerNew;
import chanceCardPackage.ChanceCardFactory;
import databasePackage.ISaveLoadAdapter;
import databasePackage.TerritoryDBDatabase;
import databasePackage.TerritoryJSONDBDatabase;

public class LoadMode extends JFrame {
	
	int numberOfPlayer;
	int numberOfComp;
	ArrayList<ArrayList<Integer>> informations;
	
	public ArrayList<PlayerNew> players = new ArrayList<>();
	private static JButton turn = new JButton();
	int numberOfAIPlayer;
	int numberOfHumanPlayer;
    static int turnCounter=0;
	public static boolean isContinue = true;
    public ArrayList<Shape> shapelist = new ArrayList<>();
	public static boolean isInBuildingMode = true;
	public static WorldMap worldMap;
	
	public int databaseChooser=RunningModeNew.databaseChooser;
	

	public LoadMode()  {
		super("Load Mode");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(null);
        
        
    	
    	
        ISaveLoadAdapter database;
        
        if (RunningModeNew.databaseChooser==1) {
        	System.out.println("db is chosen.");
        	database =  new TerritoryDBDatabase();
        }
        else {
        	System.out.println("json is chosen.");
        	database =  new TerritoryJSONDBDatabase();
        }
    		
        try {
    		this.informations = database.loadAll();
    		System.out.println("all loaded.");
    		} 
        catch (IOException e) {
    			e.printStackTrace();
    		}
        
        WorldMap worldMap = new WorldMap();
        
        ArrayList<Color> colors = new ArrayList<>();
        System.out.println(this.informations);
        
        for (ArrayList<Integer> arr : this.informations){
        	Color curColor = new Color(arr.get(1), arr.get(2), arr.get(3));
        	worldMap.setIndexColor(arr.get(0), curColor);
        	//System.out.println(curColor);
        	worldMap.setShapeArmyArtillery(worldMap.shapeList.get(arr.get(0)), arr.get(4));
        	worldMap.setShapeArmyCavalry(worldMap.shapeList.get(arr.get(0)), arr.get(5));
        	worldMap.setShapeArmyInfantry(worldMap.shapeList.get(arr.get(0)), arr.get(6));
        	
        	if (colors.size()==0) {
    			colors.add(curColor);
    		}
        	
        	Iterator<Color> iterator = colors.iterator();
            boolean colorExists = false;
            
            while (iterator.hasNext()) {
                Color color = iterator.next();
                if (curColor.getRed() == color.getRed() && curColor.getGreen() == color.getGreen()
                        && curColor.getBlue() == color.getBlue()) {
                    colorExists = true;
                    break;
                }
            }
            
            if (!colorExists) {
                colors.add(curColor);
            }
        }
        
        System.out.println("informations");
        System.out.println("********");
        System.out.println(informations);
        System.out.println("**********");
        System.out.println("colors.size() is "+colors.size());
        
        for (int i=1;i<colors.size()+1;i++) {
        	
        	players.add(new PlayerNew(i, colors.get(i-1), informations.get(i-1)));
        }
        
        RunningModeNew.turnCounter=1;
        BuildingModeNew.turn=1;
        
        for(PlayerNew playerNew : players) {
        	System.out.println(playerNew);
        }
        
        BuildingModeNew.playerList=players;
        WorldMap.isInBuildingMode=false;
        
        new RunningModeNew(players, worldMap, 1);
        
        /*
        JPanel worldPanel = (JPanel) worldMap.getUI();
		worldPanel.setBounds(0, 0, worldPanel.getPreferredSize().width, worldPanel.getPreferredSize().height);
		this.add(worldPanel);
		
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
					LoadMode.this.dispose();
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
					LoadMode.this.dispose();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		this.add(saveButtonJSON);
		
		JButton pickChanceCard = new JButton("pick chance card");
		pickChanceCard.setBounds(300, 550, 100, 100);
		pickChanceCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Random random = new Random();
		        int randomNumber = random.nextInt(5);
				//comment it out when the players are initialised.
		        players.get(turnCounter).addChanceCard(randomNumber);
				ChanceCardFactory factory = new ChanceCardFactory();
				
				System.out.println("card with index "+randomNumber+" which is "+factory.createCard(randomNumber).getClass().getName()+"added to the players' list with id: " +(turnCounter+1));
				
			}
		});
		this.add(pickChanceCard);
		
		JLabel turn = new JLabel("Turn: Player "+ players.get(turnCounter).getId());
		turn.setBounds(500, 650, 100, 100);
		
		this.add(turn);
		
		JPanel turnPanel = new JPanel();
		turnPanel.setBounds(600,700,100,100);
		

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


		
		String[] cardTypes = {"Draft Chance Card", "Reinforcement Card", "Trade Deal Card", "Revolution Card", "Nuclear Strike Card"};
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
				
				
				
			}
		});
		this.add(useCardButton);
		
		
		JButton exitButton = new JButton("Exit!");
		exitButton.setBounds(1200, 700, 100, 100);
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoadMode.this.dispose();
				
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
					
				}
				
				
			}
		});
		this.add(pauseButton);
		
		pack();
		setLocationRelativeTo(null);
		this.setVisible(true);
		*/
	}
	
	
	
	
	
	

}
