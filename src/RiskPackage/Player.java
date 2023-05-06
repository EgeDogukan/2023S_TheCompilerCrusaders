package RiskPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import cardPackage.IArmyCard;

public class Player {
	
	private int id;
	private Color color;
	private Army army;
	//private LinkedList<Card> cardDeck;
	private String name;
	private boolean takeTurn = false;
	protected ArrayList<Territory> territories;
	public ArrayList<IArmyCard> armyCards = new ArrayList<IArmyCard>();
	
	public Player(int id, Color color,  ArrayList<Territory> territories) {
		this.id=id;
		this.color=color;
		this.territories = territories;
		//for(Territory ter : territories)
			//this.territories.add(ter);
	}
	
	public ArrayList<Territory> getTerritories(){
		return territories;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void takeTurn(int id){
		if (id == this.id) { takeTurn = true;}
		else  {takeTurn = false;}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Army getArmy() {
		return army;
	}

	public void setArmy(Army army) {
		this.army = army;
	}
	
	public void addArmyCard(IArmyCard card) {
		this.armyCards.add(card);
	}


	
	

}
