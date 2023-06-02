package armyCardPackage;

import RiskPackage.PlayerNew;

public class CavalryArmyCard implements IArmyCard{
	
	public int type; 
	public CavalryArmyCard() {
		this.type=2;
	}

	@Override
	public void action(PlayerNew player) {
		System.out.println("Cavalry cart is appended to the players army card list.");
	}

}