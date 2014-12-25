package gameboys.chewylokumlegend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Gameboys
 *
 */
@SuppressWarnings("serial")
public class ScoreBoard extends JPanel{
	
	private static int WIDTH = (int)(Constants.WINDOW_WIDTH*Constants.DIVIDER_RATIO);
	private static int HEIGHT = Constants.WINDOW_HEIGHT;
	
	private int levelNum;
	private int currentScore;
	private int resourceLeft;
	private int targetScore;
	
	private String resourceName;
	
	private JLabel levelNumLabel;
	private JLabel resourceLeftLabel;
	private JLabel targetScoreLabel;
	private JLabel currentScoreLabel;
	
	private JButton specialSwapButton;
	
	private boolean mode;
	private Timer timer;
	
	/**
	 * @param level 
	 * 
	 */
	public ScoreBoard(Level level){
		super();
		setLayout(null);
		setCurrentScore(0);
		resourceLeft = level.getResourceAmount();
		resourceName = level.getResourceName();
		setLevelNum(level.getLevelNum());
		setTargetScore(level.getTargetScore());
		addLabels();
		
		if(resourceName.equals("Time"))timer = new Timer(1000,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(resourceLeft>0)resourceLeft--;
				resourceLeftLabel.setText(""+resourceLeft);
			}
		});
	}
	
	/**
	 * @param mode
	 */
	public void setMode(boolean mode){
		this.mode = mode;
		if(resourceName.equals("Time"))
		if(mode){
			timer.start();
		}else{
			timer.stop();
		}
	}
	
	/**
	 * 
	 */
	private void addLabels() {
		Font font = new Font("Comic Sans MS",Font.BOLD,Constants.WINDOW_WIDTH/42);
		final JLabel levelNumTitle = new JLabel("Level: ");
		levelNumTitle.setBounds(WIDTH/10,HEIGHT/10-HEIGHT/16,WIDTH-WIDTH/5,HEIGHT/8);
		levelNumTitle.setFont(font);
		add(levelNumTitle);
		final JLabel resourceLeftTitle = new JLabel(resourceName +" Left: ");
		resourceLeftTitle.setBounds(WIDTH/10,HEIGHT*3/10-HEIGHT/16,WIDTH-WIDTH/5,HEIGHT/8);
		resourceLeftTitle.setFont(font);
		add(resourceLeftTitle);
		final JLabel targetScoreTitle = new JLabel("Target Score: ");
		targetScoreTitle.setFont(font);
		targetScoreTitle.setBounds(WIDTH/10,HEIGHT*5/10-HEIGHT/16,WIDTH-WIDTH/5,HEIGHT/8);
		add(targetScoreTitle);
		final JLabel currentScoreTitle = new JLabel("Current Score: ");
		currentScoreTitle.setFont(font);
		currentScoreTitle.setBounds(WIDTH/10,HEIGHT*7/10-HEIGHT/16,WIDTH-WIDTH/5,HEIGHT/8);
		add(currentScoreTitle);

		levelNumLabel = new JLabel(""+levelNum);
		levelNumLabel.setBounds(WIDTH/10,HEIGHT*3/20-HEIGHT/16,WIDTH-WIDTH/5,HEIGHT/8);
		levelNumLabel.setFont(font);
		add(levelNumLabel);
		resourceLeftLabel = new JLabel("" + resourceLeft);
		resourceLeftLabel.setBounds(WIDTH/10,HEIGHT*7/20-HEIGHT/16,WIDTH-WIDTH/5,HEIGHT/8);
		resourceLeftLabel.setFont(font);
		add(resourceLeftLabel);
		targetScoreLabel = new JLabel("" + targetScore);
		targetScoreLabel.setBounds(WIDTH/10,HEIGHT*11/20-HEIGHT/16,WIDTH-WIDTH/5,HEIGHT/8);
		targetScoreLabel.setFont(font);
		add(targetScoreLabel);
		currentScoreLabel = new JLabel("" + currentScore);
		currentScoreLabel.setBounds(WIDTH/10,HEIGHT*15/20-HEIGHT/16,WIDTH-WIDTH/5,HEIGHT/8);
		currentScoreLabel.setFont(font);
		add(currentScoreLabel);
		
		specialSwapButton = new JButton("Special Swap!");
		specialSwapButton.setBounds(WIDTH/10,HEIGHT*17/20-HEIGHT/32,WIDTH-WIDTH/5,HEIGHT/16);
		specialSwapButton.setFont(font);
		specialSwapButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		add(specialSwapButton);
	}
	
	/**
	 * 
	 */
	public void update(){
		levelNumLabel.setText(""+levelNum);
		resourceLeftLabel.setText(""+resourceLeft);
		targetScoreLabel.setText(""+targetScore);
		currentScoreLabel.setText(""+currentScore);
	}

	/**
	 * @return the levelNum
	 */
	public int getLevelNum() {
		return levelNum;
	}
	/**
	 * @param levelNum the levelNum to set
	 */
	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}
	/**
	 * @return the currentScore
	 */
	public int getCurrentScore() {
		return currentScore;
	}
	/**
	 * @param currentScore the currentScore to set
	 */
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	/**
	 * @param score
	 */
	public void increaseScore(int score){
		setCurrentScore(currentScore + score);
		update();
	}
	/**
	 * @return the movesLeft
	 */
	public int getResourceLeft(){
		return resourceLeft;
	}
	/**
	 * Decrement movesLeft
	 */
	public void makeMove(){
		if(resourceName.equals("Moves"))resourceLeft--;
		update();
	}
	/**
	 * @return the targetScore
	 */
	public int getTargetScore() {
		return targetScore;
	}
	/**
	 * @param targetScore the targetScore to set
	 */
	public void setTargetScore(int targetScore) {
		this.targetScore = targetScore;
	}

}
