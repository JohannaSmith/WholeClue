package clueGame;

public class RoomCell extends BoardCell{
	
	public RoomCell(DoorDirection doordirection) {
		super();
		this.doordirection = doordirection;
	}

	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE};
	//instance vars
	private DoorDirection doordirection;
	private char roomInitial;
	
	//child methods
	public boolean isRoom() {
		return true;
	}
	public boolean isDoorway() {
		if (doordirection != DoorDirection.NONE)
			return true;
		else return false;
	}
	public DoorDirection getDoorDirection() {
		return doordirection;
	}
	//to do override draw method

}