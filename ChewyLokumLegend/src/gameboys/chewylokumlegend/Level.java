package gameboys.chewylokumlegend;

import java.util.ArrayList;

/**
 * @author Gameboys
 *
 */
public abstract class Level {
	
	private int levelNum;
	private int targetScore;
	private int numSpecialSwaps;
	private ArrayList<int[]> obstacleCoordinates;
	
	
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
	 * @return the obstacleCoordinates
	 */
	public ArrayList<int[]> getObstacleCoordinates() {
		return obstacleCoordinates;
	}
	/**
	 * @param obstacleCoordinates the obstacleCoordinates to set
	 */
	public void setObstacleCoordinates(ArrayList<int[]> obstacleCoordinates) {
		this.obstacleCoordinates = obstacleCoordinates;
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
