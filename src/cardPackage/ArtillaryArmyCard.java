package cardPackage;

import RiskPackage.Player;
	
public class ArtillaryArmyCard implements IArmyCard {
	public int type;
	
	public ArtillaryArmyCard() {
		this.type=1;
	}

	@Override
	public void action(Player player) {
		
		System.out.println("Artillery cart is appended to the players army card list.");
		
	}

}
