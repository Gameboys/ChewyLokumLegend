package gameboys.chewylokumlegend;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author Gameboys
 *
 */
@SuppressWarnings("javadoc")
public interface Constants {
	
	public static final double SCALE = 1;
	public static final double XSCALE = 0.55*SCALE;
	public static final double YSCALE = 0.75*SCALE;
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int BORDER_WIDTH = 5;
	public static final int WINDOW_WIDTH = (int)(SCREEN_SIZE.getWidth()*XSCALE)+ BORDER_WIDTH;
	public static final int WINDOW_HEIGHT = (int)(SCREEN_SIZE.getHeight()*YSCALE) + BORDER_WIDTH;
	public static final int HEIGHT_TASKBAR = WINDOW_WIDTH/63;
	public static final double DIVIDER_RATIO = 0.25;
	public static final int REFRESH_RATE = 30;
	public static final int TIMER_RATE = 300;
	
	public static final int TYPE_ROSE = 1;
	public static final int TYPE_HAZELNUT = 2;
	public static final int TYPE_PISTACHIO = 3;
	public static final int TYPE_COCONUT = 4;
	
	public static final String COLORBOMB = "COLORBOMB";
	public static final String WRAPPED = "WRAPPED";
	public static final String STRIPED = "STRIPED";
	public static final String NORMAL = "NORMAL";
	public static final String ILLEGAL = "ILLEGAL";
	
	public static int POINTS_NORMAL = 60;
	public static int POINTS_STRIPED_FORMED = 120;
	public static int POINTS_WRAPPED_FORMED = 200;
	public static int POINTS_COLORBOMB_FORMED = 200;
	public static int POINTS_WRAPPED_DESTROYED = 1080;
	public static int POINTS_WRAPPED_WRAPPED = 3600;
	public static int POINTS_BOARDCLEAR_PER_LOKUM = 100;
	
	/**
	 * Enumeration for swapping locations.
	 * Starts from NORTH = 0 and increases clockwise.
	 */
	public static int NORTH = 0;
	public static int NORTHEAST = 1;
	public static int EAST = 2;
	public static int SOUTHEAST = 3;
	public static int SOUTH = 4;
	public static int SOUTHWEST = 5;
	public static int WEST = 6;
	public static int NORTHWEST = 7;
	
	public static final int LOKUM_SIZE = WINDOW_WIDTH*2/27-1;

}
