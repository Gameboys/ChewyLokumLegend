package gameboys.chewylokumlegend;

import java.awt.Graphics;

/**
 * @author Gameboys
 *
 */
public class StripedLokum extends SpecialLokum{
	
	/**
	 * true -> vertical,
	 * false -> horizontal
	 */
	private boolean stripeDirection;

	/**
	 * @param type
	 * 1: ROSE,
	 * 2: HAZELNUT,
	 * 3: PISTACHIO,
	 * 4: COCONUT
	 * @param stripeDirection
	 * true: vertical,
	 * false: horizontal
	 */
	public StripedLokum(int type, boolean stripeDirection) {
		super();
		setStripeDirection(stripeDirection);
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
			if(stripeDirection)setLokumImage(Main.roseStripedVerticalImage);
			else setLokumImage(Main.roseStripedHorizontalImage);
			break;
		case 2:
			if(stripeDirection)setLokumImage(Main.hazelnutStripedVerticalImage);
			else setLokumImage(Main.hazelnutStripedHorizontalImage);
			break;
		case 3:
			if(stripeDirection)setLokumImage(Main.pistachioStripedVerticalImage);
			else setLokumImage(Main.pistachioStripedHorizontalImage);
			break;
		case 4:
			if(stripeDirection)setLokumImage(Main.coconutStripedVerticalImage);
			else setLokumImage(Main.coconutStripedHorizontalImage);
			break;
		default:
			System.err.println("Error: Invalid lokum type entered.");
		}
	}

	/**
	 * @return the stripeDirection
	 */
	public boolean getStripeDirection() {
		return stripeDirection;
	}

	/**
	 * @param stripeDirection the stripeDirection to set
	 */
	public void setStripeDirection(boolean stripeDirection) {
		this.stripeDirection = stripeDirection;
	}

}
