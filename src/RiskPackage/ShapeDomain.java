package RiskPackage;

import java.awt.Color;
import java.awt.Shape;

import uipackage.WorldMap;

public class ShapeDomain {
    private Shape clickedShape;


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

}
