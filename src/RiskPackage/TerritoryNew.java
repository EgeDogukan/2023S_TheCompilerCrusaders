package RiskPackage;

import java.awt.Color;

public class TerritoryNew {
	
	public Color color;
	public int playerID;
	public int territoryID;
	public int shapeIndex;
	public boolean isInGame;
	
	public TerritoryNew(Color color, int playerID, int territoryID, int shapeIndex, boolean isInGame) {
		this.color=color;
		this.playerID=playerID;
		this.territoryID=territoryID;
		this.shapeIndex=shapeIndex;
		this.isInGame=isInGame;
	}
	
	
	
	

	public boolean isInGame() {
		return isInGame;
	}

	public void setInGame(boolean isInGame) {
		this.isInGame = isInGame;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getTerritoryID() {
		return territoryID;
	}

	public void setTerritoryID(int territoryID) {
		this.territoryID = territoryID;
	}

	public int getShapeIndex() {
		return shapeIndex;
	}

	public void setShapeIndex(int shapeIndex) {
		this.shapeIndex = shapeIndex;
	}

	
}
