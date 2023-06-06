package RiskPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextField;

import chanceCardPackage.IChanceCard;
import uipackage.RunningModeNew;
import uipackage.WorldMap;

public class PlayerNew {

	int id;
	Color color;
	ArrayList<Integer> shapeIndices;
	ArrayList<Integer> chanceCards;
	boolean cardUsable=false;
	
	public PlayerNew(int id, Color color, ArrayList<Integer> shapeIndices) {
		this.id=id;
		this.color=color;
		this.shapeIndices=shapeIndices;
		chanceCards = new ArrayList<>();
	}
	
	public void addChanceCard(int chanceCardType) {
		this.chanceCards.add(chanceCardType);
	}
	
	
	public void useCard(int chanceCardType) {
		int index = this.chanceCards.indexOf(chanceCardType);
		
		if (index!=-1) {
			this.chanceCards.remove(index);
			this.cardUsable=true;
			
		}
		else {
			System.out.println("You don't have this card.");
			this.cardUsable=false;
		}
	}
	
	public void useReinforcementCard(int territoryIndex) {
		
		
		if (this.cardUsable) {
			Random random = new Random();
			int randomNumber = random.nextInt(3) + 1;
			RunningModeNew.worldMap.setShapeArmyArtillery(RunningModeNew.worldMap.getShape(territoryIndex), randomNumber);
			System.out.println("Succesfully "+randomNumber+" added to the territory with index: "+territoryIndex);
		}
	
	}
	
	public void useNuclearStrikeCard(int sourceIndex, int targetIndex) {
		
		if (this.cardUsable) {
			RunningModeNew.worldMap.setShapeArmyArtillery(RunningModeNew.worldMap.getShape(sourceIndex), 0);
			RunningModeNew.worldMap.setShapeArmyArtillery(RunningModeNew.worldMap.getShape(targetIndex), 0);
			
			RunningModeNew.worldMap.setShapeArmyCavalry(RunningModeNew.worldMap.getShape(sourceIndex), 0);
			RunningModeNew.worldMap.setShapeArmyCavalry(RunningModeNew.worldMap.getShape(targetIndex), 0);
			
			RunningModeNew.worldMap.setShapeArmyInfantry(RunningModeNew.worldMap.getShape(sourceIndex), 0);
			RunningModeNew.worldMap.setShapeArmyInfantry(RunningModeNew.worldMap.getShape(targetIndex), 0);
		}
	}
	

	public void useRevolutionCard(int territoryIndex) {
		RunningModeNew.players.get(RunningModeNew.getTurn()).shapeIndices.add(territoryIndex);
	}
	
	public void useCoupCard(int territoryIndex) {
		
		RunningModeNew.players.get(RunningModeNew.getTurn()).shapeIndices.add(territoryIndex);
		
		RunningModeNew.worldMap.setShapeArmyArtillery(RunningModeNew.worldMap.getShape(territoryIndex), 0);
		RunningModeNew.worldMap.setShapeArmyCavalry(RunningModeNew.worldMap.getShape(territoryIndex), 0);
		RunningModeNew.worldMap.setShapeArmyInfantry(RunningModeNew.worldMap.getShape(territoryIndex), 0);
		
	}
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ArrayList<Integer> getShapeIndices() {
		return shapeIndices;
	}

	public void setShapeIndices(ArrayList<Integer> shapeIndices) {
		this.shapeIndices = shapeIndices;
	}
	

}
