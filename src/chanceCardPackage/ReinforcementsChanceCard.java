package chanceCardPackage;

import RiskPackage.PlayerNew;

public class ReinforcementsChanceCard implements IChanceCard {

	public int type;

	public ReinforcementsChanceCard() {
		this.type=1;
	}

	@Override
	public void action(PlayerNew player) {
		
		System.out.println("Action of Reinforcement Card is applied.");
	
	}
	
	public int getType() {
		return this.type;
	}

}
