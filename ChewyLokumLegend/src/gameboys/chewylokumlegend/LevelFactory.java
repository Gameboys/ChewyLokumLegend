package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class LevelFactory {


	/**
	 * @param levelNum
	 * @return
	 */
	public static Level getLevel(int levelNum){
		switch(levelNum){
		case 1:
			return new NormalLevel(levelNum,10);
		case 2:
			return new TimedLevel(levelNum,30);
		default:
			return null;
		}
	}

}
