package armyCardPackage;

import RiskPackage.PlayerNew;

public interface IArmyCard {
	
	public int type = 0;
	public void action(PlayerNew player);
	
}
