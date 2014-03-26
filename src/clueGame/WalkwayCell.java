package clueGame;

import java.awt.Color;
import java.awt.Graphics;

import clueGame.RoomCell.DoorDirection;

public class WalkwayCell extends BoardCell{

	private int row, col;
	
	public WalkwayCell(){}
	
	public WalkwayCell(int row, int col){
		super();
		this.row = row;
		this.col = col;
	}
	//child methods
	public boolean isWalkway() {
		return true;
	}
	//to do override draw method

	@Override
	public void draw(Graphics g, Board board) {
		int startRow = row*board.CELL_SIDE;
		int startCol = col*board.CELL_SIDE;
		g.setColor(Color.YELLOW);
		g.fillRect(startRow, startCol, board.CELL_SIDE, board.CELL_SIDE);
		g.setColor(Color.BLACK);
		g.drawRect(startRow, startCol, board.CELL_SIDE, board.CELL_SIDE);
		
	}

}
