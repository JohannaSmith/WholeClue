package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;


public class GameAction extends JPanel {
	
	public GameAction(){

		setLayout(new GridLayout(2, 1));

		JButton nextPlayer = new JButton("Next Player");
		add(nextPlayer, BorderLayout.NORTH);
		JButton makeAccusation = new JButton("Make Accusation");
		add(makeAccusation, BorderLayout.SOUTH);
	}

	private LayoutManager GridLayout(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

}
