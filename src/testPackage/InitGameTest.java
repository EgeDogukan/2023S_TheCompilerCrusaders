package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import RiskPackage.*;
import RiskPackage.GameController;

class InitGameTest {

    @Test
    void testInitGameWithZeroPlayersAndContinents() throws Exception {
        ArrayList<Continents> continents = new ArrayList<>();
        ArrayList<Player> players = GameController.initGame(0, 1, continents);
        
        Assertions.assertEquals(1, players.size());
    }
    
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
        // Initialize continents and territories
        // ...
        ArrayList<Player> players = GameController.initGame(3, 2, continents);
        
        Assertions.assertEquals(5, players.size());
        // Assert specific conditions for the generated players and territories
        // ...
    }
    
    @Test
    void testInitGameWithMorePlayersThanTerritories() throws Exception {
        ArrayList<Continents> continents = new ArrayList<>();
        // Initialize continents and territories
        // ...
        ArrayList<Player> players = GameController.initGame(10, 0, continents);
        
        Assertions.assertEquals(10, players.size());
        // Assert specific conditions for the generated players and territories
        // ...
    }
    
    @Test
    void testInitGameWithNegativeNumberOfComp() throws Exception {
        ArrayList<Continents> continents = new ArrayList<>();

        ArrayList<Player> players = GameController.initGame(4, -2, continents);
        
        Assertions.assertEquals(4, players.size());

    }
    
    public static void main(String[] args) {
		new InitGameTest();
	}
}

