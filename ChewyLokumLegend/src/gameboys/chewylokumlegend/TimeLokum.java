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
	 * @param bonusTime the extra time in seconds that destroying
	 * this lokum awards.
	 */
	public TimeLokum(int type, int bonusTime){
		super();
		setType(type);
		setBonusTime(bonusTime);
	}

	/**
	 * @param type the type of lokum to set
	 * 1: ROSE,
	 * 2: HAZELNUT,
	 * 3: PISTACHIO,
	 * 4: COCONUT
	 */
	public void setType(int type) {
		super.setType(type);
		switch(type){
		case 1:
			setLokumImage(Main.roseTimeImage);
			break;
		case 2:
			setLokumImage(Main.hazelnutTimeImage);
			break;
		case 3:
			setLokumImage(Main.pistachioTimeImage);
			break;
		case 4:
			setLokumImage(Main.coconutTimeImage);
			break;
		default:
			System.err.println("Error: Invalid lokum type entered.");
		}
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
