package gameboys.chewylokumlegend;

import java.awt.Graphics;

/**
 * @author Gameboys
 *
 */
public abstract class BoardObject {
	
	/** type of the boardObject, represented as an int value */
	private int type;
	
	/**
	 * 
	 */
	public BoardObject(){
		
	}
	
	/**
	 * This method should paint the object from which the method is called,
	 * on the Graphics object g. Since lokums' x and y values for its location
	 * are stored inside the lokumMatrix, they should be given as arguments.
	 * 
	 * @param g the Graphics object to paint on
	 * @param x the X coordinate of the object that will be painted
	 * @param y the Y coordinate of the object that will be painted
	 */
	public abstract void paint(Graphics g, int x, int y);

	/**
	 * @return the type
	 * 1: ROSE,
	 * 2: HAZELNUT,
	 * 3: PISTACHIO,
	 * 4: COCONUT
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 * 1: ROSE,
	 * 2: HAZELNUT,
	 * 3: PISTACHIO,
	 * 4: COCONUT
	 */
	public void setType(int type) {
		this.type = type;
	}
	

}
