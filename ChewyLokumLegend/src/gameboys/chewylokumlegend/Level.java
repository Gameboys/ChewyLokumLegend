package gameboys.chewylokumlegend;

import java.util.ArrayList;

/**
 * @author Gameboys
 *
 */
public class Level {
	
	private int levelNum;
	private int moveCount;
	private int targetScore;
	private ArrayList<int[]> obstacleCoordinates;
	
	
	/**
	 * @param levelNum
	 */
	public Level(int levelNum){
		setLevelNum(levelNum);
		switch(levelNum){
		case 1:
			setMoveCount(10);
			setTargetScore(15000);
		}
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
	 * @return the moveCount
	 */
	public int getMoveCount() {
		return moveCount;
	}
	/**
	 * @param moveCount the moveCount to set
	 */
	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
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
