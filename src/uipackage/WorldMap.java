package uipackage;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import RiskPackage.GameControllerNew;
import RiskPackage.Territory;

import java.util.List;

import java.util.Collections;
import javax.imageio.ImageIO;

public class WorldMap {


    private Image star;
    private Image cross;
    private double scale = 0.01;
    private boolean isGrowing = true;
    private boolean starVisible = false;
    private boolean crossVisible = false;

    private JComponent ui = null;
    static JLabel output = new JLabel();
    public static final int SIZE = 750;
    static BufferedImage image;
    static Area area;
    ArrayList<Shape> shapeList2=null;

    int numberOfShape = 5;
    static ArrayList<Shape> shapeList = new ArrayList<>();
    //static ArrayList<ArrayList<Integer>> armyList = new ArrayList<ArrayList<Integer>>(200);
    //public static int[][] armArrayLists = new ArrayList[60][3];
    public static int[][] armyList = new int[60][3];

    static ArrayList<Color> colorList = new ArrayList<>();
    private Shape clickedShape;
    private MouseListener ml;
    private static BufferedImage bi;
    private static Graphics2D g;
    public static int clickedShapeIndex;
    final int areaThreshold = 450;
    public static boolean isEveryTerritorySelected = false;
    public int numofSelectedTerritory = 0;
    private boolean isInBuildingMode = true;
    private ArrayList<Shape> selectedShapeList = new ArrayList<>();

    private ArrayList<JTextField> textLabels = new ArrayList<>();

    ArrayList<ArrayList<Integer>> neighbourList = new ArrayList<ArrayList<Integer>>();

    private int modeForDeployAttackFortify = 0;

    public WorldMap() {
    	InitNeighbors();
    	


    	
        try {
            // for (int i = 0; i < 60; i++) {
            //     //armyList[0].add(new ArrayList<Integer>());
            //     for(int k = 0; k < 3; k++){
            //         armArrayLists[i][k] = new ArrayList<Integer>();
            //         armArrayLists[i][k].add(i);
                    
            //     }    
            // }
            
            // Set each entry in armyList to 0
            for (int i = 0; i < numberOfShape ; i++) {

                int[] innerList = new int[3];
                
                for (int j = 0; j < 3; j++) {
                    innerList[j]=0;
                }
                armyList[i]=innerList;
            }
            initUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public final void initUI() throws Exception {
        
        
        if (ui != null) {
            return;
        }
        //URL url = new URL("https://i.stack.imgur.com/N4eOn.png");
        //image = ImageIO.read(url);
        try
        {
            String cwd = System.getProperty("user.dir");
            image = ImageIO.read(new File(cwd+"/MAP3.png"));
            

        }
        catch (IOException e) {
            System.out.println("couldnt load map image!");
        }

        long then = System.currentTimeMillis();
        System.out.println("" + then);
        area = getOutline(Color.WHITE, image, 12);
        long now = System.currentTimeMillis();
        System.out.println("Time in mins: " + (now - then) / 60000d);
        shapeList2 = separateShapeIntoRegions(area);

        System.out.println("boyut:"+shapeList2.size());
        for(int i = 0; i < shapeList2.size(); i++){
            Shape shape =shapeList2.get(i);
            if((int) (shape.getBounds2D().getHeight()*shape.getBounds2D().getWidth()) > areaThreshold)
                shapeList.add(shape);
        }
        
        System.out.println("boyut2:"+shapeList.size());
        shapeList= new ArrayList<>(shapeList.subList(0, Math.min(shapeList.size(), numberOfShape)));//Change it back to 100
        System.out.println("boyut3:"+shapeList.size());
    
        ui = new JPanel(new BorderLayout(4, 4));
        ui.setBorder(new EmptyBorder(4, 4, 4, 4));
        
        output.addMouseMotionListener(new MousePositionListener());
        MouseListener ml = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	System.out.println("Butona basildi");
                Point p = MouseInfo.getPointerInfo().getLocation();
                Point p1 = output.getLocationOnScreen();
                
                int x = p.x - p1.x;
                int y = p.y - p1.y;
                Point pointOnImage = new Point(x, y);
                
                if(isInBuildingMode == true) {
                    for (Shape shape : shapeList) {

                        if (shape.contains(pointOnImage)) {   
                            //JOptionPane.showMessageDialog(null, "Clicked!"); 
                            numofSelectedTerritory++;
                            if (numofSelectedTerritory==shapeList.size()) {
                                isEveryTerritorySelected=true;
                            }                         
                            clickedShape = shape;
                            WorldMap.clickedShapeIndex=shapeList.indexOf(shape);
                            
                            Rectangle rectangle = clickedShape.getBounds();
                            double centerX = rectangle.getCenterX();
                            double centerY = rectangle.getCenterY();
                            JTextField currentField = new JTextField();
                            
                            currentField.setBounds((int) centerX, (int) centerY, 40, 40);
                            System.out.println(clickedShape);
                            currentField.setText(Integer.toString(clickedShapeIndex)+ " "+Integer.toString(powerOfShape(shape)));
                            
                            getTextLabels().add(currentField);
                            
                            	
                            //Deploy UI
                            JFrame optionFrame = new JFrame();
                            optionFrame.setSize(500, 100);
                            optionFrame.setLocationRelativeTo(null);
                            optionFrame.setTitle("buildingmodeDeploy");
                            JPanel optionPanel = new JPanel();
                            JTextField numberOfArmy = new JTextField("0");
                            numberOfArmy.setPreferredSize(new Dimension(100,50));
                            optionPanel.add(numberOfArmy);
                            optionFrame.add(optionPanel);
                            JButton retrieveInfantryButton = new JButton("Infantry");
                            retrieveInfantryButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String numberOfArmyonarea = numberOfArmy.getText();
                                    setShapeArmyInfantry(shape, Integer.parseInt(numberOfArmyonarea));   
                                    System.out.println("Infantry at shape index " + clickedShapeIndex + ": " + getShapeArmyInfantry(clickedShapeIndex));    
                                    if(Integer.parseInt(numberOfArmyonarea) > 0){
                                        optionFrame.dispose();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Enter the amount of Infantry you wish to deploy");
                                    }
                                }
                            });
                            JButton retrieveCavalryButton = new JButton("Cavalry");
                            retrieveCavalryButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String numberOfArmyonarea = numberOfArmy.getText();
                                    setShapeArmyCavalry(shape, Integer.parseInt(numberOfArmyonarea));   
                                    System.out.println("Infantry at shape index " + clickedShapeIndex + ": " + getShapeArmyCavalry(clickedShapeIndex));    
                                    if(Integer.parseInt(numberOfArmyonarea) > 0){
                                        optionFrame.dispose();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Enter the amount of Infantry you wish to deploy");
                                    }
                                }
                            });
                            JButton retrieveArtilleryButton = new JButton("Artillery");
                            retrieveArtilleryButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String numberOfArmyonarea = numberOfArmy.getText();
                                    setShapeArmyArtillery(shape, Integer.parseInt(numberOfArmyonarea)); 
                                    
                                    if(Integer.parseInt(numberOfArmyonarea) > 0){
                                        optionFrame.dispose();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Enter the amount of Artillery you wish to deploy");
                                    }
                                    System.out.println("Artillery at shape index " + clickedShapeIndex + ": " + getShapeArmyArtillery(clickedShapeIndex));

                                }
                            });

                            optionPanel.add(retrieveArtilleryButton);
                            optionPanel.add(retrieveCavalryButton);
                            optionPanel.add(retrieveInfantryButton);
                            optionFrame.setVisible(true);
                            
                            BuildingModeNew.nextTurn();
                            selectedShapeList.add(shape);
                            
                            break;
                            
                        }

                    }
                }
                else if (isInBuildingMode == false) {
                    for (Shape shape : shapeList) {

                        if (shape.contains(pointOnImage)) {  
                            if(RunningModeNew.whichStage == "Deploy") { 
                            //JOptionPane.showMessageDialog(null, "Clicked!"); 
                            numofSelectedTerritory++;
                            if (numofSelectedTerritory==shapeList.size()) {
                                isEveryTerritorySelected=true;
                            }                         
                            clickedShape = shape;
                            WorldMap.clickedShapeIndex=shapeList.indexOf(shape);
                            
                            Rectangle rectangle = clickedShape.getBounds();
                            double centerX = rectangle.getCenterX();
                            double centerY = rectangle.getCenterY();
                            JTextField currentField = new JTextField();
                            
                            
                            
                            getTextLabels().add(currentField);
                            
                            	
                            //Deploy UI
                            JFrame optionFrame = new JFrame();
                            optionFrame.setSize(500, 100);
                            optionFrame.setLocationRelativeTo(null);
                            optionFrame.setTitle("RunningModedeploy");
                            JPanel optionPanel = new JPanel();
                            JTextField numberOfArmy = new JTextField("0");
                            numberOfArmy.setPreferredSize(new Dimension(100,50));
                            optionPanel.add(numberOfArmy);
                            optionFrame.add(optionPanel);
                            JButton retrieveInfantryButton = new JButton("Infantry");
                            retrieveInfantryButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String numberOfArmyonarea = numberOfArmy.getText();
                                    setShapeArmyInfantry(shape, Integer.parseInt(numberOfArmyonarea));   
                                    System.out.println("Infantry at shape index " + clickedShapeIndex + ": " + getShapeArmyInfantry(clickedShapeIndex));    
                                    if(Integer.parseInt(numberOfArmyonarea) > 0){
                                        optionFrame.dispose();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Enter the amount of Infantry you wish to deploy");
                                    }
                                }
                            });
                            JButton retrieveCavalryButton = new JButton("Cavalry");
                            retrieveCavalryButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String numberOfArmyonarea = numberOfArmy.getText();
                                    setShapeArmyCavalry(shape, Integer.parseInt(numberOfArmyonarea));   
                                    System.out.println("Infantry at shape index " + clickedShapeIndex + ": " + getShapeArmyCavalry(clickedShapeIndex));    
                                    if(Integer.parseInt(numberOfArmyonarea) > 0){
                                        optionFrame.dispose();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Enter the amount of Infantry you wish to deploy");
                                    }
                                }
                            });
                            JButton retrieveArtilleryButton = new JButton("Artillery");
                            retrieveArtilleryButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String numberOfArmyonarea = numberOfArmy.getText();
                                    setShapeArmyArtillery(shape, Integer.parseInt(numberOfArmyonarea)); 
                                    
                                    if(Integer.parseInt(numberOfArmyonarea) > 0){
                                        optionFrame.dispose();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Enter the amount of Artillery you wish to deploy");
                                    }
                                    System.out.println("Artillery at shape index " + clickedShapeIndex + ": " + getShapeArmyArtillery(clickedShapeIndex));

                                }
                            });
                            currentField.setBounds((int) centerX, (int) centerY, 40, 40);
                            currentField.setText(Integer.toString(clickedShapeIndex)+ " "+Integer.toString(powerOfShape(shape)));
                            optionPanel.add(retrieveArtilleryButton);
                            optionPanel.add(retrieveCavalryButton);
                            optionPanel.add(retrieveInfantryButton);
                            optionPanel.add(currentField);
                            optionFrame.setVisible(true);
                            
                            
                            selectedShapeList.add(shape);
                            
                            
                            
                        }

                        if(RunningModeNew.whichStage == "Attack") { 
                            JFrame attackFrame = new JFrame("attack");
                            JPanel attackPanel = new JPanel();
                            
                            attackFrame.setSize(500, 100);
                            attackFrame.setLocationRelativeTo(null);
                            attackFrame.setTitle("RunningModedeploy");
                            

                            ArrayList<Integer> integerList = neighbourList.get(getShapeIndex(shape));
                            ArrayList<String> stringList = new ArrayList<>();

                            for (Integer number : integerList) {
                                if(number<shapeList.size())
                                    stringList.add(String.valueOf(number));
                            }
                            String[] stringArray = stringList.toArray(new String[0]);

                            JComboBox<String> neighbourCombo = new JComboBox<String>(stringArray);
                            JButton attackButton = new JButton("Attack");
                            //attackButton.setBounds(120,  120, 100, 50);
                            attackPanel.add(attackButton);
                            attackButton.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    int destinationIndex = Integer.parseInt(neighbourCombo.getSelectedItem().toString());
                                    Shape destinationShape = getShape(destinationIndex);
                                    System.out.println("destination power**********: " + powerOfShape(destinationShape));
                                    System.out.println("destination power**********: " + powerOfShape(shape));
                                    if(powerOfShape(shape)>powerOfShape(destinationShape)){
                                        
                                        //**************** */
                                        //GameControllerNew.g.createPanel();
                                        //************************* */
                                        setIndexColor(shapeList.indexOf(destinationShape), Color.gray);
                                    }
                                }
                            });

                            neighbourCombo.setPreferredSize(new Dimension(100,50));
                            attackPanel.add(neighbourCombo);
                            attackFrame.add(attackPanel);
                            
                            attackFrame.setVisible(true);
                        }

                    }
                    }
                }
            }
            
            public void mousePressed(MouseEvent e) {
            	
            	Point p = MouseInfo.getPointerInfo().getLocation();
                Point p1 = output.getLocationOnScreen();
                
                int x = p.x - p1.x;
                int y = p.y - p1.y;
                Point pointOnImage = new Point(x, y);
                
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("right click is added.");
                    if(isInBuildingMode == true) {
                        for (Shape shape : shapeList) {
                            if (shape.contains(pointOnImage)) {   
                                JOptionPane.showMessageDialog(null, "Clicked!"); 
                                numofSelectedTerritory++;
                                if (numofSelectedTerritory==shapeList.size()) 
                                    isEveryTerritorySelected=true;
                                                        
                                clickedShape = shape;
                                setIndexColor(shapeList.indexOf(shape), Color.gray);
                                
        
                                break;
                            }
                        }
                    }
                }
                
                
            }
        };
        
        
        if (isInBuildingMode) {
        	for (JTextField currentField : getTextLabels()) {
        		ui.add(currentField);
        	}	
        }
        
        
        output.addMouseListener(ml);
        ui.add(output);
        refresh();
    }
    

    public Area getOutline(Color target, BufferedImage bi, int tolerance) {
        // construct the GeneralPath
        GeneralPath gp = new GeneralPath();

        boolean cont = false;
        for (int xx = 0; xx < bi.getWidth(); xx++) {
            for (int yy = 0; yy < bi.getHeight(); yy++) {
                if (isIncluded(new Color(bi.getRGB(xx, yy)), target, tolerance)) {
                    //if (bi.getRGB(xx,yy)==targetRGB) {
                    if (cont) {
                        gp.lineTo(xx, yy); gp.lineTo(xx, yy + 1); gp.lineTo(xx + 1, yy + 1); gp.lineTo(xx + 1, yy); gp.lineTo(xx, yy);
                    } else {
                        gp.moveTo(xx, yy);
                    }
                    cont = true;
                } else {
                    cont = false;
                }
            }
            cont = false;
        }
        gp.closePath();

        // construct the Area from the GP & return it
        return new Area(gp);
    }
    

    public static ArrayList<Shape> separateShapeIntoRegions(Shape shape) {
        ArrayList<Shape> regions = new ArrayList<>();

        PathIterator pi = shape.getPathIterator(null);
        GeneralPath gp = new GeneralPath();
        int k = 0;
        while (!pi.isDone()) {
            double[] coords = new double[6];
            int pathSegmentType = pi.currentSegment(coords);
            int windingRule = pi.getWindingRule();
            gp.setWindingRule(windingRule);
            if (pathSegmentType == PathIterator.SEG_MOVETO) {
                gp = new GeneralPath();
                gp.setWindingRule(windingRule);
                gp.moveTo(coords[0], coords[1]);
            } else if (pathSegmentType == PathIterator.SEG_LINETO) {
                gp.lineTo(coords[0], coords[1]);
            } else if (pathSegmentType == PathIterator.SEG_QUADTO) {
                gp.quadTo(coords[0], coords[1], coords[2], coords[3]);
            } else if (pathSegmentType == PathIterator.SEG_CUBICTO) {
                gp.curveTo(
                        coords[0], coords[1],
                        coords[2], coords[3],
                        coords[4], coords[5]);
            } else if (pathSegmentType == PathIterator.SEG_CLOSE) {
                gp.closePath();
                regions.add(new Area(gp));
                colorList.add(k, Color.ORANGE.darker());
                k++;
            } else {
                System.err.println("Unexpected value! " + pathSegmentType);
            }

            pi.next();
        }

        return regions;
    }

    class MousePositionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            // do nothing
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            refresh();
        }
    }

    public static boolean isIncluded(Color target, Color pixel, int tolerance) {
        int rT = target.getRed();
        int gT = target.getGreen();
        int bT = target.getBlue();
        int rP = pixel.getRed();
        int gP = pixel.getGreen();
        int bP = pixel.getBlue();
        return ((rP - tolerance <= rT) && (rT <= rP + tolerance)
                && (gP - tolerance <= gT) && (gT <= gP + tolerance)
                && (bP - tolerance <= bT) && (bT <= bP + tolerance));
    }

    private static void refresh() {
        output.setIcon(new ImageIcon(getImage()));
    }

    private static BufferedImage getImage() {
        //bi = new BufferedImage(2 * SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
        bi = new BufferedImage(1000, 500 , BufferedImage.TYPE_INT_RGB);
        int colorIndex;
        g = bi.createGraphics();
        g.drawImage(image, 0, 0, output);
        //g.setColor(Color.ORANGE.darker());
        //g.fill(area);
        g.setColor(Color.RED);
        g.draw(area);

        for(Shape k :shapeList) {
            colorIndex = shapeList.indexOf(k);
            g.setColor(colorList.get(colorIndex));
            g.fill(k);
        }
        
        try {    
            Point p = MouseInfo.getPointerInfo().getLocation();
            Point p1 = output.getLocationOnScreen();
            
            int x = p.x - p1.x;
            int y = p.y - p1.y;
            int areaCounter=0;
            Point pointOnImage = new Point(x, y);
            for (Shape shape : shapeList) {
                areaCounter++;
                
                if (shape.contains(pointOnImage)) {
                    g.setColor(Color.GREEN.darker());
                    g.fill(shape);
                    break;
                }
            }
            //System.out.println("counter:"+shapeList.size());
        } catch (Exception doNothing) {
        }

        g.dispose();

        return bi;
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




        neighbourList.add(row0);
        neighbourList.add(row1);
        neighbourList.add(row2);
        neighbourList.add(row3);
        neighbourList.add(row4);
        neighbourList.add(row5);
        neighbourList.add(row6);
        neighbourList.add(row7);
        neighbourList.add(row8);
        neighbourList.add(row9);
        neighbourList.add(row10);
        neighbourList.add(row11);
        neighbourList.add(row12);
        neighbourList.add(row13);
        neighbourList.add(row14);
        neighbourList.add(row15);
        neighbourList.add(row16);
        neighbourList.add(row17);
        neighbourList.add(row18);
        neighbourList.add(row19);
        neighbourList.add(row20);
        neighbourList.add(row21);
        neighbourList.add(row22);
        neighbourList.add(row23);
        neighbourList.add(row24);
        neighbourList.add(row25);
        neighbourList.add(row26);
        neighbourList.add(row27);
        neighbourList.add(row28);
        neighbourList.add(row29);
        neighbourList.add(row30);
        neighbourList.add(row31);
        neighbourList.add(row32);
        neighbourList.add(row33);
        neighbourList.add(row34);
        neighbourList.add(row35);
        neighbourList.add(row36);
        neighbourList.add(row37);
        neighbourList.add(row38);
        neighbourList.add(row39);
        neighbourList.add(row40);
        for(ArrayList<Integer> i : neighbourList){
            System.out.println(i);//Each territories neighbors ID's
      
        }
    }



    public JComponent getUI() {
        return ui;
    }

    
    private void removeMouseListener() {
        output.removeMouseListener(ml);
    }

    public static ArrayList<Shape> getShapeList() {
        return shapeList;
    }

    public void removeFromShapeList(Shape shape) {
        shapeList.remove(shape);
    }

    public Shape getClickedShape() {
        return clickedShape;
    }

    public void setShapeColor(Shape shape, Color color) {
        int colorIndex = shapeList.indexOf(shape);
        colorList.set(colorIndex, color);
    }

    public void setShapeArmyInfantry(Shape shape, int numberOfArmy){
        int armyIndex = shapeList.indexOf(shape);
        armyList[armyIndex][0]= numberOfArmy;
        

    }
    public void setShapeArmyCavalry(Shape shape, int numberOfArmy){
        int armyIndex = shapeList.indexOf(shape);
        armyList[armyIndex][1]= numberOfArmy;
    }
    
    public void setShapeArmyArtillery(Shape shape, int numberOfArmy){
        int armyIndex = shapeList.indexOf(shape);
        armyList[armyIndex][2]= numberOfArmy;
    }

    public int getShapeArmyInfantry(int index){
        return armyList[index][0];
    }

    public int getShapeArmyCavalry(int index) {
        return armyList[index][1];
    }

    public int getShapeArmyArtillery(int index) {
        return armyList[index][2];
    }

    public static void setIndexColor(int index, Color color) {
    	System.out.println("set index color function is called");
        colorList.set(index, color);
        refresh();
    }

    
    
    public int getClickedShapeIndex() {
    	return this.clickedShapeIndex;
    }

    public boolean getIsInBuildingMode() {
        return isInBuildingMode;
    }

    public void setIsInBuildingMode(boolean bool) {
        isInBuildingMode = bool;
    }
    
    public void setClickedShapeIndex(int clickedShapeIndex) {
    	this.clickedShapeIndex=clickedShapeIndex;
    }
    
    public MouseListener getMouseListener() {
    	return this.ml;
    }
    
    public void setMouseListener(MouseListener ml) {
    	this.ml=ml;
    }
    
    public void checkIfAllSelected(int numClicked, int numTerr){
		if(numClicked==numTerr){
            isEveryTerritorySelected=true;
            System.out.println("true olduuuuu");
        }
	}

    public boolean getIsAllSelected() {
        return isEveryTerritorySelected;
    }
    
    public static int getShapeIndex(Shape shape) {
    	return shapeList.indexOf(shape);
    }
    
    public Shape getShape(int index) {
    	return shapeList.get(index);
    }

    public static ArrayList<Color> getColorList() {
        return colorList;
    }
    
    public ArrayList<Shape> getSelectedShapes(){
    	return this.selectedShapeList;
    }

   

    public ArrayList getNeighbour(int index) {
        return neighbourList.get(index);
    }
   

	public static void main(String[] args) {
        Runnable r = () -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            WorldMap o = new WorldMap();

            JFrame f = new JFrame(o.getClass().getSimpleName());
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true);
            f.setContentPane(o.getUI());
            f.setResizable(false);
            f.pack();

            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }

	public ArrayList<JTextField> getTextLabels() {
		return textLabels;
	}

	public void setTextLabels(ArrayList<JTextField> textLabels) {
		this.textLabels = textLabels;
	} 

    public int powerOfShape(Shape shape){
        int index = getShapeIndex(shape);
        
        //int power = Integer.parseInt(armArrayLists[index][0].toString()) + 5 * Integer.parseInt(armArrayLists[index][1].toString()) + 10 * Integer.parseInt(armArrayLists[index][2].toString());
       
        int power = getShapeArmyArtillery(index)+ 5 * getShapeArmyCavalry(index) + 10 * getShapeArmyInfantry(index) ;
        System.out.println("result is  "+ power);
        return power;
    }
}