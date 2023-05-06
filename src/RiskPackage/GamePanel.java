package RiskPackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private RiskBoard board;

    public GamePanel(RiskBoard board) {
        this.board = board;
        
        this.setSize(1920,1080);
        this.setLayout(new BorderLayout());
        
       
        this.add(board);
        
        
		this.setOpaque(true);
        this.setFocusable(true);
        this.setEnabled(true);
        this.setVisible(true);

    }

    
}