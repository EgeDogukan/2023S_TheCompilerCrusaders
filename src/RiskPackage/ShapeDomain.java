package RiskPackage;

import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
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




    public void InitNeighbors(){
       
        
        ArrayList<Integer> row0 = new ArrayList<Integer>();//North West Territory
        row0.add(3);
        row0.add(7);
        row0.add(2);
        
        ArrayList<Integer> row1 = new ArrayList<Integer>();//Yakutsk
        row1.add(9);
        row1.add(6);
        row1.add(13);

        ArrayList<Integer> row2 = new ArrayList<Integer>();//Greenland
        row2.add(0);
        row2.add(11);
        row2.add(12);

        ArrayList<Integer> row3 = new ArrayList<Integer>();//Alaska
        row3.add(0);
        row3.add(7);
        row3.add(13);
        
        ArrayList<Integer> row4 = new ArrayList<Integer>();//Scandinavia
        row4.add(8);
        row4.add(14);

        ArrayList<Integer> row5 = new ArrayList<Integer>();//New Kamchatka
        row5.add(13);

        ArrayList<Integer> row6 = new ArrayList<Integer>();//Irkutsk
        row6.add(9);
        row6.add(17);
        row6.add(13);
        row6.add(1);

        ArrayList<Integer> row7 = new ArrayList<Integer>();//Alberta
        row7.add(3);
        row7.add(0);
        row7.add(11);
        row7.add(19);

        ArrayList<Integer> row8 = new ArrayList<Integer>();//Northern Europe
        row8.add(4);
        row8.add(16);
        row8.add(15);
        row8.add(14);

        ArrayList<Integer> row9 = new ArrayList<Integer>();//Siberia
        row9.add(10);
        row9.add(17);
        row9.add(6);
        row9.add(1);

        ArrayList<Integer> row10 = new ArrayList<Integer>();//Ural
        
        row10.add(14);
        row10.add(18);
        row10.add(21);
        row10.add(9);
        
        ArrayList<Integer> row11 = new ArrayList<Integer>();//Ontorio
        
        row11.add(0);
        row11.add(7);
        row11.add(19);
        row11.add(20);
        row11.add(12);
        row11.add(3);


        ArrayList<Integer> row12 = new ArrayList<Integer>();//Quebec
        row12.add(11);
        row12.add(20);

        ArrayList<Integer> row13 = new ArrayList<Integer>();//Kamchatka
        row13.add(1);
        row13.add(6);
        row13.add(17);

        ArrayList<Integer> row14 = new ArrayList<Integer>();//Ukrania
        row14.add(4);
        row14.add(8);
        row14.add(15);
        row14.add(10);
        row14.add(23);
        row14.add(18);

        ArrayList<Integer> row15 = new ArrayList<Integer>();//Southern Europe
        row15.add(14);
        row15.add(16);
        row15.add(23);
        row15.add(8);

        ArrayList<Integer> row16 = new ArrayList<Integer>();//Western Europe
        row16.add(27);
        row16.add(8);
        row16.add(15);

        ArrayList<Integer> row17 = new ArrayList<Integer>();//Mongolia
        row17.add(21);
        row17.add(9);
        row17.add(6);
        row17.add(13);

        ArrayList<Integer> row18 = new ArrayList<Integer>();//Afghanistan
        row18.add(14);
        row18.add(10);
        row18.add(21);
        row18.add(26);
        row18.add(23);

        ArrayList<Integer> row19 = new ArrayList<Integer>();//Western United States
        row19.add(7);
        row19.add(11);
        row19.add(20);
        row19.add(24);


        ArrayList<Integer> row20 = new ArrayList<Integer>();//Eastern United States
        row20.add(19);
        row20.add(24);
        row20.add(12);

        ArrayList<Integer> row21 = new ArrayList<Integer>();//Chinia
        row21.add(26);
        row21.add(25);
        row21.add(18);
        row21.add(17);
        row21.add(9);
        row21.add(10);


        ArrayList<Integer> row22 = new ArrayList<Integer>();//Egypt
        row22.add(23);
        row22.add(32);
        row22.add(27);
        row22.add(25);

        ArrayList<Integer> row23 = new ArrayList<Integer>();//Middel East
        row23.add(22);
        row23.add(32);
        row23.add(18);
        row23.add(22);
        row23.add(26);
        row23.add(14);
        row23.add(15);


        ArrayList<Integer> row24 = new ArrayList<Integer>();//Central America
        row24.add(19);
        row24.add(20);
        row24.add(28);


        ArrayList<Integer> row25 = new ArrayList<Integer>();//Slam
        row25.add(26);
        row25.add(30);
        row25.add(21);

        ArrayList<Integer> row26 = new ArrayList<Integer>();//India
        row26.add(25);
        row26.add(23);
        row26.add(18);
        row26.add(21);

        ArrayList<Integer> row27 = new ArrayList<Integer>();//North Africa
        row27.add(22);
        row27.add(32);
        row27.add(33);
        row27.add(36);

        ArrayList<Integer> row28 = new ArrayList<Integer>();//Venezuela
        row28.add(35);
        row28.add(36);
        row28.add(24);

        ArrayList<Integer> row29 = new ArrayList<Integer>();//Indonesia
        row29.add(30);
        row29.add(25);
        row29.add(38);
        row29.add(31);

        ArrayList<Integer> row30 = new ArrayList<Integer>();//RebelIndonesia
        row30.add(29);

        ArrayList<Integer> row31 = new ArrayList<Integer>();//New Guinea
        row31.add(29);
        row31.add(39);
        row31.add(38);

        ArrayList<Integer> row32 = new ArrayList<Integer>();//East Africa
        row32.add(22);
        row32.add(37);
        row32.add(33);
        row32.add(23);
        row32.add(34);

        ArrayList<Integer> row33 = new ArrayList<Integer>();//Congo
        row33.add(27);
        row33.add(37);
        row33.add(32);

        ArrayList<Integer> row34 = new ArrayList<Integer>();//Madagascar
        row34.add(32);
        row34.add(37);

        ArrayList<Integer> row35 = new ArrayList<Integer>();//Peru
        row35.add(28);
        row35.add(40);
        row35.add(36);

        ArrayList<Integer> row36 = new ArrayList<Integer>();//Brazil
        row36.add(35);
        row36.add(28);
        row36.add(40);

        ArrayList<Integer> row37 = new ArrayList<Integer>();//South Africa
        row37.add(33);
        row37.add(32);
        row37.add(34);

        ArrayList<Integer> row38 = new ArrayList<Integer>();//Western Australia

        row38.add(39);
        row38.add(30);
        row38.add(31);

        ArrayList<Integer> row39 = new ArrayList<Integer>();//Eastern Australia
        row39.add(38);
        row39.add(31);

        ArrayList<Integer> row40 = new ArrayList<Integer>();//Eastern Australia
        row40.add(35);
        row40.add(36);




        WorldMap.neighbourList.add(row0);
        WorldMap.neighbourList.add(row1);
        WorldMap.neighbourList.add(row2);
        WorldMap.neighbourList.add(row3);
        WorldMap.neighbourList.add(row4);
        WorldMap.neighbourList.add(row5);
        WorldMap.neighbourList.add(row6);
        WorldMap.neighbourList.add(row7);
        WorldMap.neighbourList.add(row8);
        WorldMap.neighbourList.add(row9);
        WorldMap.neighbourList.add(row10);
        WorldMap.neighbourList.add(row11);
        WorldMap.neighbourList.add(row12);
        WorldMap.neighbourList.add(row13);
        WorldMap.neighbourList.add(row14);
        WorldMap.neighbourList.add(row15);
        WorldMap.neighbourList.add(row16);
        WorldMap.neighbourList.add(row17);
        WorldMap.neighbourList.add(row18);
        WorldMap.neighbourList.add(row19);
        WorldMap.neighbourList.add(row20);
        WorldMap.neighbourList.add(row21);
        WorldMap.neighbourList.add(row22);
        WorldMap.neighbourList.add(row23);
        WorldMap.neighbourList.add(row24);
        WorldMap.neighbourList.add(row25);
        WorldMap.neighbourList.add(row26);
        WorldMap.neighbourList.add(row27);
        WorldMap.neighbourList.add(row28);
        WorldMap.neighbourList.add(row29);
        WorldMap.neighbourList.add(row30);
        WorldMap.neighbourList.add(row31);
        WorldMap.neighbourList.add(row32);
        WorldMap.neighbourList.add(row33);
        WorldMap.neighbourList.add(row34);
        WorldMap.neighbourList.add(row35);
        WorldMap.neighbourList.add(row36);
        WorldMap.neighbourList.add(row37);
        WorldMap.neighbourList.add(row38);
        WorldMap.neighbourList.add(row39);
        WorldMap.neighbourList.add(row40);
        
    }


}
