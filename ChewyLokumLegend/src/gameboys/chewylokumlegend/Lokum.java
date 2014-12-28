package gameboys.chewylokumlegend;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * @author Gameboys
 *
 */
public abstract class Lokum extends BoardObject{

	
	/** Image which will be displayed to represent this lokum */
	private Image lokumImage;

	/**
	 * Constructs the superclass BoardObject
	 */
	public Lokum(){
		super();
	}
	
	@Override
	public void paint(Graphics g, int x, int y) {
		Rectangle clipRect = g.getClipBounds();
		if(clipRect.intersects(new Rectangle(x,y,Constants.LOKUM_SIZE,Constants.LOKUM_SIZE))){
			g.drawImage(getLokumImage(),x,y,Constants.LOKUM_SIZE, Constants.LOKUM_SIZE,null);
		}
	}

	/**
	 * @return the lokumImage
	 */
	public Image getLokumImage() {
		return lokumImage;
	}

	/**
	 * @param lokumImage the lokumImage to set
	 */
	public void setLokumImage(Image lokumImage) {
		this.lokumImage = lokumImage;
	}

}
