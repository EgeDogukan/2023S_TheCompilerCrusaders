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
	private static ArrayList<Player> lst;
	
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
	    
        boolean isBuilding=true;
	    ArrayList<Player> lst = initGame(numberOfPlayers.get(), numberOfAIPlayers.get(), RiskGameFrame.getContinent(), isBuilding);
        ArrayList<Continents> c = RiskGameFrame.initalSharing(lst);
		RunningMode g = new RunningMode(c, lst, numberOfAIPlayers.get(), numberOfPlayers.get());
                
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
    

	static public ArrayList<Player> initGame(int numberofPlayers, int numberofComp, ArrayList<Continents> continents, boolean isBuilding) {
		
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
		ArrayList<Player> playerList2 = new ArrayList<Player>();
		
		Collections.shuffle(territories);
		for(int j = 0; j < numberofComp + numberofPlayers; j++) {
			
			ArrayList<Territory> currentTerritories = new ArrayList<Territory>();
			
			
			for (int i=0; i<territoryPerPlayer;i++) {
				currentTerritories.add(territories.get(i+j*territoryPerPlayer));
			}
			Player player = new Player(j, randomColorGenerator(), currentTerritories);
			playerList.add(player);
			
			int numberOfArmyPerPlayer=BuildingMode.getNumberOfInitialArmy(numberofPlayers+numberofComp);
			
			Player player2 = new Player(j, randomColorGenerator(), numberOfArmyPerPlayer, 0,0);
			playerList2.add(player2);
			
		
		
		}
		
		
		System.out.println("*****************");
		for (Player player : playerList) {
			System.out.println(player.getId());
			//System.out.println(player.getTerritories().getNames());
			for (Territory territory : player.getTerritories())
				System.out.println(territory.getName());
				
			System.out.println("*****************");
		}
		
		if (isBuilding) {
			return playerList2;
		}
		else {
			return playerList;
		}
	}
	

	public static Player getfromid(int id) {
		for(Player player : lst) {
			if(player.getId()==id);
				return player;
			
		}
		return null;
	}

	public static int getCurrentTurnPlayerID() {
		return turnID;
	}

	public static void setCurrentTurnPlayerID(int ID) {
		turnID = ID - 1;
	}
	
	public void getPlayerList() {
		
	}
	
	
}
