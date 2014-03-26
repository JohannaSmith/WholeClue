package clueGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Set;

public class RoomCell extends BoardCell{
	
	private int row, col;
	private final int DOOR_BOLD = 5;
	
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
		g.setColor(Color.GRAY);
		g.fillRect(startRow, startCol, board.CELL_SIDE, board.CELL_SIDE);
		
		if(isDoorway()){
			g.setColor(Color.BLUE);
			switch(doordirection) {
			case NONE:
				break;
			case LEFT:
				g.fillRect(startRow, startCol, DOOR_BOLD, board.CELL_SIDE);
				break;
			case RIGHT:
				g.fillRect((startRow + board.CELL_SIDE - DOOR_BOLD), startCol, DOOR_BOLD, board.CELL_SIDE);
				break;
			case DOWN:
				g.fillRect(startRow, (startCol+ board.CELL_SIDE - DOOR_BOLD), board.CELL_SIDE, DOOR_BOLD);
				break;
			case UP:
				g.fillRect(startRow, startCol, board.CELL_SIDE, DOOR_BOLD);
				break;
			}
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 
		for (Character c: board.getKeys()) {
			if(c.equals(roomInitial)) {
				g.drawString(board.getRooms().get(c), startRow, startCol);
				board.getKeys().remove(c);
				break;
			}
		}
	}

}