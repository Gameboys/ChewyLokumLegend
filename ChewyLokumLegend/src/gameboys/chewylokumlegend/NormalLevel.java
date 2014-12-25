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
	 * @param numSpecialSwaps 
	 */
	public NormalLevel(int levelNum, int targetScore, int moveCount, int numSpecialSwaps) {
		super(levelNum,targetScore,numSpecialSwaps);
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
