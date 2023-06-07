package armyCardPackage;

import RiskPackage.PlayerNew;

public interface IArmyCard {
	
	public int type = -1;
	public void action(PlayerNew player);
	public int getType();
	
}
