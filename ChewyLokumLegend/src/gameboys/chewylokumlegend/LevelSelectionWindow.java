package gameboys.chewylokumlegend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Gameboys
 *
 */
@SuppressWarnings("serial")
public class LevelSelectionWindow extends JPanel {
	
	/**
	 * 
	 */
	private static LevelSelectionWindow instance;

	/**
	 * 
	 */
	private LevelSelectionWindow(){
		super();
		setLayout(new BorderLayout());
		addLevels();
	}
	
	/**
	 * 
	 */
	private void addLevels() {
		JButton one = new JButton("1");
		one.setFont(new Font("Comic Sans MS", Font.BOLD, Constants.WINDOW_WIDTH/2));
		one.setBorderPainted(false);
		one.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = (JPanel) ApplicationWindow.getInstance().getContentPane();
				contentPane.removeAll();
				contentPane.add(new GameWindow(new Level(1)));
				contentPane.validate();
				contentPane.repaint();
			}
		});
		add(one,BorderLayout.CENTER);
		
	}

	/**
	 * @return
	 */
	public static LevelSelectionWindow getInstance(){
		if(instance==null) instance = new LevelSelectionWindow();
		return instance;
	}
	
}
