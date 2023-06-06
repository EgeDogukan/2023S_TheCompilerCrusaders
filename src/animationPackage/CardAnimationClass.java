package animationPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class CardAnimationClass extends JFrame {

	private final int FRAME_WIDTH = 600;

	public CardAnimationClass() {
		
		this.setSize(FRAME_WIDTH, FRAME_WIDTH);
		this.setLayout(null);
		this.setLocation(FRAME_WIDTH/2, FRAME_WIDTH/2);
		
		String cwd = System.getProperty("user.dir");
		String imagePath = cwd + "/chanceCard.jpeg";
		File imageFile = new File(imagePath);

		try {
		    BufferedImage cardImage = ImageIO.read(imageFile);

		    FadingImageComponent cardImageComponent = new FadingImageComponent(cardImage);
		    cardImageComponent.setBounds(0, 0, getWidth(), getHeight());

		    getContentPane().add(cardImageComponent);
		    getContentPane().setComponentZOrder(cardImageComponent, 0);

		    double maxX = getWidth() / 2;
		    double maxY = getHeight() / 2;

		    Timer timer = new Timer(20, new ActionListener() {
		        double posX = 0;
		        double posY = maxY / 2;
		        double scale = 0.01;
		        double velocityX = 4.0;
		        double velocityY = 3;

		        @Override
		        public void actionPerformed(ActionEvent e) {
		            // Update the image's position
		            posX += velocityX;
		            posY += velocityY;
		            cardImageComponent.setPosition(posX, posY);

		            // Update the image's scale
		            if (posX < maxX / 2) {
		                scale += 0.001;
		            } else {
		                scale -= 0.001;
		            }
		            cardImageComponent.setScale(scale);

		            // If the image has moved off the right edge of the screen, stop the animation
		            if (posX > maxX) {
		                ((Timer) e.getSource()).stop();
		                getContentPane().remove(cardImageComponent);
		                revalidate();
		                repaint();
		                dispose();
		            }
		        }
		    });
		    timer.start();
		    
		    
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		this.setVisible(true);
		
		
	}
	
	

}
