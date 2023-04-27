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

                if (username.equals("a") && password.equals("a")) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // launch main Risk game interface here
                    RiskBoard board = new RiskBoard();
                    frame.setVisible(false);
                    frame.dispose();

        
                    

                    JFrame RiskGameFrame = new JFrame("Risk Game");
                    RiskGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    
                    RiskGameFrame.setPreferredSize(new Dimension(800, 600));
                    RiskGameFrame.setLocationRelativeTo(null);
                    Territory alaska = new Territory(80, 80, 120,120, "Alaska", Color.GREEN);
                    RiskGameFrame.add(alaska);
                    RiskGameFrame.getContentPane().setLayout(null);
                    RiskGameFrame.setLocationRelativeTo(null);
                    RiskGameFrame.pack();
                    RiskGameFrame.setVisible(true);

                    


                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
                }
            }
        });

    }

    public static void main(String[] args) {
        login loginPage = new login();
        loginPage.frame.setVisible(true);
    }
}