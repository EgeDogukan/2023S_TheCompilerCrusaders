package uipackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import RiskPackage.*;

public class login extends JFrame{
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JFrame frame;

    public login() {
        frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(120, 120, 1207, 728);

        

        JPanel contentPane = new JPanel();
        frame.add(contentPane);
        contentPane.setLayout(new GridLayout(3, 2));

        frame.setContentPane(contentPane);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 153, 255));
		frame.setLocationRelativeTo(panel);

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        contentPane.add(usernameLabel);
        contentPane.add(usernameField);
        contentPane.add(passwordLabel);
        contentPane.add(passwordField);
        contentPane.add(new JLabel());
        contentPane.add(loginButton);
        contentPane.getRootPane().setDefaultButton(loginButton);
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (username.equals("admin") && password.equals("password")) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // launch main Risk game interface here
                    RiskBoard board = new RiskBoard();
                    frame.setVisible(false);

        // Add some territories to the board
        
        
     // Create some territories
<<<<<<< Updated upstream
        Territory alaska = new Territory(new int[] { 60, 60, 100, 80, 40 }, new int[] { 20, 60, 20, 10, 10 }, 5, "Alaska", Color.GREEN);
        Territory alberta = new Territory(new int[] { 100, 100, 140, 140, 120, 100 }, new int[] { 20, 60, 60, 80, 100, 100 }, 6, "Alberta", Color.BLUE);
        Territory westernUS = new Territory(new int[] { 140, 140, 180, 200, 200, 160 }, new int[] { 60, 100, 100, 120, 80, 60 }, 6, "Western United States", Color.ORANGE);
        Territory centralAmerica = new Territory(new int[] { 140, 160, 180, 180, 160, 160 }, new int[] { 120, 100, 120, 140, 140, 120 }, 6, "Central America", Color.YELLOW);
        Territory venezuela = new Territory(new int[] { 200, 220, 220, 200 }, new int[] { 100, 100, 120, 120 }, 4, "Venezuela", Color.CYAN);
        Territory brazil = new Territory(new int[] { 220, 260, 260, 240 }, new int[] { 60, 60, 100, 100 }, 4, "Brazil", Color.MAGENTA);
=======
        Territory alaska = new Territory(80, 80, 120,120, "Alaska", Color.GREEN);
       /* Territory alberta = new Territory(new int[] { 100, 100, 140, 140, 120, 100 }, new int[] { 20, 60, 60, 80, 100, 100 }, 6, "Alberta", Color.BLUE);
        Territory westernUS = new Territory(new int[] { 140, 140, 180, 200, 200, 160 }, new int[] { 60, 100, 100, 120, 80, 60 }, 6, "WesternUS", Color.ORANGE);
        Territory centralAmerica = new Territory(new int[] { 140, 160, 180, 180, 160, 160 }, new int[] { 120, 100, 120, 140, 140, 120 }, 6, "Central America", Color.YELLOW);
        Territory venezuela = new Territory(new int[] { 200, 220, 220, 200 }, new int[] { 100, 100, 120, 120 }, 4, "Venezuela", Color.CYAN);
        Territory brazil = new Territory(new int[] { 220, 260, 260, 240 }, new int[] { 60, 60, 100, 100 }, 4, "Brazil", Color.MAGENTA);
        Territory greenland = new Territory(new int[] {250, 260, 300, 350}, 
                							new int[] {120, 150, 200, 200}, 
                4, "Greenland", Color.decode("#03fc5a"));*/
>>>>>>> Stashed changes
        
        
        
        
	     // Add the territories to the game board
	     board.addTerritory(alaska);
	    /* board.addTerritory(alberta);
	     board.addTerritory(westernUS);
	     board.addTerritory(centralAmerica);
	     board.addTerritory(venezuela);
	     board.addTerritory(brazil);
	
	     // Add the neighbors to each territory
	        alaska.addNeighbor(alberta);
	        alaska.addNeighbor(westernUS);*/
	       /* alberta.addNeighbor(alaska);
	        alberta.addNeighbor(westernUS);
	        alberta.addNeighbor(centralAmerica);
	        westernUS.addNeighbor(alaska);
	        westernUS.addNeighbor(alberta);
	        westernUS.addNeighbor(centralAmerica);
	        centralAmerica.addNeighbor(alberta);
	        centralAmerica.addNeighbor(westernUS);
	        centralAmerica.addNeighbor(venezuela);
	        venezuela.addNeighbor(centralAmerica);
	        venezuela.addNeighbor(brazil);
	        brazil.addNeighbor(venezuela);
<<<<<<< Updated upstream
=======
	        brazil.addNeighbor(greenland);*/
>>>>>>> Stashed changes

                    GamePanel gamePanel = new GamePanel(board);
                    
<<<<<<< Updated upstream
                    frame.add(gamePanel);
                    frame.pack();
                    frame.setVisible(true);
=======
                    JFrame RiskGameFrame = new JFrame("Risk Game");
                    RiskGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    
                    RiskGameFrame.add(alaska);
                    RiskGameFrame.pack();
                    RiskGameFrame.setSize(800,600);
                    RiskGameFrame.setVisible(true);
                    RiskGameFrame.setLocationRelativeTo(null);
                    
>>>>>>> Stashed changes

                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        login loginPage = new login();
        loginPage.frame.setVisible(true);
        
        
    }

}