package uipackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import RiskPackage.Territory;

public class BuildingMode extends JFrame {

	public BuildingMode() {

        super("Building Mode");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200, 800));
        this.setLayout(null);
 
        Territory US = new Territory(30, 150, 40, 40, "US", Color.BLUE);
        Territory US1 = new Territory(75, 150, 40, 40, "US", Color.BLUE);
        Territory Canada = new Territory(130, 150, 40, 40, "Canada", Color.RED);
        Territory UK = new Territory(230, 250, 40, 40, "UK", Color.GREEN);
        Territory Europe = new Territory(330, 350, 40, 40, "Europe", Color.BLACK);
       
        this.add(US);
        this.add(US1);
        this.add(Canada);
        this.add(UK);
        this.add(Europe);
        this.pack();
        
        JButton startButton = new JButton("Start Game!");
        startButton.setSize(100,100);
        startButton.setBackground(Color.GRAY);
        startButton.setLocation(850, 650);
        this.getContentPane().add(startButton);
        
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click event
            	JOptionPane.showMessageDialog(null, "Game started!");
            }
        });
        
        String[] numberOfPlayers = {"2", "3", "4", "5", "6"};
        JComboBox<String> myComboBox = new JComboBox<String>(numberOfPlayers);
        myComboBox.setSize(100,100);
        myComboBox.setBackground(Color.WHITE);
        myComboBox.setLocation(550, 650);
        this.getContentPane().add(myComboBox);
        
	}
	
	public static void main(String[] args) {
		BuildingMode RiskGameFrame = new BuildingMode();
		RiskGameFrame.setLayout(null);
        RiskGameFrame.setVisible(true);
	}

}
