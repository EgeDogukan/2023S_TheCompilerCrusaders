package uipackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import RiskPackage.Territory;

public class BuildingMode extends JFrame {

	public BuildingMode() {

        super("Building Mode");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200, 800));
        this.setLayout(null);
        
        
        Territory US = new Territory(30, 50, 40, 40, "US", Color.BLUE);
        Territory Canada = new Territory(130, 150, 40, 40, "Canada", Color.RED);
        Territory UK = new Territory(230, 250, 40, 40, "UK", Color.GREEN);
        Territory Europe = new Territory(330, 350, 40, 40, "Europe", Color.BLACK);
       
        
        this.add(US);
        this.add(Canada);
        this.add(UK);
        this.add(Europe);
        
        this.pack();
        
        
	}
	
	public static void main(String[] args) {
		BuildingMode RiskGameFrame = new BuildingMode();
		RiskGameFrame.setLayout(null);
        RiskGameFrame.setVisible(true);
	}

}
