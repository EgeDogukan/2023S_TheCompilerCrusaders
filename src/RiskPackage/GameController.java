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
        
        
        AtomicBoolean status = new AtomicBoolean(false);
        CountDownLatch latch = new CountDownLatch(1);
        
        loginPage.frame.addWindowListener((WindowListener) new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	status.set(loginPage.getLoginStatus());
	            latch.countDown();
	        }
	    });
        
        latch.await();
        
        
        if (status.get()) {
            System.out.println("slmm");

            BuildingMode RiskGameFrame = new BuildingMode();
            System.out.println("RiskGameFrame initialized"); // Debug statement
            RiskGameFrame.setLayout(null);
            RiskGameFrame.setVisible(true);
            System.out.println("RiskGameFrame initialized"); // Debug statement

            // Declare a variable to store the number of players
            AtomicInteger numberOfPlayers = new AtomicInteger(0);

            // Use a CountDownLatch to synchronize the two threads
            CountDownLatch latch1 = new CountDownLatch(1);

            // Add the necessary command when the frame is closed.
            RiskGameFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    numberOfPlayers.set(RiskGameFrame.getNumberOfPlayer());
                    latch1.countDown();
                }
            });

            System.out.println("Waiting for window to close"); // Debug statement
            // Wait for the window to be closed before accessing the value of numberOfPlayers
            latch1.await();

            // Use the variable after the frame is closed
            System.out.println(numberOfPlayers.get());
            System.out.println("wslm");
        }

    	
        
        
        
    }
}
