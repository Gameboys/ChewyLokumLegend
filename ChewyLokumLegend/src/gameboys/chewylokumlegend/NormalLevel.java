package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class NormalLevel extends Level {

	private int moveCount;
	
	/**
	 * @param levelNum
	 * @param moveCount
	 */
	public NormalLevel(int levelNum, int moveCount) {
		super(levelNum);
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
