package TestPackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import RiskPackage.*;
import cardPackage.TerritoryCard;
import uipackage.*;
public class RunningModeTest {

    private RunningMode runningMode;

    @BeforeEach
    public void setUp() {
        // Initialize the RunningMode instance with sample data for testing
        ArrayList<Continents> continents = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
        int numberOfAIPlayer = 2;
        int numberOfHumanPlayer = 2;
        runningMode = new RunningMode(continents, players, numberOfAIPlayer, numberOfHumanPlayer);
    }

    @Test
    public void testInitialization() {
        assertNotNull(runningMode.getContinents());
        assertNotNull(runningMode.getPlayers());
        assertEquals(1, runningMode.getTurn());
        // Add more assertions for other initialization checks
    }

    @Test
    public void testGetTurn() {
        assertEquals(1, runningMode.getTurn());
        // Change the turn and verify the updated turn value
        // Add more assertions for other scenarios
    }

    @Test
    public void testPickTerritoryCard() {
        // Call the pickTerritoryCard method
        runningMode.pickTerritoryCard();
        // Get the current player's territory card list
        ArrayList<TerritoryCard> territoryCards = runningMode.getPlayers().get(runningMode.getTurn() - 1).getTerritoryCards();
        // Assert that the territory card list is not empty
        assertFalse(territoryCards.isEmpty());
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
        runningMode.getPlayers().get(runningMode.getTurn() - 1).addTerritories(continentToBeConquered.getTerritories());
        // Call the useTerritoryCard method
        runningMode.useTerritoryCard();
        // Assert that the player has conquered the continent
        assertTrue(runningMode.getPlayers().get(runningMode.getTurn() - 1).getContinents().contains(continentToBeConquered));
        // Add more assertions for other scenarios
    }
}
