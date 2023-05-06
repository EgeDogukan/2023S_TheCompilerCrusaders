package cardPackage;

import RiskPackage.Player;

public class NuclearStrikeChanceCard implements IChanceCard {

	public int type;
	public NuclearStrikeChanceCard() {
		this.type=4;
	}
	@Override
	public void action(Player player) {
		System.out.println("Action of Draft Card is applied.");
		
	}
	public int getType() {
		return this.type;
	}

}
