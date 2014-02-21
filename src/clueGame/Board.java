package clueGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Board {
	// Instance Vars
	ArrayList<BoardCell> cells;
	Map<Character,String> rooms;
	private int numRows;
	private int numColumns;
	// Filenames
	private String legendfile;
	private String boardlayoutfile;
	// Constructor
	public Board() {
		super();
		legendfile = "legend.csv";
		boardlayoutfile = "boardlayout.csv";
	}
	public Board(String legendfilename, String boardlayoutfilename) {
		legendfile = legendfilename;
		boardlayoutfile = boardlayoutfilename;
	}
	// Load Board
	public void loadConfigFiles() {
		loadLegend(new File(legendfile));
		loadBoard(new File(boardlayoutfile));
	}
	private void loadLegend(File f) {
		// Stub
	}
	private void loadBoard(File f) {
		// Stub
	}
	// Calculate Index
	public int calcIndex(int r, int c) {
		// Stub
		return -1;
	}
	// Getters
	public BoardCell getCell(int i) {
		return cells.get(i);
	}
	public BoardCell getCell(int r, int c) {
		return getCell(calcIndex(r, c));
	}
	public String getRoom(char c) {
		return rooms.get(c);
	}
	public int getRoomCount() {
		return rooms.keySet().size();
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
}