package uipackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PreBuildingMode extends JFrame {
	
	JComboBox<String> numberOfPlayerComboBox;
	JComboBox<String> numberOfCompComboBox;
	JButton nextButton;
	int numberOfPlayer;
	int numberOfCompPlayer;
	
	public PreBuildingMode() {
		this.setSize(1920,1080);
		this.setLayout(null);
		
		JLabel numberOfPlayerJLabel = new JLabel("Number of Human Player");
		numberOfPlayerJLabel.setBounds(1050, 110, 200, 100);
		
		JLabel numberOfCompJLabel = new JLabel("Number of Computer Player");
		numberOfCompJLabel.setBounds(1350, 110, 200, 100);
		
		
		String[] options = {"1", "2", "3", "4", "5", "6"};
	    numberOfPlayerComboBox = new JComboBox<>(options);
	    numberOfPlayerComboBox.setForeground(Color.black);
	    numberOfPlayerComboBox.setBounds(1100, 25, 100, 100);

		String[] options2 = {"1", "2"};
		numberOfCompComboBox = new JComboBox<>(options2);
		numberOfCompComboBox.setForeground(Color.black);
		numberOfCompComboBox.setBounds(1400, 25, 100, 100);
		
		JButton nextButton = new JButton("Continue to the building mode");
		nextButton.setBounds(100, 100, 200, 200);
		
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setNumberOfPlayer(Integer.parseInt((String) numberOfPlayerComboBox.getSelectedItem()));
				setNumberOfCompPlayer(Integer.parseInt((String) numberOfCompComboBox.getSelectedItem()));

				dispose();

			}
		});
		
		this.add(numberOfPlayerJLabel);
		this.add(numberOfCompJLabel);
		this.add(numberOfPlayerComboBox);
		this.add(numberOfCompComboBox);
		this.add(nextButton);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		PreBuildingMode preBuildingMode = new PreBuildingMode();
		
	}
	
	
	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}

	public void setNumberOfPlayer(int numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}

	public int getNumberOfCompPlayer() {
		return numberOfCompPlayer;
	}

	public void setNumberOfCompPlayer(int numberOfCompPlayer) {
		this.numberOfCompPlayer = numberOfCompPlayer;
	}

}

