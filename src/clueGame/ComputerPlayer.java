package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class ComputerPlayer extends Player{
	private char lastRoomVisited;
	private String myName;
	private BoardCell location;
	private Color myColor;
	public ComputerPlayer(String myName, BoardCell location, Color myColor) {
		super();
		this.myName = myName;
		this.location = location;
		this.myColor = myColor;
	}
	
	public void pickLocation(Set<BoardCell> targets) {
	
	}
	public void createSuggestion() {
		
	}
	public void updateSeen(Card seen) {
		
	}

	@Override
	public Card disproveSuggestion(String person, String room, String weapon) {
		return new Card("bob", CardType.PERSON);
	}

}
