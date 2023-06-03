package uipackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import databasePackage.ISaveLoadAdapter;
import databasePackage.TerritoryDBDatabase;
import databasePackage.TerritoryJSONDBDatabase;

public class LoadMode extends JFrame {
	
	int numberOfPlayer;
	int numberOfComp;
	ArrayList<ArrayList<Integer>> informations;
	

	public int databaseChooser=RunningModeNew.databaseChooser;

	public LoadMode()  {
		super("Load Mode");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(null);
        ISaveLoadAdapter database;
        
        if (databaseChooser==1) {
        	System.out.println("db is chosen.");
        	database =  new TerritoryDBDatabase();
        }
        else {
        	System.out.println("json is chosen.");
        	database =  new TerritoryJSONDBDatabase();
        }
    		
        try {
    		this.informations = database.loadAll();
    		System.out.println("all loaded.");
    		} 
        catch (IOException e) {
    			e.printStackTrace();
    		}
        
        WorldMap worldMap = new WorldMap();
        
        for (ArrayList<Integer> arr : this.informations){
        	worldMap.setIndexColor(arr.get(0), new Color(arr.get(1), arr.get(2), arr.get(3)));
        }
        
        JPanel worldPanel = (JPanel) worldMap.getUI();
		worldPanel.setBounds(0, 0, worldPanel.getPreferredSize().width, worldPanel.getPreferredSize().height);
		this.add(worldPanel);
		
		pack();
		setLocationRelativeTo(null);
		this.setVisible(true);

	}
	
	
	
	

}
