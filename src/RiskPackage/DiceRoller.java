package RiskPackage;

import javax.swing.*;
import javax.swing.event.SwingPropertyChangeSupport;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class DiceRoller {
    public static final String TIMER_STOPPED_PROPERTY = "timerStopped";

    private JLabel diceLabel;
    private ImageIcon[] diceImages;
    private Random random;
    private Timer timer;
    private int rollCount;
    private int result;
    private PropertyChangeSupport pcs;

    public DiceRoller(JLabel diceLabel) {
        this.diceLabel = diceLabel;
        this.pcs = new PropertyChangeSupport(this);

        diceImages = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            diceImages[i] = new ImageIcon("dice" + (i+1) + ".png");
        }

        random = new Random();
    }

    public void rollDice() {
        if (timer != null && timer.isRunning()) return;

        rollCount = 0;
        result = 0;
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = random.nextInt(6) + 1;  // This generates a number between 1 and 6
                diceLabel.setIcon(diceImages[result - 1]);

                rollCount++;
                if (rollCount >= 20) {
                    ((Timer)e.getSource()).stop();
                    pcs.firePropertyChange(TIMER_STOPPED_PROPERTY, false, true);
                } else if (rollCount > 10) {
                    ((Timer)e.getSource()).setDelay(((Timer)e.getSource()).getDelay() + 50);
                }
            }
        });
        timer.start();
    }

    public int getResult() {
        return result;
    }

    public boolean isRolling() {
        return timer != null && timer.isRunning();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}