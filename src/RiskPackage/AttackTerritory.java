package RiskPackage;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.xml.catalog.Catalog;

import uipackage.WorldMap;

public class AttackTerritory {
    Territory territory;
    Territory destination;
    //Trowing Two dices
    private int defenderSides = 6;
    private int attackerSides = 6;
    private Random random = new Random();
    public static ArrayList<Color> colorList = new ArrayList<Color>();
    int defenderDiceResult;
    int attackerDiceResult;
    public Army armyOnTerritory;
    private boolean terrWins;
    private boolean canAttack = true;
    public AttackTerritory(Territory territory, Territory destination){
        this.territory = territory;
        this.destination = destination;
        this.colorList = WorldMap.getColorList();
        setDice(random.nextInt(attackerSides) + 1, random.nextInt(defenderSides) + 1);
        
        if(destination != null){
         
                if(defenderDiceResult<attackerDiceResult){
                    
                    
                    if(territory.armyOnTerritory.getArtillery() >= destination.armyOnTerritory.getArtillery()&& destination.armyOnTerritory.getArtillery() != 0){
                        destination.armyOnTerritory.setArtillery(destination.armyOnTerritory.getArtillery() - 1);
                    }
                    if(territory.armyOnTerritory.getInfantry() >= destination.armyOnTerritory.getInfantry() && destination.armyOnTerritory.getInfantry() != 0){
                        destination.armyOnTerritory.setInfantry(destination.armyOnTerritory.getInfantry() - 1);
                    }
                    
                    if(territory.armyOnTerritory.getCavalry() >= destination.armyOnTerritory.getCavalry() && destination.armyOnTerritory.getCavalry() != 0){
                        destination.armyOnTerritory.setCavalry(destination.armyOnTerritory.getCavalry() - 1);
                    }
                    //destination.decreaseArmy(territory);
                    if(destination.armyOnTerritory.calculateStrength()<=0){
                        destination.armyOnTerritory.setInfantry(1);
                        destination.setColor(territory.getColor());
                    }
                }
                else {
                    
                    
                    if(/*destination.armyOnTerritory.getArtillery() >= territory.armyOnTerritory.getArtillery()&&*/ territory.armyOnTerritory.getArtillery() != 0){
                        territory.armyOnTerritory.setArtillery(territory.armyOnTerritory.getArtillery() - 1);
                    }
                    else if(/*destination.armyOnTerritory.getInfantry() >= territory.armyOnTerritory.getInfantry() &&*/ territory.armyOnTerritory.getInfantry() != 0){
                        territory.armyOnTerritory.setInfantry(territory.armyOnTerritory.getInfantry() - 1);
                    }
                    else if(/*destination.armyOnTerritory.getCavalry() >= territory.getCArym() && */territory.armyOnTerritory.getCavalry() != 0){
                        territory.armyOnTerritory.setCavalry(territory.armyOnTerritory.getCavalry() - 1);
                    }
                    //territory.decreaseArmy(destination);
                
                    if(territory.armyOnTerritory.calculateStrength() <= 0){
                        territory.armyOnTerritory.setInfantry(1);
                        
                    }
                }
            
            
        
            
        }


        


    }
    public boolean AttackerWins(Territory territory, Territory destination){
        if(destination != null){
            if(destination.armyOnTerritory.calculateStrength() <= territory.armyOnTerritory.calculateStrength()){
                canAttack = true;
                if(defenderDiceResult<attackerDiceResult){
                    terrWins = true;
                    
                    
                }
                else {
                    terrWins = false;
                    
                    
                }
            }
            
        
            
        }


        return terrWins;

        
    }
    public void setDice(int attackerDice, int defenderDice){
        this.defenderDiceResult = defenderDice;
        this.attackerDiceResult = attackerDice;
    }
    public boolean canAttackTerr(Territory territory, Territory destination){
        if (destination.armyOnTerritory.calculateStrength() > territory.armyOnTerritory.calculateStrength()){
                        
            canAttack = false;
            
            
        }
        else if (destination.armyOnTerritory.calculateStrength() <= territory.armyOnTerritory.calculateStrength()){
            canAttack = true;
        }
        return canAttack;
    }
     
}
