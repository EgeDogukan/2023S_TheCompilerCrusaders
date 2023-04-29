package cardPackage;

import RiskPackage.Player;

public class CavalryArmyCard implements IArmyCard{
	
	public int type; 
	public CavalryArmyCard() {
		this.type=2;
	}

	@Override
	public void action(Player player) {
		
		System.out.println("Cavalry cart is appended to the players army card list.");
		
	}

}
