package uipackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import RiskPackage.PlayerNew;
import chanceCardPackage.ChanceCardFactory;
import databasePackage.ISaveLoadAdapter;
import databasePackage.TerritoryDBDatabase;
import databasePackage.TerritoryJSONDBDatabase;

public class LoadMode {
	
	int numberOfPlayer;
	int numberOfComp;
	ArrayList<ArrayList<Integer>> informations;
	
	public ArrayList<PlayerNew> players = new ArrayList<>();
	private static JButton turn = new JButton();
	int numberOfAIPlayer;
	int numberOfHumanPlayer;
    static int turnCounter=0;
	public static boolean isContinue = true;
    public ArrayList<Shape> shapelist = new ArrayList<>();
	public static boolean isInBuildingMode = true;
	public static WorldMap worldMap;
	
	public int databaseChooser=RunningModeNew.databaseChooser;
	

	public LoadMode()  {
        
        
    	
    	
        ISaveLoadAdapter database;
        
        if (RunningModeNew.databaseChooser==1) {
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
        
        ArrayList<Color> colors = new ArrayList<>();
        System.out.println(this.informations);
        
        for (ArrayList<Integer> arr : this.informations){
        	Color curColor = new Color(arr.get(1), arr.get(2), arr.get(3));
        	worldMap.shapeDomain.setIndexColor(arr.get(0), curColor);
        	//System.out.println(curColor);
        	worldMap.shapeDomain.setShapeArmyArtillery(worldMap.shapeList.get(arr.get(0)), arr.get(4));
        	worldMap.shapeDomain.setShapeArmyCavalry(worldMap.shapeList.get(arr.get(0)), arr.get(5));
        	worldMap.shapeDomain.setShapeArmyInfantry(worldMap.shapeList.get(arr.get(0)), arr.get(6));
        	
        	if (colors.size()==0) {
    			colors.add(curColor);
    		}
        	
        	Iterator<Color> iterator = colors.iterator();
            boolean colorExists = false;
            
            while (iterator.hasNext()) {
                Color color = iterator.next();
                if (curColor.getRed() == color.getRed() && curColor.getGreen() == color.getGreen()
                        && curColor.getBlue() == color.getBlue()) {
                    colorExists = true;
                    break;
                }
            }
            
            if (!colorExists) {
                colors.add(curColor);
            }
        }
        
        System.out.println("informations");
        System.out.println("********");
        System.out.println(informations);
        System.out.println("**********");
        System.out.println("colors.size() is "+colors.size());
        
        for (int i=1;i<colors.size()+1;i++) {
        	
        	players.add(new PlayerNew(i, colors.get(i-1), informations.get(i-1)));
        }
        
        RunningModeNew.turnCounter=1;
        BuildingModeNew.turn=1;
        
        for(PlayerNew playerNew : players) {
        	System.out.println(playerNew);
        }
        
        BuildingModeNew.playerList=players;
        WorldMap.isInBuildingMode=false;
        
        new RunningModeNew(players, worldMap, 1);
        
        
	}
	
	
	
	
	
	

}
