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
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import RiskPackage.Continents;
import RiskPackage.GameManager;
import RiskPackage.GamePanel;
import RiskPackage.LinesPanel;
import RiskPackage.RiskBoard;
import RiskPackage.Territory;

public class RunningMode extends JFrame{

	public ArrayList<Continents> continents;
	private JButton turn;
	
	public RunningMode(ArrayList<Continents> continents)  {
		
		this.continents=continents;
		this.setSize(1920,1080);
		
		RiskBoard board = new RiskBoard(this.continents);
		GamePanel panel = new GamePanel(board);
		//Trying to add lines as panel
		//LinesPanel linesPanel = new LinesPanel();
		//JLayeredPane layeredPane = new JLayeredPane();
        //layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        //layeredPane.add(linesPanel, JLayeredPane.PALETTE_LAYER);
		panel.setBackground(Color.pink);
		
		//this.add(layeredPane);
		this.setTitle("Running Mode");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		
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
            	x=x%6;
            	if (x==0) {
            		x++;
            	}
            	turn.setText(Integer.toString(x));
 
            }
        });
		
		JLabel turnString = new JLabel("Turn: Player ");
		turnString.setSize(150,150);
		turnString.setLocation(0, 500);
		
		
		//for (Territory territory : BuildingMode.territories) {
		//	for (Territory neighbor : territory.getNeighbors()) {
		//		linesPanel.addConnection(territory, neighbor);
		//	}
		//}
		
		
		this.getContentPane().add(nextButton);
		this.getContentPane().add(turn);
		this.getContentPane().add(turnString);
    	this.add(panel); 
		

	}
	
	public int getTurn() {
		return Integer.parseInt(this.turn.getText());
	}

}
