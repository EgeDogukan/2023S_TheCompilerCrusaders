package RiskPackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFrame;

//import javax.management.StringValueExp;

import uipackage.*;;

public class GameControllerNew {
	
	private static int turnID = 0;
	private static ArrayList<PlayerNew> playerList = new ArrayList<>();
	
	private GameControllerNew() {
		
	}
	
	public static GameControllerNew getInstance() {
		
		GameControllerNew instance = new GameControllerNew();
		return instance;
		
	}
	
	
	
    public static void main(String[] args) throws InterruptedException {
        
    	MainMenu menu = new MainMenu();
    	menu.setVisible(true);
        
    	login loginPage = new login();
    	
    	while(true) {
    	if (menu.isLoginClicked==false) {
    		
            loginPage.frame.setVisible(false);
    	}
    	
    	else if (menu.isLoginClicked==true) {
    		menu.dispose();
    		loginPage.frame.setVisible(true);
    		


        do {                                                        //waiting until login phase completed
            System.out.println(loginPage.getLoginStatus());
            
        } while (loginPage.getLoginStatus() == false);
		loginPage.frame.dispose();
		
		MainMenuLoggedIn mainMenuLoggedIn = new MainMenuLoggedIn();
		mainMenuLoggedIn.setVisible(true);

		//whichMode modeSelection = new whichMode();
		//modeSelection.setVisible(true);
		
		do {                                                        //waiting until login phase completed
            System.out.println(mainMenuLoggedIn.status);
            
        } while (mainMenuLoggedIn.status == -1);
		mainMenuLoggedIn.dispose();
		
		if (mainMenuLoggedIn.status==1) {
			
			
	
		    // Declare a variable to store the number of players
		    AtomicInteger numberOfPlayers = new AtomicInteger(0);
		    AtomicInteger numberOfAIPlayers = new AtomicInteger(0);
		    
		    
		    do {                                                        //waiting until login phase completed
	            System.out.println(loginPage.getLoginStatus());
	            
	        } while (loginPage.getLoginStatus() == false);
			loginPage.frame.dispose();
		    
			
			
			
			
		        
	
		    // Use the variable after the frame is closed
		    System.out.println("Number of players: "+numberOfPlayers.get());
		    System.out.println("Number of AI players: "+numberOfAIPlayers.get());
		    
		    //BuildingModeNew RiskGameFrame = new BuildingModeNew(numberOfPlayers.get());
		    //RiskGameFrame.setLayout(null);
		    //RiskGameFrame.setVisible(true);
		    
		    IntermediaryBetweenPreBuildingAndBuilding s = new IntermediaryBetweenPreBuildingAndBuilding();
		    
		    for (ArrayList<Integer> indices : s.getBuildingModeNew().getShapeList()) {
		    	System.out.println("Indices of a player "+indices);
		    	for (Integer index : indices) {
		    		System.out.println("Name of the territory: "+BuildingModeNew.worldMap.getShapeList().get(index));
		    	}
		    }
		    
	        
		    
		    playerList = BuildingModeNew.getPlayerList();
		    System.out.println(playerList);
	        ArrayList<ArrayList<Shape>> shapes = s.getBuildingModeNew().initalSharing(playerList);
	        
	        //RiskGameFrame.dispose();
	        
	        RunningModeNew g = new RunningModeNew(shapes, GameControllerNew.playerList, 0, 4);
	        
	        g.setLayout(new BorderLayout());
	        g.setVisible(true);
			turnID = g.getTurn() - 1;
			s.getBuildingModeNew().dispose();

			break;
			
			
		}
		
//		else {
//			LoadMode RiskGameFrame = new LoadMode();
//		    RiskGameFrame.setLayout(null);
//		    RiskGameFrame.setVisible(true);
//		    
//		    GameControllerNew.playerList = initGameLoadMode(RiskGameFrame.getNumberofPlayers()-1, 1, RiskGameFrame.getContinent());
//	        ArrayList<Continents> c = RiskGameFrame.initalSharing(playerList);
//			RunningMode g = new RunningMode(c, playerList, 1, RiskGameFrame.getNumberofPlayers()-1);
//			
//	        g.setLayout(new BorderLayout());
//	        g.setVisible(true);
//			turnID = g.getTurn() - 1;
//			RiskGameFrame.dispose();
//			break;
//		}
	
		}
	}

	}
    

    

    public static Color randomColorGenerator() {
    	Random random = new Random(); // Create a new Random object
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
    	return new Color(r,g,b);
    }
    
    static private ArrayList<Player> initGameLoadMode(int numberofPlayers, int numberofComp, ArrayList<Continents> continents) {
    	ArrayList<Territory> territories = new ArrayList<Territory>();
    	ArrayList<Player> playerList = new ArrayList<Player>();
    	
    	for (Continents continent : continents) {
    		if (continent.isIncluded){
				for (Territory territory : continent.getTerritories()){
					territories.add(territory);
				}
    		}
		}
    	
    	for (int i=0;i<numberofPlayers;i++) {
    		ArrayList<Territory> currentTerritories = new ArrayList<Territory>();
    		for (Territory territory : territories) {
        		if (territory.getOwnerID()==i){
        			currentTerritories.add(territory);
        		}
        	}
    		playerList.add(new Player(i, randomColorGenerator(), currentTerritories));
    	}
    	
    	return playerList;
    }
    

	static private ArrayList<Player> initGame(int numberofPlayers, int numberofComp, ArrayList<Continents> continents) {
		
		ArrayList<Territory> territories = new ArrayList<Territory>();
		for (Continents continent : continents) {
			if (continent.isIncluded){
				for (Territory territory : continent.getTerritories()){
					territories.add(territory);
				}
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
		
		
		System.out.println("***");
		for (Player player : playerList) {
			System.out.println(player.getId());
			//System.out.println(player.getTerritories().getNames());
			for (Territory territory : player.getTerritories())
				System.out.println(territory.getName());
				
			System.out.println("***");
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