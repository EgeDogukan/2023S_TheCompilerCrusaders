package Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import RiskPackage.*;



public class AttackTerritoryTest {
    @Test
    public void testAttackTerritory() {//Test if Attacker terretory can attack destination when he has higher strength
        // Creating Territories
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);

        // Setting armies on territories
        Attacker.setArmy(1,2,10);
        Destination.setArmy(0,1,8);

        AttackTerritory attack = new AttackTerritory(Attacker, Destination);

        assertTrue(attack.canAttackTerr(Attacker, Destination));
    }
    
    @Test
    public void testCannotAttackStrongerTerritory() {//Test if Attacker terretory cannot attack destination when he has lower strength
        // Similar setup, but army2 has more strength this time
        // Create Territories and armies as required
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
        Attacker.setArmy(1,2,1);
        Destination.setArmy(1,3,2);
        // Attacker should not be able to attack to destination since it cannot attack its soldiers with its current strength

        AttackTerritory attack = new AttackTerritory(Attacker, Destination);

        assertFalse(attack.canAttackTerr(Attacker, Destination));
    }

    @Test
    public void testAttackerWins() {//
        // Create territories and armies, make sure attacker wins trow dice
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
        
        // creating territories and armies here
        Attacker.setArmy(1,2,0);
        Destination.setArmy(0,1,0);

        AttackTerritory attack = new AttackTerritory(Attacker, Destination);
        attack.setDice(4,3);
        assertTrue(attack.AttackerWins(Attacker, Destination));
    }

    @Test
    public void testAttackerLoses() {
        // Create territories and armies, make sure destination wins trow dice
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
       
        // Assume the code for creating territories and armies here
        Attacker.setArmy(0,1,1);
        Destination.setArmy(1,2,0);
        AttackTerritory attack = new AttackTerritory(Attacker, Destination);
        attack.setDice(3,4);
        assertFalse(attack.AttackerWins(Attacker, Destination));
    }

    @Test
    public void testDecreaseInArmy() {
        // Check if the army size decreases after an failed attack from attacker
        // Create territories and armies
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
       
        
        Attacker.setArmy(2,1,3);
        Destination.setArmy(1,2,3);

        int initialArmySize = Attacker.armyOnTerritory.calculateStrength();

        AttackTerritory attack = new AttackTerritory(Attacker, Destination);
        attack.setDice(3,4);
        assertTrue(initialArmySize > Attacker.armyOnTerritory.calculateStrength());
    }
    @Test
    public void testTerritoryChangeOwnerAfterAttack() {
        // Create territories and armies
        // Ensure destination has only 1 infantary so that it can be captured in single attack
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
       
        Attacker.setArmy(1,5,1);
        Destination.setArmy(0,0,1);

        AttackTerritory attack = new AttackTerritory(Attacker, Destination);
        attack.AttackerWins(Attacker, Destination);
        attack.setDice(4,3);
        assertEquals(Attacker.getColor(), Destination.getColor());
    }
    @Test
    public void testInfantryReductionAfterAttack() {
        // Create territories and armies
        // Ensure attacker has at least 2 Infantry
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
               
        
        Attacker.setArmy(0,0,5);
        Destination.setArmy(0,0,2);
        
        int initialInfantry = Attacker.armyOnTerritory.getInfantry();
        AttackTerritory attack = new AttackTerritory(Attacker, Destination);
        attack.AttackerWins(Attacker, Destination);
        attack.setDice(3,4);
        assertTrue(initialInfantry > Attacker.armyOnTerritory.getInfantry());
    }

    @Test
    public void testCavalryReductionAfterSuccessfulDefense() {
        // Create territories and armies
        // Ensure army2 has at least some Cavalry

        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
               
        
        Attacker.setArmy(0,5,0);
        Destination.setArmy(0,2,0);
        

        int initialCavalry = Destination.armyOnTerritory.getCavalry();
        AttackTerritory attack = new AttackTerritory(Attacker, Destination);
        attack.AttackerWins(Attacker, Destination);
        attack.setDice(3,3);
        assertTrue(initialCavalry > Destination.armyOnTerritory.getCavalry());
    }

    @Test
    public void testArtilleryReductionAfterUnsuccessfulAttack() {
        // Create territories and armies
        

        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
               
        
        Attacker.setArmy(5,0,0);
        Destination.setArmy(2,0,0);
        
        int initialArtillery = Attacker.armyOnTerritory.getArtillery();
        AttackTerritory attack = new AttackTerritory(Attacker, Destination);
        attack.AttackerWins(Attacker, Destination);
        attack.setDice(3,4);
        assertTrue(initialArtillery > Attacker.armyOnTerritory.getArtillery());
    }
}
