package uipackage;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

public class FadingImageComponent extends JComponent {
    private float alpha = 1.0f;
    private Image image;
    private double scale = 1.0;
    private double posX = 0.0;
    private double posY = 0.0;
    private double angle;

    public FadingImageComponent(Image image) {
        this.image = image;
    }

    public void setAlpha(float alpha) {
        if (alpha != this.alpha) {
            this.alpha = alpha;
            repaint();
        }
    }

    public void setPosition(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        repaint();
    }

    public void setScale(double scale) {
        this.scale = scale;
        repaint();
    }

    public float getAlpha() {
        return alpha;
    }
    public double getAngle() {
        return angle;
    }
    
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        double centerX = image.getWidth(this) / 2.0;
        double centerY = image.getHeight(this) / 2.0;

        AffineTransform at = AffineTransform.getTranslateInstance(posX, posY);
        at.scale(scale, scale);
        at.rotate(angle, centerX, centerY);
        g2d.drawImage(image, at, this);

        g2d.dispose();
    }
}