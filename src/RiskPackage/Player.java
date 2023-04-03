package RiskPackage;

import java.awt.Color;
import java.util.LinkedList;

public class Player {
	
	private int id;
	private Color color;
	private Army army;
	private LinkedList<Card> cardDeck;
	
	public Player(int id, Color color) {
		this.id=id;
		this.color=color;
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

	public LinkedList<Card> getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(LinkedList<Card> cardDeck) {
		this.cardDeck = cardDeck;
	}
	
	

}
