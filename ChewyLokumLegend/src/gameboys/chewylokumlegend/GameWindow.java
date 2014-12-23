package gameboys.chewylokumlegend;

import javax.swing.JSplitPane;

/**
 * @author Gameboys
 *
 */
@SuppressWarnings("serial")
public class GameWindow extends JSplitPane {

	private static int WIDTH = Constants.WINDOW_WIDTH;
	private static int HEIGHT = Constants.WINDOW_HEIGHT;
	
	protected static GameBoard gameBoard;
	protected static ScoreBoard scoreBoard;

	/**
	 * @param level 
	 * 
	 */
	public GameWindow(Level level){
		super(JSplitPane.HORIZONTAL_SPLIT);
		setSize(WIDTH, HEIGHT);
		
		scoreBoard = new ScoreBoard(level);
		gameBoard = new GameBoard(level);

		setEnabled(false);
		setLeftComponent(scoreBoard);
		setRightComponent(gameBoard);
		setDividerLocation(Constants.DIVIDER_RATIO);
	}
	
	/**
	 * 
	 */
	public void exitGame(){
		
	}
}
