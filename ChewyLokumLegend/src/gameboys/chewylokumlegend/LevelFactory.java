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
			return new NormalLevel(levelNum,30000,10,5);
		case 2:
			return new TimedLevel(levelNum,40000,30,8);
		default:
			System.err.println("Invalid level number.");
			return null;
		}
	}

}
