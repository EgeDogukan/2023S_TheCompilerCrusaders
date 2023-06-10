package uipackage;

import java.awt.Menu;
import java.util.concurrent.atomic.AtomicInteger;

public class UIController {

    private static UIController uiControllerInstance;
    private MainMenu menu;
    private login loginScreen;
    private MainMenuLoggedIn mainMenuLoggedIn;

    public UIController() {
        initMainMenu();
    }

    public static UIController getUiController() {
        if(uiControllerInstance == null) {
            uiControllerInstance = new UIController();
        }
        return uiControllerInstance;
    } 

    public void initMainMenu() {
        this.menu = new MainMenu();
    	menu.setVisible(true);
    }

    public void initLogin() {
        this.menu.dispose();
        this.loginScreen = new login();
        loginScreen.frame.setVisible(true);
    }

    public void initLoggedIn() {
        this.loginScreen.frame.dispose();
        mainMenuLoggedIn = new MainMenuLoggedIn();
        mainMenuLoggedIn.setVisible(true);
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
		    
		    
		    System.out.println("G IS NOT INITALIZED YET");
		    
		    IntermediaryBetweenPreBuildingAndBuilding s = new IntermediaryBetweenPreBuildingAndBuilding();
		    

		    break;
		    
	        
			
		}
		else {
			LoadMode loadMode = new LoadMode();
			break;
		}
		
	
	
		}
	    }

	}


    
}
