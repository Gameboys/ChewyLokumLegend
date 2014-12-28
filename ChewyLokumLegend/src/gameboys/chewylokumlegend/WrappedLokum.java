package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class WrappedLokum extends SpecialLokum{

	private boolean exploded = false;
	
	/**
	 * @param type the type of lokum
	 * 1: ROSE,
	 * 2: HAZELNUT,
	 * 3: PISTACHIO,
	 * 4: COCONUT
	 */
	public WrappedLokum(int type) {
		super();
		setType(type);
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
			setLokumImage(Main.roseWrappedImage);
			break;
		case 2:
			setLokumImage(Main.hazelnutWrappedImage);
			break;
		case 3:
			setLokumImage(Main.pistachioWrappedImage);
			break;
		case 4:
			setLokumImage(Main.coconutWrappedImage);
			break;
		default:
			System.err.println("Error: Invalid lokum type entered.");
		}
	}

	/**
	 * @return the exploded
	 */
	public boolean isExploded() {
		return exploded;
	}

	/**
	 * @param exploded the exploded to set
	 */
	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}
	

}
