package intBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	// Instance Vars
	private Map<Integer, ArrayList<Integer>> adjMtx;
	private boolean[] visited;
	private static final int COLUMNS = 4;
	private static final int ROWS = 4;
	
	// Constructor
	public IntBoard() {
		super();
	}
	// Methods
	private void calcAdjacencies() {
		// Init Map
		adjMtx = new HashMap<Integer, ArrayList<Integer>>();
		// Calc Adjacencies
		for(int i = 0; i < COLUMNS; i++) { // iterate columns
			for(int j = 0; j < ROWS; j++) { // iterate rows
				int index = calcIndex(j, i);
				ArrayList<Integer> adjList = new ArrayList<Integer>();
				if (j > 0) 
					adjList.add(calcIndex(j - 1, i));	
				if (j < ROWS)
					adjList.add(calcIndex(j + 1, i));
				if (i > 0) 
					adjList.add(calcIndex(j, i - 1));	
				if (i < COLUMNS)
					adjList.add(calcIndex(j, i + 1));
				adjMtx.put(index, adjList);
			}
		}
	}
	public void startTargets(int cell, int steps) { // Calculate
		// Stub
	}
	public Set<Integer> getTargets() { // Return
		// Stub
		return new HashSet<Integer>();
	}
	public ArrayList<Integer> getAdjList(int cell) {
		calcAdjacencies();
		return adjMtx.get(cell);
	}
	public int calcIndex(int r, int c) {
		return r * 4 + c;
	}
}