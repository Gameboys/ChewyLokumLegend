package gameboys.chewylokumlegend;

/**
 * @author Gameboys
 *
 */
public class NormalLokum extends Lokum{
	
	/**
	 * @param type
	 * 1 -> ROSE,
	 * 2 -> HAZELNUT,
	 * 3 -> PISTACHIO,
	 * 4 -> COCONUT
	 */
	public NormalLokum(int type){
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
			setLokumImage(Main.roseImage);
			break;
		case 2:
			setLokumImage(Main.hazelnutImage);
			break;
		case 3:
			setLokumImage(Main.pistachioImage);
			break;
		case 4:
			setLokumImage(Main.coconutImage);
			break;
		default:
			System.err.println("Error: Invalid lokum type entered.");
		}
	}

//	@Override
//	public void destroy() {
//		/* Do nothing */
//	}
	
	/**
	 * @return true if current state of NormalLokum is OK
	 *		   false otherwise
	 */
	public boolean repOK(){
		if(getType()<1 || getType()>4)
			return false;
		else return true;
	}
	
	public String toString(){
		return "" + getType();
	}

}
