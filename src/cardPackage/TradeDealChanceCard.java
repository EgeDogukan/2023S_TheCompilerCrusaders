package cardPackage;

import RiskPackage.Player;

public class TradeDealChanceCard implements IChanceCard {
	
	public int type;

	public TradeDealChanceCard() {
		this.type=3;
	}

	@Override
	public void action(Player player) {
		System.out.println("Action of Trade Deal Card is applied.");
		
	}

}
