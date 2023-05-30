package uipackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class login extends JFrame {
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    public JFrame frame;
    private JButton registerButton;
    public boolean isSuccessful = false;

    public login() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(120, 120, 800, 500);

        // Set the background image
        ImageIcon backgroundImage = new ImageIcon("login.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridLayout(10, 10, 10, 10));
        frame.setContentPane(backgroundLabel);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(153, 153, 255));
        frame.setLocationRelativeTo(panel);

        usernameLabel = new JLabel("                                                       Username:");
        passwordLabel = new JLabel("                                                       Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        registerButton = new JButton("Register");

        // HELP SCREEN DISPLAYING
        JButton helpScreenButton = new JButton("Help");
        helpScreenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to execute when the button is clicked
                helpScreen helpScreen = new helpScreen("Welcome to the Login Screen!\n\n"
                + "To log in, enter your username and password in the respective fields and click the 'Login' button.\n\n"
                + "If you don't have an account yet, you can click the 'Register' button to create a new account.\n\n");
                helpScreen.setVisible(true);
            }
        });

        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(helpScreenButton);
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(usernameLabel);
        backgroundLabel.add(usernameField);
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(passwordLabel);
        backgroundLabel.add(passwordField);
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(loginButton);
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel("Don't have an account?"));
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(registerButton);
        backgroundLabel.add(new JLabel()); // adding empty JLabels for arranging the grid layout display
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel());
        backgroundLabel.add(new JLabel());

        backgroundLabel.getRootPane().setDefaultButton(loginButton);

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
                                if (password.equals(line.split(" ")[0])) {
                                    JOptionPane.showMessageDialog(null, "This username has already been taken!");
                                    dupFlag = 1;
                                    registerFrame.dispose();
                                    break;
                                }
                                lineCount++;
                            }
                        } catch (IOException x) {
                            x.printStackTrace();
                        }
                        if (dupFlag == 0) {
                            try (BufferedWriter br = new BufferedWriter(new FileWriter(absoFileName, true))) {
                                br.append(password);
                                br.append(" ");
                                // br.newLine();
                                br.append(username);
                                br.newLine();
                                JOptionPane.showMessageDialog(null, "Register successful!");
                                registerFrame.dispose();
                            } catch (IOException x) {
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

                        if (username.equals(line.split(" ")[0]) && password.equals(line.split(" ")[1])) {
                            contFlag = 2;
                        }
                    }
                } catch (IOException x) {
                    x.printStackTrace();
                }

                if (contFlag == 2) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    System.out.println("BURADAYIZ");

                    setLoginStatus(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
                }
            }
        });
    }

    public boolean getLoginStatus() {
        return this.isSuccessful;
    }

    public void setLoginStatus(boolean newStatus) {
        this.isSuccessful = true;
    }

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

        System.out.println(status);
    }
}
