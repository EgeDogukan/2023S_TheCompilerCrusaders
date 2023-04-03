package RiskPackage;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        RiskBoard board = new RiskBoard();

        // Add some territories to the board
        
        
     // Create some territories
        Territory alaska = new Territory(new int[] { 60, 60, 100, 80, 40 }, new int[] { 20, 60, 20, 10, 10 }, 5, "Alaska", Color.GREEN);
        Territory alberta = new Territory(new int[] { 100, 100, 140, 140, 120, 100 }, new int[] { 20, 60, 60, 80, 100, 100 }, 6, "Alberta", Color.BLUE);
        Territory westernUS = new Territory(new int[] { 140, 140, 180, 200, 200, 160 }, new int[] { 60, 100, 100, 120, 80, 60 }, 6, "Western United States", Color.ORANGE);
        Territory centralAmerica = new Territory(new int[] { 140, 160, 180, 180, 160, 160 }, new int[] { 120, 100, 120, 140, 140, 120 }, 6, "Central America", Color.YELLOW);
        Territory venezuela = new Territory(new int[] { 200, 220, 220, 200 }, new int[] { 100, 100, 120, 120 }, 4, "Venezuela", Color.CYAN);
        Territory brazil = new Territory(new int[] { 220, 260, 260, 240 }, new int[] { 60, 60, 100, 100 }, 4, "Brazil", Color.MAGENTA);
        
        
        
	     // Add the territories to the game board
	     board.addTerritory(alaska);
	     board.addTerritory(alberta);
	     board.addTerritory(westernUS);
	     board.addTerritory(centralAmerica);
	     board.addTerritory(venezuela);
	     board.addTerritory(brazil);
	
	     // Add the neighbors to each territory
	        alaska.addNeighbor(alberta);
	        alaska.addNeighbor(westernUS);
	        alberta.addNeighbor(alaska);
	        alberta.addNeighbor(westernUS);
	        alberta.addNeighbor(centralAmerica);
	        westernUS.addNeighbor(alaska);
	        westernUS.addNeighbor(alberta);
	        westernUS.addNeighbor(centralAmerica);
	        centralAmerica.addNeighbor(alberta);
	        centralAmerica.addNeighbor(westernUS);
	        centralAmerica.addNeighbor(venezuela);
	        venezuela.addNeighbor(centralAmerica);
	        venezuela.addNeighbor(brazil);
	        brazil.addNeighbor(venezuela);

	        
       GamePanel gamePanel = new GamePanel(board);

       JFrame frame = new JFrame("Risk Game");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(gamePanel);
       frame.pack();
       frame.setVisible(true);

	 
    }
}
