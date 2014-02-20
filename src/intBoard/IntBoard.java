package intBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	// Instance Vars
	private Map<Integer, ArrayList<Integer>> adjMtx;
	private Set<Integer> targets;
	boolean[] visited;
	private static final int COLUMNS = 4;
	private static final int ROWS = 4;
	
	// Constructor
	public IntBoard() {
		super();
		visited = new boolean[getBoardSize()];
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
		targets = new HashSet<Integer>();
		calcTargets(cell, steps);
	}
	private void calcTargets(int cell, int steps) {
		for(int adjCell : getAdjList(cell)) {
			visited[adjCell] = true;
			if(steps == 1) {
				targets.add(cell);
			}
			else {
				calcTargets(cell, steps--);
			}
			visited[adjCell] = false;
		}
	}
	public Set<Integer> getTargets() { // Return
		return targets;
	}
	public ArrayList<Integer> getAdjList(int cell) {
		// Stub
		return new ArrayList<Integer>();
	}
	public int calcIndex(int r, int c) {
		return r * 4 + c;
	}
	public int getBoardSize() {
		return ROWS * COLUMNS;
	}
}