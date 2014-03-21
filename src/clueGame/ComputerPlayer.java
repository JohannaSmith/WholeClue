package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ComputerPlayer extends Player{
	private char lastRoomVisited;
	private ArrayList<Card> seenList;
	public ComputerPlayer(String myName, BoardCell location, Color myColor) {
		super(myName, location, myColor);
		seenList = new ArrayList<Card>();
	}
	
	public void pickLocation(Set<BoardCell> targets) {
	
	}
	
	public Suggestion createSuggestion(String room, ArrayList<Card> deck) { 
		
		
		
		
		return new Suggestion("c", "b", room);
	}
	public void updateSeen(Card seen) {
		seenList.add(seen);		
	}
}
