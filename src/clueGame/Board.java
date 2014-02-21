package clueGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Board {
	// Instance Vars
	ArrayList<BoardCell> cells;
	Map<Character,String> rooms;
	int numRows;
	int numColumns;
	// Filenames
	private static final String LEGENDFILE = "legend.csv";
	private static final String BOARDLAYOUTFILE = "boardlayout.csv";
	// Constructor
	public Board() {
		super();
		// Stub
	}
	// Load Board
	public void loadConfigFiles() {
		loadLegend(new File(LEGENDFILE));
		loadBoard(new File(BOARDLAYOUTFILE));
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
}