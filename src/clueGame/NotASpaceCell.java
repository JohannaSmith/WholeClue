package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class NotASpaceCell extends BoardCell {

	private int row, col;
	public NotASpaceCell(int row, int col){
		this.row = row;
		this.col = col;
	}
	@Override
	public void draw(Graphics g, Board board) {
		int startRow = row*board.CELL_SIDE;
		int startCol = col*board.CELL_SIDE;
		g.setColor(Color.BLACK);
		g.fillRect(startRow, startCol, board.CELL_SIDE, board.CELL_SIDE);
		
	}

	public void drawName(Graphics g, Board board){}
}