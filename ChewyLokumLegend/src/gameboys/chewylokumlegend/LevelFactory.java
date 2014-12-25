package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class LevelFactory {
	
	public static int numLevels = 2;


	/**
	 * @param levelNum
	 * @return
	 */
	public static Level getLevel(int levelNum){
		switch(levelNum){
		case 1:
			return new NormalLevel(levelNum,15000,10);
		case 2:
			return new TimedLevel(levelNum,20000,30);
		default:
			System.err.println("Invalid level number.");
			return null;
		}
	}

}
