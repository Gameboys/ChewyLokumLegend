package gameboys.chewylokumlegend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author O. Kaan Demiröz
 * 
 * @category SINGLETON
 */
@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	private static int WIDTH = Constants.WINDOW_WIDTH;
	private static int HEIGHT = Constants.WINDOW_HEIGHT;
	private static MainMenu instance;
	private JButton levelSelection;
	private JButton resumeGame;
	
	/**
	 * Default constructor, called by the getInstance() method if
	 * it is the first time a MainMenu object is requested.
	 */
	private MainMenu(){
		super();
		setLayout(null);
		addTitle();
		addButtons();
		
		
	}
	
	private void addTitle() {
		JLabel title = new JLabel("Chewy Lokum Legend");
		title.setFont(new Font("Comic Sans MS", Font.BOLD, WIDTH/12));
		title.setForeground(Color.cyan);
		title.setBounds(0,0, WIDTH, HEIGHT/5);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);
		
		JLabel titleShadow = new JLabel(title.getText());
		titleShadow.setFont(title.getFont());
		titleShadow.setForeground(Color.black);
		titleShadow.setBounds(title.getX()+WIDTH/150,title.getY()+WIDTH/150, WIDTH, HEIGHT/5);
		titleShadow.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleShadow);
	}

	/**
	 * 
	 */
	protected void addButtons() {
		JButton newGame = new JButton("Level Select");
		newGame.setBounds(WIDTH/4-WIDTH/8,HEIGHT/4,WIDTH/4,HEIGHT/10);
		newGame.setFont(new Font("Comic Sans MS", Font.BOLD, WIDTH/42));
		newGame.setBorderPainted(false);
		newGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = (JPanel) ApplicationWindow.getInstance().getContentPane();
				contentPane.removeAll();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				contentPane.add(LevelSelectionWindow.getInstance());
				contentPane.validate();
				contentPane.repaint();
			}
		});
		add(newGame);
		
		JButton loadGame = new JButton("Load Game");
		loadGame.setBounds(WIDTH*3/4-WIDTH/8,HEIGHT/4,WIDTH/4,HEIGHT/10);
		loadGame.setFont(new Font("Comic Sans MS", Font.BOLD, WIDTH/42));
		loadGame.setBorderPainted(false);
		loadGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = (JPanel) ApplicationWindow.getInstance().getContentPane();
				contentPane.removeAll();
				contentPane.add(new GameWindow(LevelFactory.getLevel(1)));
				contentPane.validate();
				contentPane.repaint();
			}
		});
//		add(loadGame);
	}
	
	public void paint(Graphics g){
		super.paintChildren(g);
		g.drawImage(Main.t1,WIDTH/10,HEIGHT*6/10, WIDTH/3, HEIGHT/3,this);
		g.drawImage(Main.t2,WIDTH*3/10,HEIGHT*2/10, WIDTH*2/3, HEIGHT*2/3,this);
		super.paintComponents(g);
	}

	/**
	 * @return the MainMenu instance
	 */
	public static MainMenu getInstance(){
		if(instance==null)instance = new MainMenu();
		return instance;
	}
}
