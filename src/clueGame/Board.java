package clueGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	// Instance Vars
	LinkedList<BoardCell> cells;
	Map<Character,String> rooms;
	private int numRows;
	private int numColumns;
	private boolean numRowsSet;
	private boolean numColumnsSet;
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private Set<BoardCell> targets;
	boolean[] visited;
	// Filenames
	private String legendfile;
	private String boardlayoutfile;
	// Legend Reserved Words
	public static final String WALKWAY_WORD = "Walkway";
	public static final String ROOM_ENDING_WORD = "room";
	// Constructor
	public Board() {
		super();
		legendfile = "legend.csv";
		boardlayoutfile = "boardlayout.csv";
		cells = new LinkedList<BoardCell>();
		rooms = new HashMap<Character, String>();
		numRowsSet = false;
		numColumnsSet = false;
	}
	public Board(String legendfilename, String boardlayoutfilename) {
		legendfile = legendfilename;
		boardlayoutfile = boardlayoutfilename;
		cells = new LinkedList<BoardCell>();
		rooms = new HashMap<Character, String>();
		numRowsSet = false;
		numColumnsSet = false;
	}
	// Load Board
	public void loadConfigFiles() {
		try {
			loadLegend();
			loadBoard();
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch(BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	public void loadLegend() throws FileNotFoundException, BadConfigFormatException {
		File f = new File(legendfile);
		Scanner in = new Scanner(new BufferedReader(new FileReader(f)));
		String line = in.nextLine();
		String partone = line.substring(0, line.indexOf(","));
		String parttwo = line.substring(line.indexOf(","));
		if(partone.contains(",") || parttwo.contains(",")) {
			in.close();
			throw new BadConfigFormatException('l');
		}
		else {
			rooms.put(partone.toCharArray()[0], parttwo);
			in.close();
		}
	}
	public void loadBoard() throws BadConfigFormatException, FileNotFoundException {
		File f = new File(boardlayoutfile);
		Scanner in = new Scanner(new BufferedReader(new FileReader(f)));
		do {
			String line = in.nextLine();
			if(line.equals(""))
				break;
			String[] arr = line.split(",");
			if(!numColumnsSet) {
				numColumns = arr.length; //maybe + 1 to clear up the 0 index
				numColumnsSet = true;
			}
			else if(arr.length != numColumns) {
				throw new BadConfigFormatException('c');
			}
			for(String s : arr) {
				if(rooms.get(s).toLowerCase().equals(WALKWAY_WORD.toLowerCase())) { // Empty Cell
					cells.add(new WalkwayCell());
				}
				else if(rooms.get(s).toLowerCase().endsWith(ROOM_ENDING_WORD.toLowerCase())) { // Room
					Character initial = s.toCharArray()[0];										//Not all of the rooms end in room
					RoomCell.DoorDirection direction;
					if(s.length() == 1) {
						direction = RoomCell.DoorDirection.NONE;
					}
					else {
						switch(s.toCharArray()[1]) {
						case 'R':
							direction = RoomCell.DoorDirection.RIGHT;
							break;
						case 'L':
							direction = RoomCell.DoorDirection.LEFT;
							break;
						case 'U':
							direction = RoomCell.DoorDirection.UP;
							break;
						case 'D':
							direction = RoomCell.DoorDirection.DOWN;
							break;
						default:
							throw new BadConfigFormatException('s');
						}
					}
					cells.add(new RoomCell(initial, direction));
				}
				else { // Problem!!
					throw new BadConfigFormatException('s');
				}
			}
		} while(in.hasNextLine());
		numRows = cells.size() % numColumns; //couldn't this just be numRows += 1 since numRows could potentially be larger than numColumns
		numRowsSet = true;
		visited = new boolean[getBoardSize()];
		in.close();
	}
	// Calculate Index
	public int calcIndex(int r, int c) {
		return r * numColumns + c;
	}
	// Calculate Adjacencies / Paths
	public void calcAdjacencies() {
		// Init Map
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		// Calc Adjacencies
		for(int i = 0; i < numColumns; i++) { // iterate columns
			for(int j = 0; j < numRows; j++) { // iterate rows
				int index = calcIndex(j, i);
				LinkedList<Integer> adjList = new LinkedList<Integer>();
				int index2;
				if (j > 0) { // Top
					index2 = calcIndex(j - 1, i);
					if(cells.get(index2).isWalkway()) {
						adjList.add(index2);
					}
					else if(cells.get(index2).isDoorway()) {
						RoomCell c = (RoomCell) cells.get(index2);
						if(c.getDoorDirection() == RoomCell.DoorDirection.DOWN) {
							adjList.add(index2);
						}
					}
				}
				if (j < numRows - 1) { // Bottom
					index2 = calcIndex(j + 1, i);
					if(cells.get(index2).isWalkway()) {
						adjList.add(index2);
					}
					else if(cells.get(index2).isDoorway()) {
						RoomCell c = (RoomCell) cells.get(index2);
						if(c.getDoorDirection() == RoomCell.DoorDirection.UP) {
							adjList.add(index2);
						}
					}
				}
				if (i > 0) { // Left
					index2 = calcIndex(j, i - 1);
					if(cells.get(index2).isWalkway()) {
						adjList.add(index2);
					}
					else if(cells.get(index2).isDoorway()) {
						RoomCell c = (RoomCell) cells.get(index2);
						if(c.getDoorDirection() == RoomCell.DoorDirection.RIGHT) {
							adjList.add(index2);
						}
					}
				}
				if (i < numColumns - 1) { // Right
					index2 = calcIndex(j, i + 1);
					if(cells.get(index2).isWalkway()) {
						adjList.add(index2);
					}
					else if(cells.get(index2).isDoorway()) {
						RoomCell c = (RoomCell) cells.get(index2);
						if(c.getDoorDirection() == RoomCell.DoorDirection.LEFT) {
							adjList.add(index2);
						}
					}
					adjMtx.put(index, adjList);
				}
			}
		}
	}
	public void calcTargets(int row, int column, int steps) {
		calcTargets(calcIndex(row, column), steps);
	}
	public void calcTargets(int cell, int steps) { // Calculate
		targets = new HashSet<BoardCell>();
		visited[cell] = true;
		calcTargets(cell, steps); //infinite recursion???
		visited[cell] = false;
	}
	private void calcTargetsRecurse(int cell, int steps) {
		steps--;
		ArrayList<Integer> adjacentCells = new ArrayList<Integer>();
		for(int c : adjMtx.get(cell))
			if(!visited[c])
				adjacentCells.add(c);
		for(int adjCell : adjacentCells) {
			visited[adjCell] = true;
			if(steps == 0)
				targets.add(cells.get(adjCell));
			else
				calcTargets(adjCell, steps);
			visited[adjCell] = false;
		}
	}
	// Getters
	public Set<BoardCell> getTargets() { // Return
		return targets;
	}
	public LinkedList<Integer> getAdjList(int cell) {
		return adjMtx.get(cell);
	}
	public BoardCell getCellAt(int i) {
		return cells.get(i);
	}
	public BoardCell getCellAt(int r, int c) {
		return getCellAt(calcIndex(r, c));
	}
	public RoomCell getRoomCellAt(int i) {
		return (RoomCell) cells.get(i);
	}
	public RoomCell getRoomCellAt(int r, int c) {
		return (RoomCell) getCellAt(calcIndex(r, c));
	}
	public Map<Character, String> getRooms() {
		return rooms;
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
	public int getBoardSize() {
		return numColumns * numRows;
	}
}