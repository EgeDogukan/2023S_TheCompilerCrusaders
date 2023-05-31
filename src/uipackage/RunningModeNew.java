package uipackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import RiskPackage.Continents;
import RiskPackage.GameController;
import RiskPackage.GamePanel;
import RiskPackage.Player;
import RiskPackage.PlayerNew;
import RiskPackage.RiskBoard;
import RiskPackage.Territory;
import cardPackage.ArmyCardFactory;
import cardPackage.ChanceCardFactory;
import cardPackage.IChanceCard;
import cardPackage.TerritoryCard;
import databasePackage.ISaveLoadAdapter;
import databasePackage.TerritoryDBDatabase;
import databasePackage.TerritoryJSONDBDatabase;


public class RunningModeNew extends JFrame {

    public ArrayList<PlayerNew> players;
	private static JButton turn = new JButton();
	int numberOfAIPlayer;
	int numberOfHumanPlayer;
    static int turnCounter=1;
	public static boolean isContinue = true;
	public static int databaseChooser=0;
    public ArrayList<ArrayList<Shape>> shapelist = new ArrayList<>();
    public ArrayList<TerritoryCard> territoryCards = new ArrayList<TerritoryCard>();
	public static boolean isInBuildingMode = true;

	
	
    public RunningModeNew(ArrayList<ArrayList<Shape>> shapelist, ArrayList<PlayerNew> players , int numberOfAIPlayer, int numberOfHumanPlayer){
        this.numberOfAIPlayer=numberOfAIPlayer;
		this.numberOfHumanPlayer=numberOfHumanPlayer;
		this.players=players;
		this.shapelist=shapelist;
		this.setSize(1920,1080);
		this.setTitle("ConKUerror");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		RiskBoard board = new RiskBoard();
//		GamePanel panel = new GamePanel(board);
//		panel.setBackground(Color.pink);
		
		BuildingModeNew buildingModeNew = new BuildingModeNew(numberOfHumanPlayer);
		BuildingModeNew.worldMap = new WorldMap();

	     buildingModeNew.setResizable(false);
	     buildingModeNew.pack();
	     buildingModeNew.setVisible(true);
			 
		
		 

		JButton nextButton = new JButton("next turn");
		nextButton.setSize(100,100);
		nextButton.setLocation(100, 650);
		nextButton.setBackground(this.players.get(getTurn()-1).getColor());
		
		
		
		
		
		
		
		
		
		
		
		
		
		JButton pickChanceCardButton = new JButton("Pick a Chance Card");
		pickChanceCardButton.setSize(175,175);
		pickChanceCardButton.setLocation(300, 650);
		
		pickChanceCardButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pickChanceCard();
				
			}
		});

		JLabel turn = new JLabel(Integer.toString(turnCounter));
		turn.setSize(150, 150);
		turn.setLocation(100, 500);

		nextButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

				
				//nextButton.setBackground(GameController.getCurrentTurnPlayerID());
            	//System.out.println();
            	//printTerritoryCard();
            	RunningMode.turnCounter++;
            	RunningMode.turnCounter=RunningMode.turnCounter%(numberOfAIPlayer+numberOfHumanPlayer+1);
            	if (RunningMode.turnCounter==0) {
            		RunningMode.turnCounter++;
            	}
            	turn.setText(Integer.toString(RunningMode.turnCounter));
				GameController.setCurrentTurnPlayerID(RunningMode.turnCounter);
				System.out.println("------------------------------" + RunningMode.turnCounter);
				nextButton.setBackground(getPlayer(getTurn()-1));
				
            }
        });

		JLabel turnString = new JLabel("Turn: Player ");
		turnString.setSize(150,150);
		turnString.setLocation(0, 500);
		
		JButton helpButton = new JButton("Help Button");
		helpButton.setSize(100,100);
		helpButton.setLocation(0,0);
		
		helpButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				helpScreen helpScreen = new helpScreen("BLA BLA");
				helpScreen.setVisible(true);
			}
		});
		
		
		


		//this.players.get(turnCounter).chanceCards.get(0).getClass().getName().split("\\.")[1]
		String[] CardsOfCurrentPlayer = {"Draft Chance Card", "Reinforcement Card", "Trade Deal Card", "Revolution Card", "Nuclear Strike Card"};
        JComboBox<String> cardComboBox = new JComboBox<String>(CardsOfCurrentPlayer);
        cardComboBox.setSize(100, 100);
        cardComboBox.setLocation(600, 700);
		
		JButton useCard = new JButton("Use Card");
		useCard.setSize(100,100);
		useCard.setLocation(850,700);
		
		useCard.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedCard = cardComboBox.getSelectedIndex();
				//players.get(getTurn()-1).useChanceCard(selectedCard);
			}
		});
		
		
		
		
		JButton quitButton = new JButton("Quit");
		quitButton.setSize(75,75);
		quitButton.setLocation(1450, 0);
		
		
		Image image2 = null;
		try {
			String cwd = System.getProperty("user.dir");
			image2 = ImageIO.read(new File(cwd + "/images.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ImageIcon icon2 = new ImageIcon(image2.getScaledInstance(75, 75, Image.SCALE_SMOOTH));
		quitButton.setIcon(icon2);
		
		quitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		
		
		JButton pickTerritoryCardButton = new JButton("Pick a Territory Card");
		pickTerritoryCardButton.setSize(175,175);
		pickTerritoryCardButton.setLocation(500, 450);
		
		pickTerritoryCardButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//pickTerritoryCard();
				
			}
		});
		
		
		
		JButton saveButton = new JButton("Save Button");
		saveButton.setSize(100,100);
		saveButton.setLocation(1050, 700); 
		
		saveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("Save button is clicked.");
				
			
				
				ISaveLoadAdapter database;
				
				if (databaseChooser==0) {
					database = new TerritoryJSONDBDatabase();
				}
				else {
					database = new TerritoryDBDatabase();
				}
					try {
						database.empty();
						database.saveAll();
                        
						RunningModeNew.this.dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						ArrayList<ArrayList<ArrayList<String>>> informations =  database.loadAll();
						for(int i=0;i<informations.size();i++) {
							System.out.println(informations.get(i));
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
					
				
				
				
			}
		});
		
		
		JButton pauseButton = new JButton("Pause");
		pauseButton.setSize(75,75);
		pauseButton.setLocation(1450, 700);
		
		Image image1 = null;
		try {
			String cwd = System.getProperty("user.dir");
			image1 = ImageIO.read(new File(cwd + "/495307-200.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(image1.getScaledInstance(75, 75, Image.SCALE_SMOOTH));
		pauseButton.setIcon(icon);
		
		pauseButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isContinue){
					pauseButton.setText("Resume");
					
					nextButton.setVisible(false);
					pickChanceCardButton.setVisible(false);
					helpButton.setVisible(false);
					useCard.setVisible(false);
					cardComboBox.setVisible(false);
					pickTerritoryCardButton.setVisible(false);
					saveButton.setVisible(false);
					isContinue=false;
				}
				else {
					pauseButton.setText("Pause");
					nextButton.setVisible(true);
					pickChanceCardButton.setVisible(true);
					helpButton.setVisible(true);
					useCard.setVisible(true);
					cardComboBox.setVisible(true);
					pickTerritoryCardButton.setVisible(true);
					saveButton.setVisible(true);
					isContinue=true;
				}
				
			}
		});
		
		
		
		this.getContentPane().add(nextButton);
		this.getContentPane().add(turn);
		this.getContentPane().add(turnString);
		this.getContentPane().add(pickChanceCardButton);
		this.getContentPane().add(helpButton);
		this.getContentPane().add(useCard);
		this.getContentPane().add(cardComboBox);
		this.getContentPane().add(pauseButton);
		this.getContentPane().add(quitButton);
		this.getContentPane().add(pickTerritoryCardButton);
		this.getContentPane().add(saveButton);
		this.getContentPane().add(BuildingModeNew.worldMap.getUI()); 

		//initializeArmies();
		//initializeTerritoryCards();
    }
    
    public int getTurn() {
		return RunningMode.turnCounter;
	}
	public Color getTurnColor(){
		return this.players.get(getTurn()-1).getColor();
	}

	public void pickChanceCard() {
		int curId=getTurn();
		Random rand = new Random();
        int randomNumber = rand.nextInt(5);
        
        IChanceCard chanceCard = new ChanceCardFactory().createCard(randomNumber);
        //this.players.get(curId-1).chanceCards.add(chanceCard);
        System.out.println("Player with ID"+curId+" has drawn the "+chanceCard.getClass().getName().split("\\.")[1]+" and it is added his/her list.");
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

	public void initGame(){
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1920, 1080));
		this.setLayout(new BorderLayout()); // Use BorderLayout for the JFrame
		this.setVisible(true);
	}
}

