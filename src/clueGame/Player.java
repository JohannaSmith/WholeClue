package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Player {
	private ArrayList<Card> myCards = new ArrayList<Card>();

	public abstract Card disproveSuggestion(String person, String room, String weapon);

	public ArrayList<Card> getMyCards() {
		return myCards;
	}

}
