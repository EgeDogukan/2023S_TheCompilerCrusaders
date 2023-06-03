package chanceCardPackage;

import RiskPackage.PlayerNew;

public class NuclearStrikeChanceCard implements IChanceCard {

	public int type;
	public NuclearStrikeChanceCard() {
		this.type=4;
	}
	@Override
	public void action(PlayerNew player) {
		System.out.println("Action of Draft Card is applied.");
		
	}
	public int getType() {
		return this.type;
	}
	
	

}
