package RiskPackage;

public class chanceCard  extends Card {
	
	private int ID;
    
	public chanceCard(int type, String name, int ID) {
		super(2, "chance");
		this.setID(ID);	
		
		if (ID == 0){
	        this.setName("Draft");
	    } 
		else if (ID == 1) {
	        this.setName("Trade Deal");
	    } 
		else if (ID == 2) {
	        this.setName("Reinforcements");
	    } 
		else if (ID == 3) {
	        this.setName("Revolution");
	    } 
		else if (ID == 4) {
	        this.setName("Nuclear Strike");
	    } 
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
}
