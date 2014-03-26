package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.JComboBox;
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
	private JCheckBox kitchen, conservatory, ballroom, hall, library, dining, billiards, study, lounge;
	private JComboBox roomGuess;

	public DetectiveNotesGUI() {

		setSize(600, 300);
		setupPanels();
	}

	public void setupPanels() { // read names from file
		setTitle("Detective Notes");
		RoomsPanel rooms = new RoomsPanel();
		add(rooms);
		RoomGuessPanel rGuess = new RoomGuessPanel();
		add(rGuess);
		peoplePanel people = new peoplePanel();
		add(people);
		weaponsPanel w = new weaponsPanel();
		add(w);
	}

	public class RoomsPanel extends JPanel{

		public RoomsPanel(){
			//rooms
			kitchen = new JCheckBox("Kitchen");
			conservatory = new JCheckBox("Conservatory");
			ballroom = new JCheckBox("Ballroom");
			hall = new JCheckBox("Hall");
			library = new JCheckBox("Library");
			dining = new JCheckBox("Dining Room");
			billiards = new JCheckBox("Billiards Room");
			study = new JCheckBox("Study");
			lounge = new JCheckBox("Lounge");
			
			setLayout(new GridLayout(5,2));
			setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
			
			this.add(kitchen);
			this.add(conservatory);
			this.add(ballroom);
			this.add(hall);
			this.add(library);
			this.add(dining);
			this.add(billiards);
			this.add(study);
			this.add(lounge);

		}
	}

	public class RoomGuessPanel extends JPanel{
		private JComboBox roomGuess;
		public RoomGuessPanel(){

			roomGuess = new JComboBox();
			setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
			roomGuess.addItem("Kitchen");
			roomGuess.addItem("Conservatory");
			roomGuess.addItem("Ballroom");
			roomGuess.addItem("Hall");
			roomGuess.addItem("Library");
			roomGuess.addItem("Dining Room");
			roomGuess.addItem("Billiards Room");
			roomGuess.addItem("Study");
			roomGuess.addItem("Lounge");
			roomGuess.addItem("Unsure");
			setLayout(new GridLayout(0,1));
			this.add(roomGuess);

		}
	}


	public class weaponsPanel extends JPanel{
		public weaponsPanel(){
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

	}
	public class peoplePanel extends JPanel{

		public peoplePanel(){
			scarlet = new JCheckBox("Miss Scarlet");
			green = new JCheckBox("Mr. Green");
			plum = new JCheckBox("Professor Plum");
			mustard = new JCheckBox("Colonel Mustard");
			white = new JCheckBox("Mrs. White");
			peacock = new JCheckBox("Mrs. Peacock");
			setLayout(new GridLayout(5,2));
			setBorder(new TitledBorder (new EtchedBorder(), "People"));
			this.add(scarlet);
			this.add(green);
			this.add(plum);
			this.add(mustard);
			this.add(white);
			this.add(peacock);
		}
	}
	
	public void createLayout(){
		setSize(600, 300);
		setLayout(new GridLayout(0, 2));
		setTitle("Detective Notes");
		RoomsPanel rooms = new RoomsPanel();
		add(rooms);
		RoomGuessPanel rGuess = new RoomGuessPanel();
		add(rGuess);
	}
	public static void main(String[] args) {
		DetectiveNotesGUI ourNotes = new DetectiveNotesGUI();
		ourNotes.setVisible(true);
	}

}

