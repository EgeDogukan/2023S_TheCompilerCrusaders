package RiskPackage;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import uipackage.*;;

public class GameController {
    public static void main(String[] args) throws InterruptedException {
        
    	login loginPage = new login();
        loginPage.frame.setVisible(true);

        do {                                                        //waiting until login phase completed
            System.out.println(loginPage.getLoginStatus());
            
        } while (loginPage.getLoginStatus() == false);
		loginPage.frame.dispose();
        startBuildingMode();	
    }
    
    static private void startLogin() {
        login loginPage = new login();
        loginPage.frame.setVisible(true);
    }

    static private void startBuildingMode() throws InterruptedException {
        BuildingMode RiskGameFrame = new BuildingMode();
	    RiskGameFrame.setLayout(null);
	    RiskGameFrame.setVisible(true);

	    // Declare a variable to store the number of players
	    AtomicInteger numberOfPlayers = new AtomicInteger(0);

	    // Use a CountDownLatch to synchronize the two threads
	    CountDownLatch latch = new CountDownLatch(1);

	    // Add the necessary command when the frame is closed.
	    RiskGameFrame.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            numberOfPlayers.set(RiskGameFrame.getNumberOfPlayer());
	            latch.countDown();
	        }
	    });

	    // Wait for the window to be closed before accessing the value of numberOfPlayers
	    latch.await();

	    // Use the variable after the frame is closed
	    System.out.println(numberOfPlayers.get());
    }
}
