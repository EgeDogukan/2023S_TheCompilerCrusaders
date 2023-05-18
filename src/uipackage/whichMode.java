package uipackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputFilter.Status;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class whichMode extends JFrame {
	
	public static int status = -1; 

	public whichMode() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setLayout(null);
        
		JButton loadButton = new JButton("For loading a previous game press this button.");
		loadButton.setSize(275,275);
		loadButton.setLocation(300, 300);
		this.add(loadButton);
		
		
		loadButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				whichMode.status=0;
				System.out.println(status);
			}
		});
		
		JButton buildingButton = new JButton("To continue to building mode press this button.");
		buildingButton.setSize(275,275);
		buildingButton.setLocation(1000, 300);
		this.add(buildingButton);
		
		buildingButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				whichMode.status=1;
				System.out.println(status);
			}
		});
		
		JLabel label = new JLabel("For now, just press the building mode button.");
		label.setSize(400,25);
		label.setLocation(700, 200);
		this.add(label);
		
	
	}
	public static void main(String[] args) {
		whichMode saMode = new whichMode();
		saMode.setVisible(true);
	}

}
