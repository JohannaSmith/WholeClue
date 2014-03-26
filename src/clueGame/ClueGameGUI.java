package clueGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClueGameGUI extends JFrame {

	private Board board;
	private JMenuBar bar;
	private JMenu file;
	private JMenuItem notes, exit;
	private DetectiveNotesGUI ourNotes;
	
	public ClueGameGUI(){
		board = new Board();
		board.loadConfigFiles();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		setSize(1000, 750);
		add(board, BorderLayout.CENTER);
		createMenu();
	}
	
	public void createMenu() {
		bar = new JMenuBar();
		file = new JMenu("File");
		bar.add(file);
		notes = new JMenuItem("Show Detective Notes");
		exit = new JMenuItem("Exit");
		ourNotes = new DetectiveNotesGUI();
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		notes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ourNotes.setVisible(true);
			}
		});
		file.add(notes);
		file.add(exit);
		setJMenuBar(bar);
		
	}
	
	public static void main(String[] args) {
		ClueGameGUI clueGame = new ClueGameGUI();
		clueGame.setVisible(true);
	}

}
