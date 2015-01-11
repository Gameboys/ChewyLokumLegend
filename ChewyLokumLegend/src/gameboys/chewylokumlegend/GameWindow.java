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
	
	/**
	 * The UI element which contains the LokumMatrix where the Lokums are stored
	 */
	protected static GameBoard gameBoard;
	/**
	 * The UI element which contains information about the current Level and score
	 */
	protected static ScoreBoard scoreBoard;

	/**
	 * @param level The current Level object that the game state
	 * will be based on
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
		
		setMode(true);
	}
	
	/**
	 * @param mode set to true if the game is running, false otherwise
	 */
	public static void setMode(boolean mode){
		gameBoard.setMode(mode);
		scoreBoard.setMode(mode);
	}
	
}
