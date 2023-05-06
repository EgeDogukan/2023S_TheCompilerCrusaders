package RiskPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import cardPackage.IArmyCard;
import cardPackage.IChanceCard;

public class Player {
	
	private int id;
	private Color color;
	private Army army;
	//private LinkedList<Card> cardDeck;
	private String name;
	private boolean takeTurn = false;
	protected ArrayList<Territory> territories;
	public ArrayList<IArmyCard> armyCards = new ArrayList<IArmyCard>();
	public ArrayList<IChanceCard> chanceCards = new ArrayList<IChanceCard>();
	
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
	
	public void useChanceCard(int id) {
		
		boolean exist=false;
		
		for (IChanceCard chanceCard : chanceCards) {
			System.out.println("type of chance card : "+chanceCard.type);
			if (id==chanceCard.type) {
				exist=true;
				break;
			}
		}
		
		if (exist) {
			if (id==0){ System.out.println("Action of card 1 is applied.");  }
			else if (id==1){ System.out.println("Action of card 2 is applied.");  }
			else if (id==2){ System.out.println("Action of card 3 is applied.");  }
			else if (id==3){ System.out.println("Action of card 4 is applied.");  }
			else if (id==4){ System.out.println("Action of card 5 is applied.");  }
		}
		else {
			System.out.println("You don't have this card.");
		}
	}
		
			
	

	
	

}
