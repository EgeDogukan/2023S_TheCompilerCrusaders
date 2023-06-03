package uipackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import RiskPackage.Deploy;

public class DeployUI extends JFrame {

    private Deploy deploy;

    private JLabel infantryLabel;
    private JLabel amountInfantryLabel;
    private JButton infantryButton;

    private JLabel cavalryLabel;
    private JLabel amountCavalryLabel;
    private JButton cavalryButton;

    private JLabel artilleryLabel;
    private JLabel AmountArtilleryLabel;
    private JButton artilleryButton;

    private JButton deployAllButton;

    public DeployUI() {
        

        setLayout(new GridLayout(4, 3));

        infantryLabel = new JLabel("Infantry to Deploy");
        amountInfantryLabel = new JLabel("");
        infantryButton = new JButton("Deploy Infantry");
        infantryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int infantryToDeploy = Integer.parseInt(amountInfantryLabel.getText());
                
                deploy.deployInfantry();
            }
        });

        cavalryLabel = new JLabel("Cavalry to Deploy");
        amountCavalryLabel = new JLabel("");
        cavalryButton = new JButton("Deploy Cavalry");
        cavalryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cavalryToDeploy = Integer.parseInt(amountCavalryLabel.getText());
                
                deploy.deployCavalry();
            }
        });

        artilleryLabel = new JLabel("Artillery to Deploy");
        AmountArtilleryLabel = new JLabel("");
        artilleryButton = new JButton("Deploy Artillery");
        artilleryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int artilleryToDeploy = Integer.parseInt(AmountArtilleryLabel.getText());
                
                deploy.deployArtillery();
            }
        });

        deployAllButton = new JButton("Deploy All");
        deployAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deploy.deployAll();
            }
        });

        add(infantryLabel);
        add(amountInfantryLabel);
        add(infantryButton);

        add(cavalryLabel);
        add(amountCavalryLabel);
        add(cavalryButton);

        add(artilleryLabel);
        add(AmountArtilleryLabel);
        add(artilleryButton);

        add(deployAllButton);
    }
}
