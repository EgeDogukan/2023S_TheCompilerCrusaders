package uipackage;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import RiskPackage.Continents;
import RiskPackage.Player;
import RiskPackage.Territory;
import databasePackage.TerritoryDBDatabase;

public class LoadMode extends JFrame {
	
	int numberOfPlayer;
	int numberOfComp;
	ArrayList<ArrayList<ArrayList<String>>> allArrayList;
	public ArrayList<Continents> continents;
	public ArrayList<Territory> territories = new ArrayList<>();

	public LoadMode()  {
		super("Load Mode");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(null);
		

		TerritoryDBDatabase database =  new TerritoryDBDatabase();
		try {
			this.allArrayList = database.loadAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.numberOfComp = 1;
		this.numberOfPlayer = this.allArrayList.size();
		this.continents = new ArrayList<>();
		
        ArrayList<Territory> asiaTerritories = new ArrayList<>();
        ArrayList<Territory> africaTerritories = new ArrayList<>();
        ArrayList<Territory> europeTerritories = new ArrayList<>();
        ArrayList<Territory> northAmericaTerritories = new ArrayList<>();
        ArrayList<Territory> southAmericaTerritories = new ArrayList<>();
        ArrayList<Territory> australiaTerritories = new ArrayList<>();
        
        Continents Africa = new Continents("Africa", africaTerritories, 200, 300, Color.LIGHT_GRAY);
        Africa.setLocation(750, 330);

        Continents Europe = new Continents("Europe",europeTerritories, 400, 300, Color.LIGHT_GRAY);
        Europe.setLocation(645, 20);

        Continents NorthAmerica = new Continents("North America",northAmericaTerritories, 400, 300, Color.LIGHT_GRAY);
        NorthAmerica.setLocation(150, 50);
        
        Continents SouthAmerica = new Continents("South America",southAmericaTerritories, 200, 200, Color.LIGHT_GRAY);
        SouthAmerica.setLocation(200, 360);
        
        Continents Asia = new Continents("Asia",asiaTerritories, 350, 300, Color.LIGHT_GRAY);
        Asia.setLocation(1050, 65);
        
        Continents Australia = new Continents("Australia",australiaTerritories, 200, 200, Color.LIGHT_GRAY);
        Australia.setLocation(1150, 450);
        
        for(int i=0;i<this.allArrayList.size();i++) {
        	for (int j=0;j<this.allArrayList.get(i).size();j++) {
        			//System.out.println(this.allArrayList.get(i).get(j));
        			//System.out.println(this.allArrayList.get(i).get(j).get(6));
        			int xCoordinate = Integer.parseInt(this.allArrayList.get(i).get(j).get(0));
        			
        			int yCoordinate = Integer.parseInt(this.allArrayList.get(i).get(j).get(1));
        			
        			int width = Integer.parseInt(this.allArrayList.get(i).get(j).get(2));
        			
        			int height = Integer.parseInt(this.allArrayList.get(i).get(j).get(3));
        			
        			
        			
        			
        			String territory = this.allArrayList.get(i).get(j).get(4);
        			String colorString = this.allArrayList.get(i).get(j).get(5);
        			String continent = this.allArrayList.get(i).get(j).get(6);
        			int id = Integer.parseInt(this.allArrayList.get(i).get(j).get(7));
        			
        			Color color = changeToColor(colorString);
        			
        			switch (continent) {
        		    case "Asia":
        		    	//String name, Color color, Continents continent, int playerID
        		    	Territory terrAs = new Territory(xCoordinate, yCoordinate, width, height, territory,color, Asia , id);
        		    	asiaTerritories.add(terrAs);
        		    	this.territories.add(terrAs);
        		        
        		        break;
        		        
		        	case "Africa":
				    	//String name, Color color, Continents continent, int playerID
				    	Territory terrAf = new Territory(xCoordinate, yCoordinate, width, height, territory,color, Africa , id);
				    	africaTerritories.add(terrAf);
				    	this.territories.add(terrAf);
				        
				        break;
				       
		        	case "Australia":
        		    	//String name, Color color, Continents continent, int playerID
        		    	Territory terrAu = new Territory(xCoordinate, yCoordinate, width, height, territory,color, Australia , id);
        		    	australiaTerritories.add(terrAu);
        		    	this.territories.add(terrAu);
        		        break;
        		        
		        	case "South America":
				    	//String name, Color color, Continents continent, int playerID
				    	Territory terrSa = new Territory(xCoordinate, yCoordinate, width, height, territory,color, SouthAmerica , id);
				    	southAmericaTerritories.add(terrSa);
				    	this.territories.add(terrSa);
				        
				        break;
				        
				        
		        	case "North America":
				    	//String name, Color color, Continents continent, int playerID
				    	Territory terrNa = new Territory(xCoordinate, yCoordinate, width, height, territory,color, NorthAmerica , id);
				    	northAmericaTerritories.add(terrNa);
				    	this.territories.add(terrNa);
				        
				        break;
				        
		        	case "Europe":
				    	//String name, Color color, Continents continent, int playerID
				    	Territory terrEu = new Territory(xCoordinate, yCoordinate, width, height, territory,color, Europe , id);
				    	europeTerritories.add(terrEu);
				    	this.territories.add(terrEu);
				        
				        break;
        
        			}	
        		}
        	}
        
        System.out.println("************** LOAD MODE ********");
        
        for (Territory territory : this.territories) {
        	System.out.println(territory);
        }
        System.out.println(this.territories.size());
        System.out.println("************** LOAD MODE ********");
        
        
        if (Asia.isIncluded) {
        	continents.add(Asia);
        	this.add(Asia);
        }
        if (NorthAmerica.isIncluded) {
        	continents.add(NorthAmerica);
        	this.add(NorthAmerica);
        }
        if (SouthAmerica.isIncluded) {
        	continents.add(SouthAmerica);
        	this.add(SouthAmerica);
        }
        if (Europe.isIncluded) {
        	continents.add(Europe);
        	this.add(Europe);
        }
        if (Australia.isIncluded) {
        	continents.add(Australia);
        	this.add(Australia);
        }
        if (Africa.isIncluded) {
        	continents.add(Africa);
        	this.add(Africa);
        }
        
        
        for (Territory territory1 : this.territories) {
        	for (Territory territory2 : this.territories) {
        		if ((territory1.getName().equals("Alaska") && territory2.getName().equals("Kamchatka")) ||
        		(territory1.getName().equals("Alaska") && territory2.getName().equals("NorthWestTerritory")) ||
        		(territory1.getName().equals("Alaska") && territory2.getName().equals("Alberta")) ||
        		(territory1.getName().equals("NorthWestTerritory") && territory2.getName().equals("Greenland")) ||
        		(territory1.getName().equals("NorthWestTerritory") && territory2.getName().equals("Alberta")) ||
        		(territory1.getName().equals("NorthWestTerritory") && territory2.getName().equals("Alaska")) ||
        		(territory1.getName().equals("NorthWestTerritory") && territory2.getName().equals("Ontario")) ||
        		(territory1.getName().equals("EasternUnitedStates") && territory2.getName().equals("CentralAmerica")) ||
        		(territory1.getName().equals("EasternUnitedStates") && territory2.getName().equals("WesternUnitedStates")) ||
        		(territory1.getName().equals("EasternUnitedStates") && territory2.getName().equals("Quebec")) ||
        		(territory1.getName().equals("EasternUnitedStates") && territory2.getName().equals("Ontario")) ||
        		(territory1.getName().equals("Alberta") && territory2.getName().equals("Alaska")) ||
        		(territory1.getName().equals("Alberta") && territory2.getName().equals("NorthWestTerritory")) ||
        		(territory1.getName().equals("Alberta") && territory2.getName().equals("Ontario")) ||
        		(territory1.getName().equals("Alberta") && territory2.getName().equals("WesternUnitedStates")) ||
        		(territory1.getName().equals("Quebec") && territory2.getName().equals("Greenland")) ||
        		(territory1.getName().equals("Quebec") && territory2.getName().equals("EasternUnitedStates")) ||
        		(territory1.getName().equals("Quebec") && territory2.getName().equals("Ontario")) ||
        		(territory1.getName().equals("Ontario") && territory2.getName().equals("Quebec")) ||
        		(territory1.getName().equals("Ontario") && territory2.getName().equals("Alberta")) ||
        		(territory1.getName().equals("Ontario") && territory2.getName().equals("Greenland")) ||
        		(territory1.getName().equals("Ontario") && territory2.getName().equals("WesternUnitedStates")) ||
        		(territory1.getName().equals("Ontario") && territory2.getName().equals("EasternUnitedStates")) ||
        		(territory1.getName().equals("Ontario") && territory2.getName().equals("NorthWestTerritory")) ||
        		(territory1.getName().equals("Greenland") && territory2.getName().equals("Iceland")) ||
        		(territory1.getName().equals("Greenland") && territory2.getName().equals("Quebec")) ||
        		(territory1.getName().equals("Greenland") && territory2.getName().equals("Ontario")) ||
        		(territory1.getName().equals("Greenland") && territory2.getName().equals("NorthWestTerritory")) ||
        		(territory1.getName().equals("WesternUnitedStates") && territory2.getName().equals("EasternUnitedStates")) ||
        		(territory1.getName().equals("WesternUnitedStates") && territory2.getName().equals("Alberta")) ||
        		(territory1.getName().equals("WesternUnitedStates") && territory2.getName().equals("Ontario")) ||
        		(territory1.getName().equals("WesternUnitedStates") && territory2.getName().equals("CentralAmerica")) ||
        		(territory1.getName().equals("CentralAmerica") && territory2.getName().equals("Venezuela")) ||
        		(territory1.getName().equals("CentralAmerica") && territory2.getName().equals("WesternUnitedStates")) ||
        		(territory1.getName().equals("CentralAmerica") && territory2.getName().equals("EasternUnitedStates")) ||
        		(territory1.getName().equals("Venezuela") && territory2.getName().equals("Brazil")) ||
        		(territory1.getName().equals("Venezuela") && territory2.getName().equals("Peru")) ||
        		(territory1.getName().equals("Venezuela") && territory2.getName().equals("CentralAmerica")) ||
        		(territory1.getName().equals("Peru") && territory2.getName().equals("Argentina")) ||
        		(territory1.getName().equals("Peru") && territory2.getName().equals("Brazil")) ||
        		(territory1.getName().equals("Peru") && territory2.getName().equals("Venezuela")) ||
        		(territory1.getName().equals("Brazil") && territory2.getName().equals("NorthAfrica")) ||
        		(territory1.getName().equals("Brazil") && territory2.getName().equals("Peru")) ||
        		(territory1.getName().equals("Brazil") && territory2.getName().equals("Venezuela")) ||
        		(territory1.getName().equals("Brazil") && territory2.getName().equals("Argentina")) ||
        		(territory1.getName().equals("Argentina") && territory2.getName().equals("Peru")) ||
        		(territory1.getName().equals("Argentina") && territory2.getName().equals("Brazil")) ||
        		(territory1.getName().equals("NorthAfrica") && territory2.getName().equals("Egypt")) ||
        		(territory1.getName().equals("NorthAfrica") && territory2.getName().equals("EastAfrica")) ||
        		(territory1.getName().equals("NorthAfrica") && territory2.getName().equals("Congo")) ||
        		(territory1.getName().equals("NorthAfrica") && territory2.getName().equals("SouthernEurope")) ||
        		(territory1.getName().equals("NorthAfrica") && territory2.getName().equals("WesternEurope")) ||
        		(territory1.getName().equals("NorthAfrica") && territory2.getName().equals("Brazil")) ||
        		(territory1.getName().equals("Egypt") && territory2.getName().equals("EastAfrica")) ||
        		(territory1.getName().equals("Egypt") && territory2.getName().equals("SouthernEurope")) ||
        		(territory1.getName().equals("Egypt") && territory2.getName().equals("MiddleEast")) ||
        		(territory1.getName().equals("Egypt") && territory2.getName().equals("NorthAfrica")) ||
        		(territory1.getName().equals("Congo") && territory2.getName().equals("NorthAfrica")) ||
        		(territory1.getName().equals("Congo") && territory2.getName().equals("EastAfrica")) ||
        		(territory1.getName().equals("Congo") && territory2.getName().equals("SouthAfrica")) ||
        		(territory1.getName().equals("SouthAfrica") && territory2.getName().equals("Congo")) ||
        		(territory1.getName().equals("SouthAfrica") && territory2.getName().equals("EastAfrica")) ||
        		(territory1.getName().equals("SouthAfrica") && territory2.getName().equals("Madagascar")) ||
        		(territory1.getName().equals("EastAfrica") && territory2.getName().equals("Madagascar")) ||
        		(territory1.getName().equals("EastAfrica") && territory2.getName().equals("Congo")) ||
        		(territory1.getName().equals("EastAfrica") && territory2.getName().equals("SouthAfrica")) ||
        		(territory1.getName().equals("EastAfrica") && territory2.getName().equals("NorthAfrica")) ||
        		(territory1.getName().equals("EastAfrica") && territory2.getName().equals("Egypt")) ||
        		(territory1.getName().equals("EastAfrica") && territory2.getName().equals("MiddleEast")) ||
        		(territory1.getName().equals("Madagascar") && territory2.getName().equals("SouthAfrica")) ||
        		(territory1.getName().equals("Madagascar") && territory2.getName().equals("EastAfrica")) ||
        		(territory1.getName().equals("Iceland") && territory2.getName().equals("GreatBritain")) ||
        		(territory1.getName().equals("Iceland") && territory2.getName().equals("Greenland")) ||
        		(territory1.getName().equals("Iceland") && territory2.getName().equals("Scandinavia")) ||
        		(territory1.getName().equals("Scandinavia") && territory2.getName().equals("Iceland")) ||
        		(territory1.getName().equals("Scandinavia") && territory2.getName().equals("GreatBritain")) ||
        		(territory1.getName().equals("Scandinavia") && territory2.getName().equals("NorthernEurope")) ||
        		(territory1.getName().equals("Scandinavia") && territory2.getName().equals("Ukraine")) ||
        		(territory1.getName().equals("GreatBritain") && territory2.getName().equals("Iceland")) ||
        		(territory1.getName().equals("GreatBritain") && territory2.getName().equals("Scandinavia")) ||
        		(territory1.getName().equals("GreatBritain") && territory2.getName().equals("NorthernEurope")) ||
        		(territory1.getName().equals("GreatBritain") && territory2.getName().equals("WesternEurope")) ||
        		(territory1.getName().equals("Ukraine") && territory2.getName().equals("Scandinavia")) ||
        		(territory1.getName().equals("Ukraine") && territory2.getName().equals("NorthernEurope")) ||
        		(territory1.getName().equals("Ukraine") && territory2.getName().equals("SouthernEurope")) ||
        		(territory1.getName().equals("Ukraine") && territory2.getName().equals("Ural")) ||
        		(territory1.getName().equals("Ukraine") && territory2.getName().equals("Afghanistan")) ||
        		(territory1.getName().equals("Ukraine") && territory2.getName().equals("MiddleEast")) ||
        		(territory1.getName().equals("WesternEurope") && territory2.getName().equals("NorthAfrica")) ||
        		(territory1.getName().equals("WesternEurope") && territory2.getName().equals("GreatBritain")) ||
        		(territory1.getName().equals("WesternEurope") && territory2.getName().equals("NorthernEurope")) ||
        		(territory1.getName().equals("WesternEurope") && territory2.getName().equals("SouthernEurope")) ||
        		(territory1.getName().equals("NorthernEurope") && territory2.getName().equals("GreatBritain")) ||
        		(territory1.getName().equals("NorthernEurope") && territory2.getName().equals("Scandinavia")) ||
        		(territory1.getName().equals("NorthernEurope") && territory2.getName().equals("Ukraine")) ||
        		(territory1.getName().equals("NorthernEurope") && territory2.getName().equals("SouthernEurope")) ||
        		(territory1.getName().equals("SouthernEurope") && territory2.getName().equals("MiddleEast")) ||
        		(territory1.getName().equals("SouthernEurope") && territory2.getName().equals("Egypt")) ||
        		(territory1.getName().equals("SouthernEurope") && territory2.getName().equals("NorthAfrica")) ||
        		(territory1.getName().equals("SouthernEurope") && territory2.getName().equals("WesternEurope")) ||
        		(territory1.getName().equals("SouthernEurope") && territory2.getName().equals("NorthernEurope")) ||
        		(territory1.getName().equals("SouthernEurope") && territory2.getName().equals("Ukraine")) ||
        		(territory1.getName().equals("MiddleEast") && territory2.getName().equals("SouthernEurope")) ||
        		(territory1.getName().equals("MiddleEast") && territory2.getName().equals("Egypt")) ||
        		(territory1.getName().equals("MiddleEast") && territory2.getName().equals("Ukraine")) ||
        		(territory1.getName().equals("MiddleEast") && territory2.getName().equals("Afghanistan")) ||
        		(territory1.getName().equals("MiddleEast") && territory2.getName().equals("India")) ||
        		(territory1.getName().equals("Ural") && territory2.getName().equals("Afghanistan")) ||
        		(territory1.getName().equals("Ural") && territory2.getName().equals("Ukraine")) ||
        		(territory1.getName().equals("Ural") && territory2.getName().equals("Siberia")) ||
        		(territory1.getName().equals("Ural") && territory2.getName().equals("China")) ||
        		(territory1.getName().equals("Afghanistan") && territory2.getName().equals("Ukraine")) ||
        		(territory1.getName().equals("Afghanistan") && territory2.getName().equals("Ural")) ||
        		(territory1.getName().equals("Afghanistan") && territory2.getName().equals("MiddleEast")) ||
        		(territory1.getName().equals("Afghanistan") && territory2.getName().equals("India")) ||
        		(territory1.getName().equals("Afghanistan") && territory2.getName().equals("China")) ||
        		(territory1.getName().equals("Siberia") && territory2.getName().equals("Ural")) ||
        		(territory1.getName().equals("Siberia") && territory2.getName().equals("Yakutsk")) ||
        		(territory1.getName().equals("Siberia") && territory2.getName().equals("Irkutsk")) ||
        		(territory1.getName().equals("Siberia") && territory2.getName().equals("Mongolia")) ||
        		(territory1.getName().equals("Siberia") && territory2.getName().equals("China")) ||
        		(territory1.getName().equals("Yakutsk") && territory2.getName().equals("Irkutsk")) ||
        		(territory1.getName().equals("Yakutsk") && territory2.getName().equals("Siberia")) ||
        		(territory1.getName().equals("Yakutsk") && territory2.getName().equals("Kamchatka")) ||
        		(territory1.getName().equals("Irkutsk") && territory2.getName().equals("Yakutsk")) ||
        		(territory1.getName().equals("Irkutsk") && territory2.getName().equals("Kamchatka")) ||
        		(territory1.getName().equals("Irkutsk") && territory2.getName().equals("Mongolia")) ||
        		(territory1.getName().equals("Irkutsk") && territory2.getName().equals("Siberia")) ||
        		(territory1.getName().equals("Mongolia") && territory2.getName().equals("Japan")) ||
        		(territory1.getName().equals("Mongolia") && territory2.getName().equals("Kamchatka")) ||
        		(territory1.getName().equals("Mongolia") && territory2.getName().equals("China")) ||
        		(territory1.getName().equals("Mongolia") && territory2.getName().equals("Siberia")) ||
        		(territory1.getName().equals("Mongolia") && territory2.getName().equals("Irkutsk")) ||
        		(territory1.getName().equals("Kamchatka") && territory2.getName().equals("Alaska")) ||
        		(territory1.getName().equals("Kamchatka") && territory2.getName().equals("Japan")) ||
        		(territory1.getName().equals("Kamchatka") && territory2.getName().equals("Yakutsk")) ||
        		(territory1.getName().equals("Kamchatka") && territory2.getName().equals("Irkutsk")) ||
        		(territory1.getName().equals("Kamchatka") && territory2.getName().equals("Mongolia")) ||
        		(territory1.getName().equals("Japan") && territory2.getName().equals("Kamchatka")) ||
        		(territory1.getName().equals("Japan") && territory2.getName().equals("Mongolia")) ||
        		(territory1.getName().equals("China") && territory2.getName().equals("Mongolia")) ||
        		(territory1.getName().equals("China") && territory2.getName().equals("Siberia")) ||
        		(territory1.getName().equals("China") && territory2.getName().equals("Ural")) ||
        		(territory1.getName().equals("China") && territory2.getName().equals("Afghanistan")) ||
        		(territory1.getName().equals("China") && territory2.getName().equals("India")) ||
        		(territory1.getName().equals("China") && territory2.getName().equals("Siam")) ||
        		(territory1.getName().equals("Siam") && territory2.getName().equals("India")) ||
        		(territory1.getName().equals("Siam") && territory2.getName().equals("China")) ||
        		(territory1.getName().equals("Siam") && territory2.getName().equals("Indonesia")) ||
        		(territory1.getName().equals("India") && territory2.getName().equals("Siam")) ||
        		(territory1.getName().equals("India") && territory2.getName().equals("China")) ||
        		(territory1.getName().equals("India") && territory2.getName().equals("MiddleEast")) ||
        		(territory1.getName().equals("India") && territory2.getName().equals("Afghanistan")) ||
        		(territory1.getName().equals("Indonesia") && territory2.getName().equals("NewGuinea")) ||
        		(territory1.getName().equals("Indonesia") && territory2.getName().equals("WesternAustralia")) ||
        		(territory1.getName().equals("Indonesia") && territory2.getName().equals("Siam")) ||
        		(territory1.getName().equals("NewGuinea") && territory2.getName().equals("Indonesia")) ||
        		(territory1.getName().equals("NewGuinea") && territory2.getName().equals("EasternAustralia")) ||
        		(territory1.getName().equals("NewGuinea") && territory2.getName().equals("WesternAustralia")) ||
        		(territory1.getName().equals("WesternAustralia") && territory2.getName().equals("EasternAustralia")) ||
        		(territory1.getName().equals("WesternAustralia") && territory2.getName().equals("Indonesia")) ||
        		(territory1.getName().equals("WesternAustralia") && territory2.getName().equals("NewGuinea")) ||
        		(territory1.getName().equals("EasternAustralia") && territory2.getName().equals("NewGuinea")) ||
        		(territory1.getName().equals("EasternAustralia") && territory2.getName().equals("WesternAustralia"))) {
        			territory1.addNeighbor(territory2);
        		}
        		
        	}
        }
        this.pack();
        
           
	
	}
	public static Color changeToColor(String colorString) {
		//String colorString = "java.awt.Color[r=0,g=0,b=255]";

		// Extract RGB values from the string
		int startIndex = colorString.indexOf("r=") + 2;
		int endIndex = colorString.indexOf(",", startIndex);
		int red = Integer.parseInt(colorString.substring(startIndex, endIndex));

		startIndex = colorString.indexOf("g=") + 2;
		endIndex = colorString.indexOf(",", startIndex);
		int green = Integer.parseInt(colorString.substring(startIndex, endIndex));

		startIndex = colorString.indexOf("b=") + 2;
		endIndex = colorString.indexOf("]", startIndex);
		int blue = Integer.parseInt(colorString.substring(startIndex, endIndex));

		// Create the Color object
		Color color = new Color(red, green, blue);
		
		return color;

	}
	
	public ArrayList<Continents> initalSharing(ArrayList<Player> players) {
        
		for(Player p : players){
        	for(Territory t: p.getTerritories()){
        		if (t.getContinent().isIncluded==true) {
        			Continents c = t.getContinent();
        			t.setColor(p.getColor());
        			c.add(t);
                    t.setOwnerID(p.getId());
                    t.setArmy(1,2,3);
        		}
        			
            }
        } 
        return getContinent();
	}
	
	public ArrayList<Continents> getContinent() {
		ArrayList<Continents> currentContinents = new ArrayList<>();
		for (Continents continent : this.continents) {
			if (continent.isIncluded) {
				currentContinents.add(continent);
			}
		}
		return currentContinents;
	}
	
	public int getNumberofPlayers() {
		System.out.println("Number of Player Load Mode: "+this.allArrayList.size());
		return this.allArrayList.size();
	}
	
	
	
	

}
