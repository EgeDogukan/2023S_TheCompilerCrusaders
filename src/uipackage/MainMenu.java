package uipackage;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    private JButton startButton;
    private JButton loadButton;
    private JButton quitButton;
    private JLabel imageLabel;
    private Clip backgroundMusic;

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
        startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            // TODO: Implement code to start a new game
            System.out.println("Starting new game...");
        });

        // Create the load button and add an action listener
        loadButton = new JButton("Load Game");
        loadButton.addActionListener(e -> {
            // TODO: Implement code to load a saved game
            System.out.println("Loading saved game...");
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
        buttonPanel.add(startButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add the image to the main menu window
        ImageIcon imageIcon = new ImageIcon("map2.png");
        Image image = imageIcon.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        imageLabel = new JLabel("", resizedIcon, JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Show the main menu window and start the background music
        setVisible(true);
        backgroundMusic.start();
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
    }
}
