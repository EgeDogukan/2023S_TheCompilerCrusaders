package animationPackage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import RiskPackage.DiceRoller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DiceRollingFrame extends JFrame {
    private DiceRoller diceRoller1;
    private DiceRoller diceRoller2;
    private JLabel diceLabel1;
    private JLabel diceLabel2;
    public static int attackerDiceResult;
    public static int defenderDiceResult;
    public static boolean DiceRollingFrameClosed = false;
    public interface OnFrameClosedListener {
        void onFrameClosed();
    }
    private OnFrameClosedListener onFrameClosedListener;

    public DiceRollingFrame() {
        setSize(500, 500);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setTitle("Attacker Vs Defender");
        diceLabel1 = new JLabel();
        diceLabel2 = new JLabel();

        JLabel attackerLabel = new JLabel("Attacker");
        JLabel defenderLabel = new JLabel("Defender");

        JPanel attackerPanel = new JPanel();
        attackerPanel.setLayout(new BoxLayout(attackerPanel, BoxLayout.Y_AXIS));
        attackerPanel.add(attackerLabel);
        attackerPanel.add(diceLabel1);

        JPanel defenderPanel = new JPanel();
        defenderPanel.setLayout(new BoxLayout(defenderPanel, BoxLayout.Y_AXIS));
        defenderPanel.add(defenderLabel);
        defenderPanel.add(diceLabel2);

        add(attackerPanel);
        add(defenderPanel);

        PropertyChangeListener listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (DiceRoller.TIMER_STOPPED_PROPERTY.equals(evt.getPropertyName())) {
                    if (!diceRoller1.isRolling() && !diceRoller2.isRolling()) {
                        System.out.println("Dice 1: " + diceRoller1.getResult());
                        System.out.println("Dice 2: " + diceRoller2.getResult());
                        attackerDiceResult = diceRoller1.getResult();
                        defenderDiceResult = diceRoller2.getResult();

                    }
                }
            }
        };

        diceRoller1 = new DiceRoller(diceLabel1);
        diceRoller1.addPropertyChangeListener(listener);
        diceRoller1.rollDice();
        
        diceRoller2 = new DiceRoller(diceLabel2);
        diceRoller2.addPropertyChangeListener(listener);
        diceRoller2.rollDice();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (onFrameClosedListener != null) {
                    onFrameClosedListener.onFrameClosed();
                }
            }
        });
    }
    public void setOnFrameClosedListener(OnFrameClosedListener onFrameClosedListener) {
        this.onFrameClosedListener = onFrameClosedListener;
    }

    public void showFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }
   
}
