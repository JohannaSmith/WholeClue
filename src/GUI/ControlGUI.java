package GUI;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class ControlGUI extends JFrame{
	
	public ControlGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		setSize(800, 300);	
		
		//create Control Panel First
		WhoseTurnPanel whoseTurn = new WhoseTurnPanel();
		add(whoseTurn, BorderLayout.NORTH);
		GameAction gameAction = new GameAction();
		add(gameAction, BorderLayout.WEST);
		createLayout();	
	}

	private void createLayout() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		ControlGUI controlPanel = new ControlGUI();
		controlPanel.setVisible(true);

	}

}
