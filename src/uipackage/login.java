package uipackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import RiskPackage.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class login extends JFrame{
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    public JFrame frame;
    private JButton registerButton;

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
        registerButton = new JButton("Register");

        contentPane.add(usernameLabel);
        contentPane.add(usernameField);
        contentPane.add(passwordLabel);
        contentPane.add(passwordField);
        contentPane.add(new JLabel());
        contentPane.add(loginButton);
        contentPane.add(registerButton);
        contentPane.getRootPane().setDefaultButton(loginButton);

        File f = new File("database.txt");
        String absoFileName = f.getAbsolutePath();

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JLabel usernameLabel = new JLabel("New Username:");
                JLabel passwordLabel = new JLabel("New Password:");
                JTextField newPassword = new JTextField();
                JTextField newUsername = new JTextField();
                JPanel registerPanel = new JPanel();
                JButton saveEssentialsButton = new JButton("Register Now!");

                registerPanel.add(usernameLabel);
                registerPanel.add(passwordLabel);
                registerPanel.add(newPassword);
                registerPanel.add(newUsername);
                registerPanel.add(saveEssentialsButton);
                

                JFrame registerFrame = new JFrame();

                
                registerFrame.setVisible(true);
                registerFrame.setResizable(false); 
                registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                registerFrame.setBounds(120, 120, 1207, 728);
                registerFrame.add(registerPanel);
                registerPanel.setLayout(new GridLayout(3, 2));
                registerFrame.setContentPane(registerPanel);           
                registerPanel.setBackground(new Color(153, 153, 255));
                registerPanel.getRootPane().setDefaultButton(saveEssentialsButton);

                saveEssentialsButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a) {
                        String username = newUsername.getText();
                        String password = newPassword.getText();

                        int lineCount = 0;
                        int dupFlag = 0;

                        try (BufferedReader br = new BufferedReader(new FileReader(absoFileName))) {
                            String line;
                            
                            while ((line = br.readLine()) != null) {
                                System.out.println(lineCount);
                                if(password.equals(line)) {
                                    JOptionPane.showMessageDialog(null, "This username has already been taken!");
                                    dupFlag = 1;
                                    registerFrame.dispose();
                                    break;
                                }
                                lineCount++;
                                }
                            }catch (IOException x) {
                                x.printStackTrace();
                            }                        
                        if(dupFlag == 0) {
                            try (BufferedWriter br = new BufferedWriter(new FileWriter(absoFileName, true))) {
                                br.append(password);
                                br.newLine();
                                br.append(username);
                                br.newLine();
                                JOptionPane.showMessageDialog(null, "Register successful!");
                                registerFrame.dispose();
                            }catch (IOException x) {
                                x.printStackTrace();
                            }
                        }
                    }
                });           
            }
        });
  
        loginButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);


                int contFlag = 0;

                try (BufferedReader br = new BufferedReader(new FileReader(absoFileName))) {
                    String line;
                    
                    while ((line = br.readLine()) != null) {
                        if(username.equals(line) && contFlag == 0) {
                        contFlag = 1;
                        }
                        if(contFlag == 1 && password.equals(line)) {
                        contFlag = 2;
                        }
                    }
                }catch (IOException x) {
                    x.printStackTrace();
                }     

                if (contFlag == 2) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // launch main Risk game interface here
                    RiskBoard board = new RiskBoard();
                    frame.setVisible(false);

        // Add some territories to the board
        
        
     // Create some territories
        Territory alaska = new Territory(new int[] { 60, 60, 100, 80, 40 }, new int[] { 20, 60, 20, 10, 10 }, 5, "Alaska", Color.GREEN);
        Territory alberta = new Territory(new int[] { 100, 100, 140, 140, 120, 100 }, new int[] { 20, 60, 60, 80, 100, 100 }, 6, "Alberta", Color.BLUE);
        Territory westernUS = new Territory(new int[] { 140, 140, 180, 200, 200, 160 }, new int[] { 60, 100, 100, 120, 80, 60 }, 6, "Western United States", Color.ORANGE);
        Territory centralAmerica = new Territory(new int[] { 140, 160, 180, 180, 160, 160 }, new int[] { 120, 100, 120, 140, 140, 120 }, 6, "Central America", Color.YELLOW);
        Territory venezuela = new Territory(new int[] { 200, 220, 220, 200 }, new int[] { 100, 100, 120, 120 }, 4, "Venezuela", Color.CYAN);
        Territory brazil = new Territory(new int[] { 220, 260, 260, 240 }, new int[] { 60, 60, 100, 100 }, 4, "Brazil", Color.MAGENTA);
        
        
        
        
	     // Add the territories to the game board
	     board.addTerritory(alaska);
	     board.addTerritory(alberta);
	     board.addTerritory(westernUS);
	     board.addTerritory(centralAmerica);
	     board.addTerritory(venezuela);
	     board.addTerritory(brazil);
	
	     // Add the neighbors to each territory
	        alaska.addNeighbor(alberta);
	        alaska.addNeighbor(westernUS);
	        alberta.addNeighbor(alaska);
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

                    GamePanel gamePanel = new GamePanel(board);

                    JFrame frame = new JFrame("Risk Game");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    
                    frame.add(gamePanel);
                    frame.pack();
                    frame.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
                }
            }
        });
    }
}