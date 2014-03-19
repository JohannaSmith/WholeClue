package clueGame;

import java.awt.Color;

public class HumanPlayer extends Player{
	public HumanPlayer(String myName, BoardCell location, Color myColor) {
		super(myName, location, myColor);
		
	}

	@Override
	public Card disproveSuggestion(String person, String room, String weapon) {
		return new Card("bob", CardType.PERSON);
	}
	
	/*public boolean isHuman(){
		return true;
	}
	public boolean isComputer(){
		return false;
	}*/
}
