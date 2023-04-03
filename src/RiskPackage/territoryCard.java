package RiskPackage;

public class territoryCard extends Card{
	
	private int ID;
	
	public territoryCard(int type, String name) {
		super(0, "territory");
		this.setID(ID);
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

}
