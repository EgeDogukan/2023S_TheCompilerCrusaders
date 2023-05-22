package uipackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import RiskPackage.Player;
import RiskPackage.PlayerNew;

public class BuildingModeNew extends JFrame {
	
	public static ArrayList<PlayerNew> getPlayerList() {
		return playerList;
	}

	public static int numberOfPlayer;
	public static int turn;
	public static WorldMap worldMap;
	public MouseListener mouseListener;
	static ArrayList<PlayerNew> playerList = new ArrayList<>();
	public ArrayList<ArrayList<Integer>> shapeList = new ArrayList<>();
	static JLabel turnShowButton;

	


	public BuildingModeNew() {
		super("Building Mode New");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1920, 1080));
        //this.setLayout(null);
        
		numberOfPlayer=4;
		turn=1;
		worldMap=new WorldMap();
		
		ArrayList<Integer> indices = new ArrayList<>();
		for(int i=1;i<numberOfPlayer+1;i++) {
			playerList.add(new PlayerNew(i, generateRandomColor(), indices));
			this.shapeList.add(indices);
		}
		JPanel panel = new JPanel();
		
		turnShowButton = new JLabel("TURN");
		turnShowButton.setForeground(Color.BLUE);
		turnShowButton.setSize(50,50);
		
		//panel.add(turnShowButton);
		this.add(worldMap.getUI());
		//this.setVisible(true);
		
		
		
		

		
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
		turnShowButton.setForeground(playerList.get(turn-1).getColor());
		
		turn++;
		
		if(turn==numberOfPlayer+1) {
			turn=1;
		}	
	}
	
	public ArrayList<ArrayList<Shape>> initalSharing(ArrayList<PlayerNew> players){
		
		ArrayList<ArrayList<Shape>> initialShapes = new ArrayList<>();
		
		for(PlayerNew player : players) {
			ArrayList<Shape> shapesOfPlayer = new ArrayList<>();
			for(Integer index : player.getShapeIndices()) {
				shapesOfPlayer.add(worldMap.getShape(index));
			}
			initialShapes.add(shapesOfPlayer);
		}
		
		return initialShapes;
		
	}
	
	public ArrayList<ArrayList<Integer>> getShapeList() {
		return shapeList;
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