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
    public ArrayList<Continents> continents;
	
	public BuildingMode() {
        super("Building Mode");
        
            this.numberOfPlayer = -1;
            this.numberOfComp = -1;
            this.continents = new ArrayList<>();
        

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(null);


        ArrayList<Territory> asiaTerritories = new ArrayList<>();
       
        ArrayList<Territory> africaTerritories = new ArrayList<>();

        ArrayList<Territory> europeTerritories = new ArrayList<>();

        ArrayList<Territory> northAmericaTerritories = new ArrayList<>();
        
        ArrayList<Territory> southAmericaTerritories = new ArrayList<>();
        
        ArrayList<Territory> australiaTerritories = new ArrayList<>();        

        
        Continents Africa = new Continents("Africa", africaTerritories, 200, 300, Color.LIGHT_GRAY);
        Africa.setLocation(750, 330);

        Continents Europe = new Continents("Europe",europeTerritories, 400, 300, Color.LIGHT_GRAY);
        Europe.setLocation(645, 20);

        Continents NorthAmerica = new Continents("North America",northAmericaTerritories, 400, 300, Color.LIGHT_GRAY);
        NorthAmerica.setLocation(150, 50);
        
        Continents SouthAmerica = new Continents("South America",southAmericaTerritories, 200, 200, Color.LIGHT_GRAY);
        SouthAmerica.setLocation(200, 360);
        
        Continents Asia = new Continents("Asia",asiaTerritories, 350, 300, Color.LIGHT_GRAY);
        Asia.setLocation(1050, 65);
        
        Continents Australia = new Continents("Australia",australiaTerritories, 200, 200, Color.LIGHT_GRAY);
        Australia.setLocation(1150, 450);
        
        
        Territory Alaska = new Territory(0, 200, 50, 50, "Alaska", Color.BLUE, NorthAmerica, 0);
        Territory Alberta = new Territory(50, 150, 50, 50, "Alberta", Color.BLUE, NorthAmerica, 0);
        Territory NorthWestTerritory = new Territory(150, 50, 50, 50, "NorthWestTerritory", Color.BLUE, NorthAmerica, 0);
        Territory Ontario = new Territory(250, 50, 50, 50, "Ontario", Color.BLUE, NorthAmerica, 0);
        Territory Quebec = new Territory(150, 150, 50, 50, "Quebec", Color.BLUE, NorthAmerica, 0);
        Territory Greenland = new Territory(250, 250, 50, 50, "Greenland", Color.BLUE, NorthAmerica, 0);
        Territory WesternUnitedStates = new Territory(100, 100, 50, 50, "WesternUnitedStates", Color.BLUE, NorthAmerica, 0);
        Territory EasternUnitedStates = new Territory(50, 225, 50, 50, "EasternUnitedStates", Color.BLUE, NorthAmerica, 0);
        Territory CentralAmerica = new Territory(150, 100, 50, 50, "CentralAmerica", Color.BLUE, NorthAmerica, 0);
        
        Territory Venezuela = new Territory(50, 150, 50, 50, "Venezuela", Color.BLUE, SouthAmerica, 0);
        Territory Brazil = new Territory(150, 50, 50, 50, "Brazil", Color.BLUE, SouthAmerica, 0);
        Territory Peru = new Territory(150, 150, 50, 50, "Peru", Color.BLUE, SouthAmerica, 0);
        Territory Argentina = new Territory(0, 100, 50, 50, "Argentina", Color.BLUE, SouthAmerica, 0);
        
        Territory Iceland = new Territory(0, 200, 50, 50, "Iceland", Color.BLUE, Europe, 0);
        Territory GreatBritain  = new Territory(50, 250, 50, 50, "GreatBritain ", Color.BLUE, Europe, 0);
        Territory Scandinavia = new Territory(150, 50, 50, 50, "Scandinavia", Color.BLUE, Europe, 0);
        Territory Ukraine = new Territory(250, 50, 50, 50, "Ukraine", Color.BLUE, Europe, 0);
        Territory NorthernEurope = new Territory(150, 150, 50, 50, "NorthernEurope", Color.BLUE, Europe, 0);
        Territory WesternEurope = new Territory(250, 250, 50, 50, "WesternEurope", Color.BLUE, Europe, 0);
        Territory SouthernEurope = new Territory(100, 100, 50, 50, "SouthernEurope", Color.BLUE, Europe, 0);

        Territory NorthAfrica = new Territory(50, 250, 50, 50, "NorthAfrica", Color.BLUE, Africa, 0);
        Territory Egypt = new Territory(150, 50, 50, 50, "Egypt", Color.BLUE, Africa, 0);
        Territory EastAfrica = new Territory(100, 50, 50, 50, "EastAfrica", Color.BLUE, Africa, 0);
        Territory Congo = new Territory(50, 150, 50, 50, "Congo", Color.BLUE, Africa, 0);
        Territory SouthAfrica = new Territory(150, 150, 50, 50, "SouthAfrica", Color.BLUE, Africa, 0);
        Territory Madagascar = new Territory(100, 150, 50, 50, "Madagascar", Color.BLUE, Africa, 0);
        
        Territory MiddleEast = new Territory(0, 200, 50, 50, "MiddleEast", Color.BLUE, Asia, 0);
        Territory Afghanistan = new Territory(50, 150, 50, 50, "Afghanistan", Color.BLUE, Asia, 0);
        Territory Ural = new Territory(150, 50, 50, 50, "Ural", Color.BLUE, Asia, 0);
        Territory India = new Territory(250, 50, 50, 50, "India", Color.BLUE, Asia, 0);
        Territory Siberia = new Territory(150, 150, 50, 50, "Siberia", Color.BLUE, Asia, 0);
        Territory Yakutsk = new Territory(250, 250, 50, 50, "Yakutsk", Color.BLUE, Asia, 0);
        Territory Irkutsk = new Territory(0, 125, 50, 50, "Irkutsk", Color.BLUE, Asia, 0);
        Territory Kamchatka = new Territory(50, 225, 50, 50, "Kamchatka", Color.BLUE, Asia, 0);
        Territory Mongolia = new Territory(150, 100, 50, 50, "Mongolia", Color.BLUE, Asia, 0);
        Territory China = new Territory(150, 250, 50, 50, "China", Color.BLUE, Asia, 0);
        Territory Siam = new Territory(150, 200, 50, 50, "Siam", Color.BLUE, Asia, 0);
        Territory Japan = new Territory(250, 200, 50, 50, "Japan", Color.BLUE, Asia, 0);
        
        Territory Indonesia = new Territory(50, 150, 50, 50, "Indonesia", Color.BLUE, Australia, 0);
        Territory NewGuinea = new Territory(150, 50, 50, 50, "NewGuinea", Color.BLUE, Australia, 0);
        Territory WesternAustralia = new Territory(150, 150, 50, 50, "WesternAustralia", Color.BLUE, Australia, 0);
        Territory EasternAustralia = new Territory(0, 150, 50, 50, "EasternAustralia", Color.BLUE, Australia, 0);
        

        
        
        
        NorthAmerica.addTerritory(Alaska);
        NorthAmerica.addTerritory(Alberta);
        NorthAmerica.addTerritory(NorthWestTerritory);
        NorthAmerica.addTerritory(Ontario);
        NorthAmerica.addTerritory(Quebec);
        NorthAmerica.addTerritory(Greenland);
        NorthAmerica.addTerritory(WesternUnitedStates);
        NorthAmerica.addTerritory(EasternUnitedStates);
        NorthAmerica.addTerritory(CentralAmerica);
        
        SouthAmerica.addTerritory(Venezuela);
        SouthAmerica.addTerritory(Peru);
        SouthAmerica.addTerritory(Brazil);
        SouthAmerica.addTerritory(Argentina);
        
        Europe.addTerritory(Iceland);
        Europe.addTerritory(GreatBritain );
        Europe.addTerritory(Scandinavia);
        Europe.addTerritory(Ukraine);
        Europe.addTerritory(NorthernEurope);
        Europe.addTerritory(SouthernEurope);
        Europe.addTerritory(WesternEurope);
        
        Africa.addTerritory(NorthAfrica);
        Africa.addTerritory(Egypt);
        Africa.addTerritory(Madagascar);
        Africa.addTerritory(SouthAfrica);
        Africa.addTerritory(EastAfrica);
        Africa.addTerritory(Congo);
        
        Asia.addTerritory(Japan);
        Asia.addTerritory(Siam);
        Asia.addTerritory(China);
        Asia.addTerritory(Mongolia);
        Asia.addTerritory(Kamchatka);
        Asia.addTerritory(Irkutsk);
        Asia.addTerritory(Yakutsk);
        Asia.addTerritory(Siberia);
        Asia.addTerritory(India);
        Asia.addTerritory(Ural);
        Asia.addTerritory(Afghanistan);
        Asia.addTerritory(MiddleEast);
        
        Australia.addTerritory(Indonesia);
        Australia.addTerritory(NewGuinea);
        Australia.addTerritory(EasternAustralia);
        Australia.addTerritory(WesternAustralia);
        
        
        continents.add(Asia);
        continents.add(NorthAmerica);
        continents.add(SouthAmerica);
        continents.add(Europe);
        continents.add(Africa);
        continents.add(Australia);

        

        this.add(Europe);
        this.add(Asia);
        this.add(NorthAmerica);
        this.add(SouthAmerica);
        this.add(Africa);
        this.add(Australia);
        this.pack();
        
        

        
        String[] numberOfPlayers = {"2", "3", "4", "5", "6"};
        JComboBox<String> myComboBox = new JComboBox<String>(numberOfPlayers);
        myComboBox.setName("numberOfHuman");
        myComboBox.setSize(100,50);
        myComboBox.setBackground(Color.WHITE);
        myComboBox.setLocation(550, 750);
        JLabel label = new JLabel("Number of Players:");
        label.setLabelFor(myComboBox);
        this.getContentPane().add(myComboBox);
        this.getContentPane().add(label);
        label.setBounds(550,680,300,100);

        String[] numberOfComp = {"2","1"};
        JComboBox<String> compBox = new JComboBox<String>(numberOfComp);
        compBox.setName("numberOfComp");
        compBox.setSize(100,50);
        compBox.setBackground(Color.WHITE);
        compBox.setLocation(250, 750);
        JLabel CompL = new JLabel("Number of AI's:");
        CompL.setLabelFor(myComboBox);
        this.getContentPane().add(compBox);
        this.getContentPane().add(CompL);
        CompL.setBounds(250,680,300,100);

        JButton startButton = new JButton("Start Game!");
        startButton.setSize(150,50);
        startButton.setBackground(Color.GRAY);
        startButton.setLocation(950, 750);
        this.getContentPane().add(startButton);
        
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	
                setNumberOfPlayer(((int)Integer.valueOf((String) myComboBox.getSelectedItem())));
                setNumberOfComp(((int)Integer.valueOf((String) compBox.getSelectedItem())));
            	JOptionPane.showMessageDialog(null, "Game started!");
            	
            	RunningMode g = new RunningMode(continents, getNumberOfComp(), getNumberOfPlayer());
                
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
	
	public void initalSharing(ArrayList<Player> players) {
		for(Player p : players){
        	for(Territory t: p.getTerritories()){
        			Continents c = t.getContinent();
        			t.setColor(p.getColor());
        			c.add(t);
                    t.setOwnerID(p.getId());
            }
        } 
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
    
    public ArrayList<Continents> getContinent() {

    	return this.continents;
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
