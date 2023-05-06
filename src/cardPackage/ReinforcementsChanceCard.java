package cardPackage;

import RiskPackage.Player;

public class ReinforcementsChanceCard implements IChanceCard {
	
	public int type;

	public ReinforcementsChanceCard() {
		this.type=2;
	}

	@Override
	public void action(Player player) {
		
		System.out.println("Action of Reinforcement Card is applied.");
	
	}
	
	public int getType() {
		return this.type;
	}

}
