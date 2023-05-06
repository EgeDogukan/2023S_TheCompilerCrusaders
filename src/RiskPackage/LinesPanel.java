package RiskPackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class LinesPanel extends JPanel {
    private List<TerritoryConnection> connections = new ArrayList<>();

    public LinesPanel() {
        setOpaque(false);
    }

    public void addConnection(Territory source, Territory target) {
        connections.add(new TerritoryConnection(source, target));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));

        for (TerritoryConnection connection : connections) {
            g2.draw(new Line2D.Double(
                    connection.source.getBounds().x + connection.source.getWidth() / 2,
                    connection.source.getBounds().y + connection.source.getHeight() / 2,
                    connection.target.getBounds().x + connection.target.getWidth() / 2,
                    connection.target.getBounds().y + connection.target.getHeight() / 2));
        }
    }

    private static class TerritoryConnection {
        private final Territory source;
        private final Territory target;

        public TerritoryConnection(Territory source, Territory target) {
            this.source = source;
            this.target = target;
        }
    }
}
