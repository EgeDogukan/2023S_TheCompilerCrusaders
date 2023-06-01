package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import RiskPackage.*;

class InitGameTest {

	
		/*
		Specifications for initGame() Method:
		initGame initialises the game by assigning territories to players based on the specified parameters.
		
		Requires:
		The number of players and computer players should not be negative.
		The continent list should not be null.
		
		Modifies:
		playerList
		territories
		
		Effects:
		Returns an ArrayList of Player objects representing the initialized players with assigned territories.
		Throws an exception if any of the following conditions are violated:
		- The number of players is negative.
		- The number of computer players is negative.
		- The continent list is null.
		- There are insufficient territories to assign to players.
		
		*/
    
    @Test
    void testInitGameWithOnePlayerAndNoContinents() throws Exception {
    	
    	/*
    	This test case verifies the behavior of the initGame() method when there is only one player and no continents.
		It initializes an empty continent list and calls the initGame() method with one player and zero continents.
		The test checks that the returned player list has a size of 1 and the first player has no territories assigned.
    	*/
    	
        ArrayList<Continents> continents = new ArrayList<>();
        ArrayList<Player> players = GameController.initGame(1, 0, continents);
        
        Assertions.assertEquals(1, players.size());
        Assertions.assertEquals(0, players.get(0).getTerritories().size());
    }
    
    @Test
    void testInitGameWithMultiplePlayersAndContinents() throws Exception {
    	
    	/* 
    	This test case tests the initGame() method with multiple players and continents.
		It creates a list of 8 continents and calls the initGame() method with 3 human players and 2 computer players.
		The test checks that the returned player list has a size of 5. 
    	*/
    	
        ArrayList<Continents> continents = new ArrayList<>();
        
        for (int i=0;i<8;i++)
        	continents.add(new Continents("name", new ArrayList<Territory>(), 0, 0, Color.red));
        
        
        ArrayList<Player> players = GameController.initGame(3, 2, continents);
        
        Assertions.assertEquals(5, players.size());
    }
    
    @Test
    
    void repOK() throws Exception {
    	/*
        The repOK() test case calls the testInitGameWithMultiplePlayersAndContinents() method to initialize the game and validate its behavior. 
        It then checks the representation invariant of the GameController class by calling the repOk() method on the GameController instance. 
        The test case asserts that the repOk() method returns true, indicating that the GameController class is in a valid state 
        and satisfies its representation invariant. 
        */
    	testInitGameWithMultiplePlayersAndContinents();
    	GameController instance =  GameController.getInstance();
        Assertions.assertTrue(instance.repOk());
    }
    
   
    
    @Test
    void testInitGameWithNullContinents() throws Exception {
        ArrayList<Continents> continents = null;
        
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            GameController.initGame(3, 1, continents);
        });
        
        String expectedErrorMessage = "continent list cannot be null";
        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }

    
    @Test
    void testInitGameWithNegativeNumberOfPlayer() throws Exception {
        ArrayList<Continents> continents = new ArrayList<>();

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            GameController.initGame(-4, 1, continents);
        });

        String expectedErrorMessage = "number of player cannot be negative";
        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }

    
    @Test
    void testInitGameWithPlayersGreaterThanTerritories() throws Exception {
        ArrayList<Continents> continents = new ArrayList<>();
        // Assume there are 10 territories in the continents list

        for (int i=0;i<4;i++) {
        	continents.add(new Continents("name", new ArrayList<Territory>(), 0, 0, Color.red));
        }

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            GameController.initGame(12, 3, continents);
        });

        String expectedErrorMessage = "insufficient territories to assign to players";
        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }

    
   
}

