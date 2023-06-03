package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import RiskPackage.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import cardPackage.*;


public class useCardTest {
    
    @Test 
    public void testUseChanceCard_ValidCardID() {
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Player player = new Player(1, null, territories);
        
        IChanceCard card = new DraftChanceCard();
        player.chanceCards.add(card); // Add a chance card to the player's collection
        
        // Test using a valid card ID for DraftChanceCard (0 represents DraftChanceCard)
        player.useChanceCard(0);
        
        // Assert that the card was removed from the player's collection
        assertEquals(0, player.chanceCards.size());
    }
    
    @Test
    public void testUseChanceCard_InvalidCardID() {
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Player player = new Player(1, null, territories);
        
        // Test using an invalid card ID
        player.useChanceCard(5);
        
        // Assert that the player's collection remains unchanged
        assertEquals(0, player.chanceCards.size());
    }
    
    @Test
    public void testUseChanceCard_PlayerWithoutCard() {
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Player player = new Player(1, null, territories);
        
        // Test using a card ID when the player doesn't have any chance cards
        player.useChanceCard(1);
        
        // Assert that the player's collection remains unchanged
        assertEquals(0, player.chanceCards.size());
    }
    
    @Test
    public void testUseChanceCard_ReinforcementCard() {
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Player player = new Player(1, null, territories);
        
        IChanceCard card = new ReinforcementsChanceCard();
        player.chanceCards.add(card); // Add a ReinforcementCard to the player's collection
        
        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        // Test using a card ID for ReinforcementCard (assuming 1 represents ReinforcementCard)
        player.useChanceCard(1);
        
        // Reset System.out
        System.setOut(System.out);
        
        // Assert that the correct message was printed
        assertEquals("Reinforcement Card is applied.", outputStream.toString().trim());
    }
    
    @Test
    public void testUseChanceCard_CardRemoved() {
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Player player = new Player(1, null, territories);
        
        IChanceCard card = new DraftChanceCard();
        player.chanceCards.add(card); // Add a chance card to the player's collection
        
        // Test using a valid card ID for DraftChanceCard (0 represents DraftChanceCard)
        player.useChanceCard(0);
        
        // Assert that the card was removed from the player's collection
        assertFalse(player.chanceCards.contains(card));
    }
    
    @Test
    public void testUsageOfDraftChanceCard() {
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Player player = new Player(1, null, territories);
        
        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        // Test the usage of DraftChanceCard
        player.usageOfDraftChanceCard();
        
        // Reset System.out
        System.setOut(System.out);
        
        // Assert that the correct number of army cards were added
        assertEquals(2, player.armyCards.size());
    }
    @Test
    public void testRepOk() {
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Player player = new Player(1, null, territories);

        // Assert that the representation invariant holds initially
        assertTrue(player.repOk());

        // Add a chance card to the player's collection
        IChanceCard card = new DraftChanceCard();
        player.chanceCards.add(card);

        // Assert that the representation invariant still holds after adding the card
        assertTrue(player.repOk());

        // Remove the chance card from the player's collection
        player.chanceCards.remove(card);

        // Assert that the representation invariant holds after removing the card
        assertTrue(player.repOk());

        // Clear the player's chance cards collection
        player.chanceCards.clear();

        // Assert that the representation invariant still holds after clearing the collection
        assertTrue(player.repOk());
    }

}


