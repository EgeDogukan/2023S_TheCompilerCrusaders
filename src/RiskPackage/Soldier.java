package RiskPackage;

public class Soldier {
	
	private int type;
	private boolean status;
	private int strength;
	
	public Soldier(int type, boolean status, int strength) {
		this.type=type;
		this.status=status;
		this.strength=strength;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	

}
