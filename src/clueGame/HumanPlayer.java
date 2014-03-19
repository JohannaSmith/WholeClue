package clueGame;

import java.awt.Color;

public class HumanPlayer extends Player{
	private String myName;
	private BoardCell location;
	private Color myColor;
	public HumanPlayer(String myName, BoardCell location, Color myColor) {
		super();
		this.myName = myName;
		this.location = location;
		this.myColor = myColor;
	}

	@Override
	public Card disproveSuggestion(String person, String room, String weapon) {
		return new Card("bob", CardType.PERSON);
	}
}
