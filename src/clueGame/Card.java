package clueGame;

public class Card {
	private CardType myType;
	private String myName;
	
	public Card(String myName, CardType myType) {
		this.myName = myName;
		this.myType = myType;
	}
	
	public boolean equals(Card c){ //equals method compares each attribute
		if(this.myName.equals(c.getName()) && this.myType.equals(c.getMyType()))
			return true;
		return false;
	}
	
	//getters and setters for testing purposes below
	
	public CardType getMyType() {
		return myType; 
	}


	public void setMyType(CardType myType) {
		this.myType = myType;
	}


	public String getName() {
		return myName; 
	}


	public void setName(String myName) {
		this.myName = myName;
	}


}
