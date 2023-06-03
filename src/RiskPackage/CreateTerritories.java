package RiskPackage;

import java.awt.Shape;
import java.util.ArrayList;

import uipackage.WorldMap;

public class CreateTerritories {
    ArrayList<Shape> shapeList = WorldMap.getShapeList();
    ArrayList<Territory> territories = new ArrayList<Territory>();
    public CreateTerritories(){
        for(Shape shape: shapeList){
            Territory northWestTerritory = new Territory("North West", shapeList.get(0));
            Territory Yakutsk = new Territory("Yakutsk", shapeList.get(1));
            Territory Greenland = new Territory("Greenland", shapeList.get(2));
            Territory Alaska = new Territory("Alaska", shapeList.get(3));



        
            territories.add(northWestTerritory);
            territories.add(Yakutsk);
            territories.add(Greenland);
            territories.add(Alaska);
        }
    }
}
