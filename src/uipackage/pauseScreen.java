package uipackage;

import java.awt.Component;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class pauseScreen extends JFrame {

	public pauseScreen(){
		
        setTitle("PAUSED");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Create a panel to hold the instructions
        JPanel panel = new JPanel();
        JTextArea textArea = new JTextArea(20, 10);
        textArea.setText("GAME IS PAUSED.");
        textArea.setAlignmentX(JTextField.CENTER);
        textArea.setAlignmentY(JTextField.CENTER);
        
        JButton closeButton = new JButton("Continue to the game.");
        panel.add(closeButton);
        
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
        

        textArea.setEditable(false);
        panel.add(textArea);
        
        // Add the panel to the frame
        this.add(panel);
        
        // Center the frame on the screen
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
    	pauseScreen pauseScreen = new pauseScreen();
    	pauseScreen.setVisible(true);
    }

}
