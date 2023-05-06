package RiskPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RiskBoard extends JPanel{
    private ArrayList<Continents> continents;
    private Color color;
	private String name;
	public boolean isIncluded;
	private int width;
	private int height;
    
    public RiskBoard(ArrayList<Continents> continents) {
        this.continents = continents;
        this.setBackground(color);
		this.setLayout(new BorderLayout());
        this.setPreferredSize(getPreferredSize());
        this.setSize(1920, 1080);
        for (Continents cont : continents) {
            if(cont.isIncluded==true)//If continent selected in building mode add this to game otherwise not.
			    this.add(cont);
		}
        this.setLayout(new BorderLayout());
        
      
        
		this.setOpaque(true);
        this.setFocusable(true);
        this.setEnabled(true);
        this.setVisible(true);
    }

    // public void addTerritory(Territory territory) {
    //     territories.add(territory);
        
    // }

    // public ArrayList<Territory> getTerritories() {
    //     return territories;
    // }
    
    //Handle the attacking
    /*public void attack(Territory defender, Territory attacker) {
        int attackerArmies = attacker.getArmies();
        int defenderArmies = defender.getArmies();
        
        // Roll the dice
        int[] attackerDice = rollDice(MinDiceRoll(attackerArmies));
        int[] defenderDice = rollDice(MinDiceRoll(defenderArmies));
        
        // Sort the dice rolls in descending order
        Arrays.sort(attackerDice);
        reverseArray(attackerDice);
        Arrays.sort(defenderDice);
        reverseArray(defenderDice);
        
        if(attackerArmies>=2 && defenderArmies>1) {
        	// Compare the highest dice rolls
            if (attackerDice[0] > defenderDice[0]) {
                defender.removeArmy();
            } else {
                attacker.removeArmy();
            }
        }
    }*/

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
    private int MinDiceRoll(int armies) {
    	 if (armies >= 3) {
    		 armies= 3; 
    	 }
    	return armies;
    }

    


}