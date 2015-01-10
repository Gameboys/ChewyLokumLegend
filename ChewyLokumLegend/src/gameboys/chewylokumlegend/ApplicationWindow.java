package gameboys.chewylokumlegend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Gameboys
 * 
 * @category SINGLETON
 */
@SuppressWarnings("serial")
public class ApplicationWindow extends JFrame {

	private static ApplicationWindow instance;

	/**
	 * 
	 */
	private ApplicationWindow(){
		super("Gameboys Present: Chewy Lokum Legend");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		int screenWidth = (int)Constants.SCREEN_SIZE.getWidth();
		int screenHeight = (int)Constants.SCREEN_SIZE.getHeight();
		int windowWidth = Constants.WINDOW_WIDTH;
		int windowHeight = Constants.WINDOW_HEIGHT;

		setLocation((screenWidth-windowWidth)/2, (screenHeight-windowHeight)/2-Constants.HEIGHT_TASKBAR);
		setPreferredSize(new Dimension(windowWidth,windowHeight));

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(MainMenu.getInstance(),BorderLayout.CENTER);
		setContentPane(contentPane);
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we) {
				if(GameWindow.gameBoard!=null){
					String ObjButtons[] = {"Yes","No"};
					int PromptResult = JOptionPane.showOptionDialog(null, 
							"Do you wish to save your progress?", "Save Game State?", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
							ObjButtons,ObjButtons[0]);
					if(PromptResult==0){
						SavedState save = new SavedState();
						System.exit(0);
					}else System.exit(0);

				}else System.exit(0);
			}
		});
	}

	/**
	 * @return the ApplicationWindow instance
	 */
	public static ApplicationWindow getInstance(){
		if(instance==null)instance = new ApplicationWindow();
		return instance;
	}

}
