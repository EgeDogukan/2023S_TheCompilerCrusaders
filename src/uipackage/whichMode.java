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
	public static int databaseChooser = 0;

	public whichMode() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setLayout(null);
        
		JButton loadJSON = new JButton("For JSON.");
		loadJSON.setSize(275,275);
		loadJSON.setLocation(300, 300);
		this.add(loadJSON);
		
		
		loadJSON.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				whichMode.status=0;
				System.out.println(status);
				RunningMode.databaseChooser=1;
			}
		});
		
		JButton loadMONGO = new JButton("Load MONGO.");
		loadMONGO.setSize(275,275);
		loadMONGO.setLocation(300, 600);
		this.add(loadMONGO);
		
		
		loadMONGO.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				whichMode.status=0;
				System.out.println(status);
				databaseChooser=1;
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
		
	
	}
	public static void main(String[] args) {
		whichMode saMode = new whichMode();
		saMode.setVisible(true);
	}

}
