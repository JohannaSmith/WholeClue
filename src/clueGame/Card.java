package clueGame;

public class Card {
	private CardType myType;
	private String name;
	
	public Card() {
		//don't know if I actually need to write this constructor yet
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


	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
