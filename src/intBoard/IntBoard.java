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
	public void calcAdjacencies() {
		// Init Map
		adjMtx = new HashMap<Integer, ArrayList<Integer>>();
		// Calc Adjacencies
		for(int i = 1; i < COLUMNS; i++) { // iterate columns
			for(int j = 1; j < ROWS; j++) { // iterate rows
				
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
		// Stub
		return new ArrayList<Integer>();
	}
	public int calcIndex(int r, int c) {
		return r * 4 + c;
	}
}