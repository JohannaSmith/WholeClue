package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {

	//protected because it is more efficient to have both computerPlayers and HumanPlayers inherit these attributes
	protected String myName;
	protected BoardCell location;
	protected Color myColor;
	protected ArrayList<Card> myCards = new ArrayList<Card>();
	protected ArrayList<Card> seenList = new ArrayList<Card>(); 
	
	public Player(String myName, BoardCell location, Color myColor) {
		this.myName = myName;
		this.location = location;
		this.myColor = myColor;
	}

	public Card disproveSuggestion(String person, String room, String weapon){
		Card disprove = new Card(person, CardType.PERSON);
		if(myCards.contains(disprove))
			return disprove;
		disprove = new Card(room, CardType.ROOM);
		if(myCards.contains(disprove))
			return disprove;
		disprove = new Card(weapon, CardType.WEAPON);
		if(myCards.contains(disprove))
			return disprove;
		else
			return null;
		
	}
	
	public void updateSeen(Card seen) {
		seenList.add(seen);		
	}

	
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

