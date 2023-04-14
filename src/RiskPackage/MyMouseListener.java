package RiskPackage;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
	private Territory territory; 
	private Color color;
    public MyMouseListener(Territory territory) {
		// TODO Auto-generated constructor stub
    	this.territory = territory;
    	color = territory.getColor();
	}

	@Override
    public void mouseClicked(MouseEvent e) {
        // This method is called when the mouse is clicked on the component
    	
		territory.setColor(Color.decode("#7cb0bf"));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // This method is called when the mouse button is pressed on the component
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // This method is called when the mouse button is released on the component
    	territory.setColor(color);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // This method is called when the mouse enters the component
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // This method is called when the mouse exits the component
    }
}
