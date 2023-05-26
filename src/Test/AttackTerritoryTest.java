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
    public void testAttackTerritory() {
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
    public void testCannotAttackStrongerTerritory() {
        // Similar setup, but army2 has more strength this time
        // Create Territories and armies as required
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
        Attacker.setArmy(1,2,1);
        Destination.setArmy(1,3,2);
        // Attacker should not be able to attack to destination since it cannot attack its soldiers with his

        AttackTerritory attack = new AttackTerritory(Attacker, Destination);

        assertFalse(attack.canAttackTerr(Attacker, Destination));
    }

    @Test
    public void testAttackerWins() {
        // Create territories and armies, make sure army1 has more strength
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
        
        // creating territories and armies here
        Attacker.setArmy(1,2,0);
        Destination.setArmy(0,1,0);

        AttackTerritory attack = new AttackTerritory(Attacker, Destination);

        assertTrue(attack.AttackerWins(Attacker, Destination));
    }

    @Test
    public void testAttackerLoses() {
        // Create territories and armies, make sure army2 has more strength
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
       
        // Assume the code for creating territories and armies here
        Attacker.setArmy(0,1,1);
        Destination.setArmy(1,2,0);
        AttackTerritory attack = new AttackTerritory(Attacker, Destination);

        assertFalse(attack.AttackerWins(Attacker, Destination));
    }

    @Test
    public void testDecreaseInArmy() {
        // Check if the army size decreases after an attack
        // Create territories and armies
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
       
        // Assume the code for creating territories and armies here
        Attacker.setArmy(2,1,3);
        Destination.setArmy(1,2,3);

        int initialArmySize = Attacker.armyOnTerritory.calculateStrength();

        AttackTerritory attack = new AttackTerritory(Attacker, Destination);

        assertTrue(initialArmySize > Attacker.armyOnTerritory.calculateStrength());
    }
    @Test
    public void testTerritoryChangeOwnerAfterAttack() {
        // Create territories and armies
        // Ensure army1 has significantly more strength to ensure win
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
       
        Attacker.setArmy(1,5,1);
        Destination.setArmy(0,0,1);

        AttackTerritory attack = new AttackTerritory(Attacker, Destination);
        attack.AttackerWins(Attacker, Destination);

        assertEquals(Attacker.getColor(), Destination.getColor());
    }
    public void testInfantryReductionAfterAttack() {
        // Create territories and armies
        // Ensure army1 has at least some Infantry
        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
               
        
        Attacker.setArmy(0,0,5);
        Destination.setArmy(0,0,2);
        
        int initialInfantry = Attacker.armyOnTerritory.getInfantry();
        AttackTerritory attack = new AttackTerritory(Attacker, Destination);
        attack.AttackerWins(Attacker, Destination);

        assertTrue(initialInfantry > Attacker.armyOnTerritory.getInfantry());
    }

    @Test
    public void testCavalryReductionAfterDefense() {
        // Create territories and armies
        // Ensure army2 has at least some Cavalry

        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
               
        
        Attacker.setArmy(0,5,0);
        Destination.setArmy(0,2,0);
        

        int initialCavalry = Destination.armyOnTerritory.getCavalry();
        AttackTerritory attack = new AttackTerritory(Attacker, Destination);
        attack.AttackerWins(Attacker, Destination);

        assertTrue(initialCavalry > Destination.armyOnTerritory.getCavalry());
    }

    @Test
    public void testArtilleryReductionAfterUnsuccessfulAttack() {
        // Create territories and armies
        // Ensure army1 has at least some Artillery and it's weaker

        Territory Attacker = new Territory(1, 1, 1, 1, "Attacker", Color.black, null, 1);
        Territory Destination = new Territory(2, 2, 1, 1, "Destination", Color.red, null, 2);
               
        
        Attacker.setArmy(5,0,0);
        Destination.setArmy(2,0,0);
        
        int initialArtillery = Attacker.armyOnTerritory.getArtillery();
        AttackTerritory attack = new AttackTerritory(Attacker, Destination);
        attack.AttackerWins(Attacker, Destination);

        assertTrue(initialArtillery > Attacker.armyOnTerritory.getArtillery());
    }
}
