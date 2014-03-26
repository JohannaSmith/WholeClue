package clueGame;

import java.awt.Graphics;

public abstract class BoardCell {
	
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
	
	public abstract void draw(Graphics g, Board board);
	public abstract void drawName(Graphics g, Board board);
	public boolean equals(BoardCell cell) {
		// TODO Auto-generated method stub
		return false;
	}
	//to do draw method
}
