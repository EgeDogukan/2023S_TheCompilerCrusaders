package RiskPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextField;

import armyCardPackage.ArmyCardFactory;
import armyCardPackage.IArmyCard;
import chanceCardPackage.IChanceCard;
import uipackage.RunningModeNew;
import uipackage.WorldMap;

public class PlayerNew {

	int id;
	Color color;
	ArrayList<Integer> shapeIndices;
	ArrayList<Integer> chanceCards;
	boolean cardUsable=false;
	ArrayList<IArmyCard> armyCards;
	ArmyCardFactory cardFactory = new ArmyCardFactory();

	
	public PlayerNew(int id, Color color, ArrayList<Integer> shapeIndices) {
		this.id=id;
		this.color=color;
		this.shapeIndices=shapeIndices;
		chanceCards = new ArrayList<>();
		armyCards = new ArrayList<>();
	}
	
	public void addChanceCard(int chanceCardType) {
		this.chanceCards.add(chanceCardType);
	}

	public void addArmyCard(int ArmyCardType, int numberOfCard){
		for(int j = 0; j<numberOfCard;j++){
			armyCards.add(cardFactory.createArmyCard(ArmyCardType));
		}
	}

	public ArrayList<Integer> getArmyCardsInfo(){
		ArrayList<Integer> info = new ArrayList<>(3);
		for (int i = 0; i < 3; i++) {
            info.add(0);
        }
		for(IArmyCard cards: armyCards){
			if(cards.getType()==0){
				info.set(0, info.get(0)+1);
			}
			else if(cards.getType()==1){
				info.set(1, info.get(1)+1);
			}
			else{
				info.set(2, info.get(2)+1);
			}


		}
		return info;
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
		if (this.cardUsable)
			RunningModeNew.players.get(RunningModeNew.getTurn()).shapeIndices.add(territoryIndex);
	}
	
	public void useCoupCard(int territoryIndex) {
		
		if (this.cardUsable) {
			RunningModeNew.players.get(RunningModeNew.getTurn()).shapeIndices.add(territoryIndex);
			
			RunningModeNew.worldMap.setShapeArmyArtillery(RunningModeNew.worldMap.getShape(territoryIndex), 0);
			RunningModeNew.worldMap.setShapeArmyCavalry(RunningModeNew.worldMap.getShape(territoryIndex), 0);
			RunningModeNew.worldMap.setShapeArmyInfantry(RunningModeNew.worldMap.getShape(territoryIndex), 0);
		}
		
	}
	
	public void useRevoltCard(int sourceIndex, int targetIndex) {
		
		if (this.cardUsable) {
			int artillery1 = RunningModeNew.worldMap.getShapeArmyArtillery(sourceIndex);
			int cavalry1 = RunningModeNew.worldMap.getShapeArmyCavalry(sourceIndex);
			int infantry1 = RunningModeNew.worldMap.getShapeArmyInfantry(sourceIndex);
			
			int artillery2 = RunningModeNew.worldMap.getShapeArmyArtillery(targetIndex);
			int cavalry2 = RunningModeNew.worldMap.getShapeArmyCavalry(targetIndex);
			int infantry2 = RunningModeNew.worldMap.getShapeArmyInfantry(targetIndex);
			
			
			RunningModeNew.worldMap.setShapeArmyArtillery(RunningModeNew.worldMap.getShape(targetIndex), artillery1+artillery2);
			RunningModeNew.worldMap.setShapeArmyCavalry(RunningModeNew.worldMap.getShape(targetIndex), cavalry1+cavalry2);
			RunningModeNew.worldMap.setShapeArmyInfantry(RunningModeNew.worldMap.getShape(targetIndex), infantry1+infantry2);
			
			RunningModeNew.worldMap.setShapeArmyArtillery(RunningModeNew.worldMap.getShape(sourceIndex), 0);
			RunningModeNew.worldMap.setShapeArmyCavalry(RunningModeNew.worldMap.getShape(sourceIndex), 0);
			RunningModeNew.worldMap.setShapeArmyInfantry(RunningModeNew.worldMap.getShape(sourceIndex), 0);
		}	
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
