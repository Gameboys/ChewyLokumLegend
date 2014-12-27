package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class LevelFactory {
	
	/**
	 * Total number of levels in the game
	 */
	public static int numLevels = 2;


	/**
	 * @param levelNum the level number
	 * @return
	 */
	public static Level getLevel(int levelNum){
		switch(levelNum){
		case 1:
			return new NormalLevel(levelNum,20000,10,3);
		case 2:
			return new TimedLevel(levelNum,30000,30,5);
		default:
			System.err.println("Invalid level number.");
			return null;
		}
	}

}
