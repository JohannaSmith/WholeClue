package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class ComputerPlayer extends Player{
	private char lastRoomVisited;
	public ComputerPlayer(String myName, BoardCell location, Color myColor) {
		super(myName, location, myColor);
	}
	
	public void pickLocation(Set<BoardCell> targets) {
	
	}
	public void createSuggestion() {
		
	}
	public void updateSeen(Card seen) {
		
	}
}
