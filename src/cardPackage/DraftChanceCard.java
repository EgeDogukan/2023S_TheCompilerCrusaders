package cardPackage;

import RiskPackage.Player;

public class DraftChanceCard implements IChanceCard {
	
	public int type;

	public DraftChanceCard() {
		this.type=1;
	}

	@Override
	public void action(Player player) {
		System.out.println("Action of Draft Card is applied.");
		
	}

}
