package cardPackage;

import RiskPackage.Player;

public class InfantryArmyCard implements IArmyCard {
	
	public int type;

	public InfantryArmyCard() {
		this.type=3;
	}

	@Override
	public void action(Player player) {
		System.out.println("Infantry cart is appended to the players army card list.");
		
	}

}
