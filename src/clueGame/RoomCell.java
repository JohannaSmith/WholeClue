package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class RoomCell extends BoardCell{
	
	private int row, col;
	
	public RoomCell(Character roomInitial, DoorDirection doordirection) { //kept this method solely for the tests
		super();
		this.roomInitial = roomInitial;
		this.doordirection = doordirection;
	}
	
	public RoomCell(Character roomInitial, DoorDirection doordirection, int row, int col) {
		super();
		this.roomInitial = roomInitial;
		this.doordirection = doordirection;
		this.row = row;
		this.col = col;
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
	public char getInitial() {
		return roomInitial;
	}
	
	//to do override draw method
	@Override
	public void draw(Graphics g, Board board) {
		int startRow = row*board.CELL_SIDE;
		int startCol = col*board.CELL_SIDE;
		
		
	}

}