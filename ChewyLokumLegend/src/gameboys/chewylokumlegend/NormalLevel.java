package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class NormalLevel extends Level {

	private int moveCount;
	
	/**
	 * @param levelNum
	 * @param targetScore 
	 * @param moveCount
	 */
	public NormalLevel(int levelNum, int targetScore, int moveCount) {
		super(levelNum,targetScore);
		setResourceAmount(moveCount);
	}
	


	@Override
	public String getResourceName() {
		return "Moves";
	}


	@Override
	public int getResourceAmount() {
		return moveCount;
	}


	@Override
	public void setResourceAmount(int resourceAmount) {
		moveCount = resourceAmount;
	}

}
