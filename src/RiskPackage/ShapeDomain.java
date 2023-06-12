package RiskPackage;

import java.awt.Color;
import java.awt.Shape;
import java.util.Random;

import uipackage.BuildingModeNew;
import uipackage.RunningModeNew;
import uipackage.WorldMap;

public class ShapeDomain {
    private Shape clickedShape;
    public int powerOfShape(Shape shape){
        int index = WorldMap.getShapeIndex(shape);
        
        //int power = Integer.parseInt(armArrayLists[index][0].toString()) + 5 * Integer.parseInt(armArrayLists[index][1].toString()) + 10 * Integer.parseInt(armArrayLists[index][2].toString());
       
        int power = getShapeArmyArtillery(index)*10+ 5 * getShapeArmyCavalry(index) + getShapeArmyInfantry(index) ;
        System.out.println("result is  "+ power);
        return power;
    }

    public int decreasePowerOfShapeArtillery(Shape shape, int amount){
        int index = WorldMap.getShapeIndex(shape);
        int power = getShapeArmyArtillery(index)+ 5 * getShapeArmyCavalry(index) + 10 * getShapeArmyInfantry(index);
        power -= amount;
        return power;
    }

    public int decreasePowerOfShapeInfantry(Shape shape, int amount){
        int index = WorldMap.getShapeIndex(shape);
        int power = getShapeArmyArtillery(index)+ 5 * getShapeArmyCavalry(index) + 10 * getShapeArmyInfantry(index);
        power -= 10*amount;
        return power;
    }
    public int decreasePowerOfShapeCavalary(Shape shape, int amount){
        int index = WorldMap.getShapeIndex(shape);
        int power = getShapeArmyArtillery(index)+ 5 * getShapeArmyCavalry(index) + 10 * getShapeArmyInfantry(index);
        power -= 5*amount;
        return power;
    }
 

    public void setShapeColor(Shape shape, Color color) {
        int colorIndex = WorldMap.shapeList.indexOf(shape);
        WorldMap.colorList.set(colorIndex, color);
    }

    public void setShapeArmyInfantry(Shape shape, int numberOfArmy){
        int armyIndex = WorldMap.shapeList.indexOf(shape);
        WorldMap.armyList[armyIndex][0]= numberOfArmy;
        

    }


    public void addShapeArmyInfantry(Shape shape, int numberOfArmy){
        int armyIndex = WorldMap.shapeList.indexOf(shape);
        WorldMap.armyList[armyIndex][0] += numberOfArmy;
    }
    public void addShapeArmyCavalary(Shape shape, int numberOfArmy){
        int armyIndex = WorldMap.shapeList.indexOf(shape);
        WorldMap.armyList[armyIndex][1] += numberOfArmy;
    }
    public void addShapeArmyArtillery(Shape shape, int numberOfArmy){
        int armyIndex = WorldMap.shapeList.indexOf(shape);
        WorldMap.armyList[armyIndex][2] += numberOfArmy;
    }



    public void decreaseShapeArmyInfantry(Shape shape, int numberOfArmy){
        int armyIndex = WorldMap.shapeList.indexOf(shape);
        WorldMap.armyList[armyIndex][0] -= numberOfArmy;
    }
    public void decreaseShapeArmyCavalary(Shape shape, int numberOfArmy){
        int armyIndex = WorldMap.shapeList.indexOf(shape);
        WorldMap.armyList[armyIndex][1] -= numberOfArmy;
    }
    public void decreaseShapeArmyArtillery(Shape shape, int numberOfArmy){
        int armyIndex = WorldMap.shapeList.indexOf(shape);
        WorldMap.armyList[armyIndex][2] -= numberOfArmy;
    }


    public void setShapeArmyCavalry(Shape shape, int numberOfArmy){
        int armyIndex = WorldMap.shapeList.indexOf(shape);
        WorldMap.armyList[armyIndex][1]= numberOfArmy;
    }
    
    public void setShapeArmyArtillery(Shape shape, int numberOfArmy){
        int armyIndex = WorldMap.shapeList.indexOf(shape);
        WorldMap.armyList[armyIndex][2]= numberOfArmy;
    }

    public int getShapeArmyInfantry(int index){
        return WorldMap.armyList[index][0];
    }

    public int getShapeArmyCavalry(int index) {
        return WorldMap.armyList[index][1];
    }

    public int getShapeArmyArtillery(int index) {
        return WorldMap.armyList[index][2];
    }

    public void SuccesfullAttack(Shape shape, Shape destinationShape, int destinationsShapeIndex){
        if(powerOfShape(destinationShape) != 0){
            if(getShapeArmyArtillery(destinationsShapeIndex) > 0){
                decreaseShapeArmyArtillery(destinationShape, 1);
                decreasePowerOfShapeArtillery(destinationShape, 1);
            }
            if(getShapeArmyCavalry(destinationsShapeIndex)>0){
                decreaseShapeArmyCavalary(destinationShape, 1);
                decreasePowerOfShapeCavalary(destinationShape, 1);
            }
            if(getShapeArmyInfantry(destinationsShapeIndex)>0){
                decreaseShapeArmyInfantry(destinationShape, 1);
                decreasePowerOfShapeInfantry(destinationShape, 1);
            }
        }
        if(powerOfShape(destinationShape) <= 0){
        
            setIndexColor(WorldMap.shapeList.indexOf(destinationShape), WorldMap.colorList.get(WorldMap.clickedShapeIndex));
            setShapeArmyInfantry(destinationShape, 1);
            decreasePowerOfShapeInfantry(shape, 1);
            decreaseShapeArmyInfantry(shape, 1);
        }
        Random random = new Random();
        int randomIndex = random.nextInt(RunningModeNew.armyCards.size());
        int chance = random.nextInt(1)+1;
        if (chance==1){
            RunningModeNew.armyCards.remove(randomIndex);
            BuildingModeNew.playerList.get(RunningModeNew.getTurn()).addArmyCard(randomIndex, 1);
            System.out.println("Infantry army card is added to the list.");
        }
        else {
            int randomIndex2 = random.nextInt(RunningModeNew.territoryCards.size());
            BuildingModeNew.playerList.get(RunningModeNew.getTurn()).addTerritoryCard(randomIndex2);
        }
}

    public void UnsuccesfullAttack(Shape shape,int currTerr){
        if(powerOfShape(shape)!= 0){
            if(getShapeArmyArtillery(currTerr) > 0){
                decreaseShapeArmyArtillery(shape, 1);
                decreasePowerOfShapeArtillery(shape, 1);
            }    
            else if(getShapeArmyCavalry(currTerr)>0){
                decreaseShapeArmyCavalary(shape, 1);
                decreasePowerOfShapeCavalary(shape, 1);
            }
            else if(getShapeArmyInfantry(currTerr)>0){
                decreaseShapeArmyInfantry(shape, 1);
                decreasePowerOfShapeInfantry(shape, 1);
            }
    }
}

public void Fortify(Shape clickedShape, Shape destinationShape, int fortifyAmountArtilarry, int fortifyAmountCavalary, int fortifyAmountInfantry){
    // Adding inputed amount of artilarries and decreasing it from territory
    addShapeArmyArtillery(destinationShape, fortifyAmountArtilarry);
    decreaseShapeArmyArtillery(clickedShape, fortifyAmountArtilarry);

    // Adding inputed amount of cavalaries and decreasing it from territory
    addShapeArmyCavalary(destinationShape, fortifyAmountCavalary);
    decreaseShapeArmyCavalary(clickedShape, fortifyAmountCavalary);

    // Adding inputed amount of infantries and decreasing it from territory
    addShapeArmyInfantry(destinationShape, fortifyAmountInfantry);
    decreaseShapeArmyInfantry(clickedShape, fortifyAmountInfantry);

}

    public void setIndexColor(int index, Color color) {
    	System.out.println("set index color function is called");
        WorldMap.colorList.set(index, color);
        WorldMap.refresh();
    }
}
