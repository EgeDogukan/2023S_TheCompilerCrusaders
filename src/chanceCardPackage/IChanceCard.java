package chanceCardPackage;

import RiskPackage.PlayerNew;

public interface IChanceCard {
	public int type = -1;
	public void action(PlayerNew player);
	public int getType();

}
