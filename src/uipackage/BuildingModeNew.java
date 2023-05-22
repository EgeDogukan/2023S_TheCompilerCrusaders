package uipackage;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import RiskPackage.PlayerNew;

public class BuildingModeNew extends JFrame {
	
	public static int numberOfPlayer;
	public static int turn;
	public static WorldMap worldMap;
	public MouseListener mouseListener;
	static ArrayList<PlayerNew> playerList = new ArrayList<>();
	JPanel panel = new JPanel();

	public BuildingModeNew() {
		super("Building Mode New");
		this.numberOfPlayer=4;
		this.turn=1;
		worldMap=new WorldMap();
		
		ArrayList<Integer> indices = new ArrayList<>();
		for(int i=1;i<numberOfPlayer+1;i++) {
			playerList.add(new PlayerNew(i, generateRandomColor(), indices));
		}
		
		System.out.println("slm");
		this.mouseListener=worldMap.getMouseListener();
		
			
		this.panel.add(worldMap.getUI());
		this.add(panel);

		
	}
	
	private Color generateRandomColor() {
	    int red = (int) (Math.random() * 256);
	    int green = (int) (Math.random() * 256);
	    int blue = (int) (Math.random() * 256);

	    return new Color(red, green, blue);
	}

	public static void nextTurn() {
		int clicked = worldMap.clickedShapeIndex;
		System.out.println(clicked);
		playerList.get(turn-1).getShapeIndices().add(clicked);
		
		worldMap.setIndexColor(clicked, playerList.get(turn-1).getColor());
		
		
		turn++;
		if(turn==numberOfPlayer+1) {
			turn=1;
		}	
	}

	
	
	public static void main(String[] args) {
		
		 BuildingModeNew buildingModeNew = new BuildingModeNew();
		 BuildingModeNew.worldMap = new WorldMap();

	     buildingModeNew.setResizable(false);
	     buildingModeNew.pack();
	     buildingModeNew.setVisible(true);
		 boolean flag = true;
		 
		 do{
			System.out.println(WorldMap.isEveryTerritorySelected);
			if(WorldMap.isEveryTerritorySelected){
				System.out.println("Building Mode Ended.");
				flag = false;
		 		buildingModeNew.dispose();
			}
		}
		 while(flag);
		 	
		 
	}

}



