package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GuessResultPanel extends JPanel {

	private JTextArea guessResult;
	public GuessResultPanel(){
		setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		
		guessResult = new JTextArea(1,5);
		guessResult.setEditable(false);
		JLabel label = new JLabel("Guess Result");
		add(label);
		add(guessResult);
		
	}
}
