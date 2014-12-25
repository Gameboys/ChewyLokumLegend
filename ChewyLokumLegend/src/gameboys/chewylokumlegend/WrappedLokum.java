package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class WrappedLokum extends SpecialLokum{

	/**
	 * @param type
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

}
