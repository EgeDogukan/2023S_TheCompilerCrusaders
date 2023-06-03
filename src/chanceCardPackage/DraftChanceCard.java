package chanceCardPackage;

import RiskPackage.PlayerNew;

public class DraftChanceCard implements IChanceCard {
	
	public int type;

	public DraftChanceCard() {
		this.type=0;
	}

	@Override
	public void action(PlayerNew player) {
		System.out.println("Action of Draft Card is applied.");
		
	}
	public int getType() {
		return this.type;
	}

}
