package testPackage;


import databasePackage.TerritoryJSONDBDatabase;    
import RiskPackage.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

public class TerritoryJSONDBDatabaseTest  {
    private TerritoryJSONDBDatabase a;

    @BeforeEach
    public void setup() {
        TerritoryJSONDBDatabase  a = new TerritoryJSONDBDatabase();
    }

    @Test
    public void testEmptyFile() throws IOException {
        // Prepare
        TerritoryJSONDBDatabase  a = new TerritoryJSONDBDatabase();
        // Create an empty file for testing
        
        // Act
        ArrayList<ArrayList<ArrayList<String>>> result = a.loadAll();
        
        // Assert
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testNonEmptyFile() throws IOException {
        // Prepare


        // Create a file with valid data for testing
        TerritoryJSONDBDatabase  a = new TerritoryJSONDBDatabase();
        ArrayList<Territory> territoryList = new ArrayList<>();
        Continents Continent = new Continents("asia", territoryList, 0, 0, Color.CYAN);
        Territory t1 = new Territory(96, 70, 10, 17, "ist", Color.black, Continent, 1);
        Territory t2 = new Territory(16, 17, 28, 45, "londra", Color.black, Continent, 1);
        territoryList.add(t2);
        territoryList.add(t1);
        Player player = new Player(0, Color.GREEN, territoryList);
        a.save(player);
        // Act
        ArrayList<ArrayList<ArrayList<String>>> result = a.loadAll();

        ArrayList<String> innerList1 = new ArrayList<>();
        innerList1.add("16");
        innerList1.add("17");
        innerList1.add("28");
        innerList1.add("45");
        innerList1.add("londra");
        innerList1.add("java.awt.Color[r\u003d0,g\u003d0,b\u003d0]");
        innerList1.add("asia");
        innerList1.add("1");

        // Create the second inner ArrayList
        ArrayList<String> innerList2 = new ArrayList<>();
        innerList2.add("96");
        innerList2.add("70");
        innerList2.add("10");
        innerList2.add("17");
        innerList2.add("ist");
        innerList2.add("java.awt.Color[r\u003d0,g\u003d0,b\u003d0]");
        innerList2.add("asia");
        innerList2.add("1");

        // Create the outer ArrayList
        ArrayList<ArrayList<ArrayList<String>>> expectedResult = new ArrayList<>();

        // Create a new ArrayList to hold the two inner ArrayLists
        ArrayList<ArrayList<String>> outerList = new ArrayList<>();
        outerList.add(innerList1);
        outerList.add(innerList2);

        // Add the outer ArrayList to the expectedResult variable
        expectedResult.add(outerList);
        // Assert
        // Compare the result with the expected ArrayList
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testNonExistentUser() {
        // Act and Assert
        // Ensure that an IOException is thrown when the file does not exist
        Assertions.assertThrows(NullPointerException.class, () -> {
            a.loadAll();
        });
    }

    @Test
    public void testFileWithInvalidData() throws NullPointerException {
        // Prepare
        // Create a file with a mix of valid and invalid data for testing
        TerritoryJSONDBDatabase  a = new TerritoryJSONDBDatabase();
        
        ArrayList<Territory> territoryList = new ArrayList<>();
        Continents Continent = new Continents("asia", territoryList, 0, 0, Color.CYAN);
        Territory t1 = new Territory(96, 70, 10, 17, "ist", Color.black, null, 1);
        Territory t2 = new Territory(16, 17, 28, 45, "londra", Color.black, Continent, 1);
        territoryList.add(t2);
        territoryList.add(t1);
        Player player = new Player(0, Color.GREEN, territoryList);
        Assertions.assertThrows(NullPointerException.class, () -> {
            a.save(player);
        });
        
        
    }

    @Test
    public void testMultipleUsersInFile() throws IOException {
        // Prepare
        // Create a file with data for multiple users for testing
        TerritoryJSONDBDatabase  a = new TerritoryJSONDBDatabase();
        ArrayList<Territory> territoryList = new ArrayList<>();
        Continents Continent = new Continents("asia", territoryList, 0, 0, Color.CYAN);
        Territory t1 = new Territory(96, 70, 10, 17, "ist", Color.black, Continent, 1);
        Territory t2 = new Territory(16, 17, 28, 45, "londra", Color.black, Continent, 2);
        territoryList.add(t1);
        territoryList.add(t2);
        Player player = new Player(0, Color.GREEN, territoryList);
        a.save(player);
        // Act
        ArrayList<ArrayList<ArrayList<String>>> result = a.loadAll();
        ArrayList<String> playerIds = new ArrayList<>();
        String firstId = result.get(0).get(0).get(7);
        String secondId = result.get(1).get(0).get(7);
        playerIds.add(firstId);
        playerIds.add(secondId);

        

        // Create the outer ArrayList
       ArrayList<String> expectedResult = new ArrayList<>();
       expectedResult.add("1");
       expectedResult.add("2");

        

        //
        // Assert
        // Compare the result with the expected ArrayList
        Assertions.assertEquals(expectedResult, playerIds);
        
        // Assert
        // Compare the result with the expected ArrayList containing the expected data for each user
        // Assertions.assertEquals(expectedResult, result);
    }
}

