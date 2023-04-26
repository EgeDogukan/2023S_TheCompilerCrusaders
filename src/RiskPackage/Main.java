package RiskPackage;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;

import uipackage.login;

public class Main {
    public static void main(String[] args) {
        RiskBoard board = new RiskBoard();
        new GameManager();
        // Add some territories to the board
        
        
     // Create some territories
        
        
        
	

	    
		login log = new login();
       	GamePanel gamePanel = new GamePanel(board);

       	JFrame frame = new JFrame("Risk Game");
       	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
       	frame.add(gamePanel);
    	frame.pack();
       	frame.setVisible(true);

	 
    }
}
