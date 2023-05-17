package uipackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.Random;
import RiskPackage.*;

public class AttackTerr extends JFrame {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int DELAY = 20;

    private Image star;
    private Image cross;
    private double scale = 0.01;
    private boolean isGrowing = true;
    private boolean starVisible = false;
    private boolean crossVisible = false;
    private JPanel territoryPromptJPanel;
    private JButton attackButton;
    private JComboBox<String> chooseToAttackBox;
    //Trowing Two dices
    private int defenderSides = 6;
    private int attackerSides = 6;
    private Random random = new Random();

    public AttackTerr(Territory territory){
        this.setTitle(territory.getName());
        this.setLayout(null);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.pack();
        this.setLocationRelativeTo(null);
        

        int defenderDiceResult = random.nextInt(defenderSides) + 1;
        int attackerDiceResult = random.nextInt(attackerSides) + 1;

        


        // Load images
        star = Toolkit.getDefaultToolkit().getImage("star.png");
        cross = Toolkit.getDefaultToolkit().getImage("cross.png");

        territoryPromptJPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (starVisible) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    int x = (getWidth() - (int)(star.getWidth(null) * scale)) / 2;
                    int y = (getHeight() - (int)(star.getHeight(null) * scale)) / 2;
                    AffineTransform at = AffineTransform.getTranslateInstance(x, y);
                    at.scale(scale, scale);
                    g2d.drawImage(star, at, null);
                    g2d.dispose();
                } else if (crossVisible) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    int x = (getWidth() - (int)(cross.getWidth(null) * scale)) / 2;
                    int y = (getHeight() - (int)(cross.getHeight(null) * scale)) / 2;
                    AffineTransform at = AffineTransform.getTranslateInstance(x, y);
                    at.scale(scale, scale);
                    g2d.drawImage(cross, at, null);
                    g2d.dispose();
                }
            }
        };
        territoryPromptJPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        territoryPromptJPanel.setBackground(Color.green);
        this.add(territoryPromptJPanel);

        // Assume other UI components are set up here like chooseToAttackBox
        chooseToAttackBox = new JComboBox<>();
        int yPosition = 70;
        for (Territory neighbor : territory.getNeighbors()) {
            chooseToAttackBox.addItem(neighbor.getName());
            // Create a detailed JLabel for each neighbor
            JLabel neighborLabel = new JLabel();
            neighborLabel.setText("<html>" + neighbor.getName() + " territory power: " + neighbor.armyOnTerritory.calculateStrength()
                + "<br/>Present Armies: " + neighbor.Anumber + " Artillery, " + neighbor.Cnumber + " Cavalry, " + neighbor.Inumber + " Infantry.</html>");
            neighborLabel.setBounds(10, yPosition, 400, 70);
            territoryPromptJPanel.add(neighborLabel);

            yPosition += 80; // Update the y position for the next label
            
        }
        chooseToAttackBox.setBounds(10, yPosition + 120, 100, 50);
        territoryPromptJPanel.add(chooseToAttackBox);

        attackButton = new JButton("Attack");
        attackButton.setBounds(120, yPosition + 120, 100, 50);
        territoryPromptJPanel.add(attackButton);

        JLabel territoryPowerLabel = new JLabel();
        territoryPowerLabel.setText("<html>Current Territory Power: " + territory.armyOnTerritory.calculateStrength()
                + "<br/>Present Armies: " + territory.Anumber + " Artillery, " + territory.Cnumber + " Cavalry, " + territory.Inumber + " Infantry.</html>");
        territoryPowerLabel.setBounds(10, yPosition + 90, 400, 70);
        territoryPromptJPanel.add(territoryPowerLabel);

        attackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Territory destination = null;
                String name = (String) chooseToAttackBox.getSelectedItem();
                for(Territory t : territory.getNeighbors()){
                    if(name.equals(t.getName())){
                        destination = t;
                        break;
                    }
                }

                if(destination != null){
                    if(destination.armyOnTerritory.calculateStrength() <= territory.armyOnTerritory.calculateStrength()&& defenderDiceResult<attackerDiceResult ){
                        
                        destination.decreaseArmy(territory);
                        if(destination.armyOnTerritory.calculateStrength()<=0){
                            destination.setColor(territory.getColor());    
                        }
                        
                        
                        //Winning animation
                        starVisible = true;
                        Timer timer = new Timer(DELAY, null);
                        timer.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (isGrowing) {
                                    scale += 0.03;
                                    if (scale > 0.3) {
                                        isGrowing = false;
                                    }
                                } else {
                                    scale -= 0.03;
                                    if (scale < 0) {
                                        scale = 0.01;
                                        isGrowing = true;
                                        starVisible = false;
                                        ((Timer) e.getSource()).stop();
                                    }
                                }
                                territoryPromptJPanel.repaint();
                            }
                        });
                        timer.start();
                    } else /*if (destination.armyOnTerritory.calculateStrength() >= territory.armyOnTerritory.calculateStrength())*/{
                        
                        territory.decreaseArmy(destination);
                        
                        if(territory.armyOnTerritory.calculateStrength() == 0){
                            territory.armyOnTerritory.setInfantry(1);
                        }
                        
                        
                        //Loosing animation
                        crossVisible = true;
                        Timer timer = new Timer(DELAY, null);
                        timer.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (isGrowing) {
                                    scale += 0.03;
                                    if (scale > 0.3) {
                                        isGrowing = false;
                                    }
                                } else {
                                    scale -= 0.03;
                                    if (scale < 0) {
                                        scale = 0.01;
                                        isGrowing = true;
                                        crossVisible = false;
                                        ((Timer) e.getSource()).stop();
                                    }
                                }
                                territoryPromptJPanel.repaint();
                            }
                        });
                        timer.start();
                    }
                }
            }
        });
        this.pack();
    }
}
