package cardPackage;

public class ArmyCardFactory {

	private int id;

	public ArmyCardFactory(int id) {
		this.id = id;
		this.createArmyCard(id);
	}
	
	public IArmyCard createArmyCard(int id) {
		 if (id==1) { return new ArtillaryArmyCard(); }
		 else if (id==2) { return new CavalryArmyCard(); }
		 else if (id==3) { return new InfantryArmyCard(); }
		 else {throw new IllegalArgumentException("Invalid card type:"); } 
	}

}
