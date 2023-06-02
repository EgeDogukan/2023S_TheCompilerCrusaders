package armyCardPackage;

import RiskPackage.PlayerNew;

public class ArtillaryArmyCard implements IArmyCard {
	
	public int type;
	public ArtillaryArmyCard() {
		this.type=1;
	}

	@Override
	public void action(PlayerNew player) {	
		System.out.println("Artillery cart is appended to the players army card list.");
	}

}