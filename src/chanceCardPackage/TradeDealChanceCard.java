package chanceCardPackage;

import RiskPackage.PlayerNew;

public class TradeDealChanceCard implements IChanceCard {
	
	public int type;

	public TradeDealChanceCard() {
		this.type=2;
	}

	@Override
	public void action(PlayerNew player) {
		System.out.println("Action of Trade Deal Card is applied.");
		
	}
	public int getType() {
		return this.type;
	}

}