package uipackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import RiskPackage.Continents;
import RiskPackage.Territory;

public class BuildingMode extends JFrame {

	
	public int numberOfPlayer;
	
	public BuildingMode() {
        super("Building Mode");
        
            this.numberOfPlayer = -1;
        

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(null);
        
 
        Territory US = new Territory(30, 150, 40, 40, "US", Color.BLUE);
        Territory US1 = new Territory(75, 150, 40, 40, "US", Color.BLUE);
        Territory Canada = new Territory(230, 250, 40, 40, "Canada", Color.RED);
        Territory UK = new Territory(130, 150, 40, 40, "UK", Color.MAGENTA);
        Territory India = new Territory(280, 300, 40, 40, "India", Color.BLACK);
        
        ArrayList<Territory> asiaTerritories = new ArrayList<>();
        asiaTerritories.add(US);
        asiaTerritories.add(US1);
        asiaTerritories.add(Canada);
        
        ArrayList<Territory> europeTerritories = new ArrayList<>();
        europeTerritories.add(UK);
        europeTerritories.add(India);
        
        Continents Europe = new Continents(europeTerritories, 200, 200);
        Europe.setLocation(915, 415);
        
        Continents Asia = new Continents(asiaTerritories, 200, 200);
        Asia.setLocation(200, 200);
//        
//        
//       
        this.add(US);
        this.add(US1);
        this.add(Canada);
        this.add(UK);
        this.add(Europe);
        
        this.add(Europe);
        this.add(Asia);
        
        this.pack();
        
        String[] numberOfPlayers = {"2", "3", "4", "5", "6"};
        JComboBox<String> myComboBox = new JComboBox<String>(numberOfPlayers);
        myComboBox.setSize(100,100);
        myComboBox.setBackground(Color.WHITE);
        myComboBox.setLocation(550, 650);
        this.getContentPane().add(myComboBox);
        
        JButton startButton = new JButton("Start Game!");
        startButton.setSize(100,100);
        startButton.setBackground(Color.GRAY);
        startButton.setLocation(850, 650);
        this.getContentPane().add(startButton);
        
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	
                setNumberOfPlayer(((int)Integer.valueOf((String) myComboBox.getSelectedItem())));
            	JOptionPane.showMessageDialog(null, "Game started!");

            }
        });
        
	}
	
	public int getNumberOfPlayer() {
		return this.numberOfPlayer;
	}
	
	public void setNumberOfPlayer(int number) {
		this.numberOfPlayer = number;
	}
	
	public static void main(String[] args) throws InterruptedException {
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
