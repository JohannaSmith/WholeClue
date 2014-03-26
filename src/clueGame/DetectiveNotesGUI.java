package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotesGUI extends JDialog {
	private JDialog guesses = new JDialog();
	private JPanel peopleList = new JPanel();
	private JCheckBox scarlet, green, plum, mustard, white, peacock;
	private JPanel weaponsList = new JPanel();
	private JCheckBox candle, pipe, rope, knife, revolver, wrench;
	
	public DetectiveNotesGUI() {
		setSize(600, 300);
		setupPanels();
		add(weaponsList);
	}
	
	public void setupPanels() { // read names from file
		scarlet = new JCheckBox("Miss Scarlet");
		green = new JCheckBox("Mr. Green");
		plum = new JCheckBox("Professor Plum");
		mustard = new JCheckBox("Colonel Mustard");
		white = new JCheckBox("Mrs. White");
		peacock = new JCheckBox("Mrs. Peacock");
		candle = new JCheckBox("Candlestick");
		pipe = new JCheckBox("Lead Pipe");
		rope = new JCheckBox("Rope");
		knife = new JCheckBox("Knife");
		revolver = new JCheckBox("Revolver");
		wrench = new JCheckBox("Wrench");
		weaponsList.setLayout(new GridLayout(3, 2));
		weaponsList.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		weaponsList.add(candle);
		weaponsList.add(pipe);
		weaponsList.add(rope);
		weaponsList.add(knife);
		weaponsList.add(revolver);
		weaponsList.add(knife);
		
	}
	
	public static void main(String[] args) {
		DetectiveNotesGUI ourNotes = new DetectiveNotesGUI();
		ourNotes.setVisible(true);
	}
	
	
	
}
