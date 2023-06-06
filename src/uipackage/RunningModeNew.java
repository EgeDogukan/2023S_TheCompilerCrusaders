package uipackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import uipackage.WorldMap;
import javax.imageio.ImageIO;

import RiskPackage.GameControllerNew;
import RiskPackage.PlayerNew;
import chanceCardPackage.ChanceCardFactory;
import databasePackage.ISaveLoadAdapter;
import databasePackage.TerritoryDBDatabase;
import databasePackage.TerritoryJSONDBDatabase;


public class RunningModeNew extends JFrame {


	//************* */
	private Image star;
    private Image cross;
    private double scale = 0.01;
    private boolean isGrowing = true;
    private boolean starVisible = false;
    private boolean crossVisible = false;
    private static JPanel territoryPromptJPanel;
	private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int DELAY = 20;

	//*********** */

    public ArrayList<PlayerNew> players;
	private static JButton turn = new JButton();
	int numberOfAIPlayer;
	int numberOfHumanPlayer;
    static int turnCounter=0;
	public static boolean isContinue = true;
	public static int databaseChooser=0;
    public ArrayList<Shape> shapelist = new ArrayList<>();
	public static boolean isInBuildingMode = true;

	private int counter = 0;
	public static JLabel stage = new JLabel("Deploy");
	public static String whichStage= new String();


	public static WorldMap worldMap;



	public RunningModeNew(ArrayList<PlayerNew> players, WorldMap worldMap) {
		this.players=players;
		numberOfHumanPlayer=players.size();
		numberOfAIPlayer=0;
		this.worldMap=worldMap;
		initGame(players);
	}
	

    public static int getTurn() {
		return RunningModeNew.turnCounter;
	}
	public Color getTurnColor(){
		return this.players.get(getTurn()-1).getColor();
	}

	
	public Color getPlayer(int id){
		return this.players.get(id).getColor();
	}
	

	public ArrayList<PlayerNew> getPlayer(){
		return players;
	}
	
	public ArrayList<PlayerNew> getPlayers() {
		return this.players;
	}
	
	public Boolean getIsInBuildingMode() {
		return isInBuildingMode;
	}

	public void createPanel() {
		String cwd = System.getProperty("user.dir");
        star = Toolkit.getDefaultToolkit().getImage(cwd + "/Star.png");
        cross = Toolkit.getDefaultToolkit().getImage(cwd +"/Cross.png");

        JPanel territoryPromptJPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (starVisible) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    int x = (getWidth() - (int)(star.getWidth(null) * scale)) / 2;
                    int y = (getHeight() - (int)(star.getHeight(null) * scale)) / 2;
                    AffineTransform at = AffineTransform.getTranslateInstance(x, y);
                    at.scale(scale, scale);
                    g2d.drawImage(star, at, null);
                    g2d.dispose();
                } else if (crossVisible) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    int x = (getWidth() - (int)(cross.getWidth(null) * scale)) / 2;
                    int y = (getHeight() - (int)(cross.getHeight(null) * scale)) / 2;
                    AffineTransform at = AffineTransform.getTranslateInstance(x, y);
                    at.scale(scale, scale);
                    g2d.drawImage(cross, at, null);
                    g2d.dispose();
                }
            }
        };
        territoryPromptJPanel.setBounds(0, 0, 600, 600);
        territoryPromptJPanel.setBackground(Color.green);
        

		starVisible = true;
		Timer timer = new Timer(DELAY, null);
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isGrowing) {
					scale += 0.03;
					if (scale > 0.3) {
						isGrowing = false;
					}
				} else {
					scale -= 0.03;
					if (scale < 0) {
						scale = 0.01;
						isGrowing = true;
						starVisible = false;
						((Timer) e.getSource()).stop();
					}
				}
				territoryPromptJPanel.repaint();
			}
		});
		timer.start();
		this.add(territoryPromptJPanel);
    }

	public static void getTerritoryPromptJPanel() {
        
    }

    public static void triggerTerritoryPromptJPanel() {
		if(territoryPromptJPanel == null){
			GameControllerNew.g.createPanel();
			//createPanel();
		}
        territoryPromptJPanel.repaint();
    }

	public void initGame(ArrayList<PlayerNew> players){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1920, 1080));
		this.setLayout(null); // Use BorderLayout for the JFrame
	
		
		this.createPanel();
		shapelist=this.worldMap.getShapeList();
		ArrayList<JTextField> textLabels = worldMap.getTextLabels();
		for (JTextField curField : textLabels) {
			this.add(curField);
		}
		

		JPanel worldPanel = (JPanel) worldMap.getUI();
		worldPanel.setBounds(0, 0, worldPanel.getPreferredSize().width, worldPanel.getPreferredSize().height);
		this.add(worldPanel); // Add the map panel to the center of the JFrame
		
		
		JButton saveButtonMongo = new JButton("SAVE MONGO");
		saveButtonMongo.setBounds(1100, 300, 100, 100);
		saveButtonMongo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TerritoryDBDatabase database = new TerritoryDBDatabase();
				try {
					database.empty();
					database.saveAll();
					System.out.println("MONGO IS SAVED");
					RunningModeNew.this.dispose();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		this.add(saveButtonMongo);
		
		
		JButton saveButtonJSON = new JButton("SAVE JSON");
		saveButtonJSON.setBounds(1300, 300, 100, 100);
		saveButtonJSON.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TerritoryJSONDBDatabase database = new TerritoryJSONDBDatabase();
				try {
					database.empty();
					database.saveAll();
					System.out.println("JSON IS SAVED");
					RunningModeNew.this.dispose();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		this.add(saveButtonJSON);
		
		JButton pickChanceCard = new JButton("pick chance card");
		pickChanceCard.setBounds(200, 550, 100, 100);
		pickChanceCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Random random = new Random();
		        int randomNumber = random.nextInt(5);
				//comment it out when the players are initialised.
		        players.get(turnCounter).addChanceCard(randomNumber);
				ChanceCardFactory factory = new ChanceCardFactory();
				
				System.out.println("card with index "+randomNumber+" which is "+factory.createCard(randomNumber).getClass().getName()+"added to the players' list with id: " +(turnCounter+1));
				
			}
		});
		this.add(pickChanceCard);
		
		JLabel turn = new JLabel("Turn: Player "+ players.get(turnCounter).getId());
		turn.setBounds(300, 650, 100, 100);
		
		this.add(turn);
		
		JPanel turnPanel = new JPanel();
		turnPanel.setBounds(600,700,100,100);
		
		stage.setBounds(380, 620, 100, 100);
		whichStage="Deploy";

		JButton nextStage = new JButton("Next Stage");
		nextStage.setBounds(350, 550, 100, 100);
		nextStage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			counter ++;

			if(counter == 1){
				stage.setText("Attack");
				whichStage="Attack";

			}
			else if(counter==2){
				stage.setText("Fortify");
				whichStage="Fortify";
			}
			
			else if(counter>2){
				counter=0;
				stage.setText("Deploy");
				whichStage="Deploy";
			}
			}
		});

		//****************************************************************** */


		//********************************************************************* */
		
		nextStage.setVisible(true);
		this.add(stage);
		this.add(nextStage);

		JButton nextButton = new JButton("next turn");
		nextButton.setBounds(500,550,100,100);
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				turnCounter++;
				turn.setText("Turn: Player "+players.get(turnCounter-1).getId());
				if (turnCounter==numberOfAIPlayer+numberOfHumanPlayer)
						turnCounter=0;
				turnPanel.setBackground(players.get(turnCounter).getColor());
				
				
				
			}
		});
		this.add(nextButton);
		this.add(turnPanel);


		
		String[] cardTypes = {"Draft Chance Card", "Reinforcement Card", "Trade Deal Card", "Revolution Card", "Nuclear Strike Card"};
        JComboBox<String> cardComboBox = new JComboBox<String>(cardTypes);
        cardComboBox.setBounds(900,550, 100, 100);
        this.add(cardComboBox);
        
		
        
		JButton useCardButton = new JButton("use card");
		useCardButton.setBounds(700, 550, 100, 100);
		useCardButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("card with index "+cardComboBox.getSelectedIndex()+"which is "+cardComboBox.getSelectedItem()+"used by the player with id: " +(turnCounter+1));
				
				players.get(turnCounter).useCard(cardComboBox.getSelectedIndex());
				
				if (cardComboBox.getSelectedItem().equals("Reinforcement Card")) {
					
					
					JFrame optionFrame = new JFrame();
                    optionFrame.setSize(500, 100);
                    optionFrame.setLocationRelativeTo(null);
                    optionFrame.setTitle("ID Choose");
                    JPanel optionPanel = new JPanel();
                    JTextField ID = new JTextField();
                    ID.setPreferredSize(new Dimension(100,50));
                    
                    JButton closeButton = new JButton("Close");
                    
                    closeButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String IDonArea = ID.getText();
							players.get(turnCounter).useReinforcementCard(Integer.parseInt(IDonArea));
							optionFrame.dispose();
							
						}
					});
                    
                    
                    optionPanel.add(ID);
                    optionPanel.add(closeButton);
                    optionFrame.add(optionPanel);
                    optionFrame.setVisible(true);
					
					
				}
				
				
				
			}
		});
		this.add(useCardButton);
		
		
		JButton exitButton = new JButton("Exit!");
		exitButton.setBounds(1200, 700, 100, 100);
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RunningModeNew.this.dispose();
				
			}
		});
		this.add(exitButton);
		
		JButton pauseButton = new JButton("Pause");
		pauseButton.setBounds(1200, 600, 100, 100);
		pauseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (pauseButton.getText().equals("Pause")){
					pauseButton.setText("Resume");
					exitButton.setVisible(false);
					useCardButton.setVisible(false);
					cardComboBox.setVisible(false);
					saveButtonMongo.setVisible(false);
					saveButtonJSON.setVisible(false);
					pickChanceCard.setVisible(false);
					turnPanel.setVisible(false);
					nextButton.setVisible(false);
					worldPanel.setVisible(false);
					turn.setVisible(false);
					for (JTextField curField : textLabels) 
						curField.setVisible(false);
					
					
				}
				else {
					pauseButton.setText("Pause");
					exitButton.setVisible(true);
					useCardButton.setVisible(true);
					cardComboBox.setVisible(true);
					saveButtonMongo.setVisible(true);
					saveButtonJSON.setVisible(true);
					pickChanceCard.setVisible(true);
					turnPanel.setVisible(true);
					nextButton.setVisible(true);
					worldPanel.setVisible(true);
					turn.setVisible(true);
					for (JTextField curField : textLabels) 
						curField.setVisible(true);
				}
				
				
			}
		});
		this.add(pauseButton);
		
		
	
		
		
		
		
	
		pack();
		setLocationRelativeTo(null); // Center the JFrame on the screen
		setVisible(true);
	}
}

