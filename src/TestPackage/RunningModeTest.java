package testPackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import RiskPackage.*;
import cardPackage.TerritoryCard;
import uipackage.*;
public class RunningModeTest {

    /*
    testInitialization():
    Overview: This test ensures that the RunningMode instance is properly initialized.
    Abstract function: Checks if the continents list is empty and if the players list is not null.

    testRepOk():
    Overview: This test verifies the representation invariant of the RunningMode instance.
    Abstract function: Asserts that the repOk() method returns true initially and false after modifying the instance to violate the representation invariant.
    
    testGetTurn():
    Overview: This test verifies the functionality of the getTurn() method in the RunningMode class.
    Abstract function: Asserts that the initial turn value is 1 and suggests changing the turn and verifying the updated turn value.

    testPickTerritoryCard():
    Overview: This test checks the behavior of the pickTerritoryCard() method in the RunningMode class.
    Abstract function: Calls the pickTerritoryCard() method, retrieves the current player's territory card list, and asserts that the list is not empty.

    testPrintTerritoryCard():
    Overview: This test verifies the output of the printTerritoryCard() method in the RunningMode class.
    Abstract function: Assumes the player already has some territory cards, calls the printTerritoryCard() method, and suggests adding assertions to verify the output.

    testUseTerritoryCard():
    Overview: This test checks the behavior of the useTerritoryCard() method in the RunningMode class.
    Abstract function: Assumes the current player holds all territories of a continent, adds the continent to the player's territory list, calls the useTerritoryCard() method, 
    and asserts that the player no longer possesses the conquered continent.

    Overview for RunningMode class:
    The RunningMode class is a crucial component of the ConKUeror. It represents the main game mode where the actual gameplay takes place. 
    This class manages the state of the game, including the players, territories, and various game-related actions.
    */

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

    /*
     * Test case for the initialization of the RunningMode class.
     * 
     * Requires: None.
     * Modifies: None.
     * Effects: - Verifies that the continents list is empty.
     *          - Verifies that the players list is not null.
     */
    @Test
    public void testInitialization() {
    	assertEquals(0, runningMode.getContinents().size());
        assertNotNull(runningMode.getPlayers());
    }
    
    /*
     * Test case for the repOk() method of the RunningMode class.
     * 
     * Requires: None.
     * Modifies: None.
     * Effects: - Verifies that the repOk() method returns true when the class invariant is satisfied.
     *          - Verifies that the repOk() method returns false when the class invariant is violated.
     */
    @Test
    public void testRepOk() {
        assertTrue(runningMode.repOk());
        // Modify the instance to violate the representation invariant
        runningMode.numberOfAIPlayer = -1;
        assertFalse(runningMode.repOk());
        // Add more assertions for other scenarios
    }

    /*
     * Test case for the getTurn() method of the RunningMode class.
     * 
     * Requires: None.
     * Modifies: None.
     * Effects: - Verifies that the getTurn() method returns the correct turn value.
     *          - Modifies the turn and verifies the updated turn value.
     */
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

    /*
     * Test case for the pickTerritoryCard() method of the RunningMode class.
     * 
     * Requires: None.
     * Modifies: The territory card list of the current player.
     * Effects: - Verifies that the pickTerritoryCard() method updates the territory card list of the current player.
     *          - Verifies that the territory card list is not empty after picking a card.
     */
    @Test
    public void testPrintTerritoryCard() {
        // Assuming the player already has some territory cards
        runningMode.getPlayers().get(runningMode.getTurn() - 1).getTerritoryCards().add(new TerritoryCard());
        // Call the printTerritoryCard method
        runningMode.printTerritoryCard();
        // Add assertions to verify the output of the printTerritoryCard method
    }

    /**
     * Test case for the printTerritoryCard() method of the RunningMode class.
     * 
     * Requires: The current player has at least one territory card.
     * Modifies: None.
     * Effects: - Verifies the output or behavior of the printTerritoryCard() method.
     */
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
