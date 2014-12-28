package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public abstract class Level {
	
	private int levelNum;
	private int targetScore;
	private int numSpecialSwaps;
	
	
	/**
	 * @param levelNum
	 * @param targetScore 
	 * @param numSpecialSwaps 
	 */
	public Level(int levelNum, int targetScore, int numSpecialSwaps){
		setLevelNum(levelNum);
		setTargetScore(targetScore);
		setNumSpecialSwaps(numSpecialSwaps);
	}

	/**
	 * @return
	 */
	public abstract String getResourceName();
	/**
	 * @return
	 */
	public abstract int getResourceAmount();
	/**
	 * @param resourceAmount
	 */
	public abstract void setResourceAmount(int resourceAmount);
	
	/**
	 * @return true if current state of Level is OK
	 *		   false otherwise
	 */
	public boolean repOK(){
		if(this==null || levelNum==0 || targetScore==0){
			return false;
		} 
		else return true;
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

	/**
	 * @return the numSpecialSwaps
	 */
	public int getNumSpecialSwaps() {
		return numSpecialSwaps;
	}

	/**
	 * @param numSpecialSwaps the numSpecialSwaps to set
	 */
	public void setNumSpecialSwaps(int numSpecialSwaps) {
		this.numSpecialSwaps = numSpecialSwaps;
	}

}
