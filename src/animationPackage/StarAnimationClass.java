package animationPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import RiskPackage.GameControllerNew;

public class StarAnimationClass extends JFrame{
	
	private Image image;
    private double scale = 0.01;
    private boolean isGrowing = true;
    private boolean starVisible = false;
    private boolean crossVisible = false;
	private final int FRAME_WIDTH = 600;
    private final int DELAY = 30;
    public Timer timer; 
    public int chooser;
    

	public StarAnimationClass(int chooser)  {
		this.setSize(FRAME_WIDTH, FRAME_WIDTH);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.chooser=chooser;
		this.createPanel();
		
		
		this.setVisible(true);
		
		
	}
	
	public void createPanel() {
		String cwd = System.getProperty("user.dir");
		if (this.chooser==0)
			image = Toolkit.getDefaultToolkit().getImage(cwd + "/Star.png");
		else
			image = Toolkit.getDefaultToolkit().getImage(cwd + "/Cross.png");

        JPanel territoryPromptJPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
				if (starVisible) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    int x = (getWidth() - (int)(image.getWidth(null) * scale)) / 2;
                    int y = (getHeight() - (int)(image.getHeight(null) * scale)) / 2;
                    AffineTransform at = AffineTransform.getTranslateInstance(x, y);
                    at.scale(scale, scale);
                    g2d.drawImage(image, at, null);
                    g2d.dispose();
                } else if (crossVisible) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    int x = (getWidth() - (int)(image.getWidth(null) * scale)) / 2;
                    int y = (getHeight() - (int)(image.getHeight(null) * scale)) / 2;
                    AffineTransform at = AffineTransform.getTranslateInstance(x, y);
                    at.scale(scale, scale);
                    g2d.drawImage(image, at, null);
                    g2d.dispose();
                }
            }
        };
        territoryPromptJPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_WIDTH);
        territoryPromptJPanel.setBackground(Color.green);
        

		starVisible = true;
		timer = new Timer(DELAY, null);
	
		
		
		
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
				if (scale<0.02) {
					dispose();
				}
			}
		});
		this.add(territoryPromptJPanel);
		timer.start();
		
		
		
		
    }

  

}
