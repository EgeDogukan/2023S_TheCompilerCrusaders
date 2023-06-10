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

import databasePackage.ISaveLoadAdapter;
import uipackage.UIController;

//import javax.management.StringValueExp;

import uipackage.*;;

public class GameControllerNew {
	
	private static int turnID = 0;
	private static ArrayList<PlayerNew> playerList = new ArrayList<>();
	private static GameControllerNew instance;
	private static Object lock = new Object();
	public static RunningModeNew g;
	
	private GameControllerNew() {
		UIController uiController = new UIController();

	}
	
	public static GameControllerNew getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new GameControllerNew();
				}
			}
		}
		
		return instance;
		
	}
	
	
	
    public void init() throws InterruptedException {
        
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
           Thread.sleep(10);
            
        } while (loginPage.getLoginStatus() == false);
		loginPage.frame.dispose();
		
		MainMenuLoggedIn mainMenuLoggedIn = new MainMenuLoggedIn();
		mainMenuLoggedIn.setVisible(true);

		//whichMode modeSelection = new whichMode();
		//modeSelection.setVisible(true);
		
		do {                                                        //waiting until login phase completed
            Thread.sleep(10);
            
        } while (mainMenuLoggedIn.status == -1);
		mainMenuLoggedIn.dispose();
		
		if (mainMenuLoggedIn.status==1) {
			
			
	
		    // Declare a variable to store the number of players
		    AtomicInteger numberOfPlayers = new AtomicInteger(0);
		    AtomicInteger numberOfAIPlayers = new AtomicInteger(0);
		    
		    
		    do {                                                        //waiting until login phase completed
	            Thread.sleep(10);
	            
	        } while (loginPage.getLoginStatus() == false);
			loginPage.frame.dispose();
		    
			
			
			
			
		        
	
		    // Use the variable after the frame is closed
		    System.out.println("Number of players: "+numberOfPlayers.get());
		    System.out.println("Number of AI players: "+numberOfAIPlayers.get());
		    
		    //BuildingModeNew RiskGameFrame = new BuildingModeNew(numberOfPlayers.get());
		    //RiskGameFrame.setLayout(null);
		    //RiskGameFrame.setVisible(true);
		    
		    System.out.println("G IS NOT INITALIZED YET");
		    
		    IntermediaryBetweenPreBuildingAndBuilding s = new IntermediaryBetweenPreBuildingAndBuilding();
		    
//		    for (ArrayList<Integer> indices : s.getBuildingModeNew().getShapeList()) {
//		    	System.out.println("Indices of a player "+indices);
//		    	for (Integer index : indices) {
//		    		System.out.println("Name of the territory: "+BuildingModeNew.worldMap.getShapeList().get(index));
//		    	}
//		    }
		    break;
		    
	        
			
		}
		else {
			LoadMode loadMode = new LoadMode();
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
    
	public RunningModeNew getRunningModeNew() {
		System.out.println(g);
		
		return this.g;
	}
    

    public static Color randomColorGenerator() {
    	Random random = new Random(); // Create a new Random object
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
    	return new Color(r,g,b);
    }
    
    
	


	public static int getCurrentTurnPlayerID() {
		return turnID;
	}

	public static void setCurrentTurnPlayerID(int ID) {
		turnID = ID - 1;
	}
	
	
}