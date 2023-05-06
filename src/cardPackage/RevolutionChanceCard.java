package cardPackage;

import RiskPackage.Player;

public class RevolutionChanceCard implements IChanceCard {

	public int type;
	
	public RevolutionChanceCard()  {
		this.type=3;
	}

	@Override
	public void action(Player player) {
		System.out.println("Action of Revolution Card is applied.");
		
	}
	public int getType() {
		return this.type;
	}

}
