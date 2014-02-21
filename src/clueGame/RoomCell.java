package clueGame;

public class RoomCell extends BoardCell{
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE};
	//instance vars
	private DoorDirection doordirection;
	private char roomInitial;
	
	//child methods
	public boolean isRoom() {
		return true;
	}
	//to do override draw method

}