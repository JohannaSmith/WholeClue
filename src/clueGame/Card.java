package clueGame;

public class Card {
	private CardType myType;
	private String myName;
	
	public Card(String myName, CardType myType) {
		this.myName = myName;
		this.myType = myType;
	}
	
	//TODO equals method
	
	//getters and setters for testing purposes below
	
	public CardType getMyType() {
		//return myType;
		return CardType.PERSON; //stub
	}


	public void setMyType(CardType myType) {
		this.myType = myType;
	}


	public String getName() {
		//return name;
		return ""; //null stub
	}


	public void setName(String myName) {
		this.myName = myName;
	}


}
