package RiskPackage;

import java.awt.Color;
import java.util.LinkedList;

public class Player {
	
	private int id;
	private Color color;
	private Army army;
	//private LinkedList<Card> cardDeck;
	private String name;
	
	public Player(int id, Color color, String name) {
		this.id=id;
		this.color=color;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
