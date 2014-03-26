package clueGame;

import javax.swing.JFrame;

public class ClueGameGUI extends JFrame {

	public ClueGameGUI(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		setSize(1000, 750);
	}
	public static void main(String[] args) {
		
		ClueGameGUI clueGame = new ClueGameGUI();
		clueGame.setVisible(true);

	}

}
