package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import RiskPackage.*;

class InitGameTest {

    
    @Test
    void testInitGameWithOnePlayerAndNoContinents() throws Exception {
        ArrayList<Continents> continents = new ArrayList<>();
        ArrayList<Player> players = GameController.initGame(1, 0, continents);
        
        Assertions.assertEquals(1, players.size());
        Assertions.assertEquals(0, players.get(0).getTerritories().size());
    }
    
    @Test
    void testInitGameWithMultiplePlayersAndContinents() throws Exception {
        ArrayList<Continents> continents = new ArrayList<>();
        
        for (int i=0;i<8;i++)
        	continents.add(new Continents("name", new ArrayList<Territory>(), 0, 0, Color.red));
        
        
        ArrayList<Player> players = GameController.initGame(3, 2, continents);
        
        Assertions.assertEquals(5, players.size());
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
    void testInitGameWithMorePlayersThanTerritories() throws Exception {
    	ArrayList<Continents> continents = new ArrayList<>();
        ArrayList<Player> players = GameController.initGame(10, 0, continents);
        
        Assertions.assertEquals(10, players.size());
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
    void testInitGameWithNegativeNumberOfComputerPlayer() throws Exception {
        ArrayList<Continents> continents = new ArrayList<>();

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            GameController.initGame(5, -3, continents);
        });

        String expectedErrorMessage = "number of computer player cannot be negative";
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

