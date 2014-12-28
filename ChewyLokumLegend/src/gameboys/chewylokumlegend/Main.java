package gameboys.chewylokumlegend;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

/**
 * @author Gameboys
 *
 */
@SuppressWarnings("javadoc")
public class Main {

	protected static Image t1;
	protected static Image t2;
	
	protected static Image roseImage;
	protected static Image roseStripedVerticalImage;
	protected static Image roseStripedHorizontalImage;
	protected static Image roseWrappedImage;
	
	protected static Image hazelnutImage;
	protected static Image hazelnutStripedVerticalImage;
	protected static Image hazelnutStripedHorizontalImage;
	protected static Image hazelnutWrappedImage;
	
	protected static Image pistachioImage;
	protected static Image pistachioStripedVerticalImage;
	protected static Image pistachioStripedHorizontalImage;
	protected static Image pistachioWrappedImage;
	
	protected static Image coconutImage;
	protected static Image coconutStripedVerticalImage;
	protected static Image coconutStripedHorizontalImage;
	protected static Image coconutWrappedImage;
	
	protected static Image backgroundImage;
	protected static Image colorBombImage;
	protected static ImageIcon explodeImage;

	protected static Clip aahSound;
	protected static Clip winSound;
	protected static Clip cukcukSound;
	protected static Clip sweetSound;
	protected static Clip tastySound;
	protected static Clip deliciousSound;
	protected static Clip divineSound;
	protected static Clip headshotSound;
	protected static BigClip kanunMusic;

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		launchGame();
	}

	/**
	 * 
	 */
	private static void launchGame(){
		ApplicationWindow frame = ApplicationWindow.getInstance();

		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		loadMedia();
	}

	/**
	 * Loads media files from the source folder into the global fields
	 */
	private static void loadMedia() {
		try{
			t1 = openImage("title"+File.separatorChar+"t1.png");
			t2 = openImage("title"+File.separatorChar+"t2.png");

			explodeImage = new ImageIcon("media/explode.gif");
			
			roseImage = openImage("normal"+File.separatorChar+"rose.png");
			hazelnutImage = openImage("normal"+File.separatorChar+"hazelnut.png");
			pistachioImage = openImage("normal"+File.separatorChar+"pistachio.png");
			coconutImage = openImage("normal"+File.separatorChar+"coconut.png");

			roseStripedVerticalImage = openImage("striped"+File.separatorChar+"roseStripedV.png");
			roseStripedHorizontalImage = openImage("striped"+File.separatorChar+"roseStripedH.png");
			hazelnutStripedVerticalImage = openImage("striped"+File.separatorChar+"hazelnutStripedV.png");
			hazelnutStripedHorizontalImage = openImage("striped"+File.separatorChar+"hazelnutStripedH.png");
			pistachioStripedVerticalImage = openImage("striped"+File.separatorChar+"pistachioStripedV.png");
			pistachioStripedHorizontalImage = openImage("striped"+File.separatorChar+"pistachioStripedH.png");
			coconutStripedVerticalImage = openImage("striped"+File.separatorChar+"coconutStripedV.png");
			coconutStripedHorizontalImage = openImage("striped"+File.separatorChar+"coconutStripedH.png");
			
			roseWrappedImage = openImage("wrapped"+File.separatorChar+"roseWrapped.png");
			hazelnutWrappedImage = openImage("wrapped"+File.separatorChar+"hazelnutWrapped.png");
			pistachioWrappedImage = openImage("wrapped"+File.separatorChar+"pistachioWrapped.png");
			coconutWrappedImage = openImage("wrapped"+File.separatorChar+"coconutWrapped.png");
			
			colorBombImage = openImage("colorBomb.png");
			
			kanunMusic = loadMusic("kanun.wav");
			aahSound = loadMusic("aah.wav");
			winSound = loadMusic("win.wav");
			cukcukSound = loadMusic("cukcuk.wav");
			sweetSound = loadMusic("sweet.wav");
			tastySound = loadMusic("tasty.wav");
			deliciousSound = loadMusic("delicious.wav");
			divineSound = loadMusic("divine.wav");
			headshotSound = loadMusic("headshot.wav");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static BigClip loadMusic(String musicFileName){
		BigClip clip = null;
		try{
			Clip a = AudioSystem.getClip();
			clip = new BigClip(a);
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("media"+File.separatorChar+"audio"+File.separatorChar+musicFileName));
			clip.open(inputStream);
		}catch(Exception e){
			e.printStackTrace();
		}
		return clip;
	}
	
	/**
	 * Opens the Image file specified by name fileName
	 * and returns the Image object read. The media folder for the
	 * project is "media". Therefore this method checks the path 
	 * "[Project Directory]/media/fileName" for the Image.
	 * 
	 * @param fileName name of the Image file, not including the path
	 * @return the Image object read from the file
	 * @throws IOException if the file can not be read
	 */
	private static Image openImage(String fileName) throws IOException{
		return ImageIO.read(new File("media"+File.separatorChar+fileName));
	}

}
