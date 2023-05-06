package uipackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import RiskPackage.Continents;
import RiskPackage.GameManager;
import RiskPackage.GamePanel;
import RiskPackage.Player;
import RiskPackage.RiskBoard;
import cardPackage.ArmyCardFactory;

public class RunningMode extends JFrame{

	public ArrayList<Continents> continents;
	private JButton turn;
	int numberOfAIPlayer;
	int numberOfHumanPlayer;
	ArrayList<Player> players;
	
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
		
		int turnCounter = 1;
		
		JLabel turn = new JLabel(Integer.toString(turnCounter));
		turn.setSize(150, 150);
		turn.setLocation(100, 500);
		
		nextButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	
            	int x=Integer.parseInt(turn.getText());
            	x++;
            	x=x%(numberOfAIPlayer+numberOfHumanPlayer);
            	if (x==0) {
            		x++;
            	}
            	turn.setText(Integer.toString(x));
 
            }
        });
		
		JLabel turnString = new JLabel("Turn: Player ");
		turnString.setSize(150,150);
		turnString.setLocation(0, 500);
		
		this.getContentPane().add(nextButton);
		this.getContentPane().add(turn);
		this.getContentPane().add(turnString);
    	this.add(panel); 
		
		
		initializeArmies();
		

	}
	
	public int getTurn() {
		return Integer.parseInt(this.turn.getText());
	}
	
	public void initializeArmies() {
		for (Player p : this.players) {
			for (int i=0;i<3;i++) { p.addArmyCard(new ArmyCardFactory().createArmyCard(3));}
			for (int i=0;i<2;i++) { p.addArmyCard(new ArmyCardFactory().createArmyCard(2));}
			for (int i=0;i<1;i++) { p.addArmyCard(new ArmyCardFactory().createArmyCard(1));}
			
		}
	}

}
