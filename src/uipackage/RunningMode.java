package uipackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import RiskPackage.Continents;
import RiskPackage.GameController;
import RiskPackage.GameManager;
import RiskPackage.GamePanel;
import RiskPackage.Player;
import RiskPackage.RiskBoard;
import cardPackage.ArmyCardFactory;
import cardPackage.ChanceCardFactory;
import cardPackage.IChanceCard;

public class RunningMode extends JFrame{

	public ArrayList<Continents> continents;
	private static JButton turn = new JButton();
	int numberOfAIPlayer;
	int numberOfHumanPlayer;
	ArrayList<Player> players;
	static int turnCounter=1;

	public RunningMode(ArrayList<Continents> continents, ArrayList<Player> players, int numberOfAIPlayer, int numberOfHumanPlayer)  {
		this.numberOfAIPlayer=numberOfAIPlayer;
		this.numberOfHumanPlayer=numberOfHumanPlayer;
		this.players=players;
		this.continents=continents;
		this.setSize(1920,1080);

		RiskBoard board = new RiskBoard(this.continents);
		GamePanel panel = new GamePanel(board);
		panel.setBackground(Color.pink);

		JButton nextButton = new JButton("next turn");
		nextButton.setSize(100,100);
		nextButton.setLocation(100, 650);
		
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

            	int turnCounter=Integer.parseInt(turn.getText());
            	//System.out.println(x);
            	RunningMode.turnCounter++;
            	RunningMode.turnCounter=RunningMode.turnCounter%(numberOfAIPlayer+numberOfHumanPlayer+1);
            	if (RunningMode.turnCounter==0) {
            		RunningMode.turnCounter++;
            	}
            	turn.setText(Integer.toString(RunningMode.turnCounter));
				GameController.setCurrentTurnPlayerID(RunningMode.turnCounter);
				System.out.println("------------------------------" + RunningMode.turnCounter);
            }
        });

		JLabel turnString = new JLabel("Turn: Player ");
		turnString.setSize(150,150);
		turnString.setLocation(0, 500);

		this.getContentPane().add(nextButton);
		this.getContentPane().add(turn);
		this.getContentPane().add(turnString);
		this.getContentPane().add(pickChanceCardButton);
		this.add(panel); 


		initializeArmies();


	}

	public int getTurn() {
		return RunningMode.turnCounter;
	}

	public void initializeArmies() {
		for (Player p : this.players) {
			for (int i=0;i<3;i++) { p.addArmyCard(new ArmyCardFactory().createArmyCard(3));}
			for (int i=0;i<2;i++) { p.addArmyCard(new ArmyCardFactory().createArmyCard(2));}
			for (int i=0;i<1;i++) { p.addArmyCard(new ArmyCardFactory().createArmyCard(1));}

		}
	}
	
	public void pickChanceCard() {
		int curId=getTurn();
		Random rand = new Random();
        int randomNumber = rand.nextInt(5) + 1;
        IChanceCard chaneCard = new ChanceCardFactory().createCard(randomNumber);
        this.players.get(curId).chanceCards.add(chaneCard);
        System.out.println("Player with ID"+curId+" has drawn the card with ID"+randomNumber+" and it is added his/her list.");
	}
	

	

}