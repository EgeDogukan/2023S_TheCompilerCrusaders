package RiskPackage;

import java.awt.Color;
import java.util.ArrayList;

import chanceCardPackage.IChanceCard;

public class PlayerNew {

	int id;
	Color color;
	ArrayList<Integer> shapeIndices;
	ArrayList<Integer> chanceCards;
	
	public PlayerNew(int id, Color color, ArrayList<Integer> shapeIndices) {
		this.id=id;
		this.color=color;
		this.shapeIndices=shapeIndices;
	}
	
	public void addChanceCard(int chanceCardType) {
		this.chanceCards.add(chanceCardType);
	}
	
	public void removeChanceCard(int chanceCardType) {
		int index = this.chanceCards.indexOf(chanceCardType);
		
		if (index!=-1) 
			this.chanceCards.remove(index);
		
		else 
			throw new IllegalArgumentException("You don't have this card.");
		
		
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
