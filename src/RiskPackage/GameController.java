package RiskPackage;
import uipackage.*;;

public class GameController {
    public static void main(String[] args) {
        login loginPage = new login();   
        loginPage.frame.setVisible(true);     
    }
<<<<<<< Updated upstream
=======
    
    static private void startLogin() {
        login loginPage = new login();
        loginPage.frame.setVisible(true);
    }


    
    public static Color randomColorGenerator() {
    	Random random = new Random(); // Create a new Random object
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
    	return new Color(r,g,b);
    }
    

	static private ArrayList<Player> initGame(int numberofPlayers, int numberofComp, ArrayList<Continents> continents) {
		
		
	
		ArrayList<Territory> territories = new ArrayList<Territory>();
		for (Continents continent : continents) {
			for (Territory territory : continent.getTerritories()){
				territories.add(territory);
			}
		}
		
		int territoryPerPlayer = Math.floorDiv(territories.size(), (numberofPlayers+numberofComp));
		System.out.println("Number of territory: "+territories.size());
		System.out.println("Territory per player is: "+territoryPerPlayer);
		
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		Collections.shuffle(territories);
		for(int j = 0; j < numberofComp + numberofPlayers; j++) {
			
			ArrayList<Territory> currentTerritories = new ArrayList<Territory>();
			
			
			for (int i=0; i<territoryPerPlayer;i++) {
				currentTerritories.add(territories.get(i+j*territoryPerPlayer));
			}
			
			//System.out.println(currentTerritories);
				
			playerList.add(new Player(j, randomColorGenerator(), currentTerritories));
			
		
		
		}
		
		
		System.out.println("*****************");
		for (Player player : playerList) {
			System.out.println(player.getId());
			for (Territory territory : player.getTerritories())
				System.out.println(territory.getName());
				
			System.out.println("*****************");
		}
		
		return playerList;
	}
	


	public static int getCurrentTurnPlayerID() {
		return turnID;
	}
	
>>>>>>> Stashed changes
}
