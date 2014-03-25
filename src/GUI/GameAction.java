package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;


public class GameAction extends JPanel {
	
	public GameAction(){
		
		JButton nextPlayer = new JButton("Next Player");
		add(nextPlayer);
		JButton makeAccusation = new JButton("Make Accusation");
		add(makeAccusation);
	}

}
