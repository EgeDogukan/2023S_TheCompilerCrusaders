package RiskPackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFrame;

import databasePackage.ISaveLoadAdapter;
import uipackage.UIController;

import uipackage.*;

public class GameControllerNew {
	
	private static ArrayList<PlayerNew> playerList = new ArrayList<>();
	private static GameControllerNew instance;
	private static Object lock = new Object();
	public static RunningModeNew g;
	private static UIController uiController;
	
	private GameControllerNew() {
	}
	
	public static GameControllerNew getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new GameControllerNew();
				}
			}
		}
		
		return instance;
		
	}
	
	public void initGame() {
		this.uiController = new UIController();
		uiController.initGameUI();
	}
}