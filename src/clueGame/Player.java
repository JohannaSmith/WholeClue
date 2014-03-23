package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

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
		
		//Alternate Algorithm

		ArrayList<Card> couldDisprove = new ArrayList<Card>();
		
		Card disprove0 = new Card(person, CardType.PERSON);
		Card disprove1 = new Card(room, CardType.ROOM);
		Card disprove2 = new Card(weapon, CardType.WEAPON);
		
		for(int i = 0; i < myCards.size(); i++){
			if(myCards.get(i).equals(disprove0))
				couldDisprove.add(disprove0);
			else if(myCards.get(i).equals(disprove1))
				couldDisprove.add(disprove1);
			else if(myCards.get(i).equals(disprove2))
				couldDisprove.add(disprove2);
		}
		/*
		Card disprove0 = new Card(person, CardType.PERSON);
		if(myCards.contains(disprove0))
			couldDisprove.add(disprove0);
		Card disprove1 = new Card(room, CardType.ROOM);
		if(myCards.contains(disprove1))
			couldDisprove.add(disprove1);
		Card disprove2 = new Card(weapon, CardType.WEAPON);
		if(myCards.contains(disprove2))
			couldDisprove.add(disprove2);*/
		
		if(couldDisprove.size() != 0) {
			Collections.shuffle(couldDisprove);
			return couldDisprove.get(0);
		}
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

