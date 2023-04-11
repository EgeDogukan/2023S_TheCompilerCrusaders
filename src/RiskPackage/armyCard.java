package RiskPackage;

public class armyCard extends Card {

	private int ID;

	public armyCard(int type, String name) {
		super(1, "army");
		this.setID(ID);
	}
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
}
