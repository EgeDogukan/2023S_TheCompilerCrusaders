package uipackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import uipackage.WorldMap;
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
    public ArrayList<Shape> shapelist = new ArrayList<>();
    public ArrayList<TerritoryCard> territoryCards = new ArrayList<TerritoryCard>();
	public static boolean isInBuildingMode = true;

	public RunningModeNew() {
		initGame();
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
		//super("Building Mode New");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1920, 1080));
		this.setLayout(null); // Use BorderLayout for the JFrame
	
		
		WorldMap worldmap = BuildingModeNew.getWorldMap();
		shapelist=worldmap.getShapeList();
		

		JPanel worldPanel = (JPanel) worldmap.getUI();
		worldPanel.setBounds(0, 0, worldPanel.getPreferredSize().width, worldPanel.getPreferredSize().height);
		this.add(worldPanel); // Add the map panel to the center of the JFrame
		
		
		JButton saveButtonMongo = new JButton("SAVE MONGO");
		saveButtonMongo.setBounds(1100, 300, 100, 100);
		saveButtonMongo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
	
		
		
		
		
		
	
		pack();
		setLocationRelativeTo(null); // Center the JFrame on the screen
		setVisible(true);
	}
}

