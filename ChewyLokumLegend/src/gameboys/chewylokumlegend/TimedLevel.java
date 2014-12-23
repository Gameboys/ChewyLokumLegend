package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class TimedLevel extends Level {
	
	/**
	 * How much time allowed for the player, in seconds
	 */
	private int timeLimit;

	/**
	 * @param levelNum
	 * @param timeLimit
	 */
	public TimedLevel(int levelNum, int timeLimit) {
		super(levelNum);
		setResourceAmount(timeLimit);
	}

	@Override
	public String getResourceName() {
		return "Time";
	}

	@Override
	public int getResourceAmount() {
		return timeLimit;
	}

	@Override
	public void setResourceAmount(int resourceAmount) {
		timeLimit = resourceAmount;
	}

}
