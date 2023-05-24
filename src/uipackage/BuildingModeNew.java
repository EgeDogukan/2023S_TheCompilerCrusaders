package uipackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import RiskPackage.Player;
import RiskPackage.PlayerNew;

import java.util.concurrent.Semaphore;

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
	JComboBox<String> numberOfPlayerComboBox;
	JComboBox<String> numberOfCompComboBox;
	PreBuildingMode preBuildingMode;
	
	public BuildingModeNew() {
		super("Building Mode New");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(null);
        turn=1;
		
		
		preBuildingMode=new PreBuildingMode();
		
		numberOfPlayer=preBuildingMode.getNumberOfPlayer();
		
		ArrayList<Integer> indices = new ArrayList<>();
		for(int i=1;i<numberOfPlayer+1;i++) {
			playerList.add(new PlayerNew(i, generateRandomColor(), indices));
			this.shapeList.add(indices);
		}

		worldMap=new WorldMap();
		JPanel worldPanel = (JPanel) worldMap.getUI();
		worldPanel.setBounds(0, 0, worldPanel.getPreferredSize().width, worldPanel.getPreferredSize().height);
        
		this.add(worldPanel);
		
		this.pack();
	    System.out.println(numberOfPlayer);
	}
	
	public static void main(String[] args) {
		
		BuildingModeNew slModeNew = new BuildingModeNew();
		slModeNew.setVisible(true);


	}
	
	
	

	
	
	public static int getNumberOfPlayer() {
		return numberOfPlayer;
	}

	public static void setNumberOfPlayer(int numberOfPlayer) {
		BuildingModeNew.numberOfPlayer = numberOfPlayer;
	}

	public static int getTurn() {
		return turn;
	}

	public static void setTurn(int turn) {
		BuildingModeNew.turn = turn;
	}

	public static WorldMap getWorldMap() {
		return worldMap;
	}

	public static void setWorldMap(WorldMap worldMap) {
		BuildingModeNew.worldMap = worldMap;
	}

	public MouseListener getMouseListener() {
		return mouseListener;
	}

	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	public static JLabel getTurnShowButton() {
		return turnShowButton;
	}

	public static void setTurnShowButton(JLabel turnShowButton) {
		BuildingModeNew.turnShowButton = turnShowButton;
	}

	public JComboBox<String> getNumberOfPlayerComboBox() {
		return numberOfPlayerComboBox;
	}

	public void setNumberOfPlayerComboBox(JComboBox<String> numberOfPlayerComboBox) {
		this.numberOfPlayerComboBox = numberOfPlayerComboBox;
	}

	public JComboBox<String> getNumberOfCompComboBox() {
		return numberOfCompComboBox;
	}

	public void setNumberOfCompComboBox(JComboBox<String> numberOfCompComboBox) {
		this.numberOfCompComboBox = numberOfCompComboBox;
	}



	public static void setPlayerList(ArrayList<PlayerNew> playerList) {
		BuildingModeNew.playerList = playerList;
	}

	public void setShapeList(ArrayList<ArrayList<Integer>> shapeList) {
		this.shapeList = shapeList;
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
	
	
	

}