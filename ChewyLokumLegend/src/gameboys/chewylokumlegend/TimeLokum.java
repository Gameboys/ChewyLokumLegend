package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class TimeLokum extends Lokum {

	/**
	 * 
	 */
	private int bonusTime;
	
	/**
	 * @param type
	 * 1 -> ROSE,
	 * 2 -> HAZELNUT,
	 * 3 -> PISTACHIO,
	 * 4 -> COCONUT
	 */
	public TimeLokum(int type){
		super();
		setType(type);
	}

	/**
	 * @return the bonusTime
	 */
	public int getBonusTime() {
		return bonusTime;
	}

	/**
	 * @param bonusTime the bonusTime to set
	 */
	public void setBonusTime(int bonusTime) {
		this.bonusTime = bonusTime;
	}
	
}
