package clueGame;

public abstract class BoardCell {
	
	//instance vars
	private int cell;
	
	//Methods
	public boolean isWalkway() {
		return false;
	}
	public boolean isRoom() {
		return false;
	}
	public boolean isDoorway() {
		return false;
	}
	//to do draw method
}
