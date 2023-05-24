package RiskPackage;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;



import uipackage.login;

public class Main {
    public static void main(String[] args) throws Exception {
        
    	GameController instance = GameController.getInstance();
    	try {
			instance.main(args);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
