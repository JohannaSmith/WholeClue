package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GuessPanel extends JPanel {

	private JTextArea guess;
	public GuessPanel(){
		setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		guess = new JTextArea(1,5);
		guess.setEditable(false);
		JLabel label = new JLabel("Guess");
		add(label);
		add(guess);
		
	}
}
