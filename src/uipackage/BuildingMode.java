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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import RiskPackage.ComputerPlayer;
import RiskPackage.Continents;
import RiskPackage.GameManager;
import RiskPackage.GamePanel;
import RiskPackage.HumanPlayer;
import RiskPackage.Player;
import RiskPackage.RiskBoard;
import RiskPackage.Territory;

public class BuildingMode extends JFrame {

	
	public int numberOfPlayer;
    public int numberOfComp;
	
	public BuildingMode() {
        super("Building Mode");
        
            this.numberOfPlayer = -1;
            this.numberOfComp = -1;
        

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(null);


        ArrayList<Territory> asiaTerritories = new ArrayList<>();
       
        ArrayList<Territory> africaTerritories = new ArrayList<>();

        ArrayList<Territory> europeTerritories = new ArrayList<>();

        ArrayList<Territory> americaTerritories = new ArrayList<>();

        Continents Africa = new Continents("Africa", africaTerritories, 300, 300, Color.yellow);
        Africa.setLocation(900, 50);

        Continents Europe = new Continents("Europe",europeTerritories, 300, 300, Color.RED);
        Europe.setLocation(300, 280);

        Continents America = new Continents("America",americaTerritories, 200, 200, Color.magenta);
        America.setLocation(150, 50);
        
        Continents Asia = new Continents("Asia",asiaTerritories, 200, 200, Color.black);
        Asia.setLocation(900, 450);

        
        Territory Holland = new Territory(250, 250, 40, 40, "holland", Color.BLUE, Europe);
        Territory US1 = new Territory(75, 150, 40, 40, "US", Color.BLUE, America);
        Territory Canada = new Territory(230, 250, 40, 40, "Canada", Color.BLUE, America);
        Territory UK = new Territory(250, 150, 40, 40, "UK", Color.BLUE, Europe);
        Territory India = new Territory(280, 300, 40, 40, "India", Color.BLUE, Asia);
        Territory China = new Territory(250, 100, 40, 40, "China", Color.BLUE, Asia);

        ArrayList<Territory> halilT = new ArrayList<>();
        halilT.add(Holland);
        halilT.add(UK);
        halilT.add(China);
        halilT.add(US1);

        ArrayList<Territory> compT = new ArrayList<>();
        compT.add(Canada);
        compT.add(India);

        HumanPlayer halil= new HumanPlayer(899, Color.PINK, "halil", halilT);
        ComputerPlayer comp1 = new ComputerPlayer(1, Color.green, "comp1", compT);
        
        

        

        this.add(Europe);
        this.add(Asia);
        this.add(America);
        this.add(Africa);
        this.pack();
        
        

        ArrayList<Continents> continents = new ArrayList<>();

        continents.add(Asia);
        continents.add(America);
        continents.add(Europe);
        continents.add(Africa);
        
        
        
        String[] numberOfPlayers = {"2", "3", "4", "5", "6"};
        JComboBox<String> myComboBox = new JComboBox<String>(numberOfPlayers);
        myComboBox.setName("numberOfHuman");
        myComboBox.setSize(100,100);
        myComboBox.setBackground(Color.WHITE);
        myComboBox.setLocation(550, 650);
        JLabel label = new JLabel("Number of Players:");
        label.setLabelFor(myComboBox);
        this.getContentPane().add(myComboBox);
        this.getContentPane().add(label);

        String[] numberOfComp = {"2","1"};
        JComboBox<String> compBox = new JComboBox<String>(numberOfComp);
        compBox.setName("numberOfComp");
        compBox.setSize(100,100);
        compBox.setBackground(Color.WHITE);
        compBox.setLocation(250, 650);
        JLabel CompL = new JLabel("Number of Players:");
        CompL.setLabelFor(myComboBox);
        this.getContentPane().add(compBox);
        this.getContentPane().add(CompL);
        
        JButton startButton = new JButton("Start Game!");
        startButton.setSize(100,100);
        startButton.setBackground(Color.GRAY);
        startButton.setLocation(950, 650);
        this.getContentPane().add(startButton);
        
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	
                setNumberOfPlayer(((int)Integer.valueOf((String) myComboBox.getSelectedItem())));
            	JOptionPane.showMessageDialog(null, "Game started!");
                ArrayList<Player> players = new ArrayList<>();
                players.add( halil);
                players.add(comp1);
                for(Player p : players){
                for(Territory t: p.getTerritories()){
                Continents c = t.getContinent();
                t.setColor(p.getColor());
                c.add(t);
                    }
                }                                           //launching game
                RiskBoard risk = new RiskBoard(continents); //jpanel
                GamePanel pnl = new GamePanel(risk);        //jpanel
                GameManager g = new GameManager(pnl);       //jframe
                
                g.setLayout(new BorderLayout());
                g.setVisible(true);
                getThisFrame().dispose();
            }
        });
        
	}
	
	public int getNumberOfPlayer() {
		return this.numberOfPlayer;
	}
	
	public void setNumberOfPlayer(int number) {
		this.numberOfPlayer = number;
	}

    public int getNumberOfComp() {
		return this.numberOfComp;
	}
	
	public void setNumberOfComp(int number) {
		this.numberOfComp = number;
	}

    private JFrame getThisFrame() {
        return (JFrame) SwingUtilities.getRoot(this);
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
