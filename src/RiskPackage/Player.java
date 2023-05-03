package RiskPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
	
	private int id;
	private Color color;
	private Army army;
	//private LinkedList<Card> cardDeck;
	private String name;
	protected ArrayList<Territory> territories;
	
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

//	public LinkedList<Card> getCardDeck() {
//		return cardDeck;
//	}
//
//	public void setCardDeck(LinkedList<Card> cardDeck) {
//		this.cardDeck = cardDeck;
//	}
	
	

}
