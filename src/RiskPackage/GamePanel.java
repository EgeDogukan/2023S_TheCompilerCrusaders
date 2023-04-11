package RiskPackage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private RiskBoard board;

    public GamePanel(RiskBoard board) {
        this.board = board;
        setPreferredSize(new Dimension(400,400));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Territory t : board.getTerritories()) {
            // Draw the territory
            g.setColor(t.getColor());
            g.fillPolygon(t);

            // Draw the territory name
            g.setColor(Color.BLACK);
            g.drawString(t.getName(), t.getBounds().x + t.getBounds().width / 2, t.getBounds().y + t.getBounds().height / 2);
            
            
            // Draw lines to neighboring territories
            for (Territory n : t.getNeighbors()) {
                g.drawLine(t.getBounds().x + t.getBounds().width / 2, t.getBounds().y + t.getBounds().height / 2,
                           n.getBounds().x + n.getBounds().width / 2, n.getBounds().y + n.getBounds().height / 2);
            }
        }
    }
}