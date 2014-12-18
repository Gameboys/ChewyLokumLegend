package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class NormalLevel extends Level {

	private int moveCount;
	
	public NormalLevel(int levelNum) {
		super(levelNum);

		switch(levelNum){
		case 1:
			setMoveCount(10);
		}
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

}
