package clueGame;

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
		// TODO Auto-generated method stub
		
	}

}
