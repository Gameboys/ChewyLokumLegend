package gameboys.chewylokumlegend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * @author Gameboys
 *
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {
	
	private GameMouseListener mouseListener;
	private LokumMatrix matrix;
	private Timer refresh;
	private boolean mode;

	/**
	 * @param level 
	 * 
	 */
	public GameBoard(Level level){
		super();
		setLayout(new BorderLayout());
		setOpaque(true);
		setBackground(new Color(255,230,230));
		setVisible(true);
		
		refresh = new Timer(Constants.REFRESH_RATE,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		});
		
		matrix = new LokumMatrix(10,10);
		mouseListener = new GameMouseListener();

		setMode(true);
	}
	
	/**
	 * @param mode
	 */
	public void setMode(boolean mode){
		this.mode = mode;
		if(mode){
			addMouseListener(mouseListener);
			refresh.start();
			Main.kanunMusic.loop(Clip.LOOP_CONTINUOUSLY);
		}else{
			removeMouseListener(mouseListener);
			refresh.stop();
			Main.kanunMusic.stop();
		}
	}
	
	/**
	 * 
	 */
	public void youWin(){
		setMode(false);
		Main.winSound.setFramePosition(0);
		Main.winSound.loop(1);
		removeAll();
		add(new LevelCompletedScreen());
		validate();
		repaint();
	}
	
	/**
	 * 
	 */
	public void gameOver(){
		setMode(false);
		Main.aahSound.setFramePosition(0);
		Main.aahSound.loop(1);
		removeAll();
		add(new GameOverScreen());
		validate();
		repaint();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if(mode){
//			g.drawImage(Main.backgroundImage, 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, null);
			matrix.paint(g);
		}
	}
	
	/**
	 * @author Gameboys
	 *
	 */
	class LevelCompletedScreen extends JPanel{

		public int WIDTH = (int)(Constants.WINDOW_WIDTH*(1-Constants.DIVIDER_RATIO));
		public int HEIGHT = Constants.WINDOW_HEIGHT;
		
		/**
		 * 
		 */
		private LevelCompletedScreen(){
			super();
			setLayout(null);
			addLabels();
		}
		
		/**
		 * 
		 */
		private void addLabels(){
			JLabel levelCompleted = new JLabel("Level Completed!");
			levelCompleted.setBounds(0,HEIGHT/4,WIDTH,HEIGHT/10);
			levelCompleted.setHorizontalAlignment(SwingConstants.CENTER);
			levelCompleted.setFont(new Font("Comic Sans MS", Font.BOLD, WIDTH/21));
			add(levelCompleted);
		}
		
		
	}
	
	/**
	 * @author Gameboys
	 *
	 */
	class GameOverScreen extends JPanel{
		
		public int WIDTH = (int)(Constants.WINDOW_WIDTH*(1-Constants.DIVIDER_RATIO));
		public int HEIGHT = Constants.WINDOW_HEIGHT;
		
		/**
		 * 
		 */
		private GameOverScreen(){
			super();
			setLayout(null);
			addButtons();
		}

		/**
		 * 
		 */
		private void addButtons() {
			JButton tryAgain = new JButton("Try again");
			tryAgain.setBounds(WIDTH/2-WIDTH/4,HEIGHT/4,WIDTH/2,HEIGHT/10);
			tryAgain.setFont(new Font("Comic Sans MS", Font.BOLD, WIDTH/42));
			tryAgain.setBorderPainted(false);
			tryAgain.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					JPanel contentPane = (JPanel) ApplicationWindow.getInstance().getContentPane();
					contentPane.removeAll();
					contentPane.add(new GameWindow(new NormalLevel(1)));
					contentPane.validate();
					contentPane.repaint();
				}
			});
			add(tryAgain);
			
		}

//		private void addLabels() {
//			JLabel
//		}
	}
	
	/**
	 * @author Gameboys
	 *
	 */
	class GameMouseListener implements MouseListener{
		
		private int initialLokumXIndex;
		private int initialLokumYIndex;
		private int finalLokumXIndex;
		private int finalLokumYIndex;
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			initialLokumXIndex = e.getX()/Constants.LOKUM_SIZE;
			initialLokumYIndex = e.getY()/Constants.LOKUM_SIZE;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			finalLokumXIndex = e.getX()/Constants.LOKUM_SIZE;
			finalLokumYIndex = e.getY()/Constants.LOKUM_SIZE;
			int dispX = finalLokumXIndex - initialLokumXIndex;
			int dispY = finalLokumYIndex - initialLokumYIndex;
			if(dispX==0 && dispY<0)matrix.swapNorth(initialLokumXIndex, initialLokumYIndex);
			else if(dispX>0 && dispY<0)matrix.swapNorthEast(initialLokumXIndex, initialLokumYIndex);
			else if(dispX>0 && dispY==0)matrix.swapEast(initialLokumXIndex, initialLokumYIndex);
			else if(dispX>0 && dispY>0)matrix.swapSouthEast(initialLokumXIndex, initialLokumYIndex);
			else if(dispX==0 && dispY>0)matrix.swapSouth(initialLokumXIndex, initialLokumYIndex);
			else if(dispX<0 && dispY>0)matrix.swapSouthWest(initialLokumXIndex, initialLokumYIndex);
			else if(dispX<0 && dispY==0)matrix.swapWest(initialLokumXIndex, initialLokumYIndex);
			else if(dispX<0 && dispY<0)matrix.swapNorthWest(initialLokumXIndex, initialLokumYIndex);
			else;
		}
		
	}
	
}
