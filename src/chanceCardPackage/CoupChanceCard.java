package chanceCardPackage;

import RiskPackage.PlayerNew;

public class CoupChanceCard implements IChanceCard {
	
	public int type;

	public CoupChanceCard() {
		this.type=2;
	}

	@Override
	public void action(PlayerNew player) {
		System.out.println("Action of Coup Card is applied.");
		
	}
	public int getType() {
		return this.type;
	}

}