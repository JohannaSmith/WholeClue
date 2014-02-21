package clueGame;

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
		for(int i = 0; i < COLUMNS; i++) { // iterate columns
			for(int j = 0; j < ROWS; j++) { // iterate rows
				int index = calcIndex(j, i);
				ArrayList<Integer> adjList = new ArrayList<Integer>();
				if (j > 0)
					adjList.add(calcIndex(j - 1, i));	
				if (j < ROWS - 1)
					adjList.add(calcIndex(j + 1, i));
				if (i > 0) 
					adjList.add(calcIndex(j, i - 1));
				if (i < COLUMNS - 1)
					adjList.add(calcIndex(j, i + 1));
				adjMtx.put(index, adjList);
			}
		}
	}
	public void startTargets(int cell, int steps) { // Calculate
		targets = new HashSet<Integer>();
		visited[cell] = true;
		calcTargets(cell, steps);
		visited[cell] = false;
	}
	private void calcTargets(int cell, int steps) {
		steps--;
		ArrayList<Integer> adjacentCells = new ArrayList<Integer>();
		for(int c : adjMtx.get(cell))
			if(!visited[c])
				adjacentCells.add(c);
		for(int adjCell : adjacentCells) {
			visited[adjCell] = true;
			if(steps == 0)
				targets.add(adjCell);
			else
				calcTargets(adjCell, steps);
			visited[adjCell] = false;
		}
	}
	public Set<Integer> getTargets() { // Return
		return targets;
	}
	public ArrayList<Integer> getAdjList(int cell) {
		return adjMtx.get(cell);
	}
	public int calcIndex(int r, int c) {
		return r * 4 + c;
	}
	public int getBoardSize() {
		return ROWS * COLUMNS;
	}
}