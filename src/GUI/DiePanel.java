package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DiePanel extends JPanel {

	private JTextArea roll;
	public DiePanel(){
		
		setBorder(new TitledBorder (new EtchedBorder(), "Die"));
		roll = new JTextArea(1,2);
		roll.setEditable(false);
		JLabel label = new JLabel("Roll");		
		add(label);
		add(roll);
	}
}
