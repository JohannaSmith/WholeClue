package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ComputerPlayer extends Player{
	private char lastRoomVisited;
	public ComputerPlayer(String myName, BoardCell location, Color myColor) {
		super(myName, location, myColor);
	}
	
	public void pickLocation(Set<BoardCell> targets) {
	
	}
	
	public Suggestion createSuggestion(String room, ArrayList<Card> deck) { 
		
		ArrayList<Card> possibleSuggestions = new ArrayList<Card>();
		for(Card card: deck){
			if(!seenList.contains(card))
				possibleSuggestions.add(card);
		}
		
		ArrayList<Card> possiblePersons = new ArrayList<Card>();
		ArrayList<Card> possibleWeapons = new ArrayList<Card>();
		
		for(Card card: possibleSuggestions){
			if(card.getMyType() == CardType.PERSON)
				possiblePersons.add(card);
			if(card.getMyType() == CardType.WEAPON)
				possibleWeapons.add(card);
		}
		
		Collections.shuffle(possiblePersons);
		Collections.shuffle(possibleWeapons);
		String person = possiblePersons.get(0).getName();
		String weapon = possibleWeapons.get(0).getName();
		
		
		return new Suggestion(person, weapon, room);
	}
	
	public void updateSeen(Card seen) {
		seenList.add(seen);		
	}
}
