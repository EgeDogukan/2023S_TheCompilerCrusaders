package chanceCardPackage;

import RiskPackage.PlayerNew;

public class RevolutionChanceCard implements IChanceCard {

	public int type;
	
	public RevolutionChanceCard()  {
		this.type=3;
	}

	@Override
	public void action(PlayerNew player) {
		System.out.println("Action of Revolution Card is applied.");
		
	}
	public int getType() {
		return this.type;
	}

}
