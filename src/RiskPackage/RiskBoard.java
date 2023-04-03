package RiskPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RiskBoard {
    private ArrayList<Territory> territories;

    public RiskBoard() {
        this.territories = new ArrayList<Territory>();
    }

    public void addTerritory(Territory territory) {
        territories.add(territory);
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }
    //Handle the attacking
    public void attack(Territory defender, Territory attacker) {
        int attackerArmies = attacker.getArmies();
        int defenderArmies = defender.getArmies();
        
        // Roll the dice
        int[] attackerDice = rollDice(attackerArmies);
        int[] defenderDice = rollDice(defenderArmies);
        
        // Sort the dice rolls in descending order
        Arrays.sort(attackerDice);
        reverseArray(attackerDice);
        Arrays.sort(defenderDice);
        reverseArray(defenderDice);
        
        // Compare the highest dice rolls
        if (attackerDice[0] > defenderDice[0]) {
            defender.removeArmy();
        } else {
            attacker.removeArmy();
        }
    }

    private int[] rollDice(int numDice) {
        Random rand = new Random();
        int[] diceRolls = new int[numDice];
        for (int i = 0; i < numDice; i++) {
            diceRolls[i] = rand.nextInt(6) + 1;
        }
        return diceRolls;
    }
    private void reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

}