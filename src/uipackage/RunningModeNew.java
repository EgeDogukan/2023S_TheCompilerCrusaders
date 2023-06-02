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

import RiskPackage.PlayerNew;
import chanceCardPackage.ChanceCardFactory;
import databasePackage.ISaveLoadAdapter;
import databasePackage.TerritoryDBDatabase;
import databasePackage.TerritoryJSONDBDatabase;


public class RunningModeNew extends JFrame {

    public ArrayList<PlayerNew> players;
	private static JButton turn = new JButton();
	int numberOfAIPlayer;
	int numberOfHumanPlayer;
    static int turnCounter=0;
	public static boolean isContinue = true;
	public static int databaseChooser=0;
    public ArrayList<Shape> shapelist = new ArrayList<>();
	public static boolean isInBuildingMode = true;

	public RunningModeNew(ArrayList<PlayerNew> players) {
		this.players=players;
		numberOfHumanPlayer=players.size();
		numberOfAIPlayer=0;
		initGame(players);
	}
	

    public int getTurn() {
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
		pickChanceCard.setBounds(300, 550, 100, 100);
		pickChanceCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Random random = new Random();
		        int randomNumber = random.nextInt(5);
				//comment it out when the players are initialised.
		        players.get(turnCounter).addChanceCard(randomNumber);
				ChanceCardFactory factory = new ChanceCardFactory();
				
				System.out.println("card with index "+randomNumber+"which is "+factory.createCard(randomNumber).getClass().getName()+"added to the players' list with id: " +turnCounter);
				
			}
		});
		this.add(pickChanceCard);
		
		
		JButton nextButton = new JButton("next turn");
		nextButton.setBounds(500,550,100,100);
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				turnCounter++;
				if (turnCounter==numberOfAIPlayer+numberOfHumanPlayer)
						turnCounter=0;
				
				
			}
		});
		this.add(nextButton);
		
		
	
		
		
		
		
		
	
		pack();
		setLocationRelativeTo(null); // Center the JFrame on the screen
		setVisible(true);
	}
}

