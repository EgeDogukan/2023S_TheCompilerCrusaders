package uipackage;

import javax.sound.sampled.*;
import javax.swing.*;

import RiskPackage.GameControllerNew;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private JButton startButton;
    private JButton loadButton;
    private JButton quitButton;
    private JButton loginButton;
    private JLabel imageLabel1;
    private JLabel imageLabel2;
    private Clip backgroundMusic;

    private int imageX; // X-coordinate of the image
    
    public boolean isStartClicked;
    
	public boolean isLoginClicked;
	
	
    public MainMenu() {
        // Set up the main menu window
        super("Risk Game Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Load the background music
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("music.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("Error loading background music: " + ex.getMessage());
        }

        // Create the start button and add an action listener
        startButton = new JButton("New Game");
        startButton.addActionListener(e -> {
            
            System.out.println("Starting new game...");
        });

        // Create the load button and add an action listener
        loadButton = new JButton("Load Game");
        loadButton.addActionListener(e -> {
   
            System.out.println("Loading saved game...");
        });

        loginButton = new JButton("Login/Register");
        loginButton.addActionListener(e -> {

        	isLoginClicked=true;
            proceedToLogin();
            backgroundMusic.stop();
            //System.out.println("Opening login screen...");
           

        });

        // Create the quit button and add an action listener
        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            // Stop the background music and close the main menu window
            backgroundMusic.stop();
            dispose();
        });

        // Add the buttons to the main menu window
        JPanel buttonPanel = new JPanel();
        //buttonPanel.add(startButton);
        //buttonPanel.add(loadButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Create the layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 400));

        // Add the layered pane to the main menu window
        add(layeredPane, BorderLayout.CENTER);

        // Add the image labels to the layered pane
        String cwd = System.getProperty("user.dir");
        ImageIcon imageIcon = new ImageIcon(cwd + "/src/uipackage/menuMap.png");
        Image image = imageIcon.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        imageLabel1 = new JLabel("", resizedIcon, JLabel.CENTER);
        imageLabel1.setBounds(0, 0, 600, 300);
        layeredPane.add(imageLabel1, new Integer(0)); // Add the image label with lower layer

        imageLabel2 = new JLabel("", resizedIcon, JLabel.CENTER);
        imageLabel2.setBounds(-600, 0, 600, 300);
        layeredPane.add(imageLabel2, new Integer(0)); // Add the image label with lower layer
        
        ImageIcon imageIcon1 = new ImageIcon(cwd +"/src/uipackage/menuTitle.png");
        Image image1 = imageIcon1.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(image1);
        JLabel titleLabel = new JLabel("", resizedIcon1, JLabel.CENTER);
        titleLabel.setBounds(0, 0, 600, 300);
        layeredPane.add(titleLabel, new Integer(1)); // Add the title label with higher layer


        // Set the initial X-coordinate of the images
        imageX = 0;

        // Create a timer to update the image position
        int delay = 10; // Delay in milliseconds
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the image position
                imageX += 1; // Increase X-coordinate by 1

                // Update the location of the image labels
                imageLabel1.setLocation(imageX, 0);
                imageLabel2.setLocation(imageX - 600, 0);

                // If the first image reaches the right edge, reset its position to the left edge
                if (imageX >= 600) {
                    imageX = 0;
                }
            }
        });

        // Start the timer
        timer.start();

        // Show the main menu window and start the background music
        setVisible(true);
        backgroundMusic.start();
    }

    private void proceedToLogin() {
        UIController uiController = UIController.getUiController();
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
    }
}
