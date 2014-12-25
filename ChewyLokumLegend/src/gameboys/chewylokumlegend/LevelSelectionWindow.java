package gameboys.chewylokumlegend;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
		setLayout(new FlowLayout());
		addLevels();
	}
	
	/**
	 * 
	 */
	private void addLevels() {
		for(int i=1; i<=LevelFactory.numLevels; i++){
			final int levelNum = i;
			JButton levelButton = new JButton(levelNum+"");
			levelButton.setFont(new Font("Comic Sans MS",Font.BOLD,Constants.WINDOW_WIDTH/10));
			levelButton.setPreferredSize(new Dimension(levelButton.getPreferredSize().width*2,levelButton.getPreferredSize().height));
			levelButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					JPanel contentPane = (JPanel) ApplicationWindow.getInstance().getContentPane();
					contentPane.removeAll();
					contentPane.add(new GameWindow(LevelFactory.getLevel(levelNum)));
					contentPane.validate();
					contentPane.repaint();
				}
			});
			add(levelButton);
		}
		
	}

	/**
	 * @return
	 */
	public static LevelSelectionWindow getInstance(){
		if(instance==null) instance = new LevelSelectionWindow();
		return instance;
	}
	
}
