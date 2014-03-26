package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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
	public boolean equals(BoardCell cell) {
		if (this == cell)
			return true;
		if (cell == null)
			return false;
		if (getClass() != cell.getClass())
			return false;
		WalkwayCell other = (WalkwayCell) cell;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public void draw(Graphics g, Board board) {
		int startRow = row*board.CELL_SIDE;
		int startCol = col*board.CELL_SIDE;
		g.setColor(Color.YELLOW);
		g.fillRect(startRow, startCol, board.CELL_SIDE, board.CELL_SIDE);
		g.setColor(Color.BLACK);
		g.drawRect(startRow, startCol, board.CELL_SIDE, board.CELL_SIDE);
		
		ArrayList<Player> players = board.getGame().getPlayers();
		for(Player p: players){
			if(p.getLocation().equals(this)){
				Color color = p.getMyColor();
				g.setColor(color);
				g.fillOval(startRow, startCol, board.CELL_SIDE, board.CELL_SIDE);
				g.setColor(Color.BLACK);
				g.drawOval(startRow, startCol, board.CELL_SIDE, board.CELL_SIDE);
			}
		}
		
	}
	
	public void drawName(Graphics g, Board board){}

}
