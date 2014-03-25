package clueGame;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class WhoseTurnPanel extends JPanel{
	
	private JTextArea whoseTurn;
	
	public WhoseTurnPanel(){
		
		JLabel label = new JLabel("Whose Turn");
		whoseTurn = new JTextArea(1,15);
		add(label);
		add(whoseTurn);
		
	}

}
