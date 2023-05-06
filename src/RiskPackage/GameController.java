package RiskPackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

//import javax.management.StringValueExp;

import uipackage.*;;

public class GameController {
	
	private static int turnID = 0;
	
	private GameController() {
		
	}
	
	public static GameController getInstance() {
		
		GameController instance = new GameController();
		return instance;
		
	}
	
    public static void main(String[] args) throws InterruptedException {
        
    	login loginPage = new login();
        loginPage.frame.setVisible(true);

        do {                                                        //waiting until login phase completed
            System.out.println(loginPage.getLoginStatus());
            
        } while (loginPage.getLoginStatus() == false);
		loginPage.frame.dispose();
		
		BuildingMode RiskGameFrame = new BuildingMode();
	    RiskGameFrame.setLayout(null);
	    RiskGameFrame.setVisible(true);

	    // Declare a variable to store the number of players
	    AtomicInteger numberOfPlayers = new AtomicInteger(0);
	    AtomicInteger numberOfAIPlayers = new AtomicInteger(0);
	    
	    
	    do {                                                        //waiting until login phase completed
            System.out.println(loginPage.getLoginStatus());
            
        } while (loginPage.getLoginStatus() == false);
		loginPage.frame.dispose();
	    
		
		do {                                                        //waiting until login phase completed
            System.out.println(RiskGameFrame.getNumberOfPlayer());
            
        } while (RiskGameFrame.getNumberOfPlayer() < 0);
		
		numberOfPlayers.set(RiskGameFrame.getNumberOfPlayer());
		
		
		do {                                                        //waiting until login phase completed
            System.out.println(RiskGameFrame.getNumberOfComp());
            
        } while (RiskGameFrame.getNumberOfComp() < 0);

		numberOfAIPlayers.set(RiskGameFrame.getNumberOfComp());
	        

	    // Use the variable after the frame is closed
	    System.out.println("Number of players: "+numberOfPlayers.get());
	    System.out.println("Number of AI players: "+numberOfAIPlayers.get());
	    
	    for (Continents continent : RiskGameFrame.getContinent()) {
	    	System.out.println("Name of the continent: "+continent.getName());
	    	for (Territory territory : continent.getTerritories()) {
	    		System.out.println("    Name of the territory: "+territory.getName());
	    	}
	    }
	    
        
	    ArrayList<Player> playerList = initGame(numberOfPlayers.get(), numberOfAIPlayers.get(), RiskGameFrame.getContinent());
        ArrayList<Continents> c = RiskGameFrame.initalSharing(playerList);
		RunningMode g = new RunningMode(c, playerList, numberOfAIPlayers.get(), numberOfPlayers.get());
                
        g.setLayout(new BorderLayout());
        g.setVisible(true);
		turnID = g.getTurn() - 1;

    }
    
    static private void startLogin() {
        login loginPage = new login();
        loginPage.frame.setVisible(true);
    }


    
    public static Color randomColorGenerator() {
    	Random random = new Random(); // Create a new Random object
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
    	return new Color(r,g,b);
    }
    

	static private ArrayList<Player> initGame(int numberofPlayers, int numberofComp, ArrayList<Continents> continents) {
		
		ArrayList<Territory> territories = new ArrayList<Territory>();
		for (Continents continent : continents) {
			for (Territory territory : continent.getTerritories()){
				territories.add(territory);
			}
		}
		
		int territoryPerPlayer = Math.floorDiv(territories.size(), (numberofPlayers+numberofComp));
		System.out.println("Number of territory: "+territories.size());
		System.out.println("Territory per player is: "+territoryPerPlayer);
		
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		Collections.shuffle(territories);
		for(int j = 0; j < numberofComp + numberofPlayers; j++) {
			
			ArrayList<Territory> currentTerritories = new ArrayList<Territory>();
			
			
			for (int i=0; i<territoryPerPlayer;i++) {
				currentTerritories.add(territories.get(i+j*territoryPerPlayer));
			}
			
			playerList.add(new Player(j, randomColorGenerator(), currentTerritories));
			
		
		
		}
		
		
		System.out.println("*****************");
		for (Player player : playerList) {
			System.out.println(player.getId());
			//System.out.println(player.getTerritories().getNames());
			for (Territory territory : player.getTerritories())
				System.out.println(territory.getName());
				
			System.out.println("*****************");
		}
		
		return playerList;
	}
	


	public static int getCurrentTurnPlayerID() {
		return turnID;
	}

	public static void setCurrentTurnPlayerID(int ID) {
		turnID = ID - 1;
	}
	
	
}
