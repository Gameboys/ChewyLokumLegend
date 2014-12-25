package gameboys.chewylokumlegend;

import java.util.ArrayList;

/**
 * @author Gameboys
 *
 */
public abstract class Level {
	
	private int levelNum;
	private int targetScore;
	private ArrayList<int[]> obstacleCoordinates;
	
	
	/**
	 * @param levelNum
	 */
	public Level(int levelNum, int targetScore){
		setLevelNum(levelNum);
		setTargetScore(targetScore);
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

}
