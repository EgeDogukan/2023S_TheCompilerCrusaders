package RiskPackage;

public class Soldier {
	
	private int id;
	private boolean status;
	private int strength;
	
	public Soldier(int id, boolean status, int strength) {
		this.id=id;
		this.status=status;
		this.strength=strength;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	
}
