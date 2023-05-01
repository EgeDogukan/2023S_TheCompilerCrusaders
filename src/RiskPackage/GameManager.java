package RiskPackage;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
public class GameManager extends JFrame {
	RiskBoard board;
	Color color;
	GamePanel game;

	public GameManager(GamePanel game) {
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.add(game);
		this.pack();
	}
	

}
