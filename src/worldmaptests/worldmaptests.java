package worldmaptests;

import java.awt.Color;
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

    /*Abstract Function:
    The WorldMap class represents a graphical user interface component that displays a world map and allows users to interact with it. It provides methods to 
    handle user input, extract regions and shapes from the map image, and manipulate the map's appearance.*/
	
	@Test
    public void testShapeListInit() {                   // testing if shapelist init correctly
        WorldMap worldMap = new WorldMap();
        ArrayList<Shape> shapeList = worldMap.getShapeList();
        Assertions.assertFalse(shapeList.isEmpty(), "Shape list should not be empty");
        Assertions.assertEquals(103, shapeList.size(), "Expected number of shapes: 103");
    }

    @Test
    public void testClickedShapeNullInitially() {       //testing clicked shape init correctly and working
        WorldMap worldMap = new WorldMap();
        Shape clickedShape = worldMap.getClickedShape();
        Assertions.assertNull(clickedShape, "Clicked shape should be null initially");
    }

    @Test
    public void testSetShapeColorToBlue() {
        WorldMap worldMap = new WorldMap();
        ArrayList<Shape> shapeList = worldMap.getShapeList();
        ArrayList<Color> colorList = worldMap.getColorList();

        // Change color of the first shape
        Shape shape = shapeList.get(0);
        Color originalColor = colorList.get(0);
        worldMap.setShapeColor(shape, Color.BLUE);

        Assertions.assertEquals(Color.BLUE, colorList.get(0), "Shape color should be changed to blue");
    }

    @Test
    public void testSetShapeColorToRed() {
        WorldMap worldMap = new WorldMap();
        ArrayList<Shape> shapeList = worldMap.getShapeList();
        ArrayList<Color> colorList = worldMap.getColorList();

        // Change color of the first shape
        Shape shape = shapeList.get(0);
        Color originalColor = colorList.get(0);
        worldMap.setShapeColor(shape, Color.RED);

        Assertions.assertEquals(Color.RED, colorList.get(0), "Shape color should be changed to red");
    }

    @Test
    public void testSetShapeColorReset() {
        WorldMap worldMap = new WorldMap();
        ArrayList<Shape> shapeList = worldMap.getShapeList();
        ArrayList<Color> colorList = worldMap.getColorList();

        // Change color of the first shape
        Shape shape = shapeList.get(0);
        Color originalColor = colorList.get(0);
        worldMap.setShapeColor(shape, Color.BLUE);

        Assertions.assertEquals(Color.BLUE, colorList.get(0), "Shape color should be changed to blue");

        // Reset color of the first shape
        worldMap.setShapeColor(shape, originalColor);
        Assertions.assertEquals(originalColor, colorList.get(0), "Shape color should be reset");
    }

    
    
}
