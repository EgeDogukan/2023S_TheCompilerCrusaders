package RiskPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import cardPackage.ArmyCardFactory;
import cardPackage.IArmyCard;
import cardPackage.IChanceCard;
import cardPackage.TerritoryCard;

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
	public ArrayList<TerritoryCard> territoryCards = new ArrayList<TerritoryCard>();
	
	public Player(int id, Color color,  ArrayList<Territory> territories) {
		this.id=id;
		this.color=color;
		this.territories = territories;
	}
	
	public ArrayList<Territory> getTerritories(){
		return territories;
	}
	
	public void addTerritories(Territory territory) {
		this.territories.add(territory);
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
		
		IChanceCard cardToBeRemoved = null;
		
		for (IChanceCard chanceCard : chanceCards) {
			if (id==chanceCard.getType()) {
				exist=true;
				cardToBeRemoved = chanceCard;
				break;
			}
		}
		
		if (exist) {
			if (id==0){ System.out.println("Draft chance Card is applied."); usageOfDraftChanceCard(); }
			else if (id==1){ System.out.println("Reinforcement Card is applied.");  }
			else if (id==2){ System.out.println("Trade Deal Card is applied.");  }
			else if (id==3){ System.out.println("Revolution Card is applied.");  }
			else if (id==4){ System.out.println("Nuclear Strike Card is applied.");  }
			this.chanceCards.remove(cardToBeRemoved);
		}
		else {
			System.out.println("You don't have this card.");
		}
	}
	
	public void usageOfDraftChanceCard() {
		Random rand = new Random();
		for (int i=0;i<2;i++) {
	        int randomNumber = rand.nextInt(3) + 1;
			addArmyCard(new ArmyCardFactory().createArmyCard(randomNumber));
		}
        
	}
		
			
	

	
	

}
