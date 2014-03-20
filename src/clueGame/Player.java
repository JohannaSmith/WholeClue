package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {

	private String myName;
	private BoardCell location;
	private Color myColor;
	ArrayList<Card> myCards = new ArrayList<Card>();
	
	public Player(String myName, BoardCell location, Color myColor) {
		this.myName = myName;
		this.location = location;
		this.myColor = myColor;
	}

	public abstract Card disproveSuggestion(String person, String room, String weapon); 
	
	/*public abstract boolean isHuman();
	public abstract boolean isComputer();*/
	
	public String getMyName() {
		return myName;
	}

	public BoardCell getLocation() {
		return location;
	}

	public Color getMyColor() {
		return myColor;
	}

	public ArrayList<Card> getMyCards() {
		return myCards;
	}
	
}

