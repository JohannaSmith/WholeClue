package clueGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ClueGameGUI extends JFrame {

	private Board board;
	public ClueGameGUI(){
		Game game = new Game();
		game.loadConfigFiles();
		Board board = game.getGameBoard();
		board.loadConfigFiles();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		setSize(1000, 750);
		add(board, BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		
		ClueGameGUI clueGame = new ClueGameGUI();
		clueGame.setVisible(true);

	}

}
