package uipackage;

import java.awt.Menu;
import java.util.concurrent.atomic.AtomicInteger;

public class UIController {

    private static UIController uiControllerInstance;
    private MainMenu menu;
    private login loginScreen;
    private MainMenuLoggedIn mainMenuLoggedIn;
    private LoadMode loadMode;
    private IntermediaryBetweenPreBuildingAndBuilding k;

    public UIController() {
    }

    public void initGameUI() {
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
        //this.menu.dispose();
        this.loginScreen = new login();
        loginScreen.frame.setVisible(true);
    }

    public void initLoggedIn() {
        this.loginScreen.frame.dispose();
        this.mainMenuLoggedIn = new MainMenuLoggedIn();
        mainMenuLoggedIn.setVisible(true);
    }

    public void initLoadMode() {
        this.loadMode = new LoadMode();
        this.mainMenuLoggedIn.dispose();
    }

    public void initNewMode() throws Exception {
        this.k = new IntermediaryBetweenPreBuildingAndBuilding();
        this.mainMenuLoggedIn.dispose();
    }
}
