package clueGame;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class DetectiveNotesGUI extends JDialog {
	private JDialog guesses = new JDialog();
	private JPanel peopleList = new JPanel();
	private JCheckBox scarlet, green, plum, mustard, white, peacock;
	
	public DetectiveNotesGUI() {
		setSize(600, 300);
		setLayout(new GridLayout(3, 2));
	}
	
	public void setupPanels() { // read names from file
		scarlet = new JCheckBox("Miss Scarlet");
		green = new JCheckBox("Mr. Green");
		plum = new JCheckBox("Professor Plum");
		mustard = new JCheckBox("Colonel Mustard");
		white = new JCheckBox("Mrs. White");
		peacock = new JCheckBox("Mrs. Peacock");
		
		add(scarlet);
		add(green);
		add(plum);
		add(mustard);
		add(white);
		add(peacock);
	}
	
	
	
}
