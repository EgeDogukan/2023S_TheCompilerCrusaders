package uipackage;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import databasePackage.ISaveLoadAdapter;
import databasePackage.TerritoryDBDatabase;
import databasePackage.TerritoryJSONDBDatabase;

public class LoadMode extends JFrame {
	
	int numberOfPlayer;
	int numberOfComp;
	int[] informations;
	

	public int databaseChooser=0;

	public LoadMode()  {
		super("Load Mode");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(null);
        ISaveLoadAdapter database;
        
        if (databaseChooser==0) {
        	database =  new TerritoryDBDatabase();
        }
        else {
        	database =  new TerritoryJSONDBDatabase();
        }
    		
        try {
    		this.informations = database.loadAll();
    		} 
        catch (IOException e) {
    			e.printStackTrace();
    		}
        
       
		
	}
	
	
	
	

}
