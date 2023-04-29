package cardPackage;

public class ChanceCardFactory {

	public ChanceCardFactory() {
	}
	
	public IChanceCard createCard(int id) {
		 if (id==1) { return new DraftChanceCard(); }
		 else if (id==2) { return new ReinforcementsChanceCard(); }
		 else if (id==3) { return new TradeDealChanceCard(); }
		 else if (id==4) { return new RevolutionChanceCard(); }
		 else if (id==5) { return new NuclearStrikeChanceCard(); }
		 else {throw new IllegalArgumentException("Invalid card type:"); } 
		  
	}
	
	

}
