package chanceCardPackage;

public class ChanceCardFactory {

	public ChanceCardFactory() {
	}
	
	public IChanceCard createCard(int id) {
		
		 if (id==0) { return new RevoltChanceCard(); }
		 else if (id==1) { return new ReinforcementsChanceCard(); }
		 else if (id==2) { return new CoupChanceCard(); }
		 else if (id==3) { return new RevolutionChanceCard(); }
		 else if (id==4) { return new NuclearStrikeChanceCard(); }
		 else {throw new IllegalArgumentException("Invalid card type:"); } 
		  
	}
}
