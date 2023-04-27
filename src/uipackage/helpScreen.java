package uipackage;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class helpScreen extends JFrame {

	public helpScreen(){
		
        setTitle("Help");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Create a panel to hold the instructions
        JPanel instructionsPanel = new JPanel();
        JTextArea instructionsArea = new JTextArea(200, 100);
        instructionsArea.setText("Instructions:\n\n" +
                                  "BLA BLA");
        
        instructionsArea.setEditable(false);
        instructionsPanel.add(instructionsArea);
        
        // Add the panel to the frame
        add(instructionsPanel);
        
        // Center the frame on the screen
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
    	helpScreen helpScreen = new helpScreen();
        helpScreen.setVisible(true);
    }

}
