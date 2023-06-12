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
import javax.swing.plaf.OptionPaneUI;
import javax.swing.text.html.Option;

import RiskPackage.*;
import animationPackage.DiceRollingFrame;
import animationPackage.StarAnimationClass;
import armyCardPackage.IArmyCard;

import java.util.List;
import java.util.Random;
import java.util.Collections;
import javax.imageio.ImageIO;

public class WorldMap {

   

    private Image star;
    private Image cross;
    private double scale = 0.01;
    private boolean isGrowing = true;
    private boolean starVisible = false;
    private boolean crossVisible = false;
    private boolean isGameOver=false;
    
    JFrame animationFrame;

    

    private Random random = new Random();

    int defenderDiceResult;
    int attackerDiceResult;

    public ShapeDomain shapeDomain = new ShapeDomain();

    private JComponent ui = null;
    static JLabel output = new JLabel();
    public static final int SIZE = 750;
    static BufferedImage image;
    static Area area;
    ArrayList<Shape> shapeList2=null;

    int numberOfShape = 7;
    public static ArrayList<Shape>  shapeList = new ArrayList<>();
    //static ArrayList<ArrayList<Integer>> armyList = new ArrayList<ArrayList<Integer>>(200);
    //public static int[][] armArrayLists = new ArrayList[60][3];
    public static int[][] armyList = new int[60][3];

    public static ArrayList<Color> colorList = new ArrayList<>();
    private Shape clickedShape;
    private MouseListener ml;
    private static BufferedImage bi;
    private static Graphics2D g;
    public static int clickedShapeIndex;
    final int areaThreshold = 450;
    public static boolean isEveryTerritorySelected = false;
    public int numofSelectedTerritory = 0;
    static boolean isInBuildingMode = true;
    private ArrayList<Shape> selectedShapeList = new ArrayList<>();

    private ArrayList<JTextField> textLabels = new ArrayList<>();

    public static ArrayList<ArrayList<Integer>> neighbourList = new ArrayList<ArrayList<Integer>>();

    private int modeForDeployAttackFortify = 0;

    public WorldMap() {
    	shapeDomain.InitNeighbors();
    	


    	
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
                            currentField.setText(Integer.toString(clickedShapeIndex)+ " "+Integer.toString(shapeDomain.powerOfShape(shape)));
                            
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
                                    shapeDomain.addShapeArmyInfantry(shape, Integer.parseInt(numberOfArmyonarea));   
                                    System.out.println("Infantry at shape index " + clickedShapeIndex + ": " + shapeDomain.getShapeArmyInfantry(clickedShapeIndex));    
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
                                    shapeDomain.addShapeArmyCavalary(shape, Integer.parseInt(numberOfArmyonarea));   
                                    System.out.println("Infantry at shape index " + clickedShapeIndex + ": " + shapeDomain.getShapeArmyCavalry(clickedShapeIndex));    
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
                                    shapeDomain.addShapeArmyArtillery(shape, Integer.parseInt(numberOfArmyonarea)); 
                                    
                                    if(Integer.parseInt(numberOfArmyonarea) > 0){
                                        optionFrame.dispose();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Enter the amount of Artillery you wish to deploy");
                                    }
                                    System.out.println("Artillery at shape index " + clickedShapeIndex + ": " + shapeDomain.getShapeArmyArtillery(clickedShapeIndex));

                                }
                            });

                            optionPanel.add(retrieveArtilleryButton);
                            optionPanel.add(retrieveCavalryButton);
                            optionPanel.add(retrieveInfantryButton);
                            optionFrame.setVisible(true);
                            
                            BuildingModeNew.nextTurn(WorldMap.this);
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
                                    shapeDomain.addShapeArmyInfantry(shape, Integer.parseInt(numberOfArmyonarea));   
                                    System.out.println("Infantry at shape index " + clickedShapeIndex + ": " + shapeDomain.getShapeArmyInfantry(clickedShapeIndex));    
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
                                    shapeDomain.addShapeArmyCavalary(shape, Integer.parseInt(numberOfArmyonarea));   
                                    System.out.println("Infantry at shape index " + clickedShapeIndex + ": " + shapeDomain.getShapeArmyCavalry(clickedShapeIndex));    
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
                                    shapeDomain.addShapeArmyArtillery(shape, Integer.parseInt(numberOfArmyonarea)); 
                                    
                                    if(Integer.parseInt(numberOfArmyonarea) > 0){
                                        optionFrame.dispose();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Enter the amount of Artillery you wish to deploy");
                                    }
                                    System.out.println("Artillery at shape index " + clickedShapeIndex + ": " + shapeDomain.getShapeArmyArtillery(clickedShapeIndex));

                                }
                            });
                            currentField.setBounds((int) centerX, (int) centerY, 40, 40);
                            currentField.setText(Integer.toString(clickedShapeIndex)+ " "+Integer.toString(shapeDomain.powerOfShape(shape)));
                            optionPanel.add(retrieveArtilleryButton);
                            optionPanel.add(retrieveCavalryButton);
                            optionPanel.add(retrieveInfantryButton);
                            optionPanel.add(currentField);
                            optionFrame.setVisible(true);
                            
                            
                            selectedShapeList.add(shape);
                            
                            
                            
                        }

                        if(RunningModeNew.whichStage == "Attack") { 
                        	System.out.println("ATTACAK GIRDI");
                            clickedShape = shape;
                            WorldMap.clickedShapeIndex=shapeList.indexOf(shape);
                        	Color curColor = colorList.get(getShapeIndex(shape));
                        	Color playerColor = BuildingModeNew.playerList.get(RunningModeNew.getTurn()).getColor();
                        	int currTerr = shapeList.indexOf(shape);
                        	if (curColor.getRed()==playerColor.getRed() && curColor.getGreen()==playerColor.getGreen() && curColor.getBlue()==playerColor.getBlue()) {
                        		JFrame attackFrame = new JFrame("attack");
                                JPanel attackPanel = new JPanel();
                                
                                attackFrame.setSize(500, 100);
                                attackFrame.setLocationRelativeTo(null);
                                attackFrame.setTitle("Choose Neighbour To Attack");
                                

                                ArrayList<Integer> integerList = neighbourList.get(getShapeIndex(shape));
                                ArrayList<String> stringList = new ArrayList<>();

                                for (Integer number : integerList) {
                                    if(number<shapeList.size())
                                        stringList.add(String.valueOf(number));
                                }
                                String[] stringArray = stringList.toArray(new String[0]);

                                JComboBox<String> neighbourCombo = new JComboBox<String>(stringArray);

                                JButton attackButton = new JButton("Attack");
                                attackPanel.add(attackButton);
                                attackButton.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        if(shapeDomain.powerOfShape(shape) >1){
                                            int destinationIndex = Integer.parseInt(neighbourCombo.getSelectedItem().toString());
                                            Shape destinationShape = getShape(destinationIndex);
                                            System.out.println("destination power**********: " + shapeDomain.powerOfShape(destinationShape));
                                            System.out.println("source power**********: " + shapeDomain.powerOfShape(shape));
                                            String selectedItem = (String) neighbourCombo.getSelectedItem();
                                            Integer selected = Integer.parseInt(selectedItem);

                                            if(((Color) colorList.get(selected)).getRed() != playerColor.getRed()&& ((Color) colorList.get(selected)).getGreen() != playerColor.getGreen() && ((Color) colorList.get(selected)).getBlue() != playerColor.getBlue()){
                                                DiceRollingFrame FrameDiceRolling = new DiceRollingFrame();
                                                FrameDiceRolling.setOnFrameClosedListener(new DiceRollingFrame.OnFrameClosedListener() {
                                                @Override
                                                public void onFrameClosed() {

                                                    // This code will be run when the DiceRollingFrame is closed
                                                    try{
                                                        
                                                            if (shapeDomain.powerOfShape(shape) > shapeDomain.powerOfShape(destinationShape) && DiceRollingFrame.defenderDiceResult < DiceRollingFrame.attackerDiceResult) {
                                                                //**************** */
                                                                
                                                                int destinationIndex = Integer.parseInt(neighbourCombo.getSelectedItem().toString());

                                                                    animationFrame = new StarAnimationClass(0);
                                                                    
                                                                    shapeDomain.SuccesfullAttack(shape, destinationShape, destinationIndex);
                                                        
                                                                    
                                                                    areAllColorsSame();
                                                                    if (isGameOver) {
                                                                    	System.out.println("Player"+BuildingModeNew.playerList.get(RunningModeNew.getTurn())+"win the game.");
                                                                    	
                                                                    }
                                                                
                                                                
                                                                
                                                        
                                                            } 
                                                            else {
                                                                animationFrame = new StarAnimationClass(1);
                                                                shapeDomain.UnsuccesfullAttack(shape,currTerr);
                                                            }
                                                        
                                                    }
                                                    catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                }
                                                    
                                            });
                                            FrameDiceRolling.showFrame();
                                            }
                                            else{
                                                JOptionPane.showMessageDialog( null, "You already own this territory");
                                            }

                                            
                                            
                                        }
                                        else{
                                            JOptionPane.showMessageDialog( null, "You do not have enough power to attack");
                                        }
                                        
                                    }
                                    
                                });

                                neighbourCombo.setPreferredSize(new Dimension(100,50));
                                attackPanel.add(neighbourCombo);
                                attackFrame.add(attackPanel);
                                
                                attackFrame.setVisible(true);
                        	}
                        	
                            
                        }
                        if(RunningModeNew.whichStage == "Fortify"){
                            clickedShape = shape;
                            WorldMap.clickedShapeIndex=shapeList.indexOf(shape);
                            Color curColor = colorList.get(getShapeIndex(shape));
                        	Color playerColor = BuildingModeNew.playerList.get(RunningModeNew.getTurn()).getColor();
                            int currTerr = shapeList.indexOf(shape);
                            if (curColor.getRed()==playerColor.getRed() && curColor.getGreen()==playerColor.getGreen() && curColor.getBlue()==playerColor.getBlue()) {
                                JFrame fortifyFrame = new JFrame("Fortify");
                                JPanel fortPanel = new JPanel(new GridLayout(4,1));

                                

                                fortifyFrame.setSize(500, 200);
                                fortifyFrame.setLocationRelativeTo(null);
                                fortifyFrame.setTitle("Fortify");


                                
                                ArrayList<Integer> neighborsList = neighbourList.get(getShapeIndex(shape));
                               

                                
                                JComboBox<Integer> neighbourCombo = new JComboBox<>();
                                for( int i: neighborsList){
                                    neighbourCombo.addItem(i);
                                }
                                // Infantry label and text field
                                JPanel infantryPanel = new JPanel();
                                infantryPanel.setLayout(new BoxLayout(infantryPanel, BoxLayout.PAGE_AXIS));
                                JLabel infantryLabel = new JLabel("Infantry");
                                JTextField infantryTextField = new JTextField("0");
                                infantryPanel.add(infantryLabel);
                                infantryPanel.add(infantryTextField);
                                fortPanel.add(infantryPanel);

                                // Cavalary label and text field
                                JPanel cavalaryPanel = new JPanel();
                                cavalaryPanel.setLayout(new BoxLayout(cavalaryPanel, BoxLayout.PAGE_AXIS));
                                JLabel cavalaryLabel = new JLabel("Cavalary");
                                JTextField cavalaryTextField = new JTextField("0");
                                cavalaryPanel.add(cavalaryLabel);
                                cavalaryPanel.add(cavalaryTextField);
                                fortPanel.add(cavalaryPanel);

                                // Artilarry label and text field
                                JPanel artilarryPanel = new JPanel();
                                artilarryPanel.setLayout(new BoxLayout(artilarryPanel, BoxLayout.PAGE_AXIS));
                                JLabel artilarryLabel = new JLabel("Artilarry");
                                JTextField artilarryTextField = new JTextField("0");
                                artilarryPanel.add(artilarryLabel);
                                artilarryPanel.add(artilarryTextField);
                                fortPanel.add(artilarryPanel);

                                                                
                                JButton fortifyButton = new JButton("Fortify");
                                fortPanel.add(fortifyButton);
                                fortifyButton.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        int fortifyAmountArtilarry = 0;
                                        int fortifyAmountInfantry = 0;
                                        int fortifyAmountCavalary = 0;

                                        fortifyAmountArtilarry = Integer.parseInt(artilarryTextField.getText());

                                        fortifyAmountInfantry = Integer.parseInt(infantryTextField.getText());

                                        fortifyAmountCavalary = Integer.parseInt(cavalaryTextField.getText());




                                        int destinationIndex = Integer.parseInt(neighbourCombo.getSelectedItem().toString());

                                        Integer selected = (Integer) neighbourCombo.getSelectedItem();
                                        Shape destinationShape = getShape(destinationIndex);
                                        if(shapeDomain.powerOfShape(shape)>1){
                                            if(((Color) colorList.get(selected)).getRed() == playerColor.getRed()&& ((Color) colorList.get(selected)).getGreen() == playerColor.getGreen() && ((Color) colorList.get(selected)).getBlue() == playerColor.getBlue()){
                                                

                                                try {
                                                    if(fortifyAmountArtilarry >= shapeDomain.getShapeArmyArtillery(currTerr)&&fortifyAmountArtilarry !=0) {
                                                        throw new Exception("You do not have that amount of artilarries");
                                                    }

                                                    if(fortifyAmountCavalary >= shapeDomain.getShapeArmyCavalry(currTerr)&&fortifyAmountCavalary != 0) {
                                                        throw new Exception("You do not have that amount of cavalaries");
                                                    }

                                                    if(fortifyAmountInfantry >= shapeDomain.getShapeArmyInfantry(currTerr)&& fortifyAmountInfantry != 0) {
                                                        throw new Exception("You do not have that amount of infantries");
                                                    }


                                                    shapeDomain.Fortify(clickedShape,destinationShape,fortifyAmountArtilarry,fortifyAmountCavalary,fortifyAmountInfantry);
                                                    JOptionPane.showMessageDialog(null, "Fortifiyed");


                                                } catch (Exception e1) {
                                                    JOptionPane.showMessageDialog(null, e1.getMessage());
                                                }

    

                                            } 
                                            else {
                                                JOptionPane.showMessageDialog(null, "You do not own this neighbor");
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "You don't have enough power to fortify from this territory");
                                        }
                                        
                                        System.out.println("");
                                    }
                                    
                                });
                               
                                JLabel currentField = new JLabel();

                                
                                currentField.setText("ID: "+Integer.toString(clickedShapeIndex)+ " Total power: "+Integer.toString(shapeDomain.powerOfShape(shape)));

                                
                                fortPanel.add(currentField);
                                neighbourCombo.setPreferredSize(new Dimension(100,50));
                                fortPanel.add(neighbourCombo);

                                fortifyFrame.add(fortPanel);
                                fortifyFrame.setVisible(true);
                            }


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
                                shapeDomain.setIndexColor(shapeList.indexOf(shape), Color.gray);
                                
        
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

    public static void refresh() {
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

    
    

    
    
    public int getClickedShapeIndex() {
    	return this.clickedShapeIndex;
    }

    public boolean getIsInBuildingMode() {
        return isInBuildingMode;
    }

    public ArrayList<Color> getColorList() {
        return colorList;
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

       
    public void areAllColorsSame() {
    	boolean gameOver=true;
    	for (int i=0;i<colorList.size()-1;i++) {
    		if (colorList.get(i).getRed()!=colorList.get(i+1).getRed() || colorList.get(i).getBlue()!=colorList.get(i+1).getBlue() || colorList.get(i).getGreen()!=colorList.get(i+1).getGreen())
    			gameOver=false;
    	}
    	isGameOver=gameOver;
    }

    public ArrayList<Color> getColorList() {
        return colorList;
    }

    public boolean repOk() {
        // Check if clickedShape is null or refers to a shape in shapeList
        if (clickedShape != null && !shapeList.contains(clickedShape)) {
            return false;
        }

        return true; // Representation invariant holds
    }    
}
