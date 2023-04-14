package RiskPackage;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
public class GameManager implements MouseListener {

    private ArrayList<Territory> territories;
    private Territory selectedTerritory;

    public GameManager(ArrayList<Territory> territories) {
        this.territories = territories;
        this.selectedTerritory = null;
        addTerritoryListeners();
    }

    private void addTerritoryListeners() {
        for (Territory territory : territories) {
            territory.addMouseListener(this);
        }
    }

    private void removeTerritoryListeners() {
        for (Territory territory : territories) {
            territory.removeMouseListener(this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Territory clickedTerritory = (Territory) e.getSource();

        // If no territory is currently selected, select the clicked territory
        if (selectedTerritory == null) {
            selectedTerritory = clickedTerritory;
            selectedTerritory.setColor(Color.RED);
        }
        // If a territory is already selected, attack the clicked territory with the selected territory
        else {
            attack(selectedTerritory, clickedTerritory);
            selectedTerritory.setColor(selectedTerritory.getColor());
            selectedTerritory = null;
        }
    }

    private void attack(Territory attacker, Territory defender) {
        // Game logic for attacking goes here
        System.out.println(attacker.getName() + " attacks " + defender.getName());
    }

    // Implement the other MouseListener methods, but leave them empty
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
