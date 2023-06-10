package uipackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
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

import RiskPackage.GameControllerNew;
import RiskPackage.PlayerNew;


import java.util.concurrent.Semaphore;

public class BuildingModeNew extends JFrame {
	
	public static ArrayList<PlayerNew> getPlayerList() {
		return playerList;
	}

	public static int numberOfPlayer;
	public static int numberOfCompPlayer;
	public static int turn;
	public static WorldMap worldMap;
	public MouseListener mouseListener;
	static ArrayList<PlayerNew> playerList = new ArrayList<>();
	public ArrayList<ArrayList<Integer>> shapeList = new ArrayList<>();
	private boolean isInBuildingMode = true;
	public static ArrayList<ArrayList<Integer>> playerShapes = new ArrayList<ArrayList<Integer>>(100);
	public static JPanel turnPanel = new JPanel();
	public static JButton nextButton;
	
	JComboBox<String> numberOfPlayerComboBox;
	JComboBox<String> numberOfCompComboBox;
	
	public BuildingModeNew(int numberOfPlayer, int numberOfCompPlayer) {
		super("Building Mode New");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1920, 1080));
		this.setLayout(null); // Use BorderLayout for the JFrame
	
		turn = 1;
		this.numberOfPlayer = numberOfPlayer;
		this.numberOfCompPlayer = numberOfCompPlayer;
	
		ArrayList<Integer> indices = new ArrayList<>();
		System.out.println("number of comp player kerem added:" +numberOfCompPlayer);
		for (int i = 1; i < numberOfPlayer+numberOfCompPlayer + 1; i++) {
			playerList.add(new PlayerNew(i, generateRandomColor(), indices));
			this.shapeList.add(indices);
		}
	
		worldMap = new WorldMap();
		JPanel worldPanel = (JPanel) worldMap.getUI();
		worldPanel.setBounds(0, 0, worldPanel.getPreferredSize().width, worldPanel.getPreferredSize().height);
		this.add(worldPanel); // Add the map panel to the center of the JFrame
	
		
		nextButton = new JButton("Next");
		nextButton.setBounds(1200, 300, 100, 100); // Set preferred size for the button
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add the code to execute when the "Next" button is clicked
				//ArrayList<ArrayList<Shape>> shapelist, ArrayList<PlayerNew> players , int numberOfAIPlayer, int numberOfHumanPlayer
				
				worldMap.setIsInBuildingMode(false);
				BuildingModeNew.this.dispose();
				
				if (BuildingModeNew.numberOfCompPlayer==1) {
					System.out.println("Running mode started with 1 Computer Player");
					RunningModeNew runningModeNew = new RunningModeNew(playerList, worldMap, 1);
				}
				else {
					System.out.println("Running mode started with 2 Computer Player");
					RunningModeNew runningModeNew = new RunningModeNew(playerList, worldMap, 2);
				}
			}
		});
		nextButton.setVisible(false);
		this.add(nextButton);

		this.add(turnPanel);
		
		

	
		pack();
		setLocationRelativeTo(null); // Center the JFrame on the screen
		setVisible(true);
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
	

	public static void nextTurn(WorldMap worldMap) {
		
		turnPanel.setBounds(1400, 300, 100, 100);
		turnPanel.setVisible(true);
		
		
		int clicked = WorldMap.clickedShapeIndex;
		PlayerNew player = playerList.get(turn - 1);
		System.out.println(clicked);
		
		player.getShapeIndices().add(clicked);
		
		
		worldMap.setIndexColor(clicked, playerList.get(turn-1).getColor());	
		turn++;
		
		if(turn==numberOfPlayer+numberOfCompPlayer+1) {
			turn=1;
		}	
		
		
		
		BuildingModeNew.turnPanel.setBackground(playerList.get(turn-1).getColor());
		if (worldMap.getIsAllSelected()) {
			BuildingModeNew.nextButton.setVisible(true);
			System.out.println("nextButton appeared.");
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