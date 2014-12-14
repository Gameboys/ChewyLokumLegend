package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class SavedState {
	
	private int levelNum;
	private int movesLeft;
	private int currentScore;
	private int targetScore;
	private BoardObject[][] lokumMatrix;
	
	public SavedState(){
		
	}
	
	public void writeToXML(){
		
	}
	
	public void readFromXML(){
		
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
	 * @return the movesLeft
	 */
	public int getMovesLeft() {
		return movesLeft;
	}

	/**
	 * @param movesLeft the movesLeft to set
	 */
	public void setMovesLeft(int movesLeft) {
		this.movesLeft = movesLeft;
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
