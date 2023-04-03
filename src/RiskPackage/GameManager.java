package RiskPackage;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
public class GameManager extends JFrame implements MouseListener{
	RiskBoard board;
	Color color;
	
	public GameManager() {
		addMouseListener(this);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for (Territory t : board.getTerritories()) {
			t.setColor(Color.PINK);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (Territory t : board.getTerritories()) {
			t.setColor(Color.PINK);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
