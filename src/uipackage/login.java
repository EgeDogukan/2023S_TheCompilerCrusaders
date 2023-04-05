package uipackage;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import RiskPackage.*;
import database.Database;

public class login extends JFrame{
    Database db = new Database();

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
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                try {
                    if (db.checkUser(username)){
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        // launch main Risk game interface here
                        RiskBoard board = new RiskBoard();
                        frame.setVisible(false);
                    

                    /*if (username.equals("admin") && password.equals("password")) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        // launch main Risk game interface here
                        RiskBoard board = new RiskBoard();
                        frame.setVisible(false);*/

      // Add some territories to the board
      
      
    // Create some territories
      Territory alaska = new Territory(new int[] { 60, 60, 100, 80, 40 }, new int[] { 20, 60, 20, 10, 10 }, 5, "Alaska", Color.GREEN,5);
      Territory alberta = new Territory(new int[] { 100, 100, 140, 140, 120, 100 }, new int[] { 20, 60, 60, 80, 100, 100 }, 6, "Alberta", Color.BLUE,6);
      Territory westernUS = new Territory(new int[] { 140, 140, 180, 200, 200, 160 }, new int[] { 60, 100, 100, 120, 80, 60 }, 6, "Western United States", Color.ORANGE,7);
      Territory centralAmerica = new Territory(new int[] { 140, 160, 180, 180, 160, 160 }, new int[] { 120, 100, 120, 140, 140, 120 }, 6, "Central America", Color.YELLOW,3);
      Territory venezuela = new Territory(new int[] { 200, 220, 220, 200 }, new int[] { 100, 100, 120, 120 }, 4, "Venezuela", Color.CYAN,7);
      Territory brazil = new Territory(new int[] { 220, 260, 260, 240 }, new int[] { 60, 60, 100, 100 }, 4, "Brazil", Color.MAGENTA,8);
      
      
      
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
                } catch (HeadlessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    
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