package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
	private char lastRoomVisited;
	private BoardCell destination;
	private ArrayList<Card> seenList;



	//For Multiple Players disprove suggestion test (Test Purposes Only)
	public ComputerPlayer(){}

	public ComputerPlayer(String myName, BoardCell location, Color myColor) {
		super(myName, location, myColor);
		seenList = new ArrayList<Card>();
		if(location.isRoom()){
			lastRoomVisited = ((RoomCell) location).getInitial();
		}
		else
			lastRoomVisited = 'X';

	}

	public BoardCell pickLocation(Set<BoardCell> targets) {
		// destination determined
		for (BoardCell b: targets) {
			if (b.isRoom()) {
				if (((RoomCell) b).getInitial() != lastRoomVisited) { // checks to see if it was not already visited
					destination = b;
					break;
				}
				else { // gets the random target
					int item = new Random().nextInt(targets.size()); 
					int i = 0;
					for(BoardCell bc : targets)
					{
						if (i == item)
							destination = bc;
						i++;
					}
				}
			}
			else { // gets the random target
				int item = new Random().nextInt(targets.size()); 
				int i = 0;
				for(BoardCell bc : targets)
				{
					if (i == item)
						destination = bc;
					i++;
				}
			}
		}
		return destination;
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
