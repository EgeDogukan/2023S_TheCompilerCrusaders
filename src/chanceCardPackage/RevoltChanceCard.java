package chanceCardPackage;

import RiskPackage.PlayerNew;

public class RevoltChanceCard implements IChanceCard {
	
	public int type;

	public RevoltChanceCard() {
		this.type=0;
	}

	@Override
	public void action(PlayerNew player) {
		System.out.println("Action of Revolt Card is applied.");
		
	}
	public int getType() {
		return this.type;
	}

}
