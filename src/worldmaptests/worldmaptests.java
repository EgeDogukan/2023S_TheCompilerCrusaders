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
	

        // Test case for verifying the initialization of the shape list in WorldMap class.
        // Requires: None.
        // Modifies: None.
        // Effects: - Verifies that the shape list is not empty.
        //          - Verifies that the expected number of shapes (103) is initialized in the shape list.
	@Test
    public void testShapeListInit() {                   // testing if shapelist init correctly
        WorldMap worldMap = new WorldMap();
        ArrayList<Shape> shapeList = worldMap.getShapeList();
        Assertions.assertFalse(shapeList.isEmpty(), "Shape list should not be empty");
        Assertions.assertEquals(103, shapeList.size(), "Expected number of shapes: 103");
    }

        // Test case for verifying the initial state of the clicked shape in WorldMap class.
        // Requires: None.
        // Modifies: None.
        // Effects: - Verifies that the clicked shape is null initially.
    @Test
    public void testClickedShapeNullInitially() {       //testing clicked shape init correctly and working
        WorldMap worldMap = new WorldMap();
        Shape clickedShape = worldMap.getClickedShape();
        Assertions.assertNull(clickedShape, "Clicked shape should be null initially");
    }

        // Test case for setting the color of a shape in WorldMap class to blue.
        // Requires: None.
        // Modifies: The color of the specified shape in the color list.
        // Effects: - Verifies that the color of the shape is changed to blue.
    @Test
    public void testSetShapeColorToBlue() {
        WorldMap worldMap = new WorldMap();
        ArrayList<Shape> shapeList = worldMap.getShapeList();
        ArrayList<Color> colorList = worldMap.getColorList();

        // Change color of the first shape
        Shape shape = shapeList.get(0);
        worldMap.setShapeColor(shape, Color.BLUE);

        Assertions.assertEquals(Color.BLUE, colorList.get(0), "Shape color should be changed to blue");
    }

        // Test case for setting the color of a shape in WorldMap class to red.
        // Requires: None.
        // Modifies: The color of the specified shape in the color list.
        // Effects: - Verifies that the color of the shape is changed to red.
    @Test
    public void testSetShapeColorToRed() {
        WorldMap worldMap = new WorldMap();
        ArrayList<Shape> shapeList = worldMap.getShapeList();
        ArrayList<Color> colorList = worldMap.getColorList();

        // Change color of the first shape
        Shape shape = shapeList.get(0);
        worldMap.setShapeColor(shape, Color.RED);

        Assertions.assertEquals(Color.RED, colorList.get(0), "Shape color should be changed to red");
    }

        // Test case for resetting the color of a shape in WorldMap class.
        // Requires: None.
        // Modifies: The color of the specified shape in the color list.
        // Effects: - Verifies that the color of the shape is reset to its original color.
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

        // Test case for the repOk() method of the WorldMap class.
        // Requires: None.
        // Modifies: None.
        // Effects: - Verifies that the repOk() method returns true when the class invariant is satisfied.
    @Test
    public void testRepOk() {
        WorldMap worldMap = new WorldMap();
        Assertions.assertTrue(worldMap.repOk());
    }

        // Test case for removing a shape from the shape list in WorldMap class.
        // Requires: The specified shape exists in the shape list.
        // Modifies: The shape list of WorldMap class.
        // Effects: - Verifies that the specified shape is removed from the shape list.
        //          - Verifies that the shape list no longer contains the removed shape after removal.
        //          - Verifies that attempting to remove the same shape again has no effect on the shape list.
    @Test
    public void testRemoveFromShapeList() {
        WorldMap worldMap = new WorldMap();
        ArrayList<Shape> shapeList = worldMap.getShapeList();
        Shape shape = shapeList.get(0);

        // Remove shape from shape list
        worldMap.removeFromShapeList(shape);

        Assertions.assertFalse(shapeList.contains(shape), "Shape should be removed from the shape list");

        // Try to remove the same shape again (should have no effect)
        worldMap.removeFromShapeList(shape);
        Assertions.assertFalse(shapeList.contains(shape), "Shape should not be in the shape list");
    }
}
