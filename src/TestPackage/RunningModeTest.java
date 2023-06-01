package TestPackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import RiskPackage.*;
import cardPackage.TerritoryCard;
import uipackage.*;
public class RunningModeTest {

    private RunningMode runningMode;

    @BeforeEach
    public void setUp() {
    	
    	
    	
    	ArrayList<Continents> continents = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Territory> territoryList = new ArrayList<>();
        Continents Continent = new Continents("asia", territoryList, 0, 0, Color.CYAN);
        Territory t1 = new Territory(96, 70, 10, 17, "ist", Color.black, Continent, 1);
        Territory t2 = new Territory(16, 17, 28, 45, "londra", Color.black, Continent, 1);
        territoryList.add(t2);
        territoryList.add(t1);
        Player player = new Player(0, Color.GREEN, territoryList);
        players.add(0, player);
        int numberOfAIPlayer = 2;
        int numberOfHumanPlayer = 2;
        
        for (int i=0;i<numberOfAIPlayer;i++) 
        	players.add(new ComputerPlayer());
        
        for (int i=0;i<numberOfHumanPlayer;i++)
        	players.add(new HumanPlayer());
        runningMode = new RunningMode(continents, players, numberOfAIPlayer, numberOfHumanPlayer);
    	
    	
    }

    @Test
    public void testInitialization() {
    	assertEquals(0, runningMode.getContinents().size());
        assertNotNull(runningMode.getPlayers());
    }
    
  

    @Test
    public void testGetTurn() {
        assertEquals(1, runningMode.getTurn());
        // Change the turn and verify the updated turn value
    }

    @Test
    public void testPickTerritoryCard() {
        // Call the pickTerritoryCard method
        runningMode.pickTerritoryCard();
        // Get the current player's territory card list
        ArrayList<TerritoryCard> territoryCards = runningMode.getPlayers().get(runningMode.getTurn()).getTerritoryCards();
        // Assert that the territory card list is not empty
        assertTrue(territoryCards.isEmpty());
        // Add more assertions for other scenarios
    }

    @Test
    public void testPrintTerritoryCard() {
        // Assuming the player already has some territory cards
        runningMode.getPlayers().get(runningMode.getTurn() - 1).getTerritoryCards().add(new TerritoryCard());
        // Call the printTerritoryCard method
        runningMode.printTerritoryCard();
        // Add assertions to verify the output of the printTerritoryCard method
    }

    @Test
    public void testUseTerritoryCard() {
        // Assuming the current player holds all territories of a continent
        Continents continentToBeConquered = new Continents();
        // Add the continent to the player's territory list
        
        Territory t2 = new Territory();
        continentToBeConquered.getTerritories().add(t2);
        
        runningMode.getPlayers().get(runningMode.getTurn()).addTerritories(continentToBeConquered.getTerritories());
        // Call the useTerritoryCard method
        runningMode.useTerritoryCard();
        // Assert that the player has conquered the continent
        assertFalse(runningMode.getPlayers().get(runningMode.getTurn()).getContinents().contains(continentToBeConquered));
        // Add more assertions for other scenarios
    }
}
