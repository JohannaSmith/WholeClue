package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class ControlGUI extends JFrame{
	
	public ControlGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		setSize(800, 300);	
		setLayout(new GridLayout(2,3));
		
		
		//create Control Panel First
		WhoseTurnPanel whoseTurn = new WhoseTurnPanel();
		add(whoseTurn);
		GameAction gameAction = new GameAction();
		add(gameAction);
		DiePanel dieP = new DiePanel();
		add(dieP);
		GuessPanel guessP = new GuessPanel();
		add(guessP);
		GuessResultPanel guessResP = new GuessResultPanel();
		add(guessResP);
		
	}

	public static void main(String[] args) {
		ControlGUI controlPanel = new ControlGUI();
		controlPanel.setVisible(true);

	}

}
