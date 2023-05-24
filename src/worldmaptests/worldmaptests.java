package worldmaptests;

import java.awt.Shape;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import uipackage.WorldMap;

public class worldmaptests {
    //world map class tests

    //Overview of WorldMap: 
    /*The WorldMap class represents a graphical user interface component that displays a world map. It allows users to interact with the map by clicking on 
    different regions and provides functionality to identify and highlight the clicked regions. The class uses Java's AWT and Swing libraries to handle graphics 
    and user input. The map image is loaded from a file and processed to extractregions and shapes. The class provides methods to manipulate the map, 
    such as changing shape colors and retrieving information about clicked shapes.*/
	
	
	@Test
    public void testShapeListInit() {
        WorldMap worldMap = new WorldMap();
        ArrayList<Shape> shapeList = worldMap.getShapeList();
        Assertions.assertFalse(shapeList.isEmpty(), "Shape list should not be empty");
        Assertions.assertEquals(103, shapeList.size(), "Expected number of shapes: 12");
    }
    
}
