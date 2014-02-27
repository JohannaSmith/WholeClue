package clueTests;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTests {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		board = new Board();
		board.loadConfigFiles();
		board.calcAdjacencies();
	}
	@Test
	// Test Adjacencies In Rooms
	// Cells Tested are Orange
	public void testAdjacenciesInRooms() {
		// All tests use Library
		// Top Left Corner
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(6, 0));
		Assert.assertEquals(0, testList.size());
		// Top Right Corner Pieces
		testList = board.getAdjList(board.calcIndex(6, 5));
		Assert.assertEquals(0, testList.size());
		testList = board.getAdjList(board.calcIndex(7, 5));
		Assert.assertEquals(0, testList.size());
		testList = board.getAdjList(board.calcIndex(7, 6));
		Assert.assertEquals(0, testList.size());
		// Bottom Right Corner Pieces
		testList = board.getAdjList(board.calcIndex(10, 6));
		Assert.assertEquals(0, testList.size());
		testList = board.getAdjList(board.calcIndex(10, 5));
		Assert.assertEquals(0, testList.size());
		testList = board.getAdjList(board.calcIndex(11, 5));
		Assert.assertEquals(0, testList.size());
		// Bottom Left
		testList = board.getAdjList(board.calcIndex(11, 0));
		Assert.assertEquals(0, testList.size());
		// Middle
		testList = board.getAdjList(board.calcIndex(10, 3));
		Assert.assertEquals(0, testList.size());
	}
	@Test
	// Test Doorways can be left
	// Doorways tested in dark blue
	// Outside cells tested in light blue
	public void testAdjacencyDoorways() {
		// All Tests on Billiard Room
		// From Outside Left Door
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(4, 8));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 7)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 9)));
		Assert.assertTrue(testList.contains(board.calcIndex(3, 8)));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 8)));
		Assert.assertEquals(4, testList.size());
		// From Inside Left Door
		testList = board.getAdjList(board.calcIndex(4, 9));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 8)));
		Assert.assertEquals(1, testList.size());
		// From Outside Left Bottom Door
		testList = board.getAdjList(board.calcIndex(7, 12));
		Assert.assertTrue(testList.contains(board.calcIndex(6, 12)));
		Assert.assertTrue(testList.contains(board.calcIndex(8, 12)));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 11)));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 13)));
		Assert.assertEquals(4, testList.size());
		// From Inside Left Bottom Door
		testList = board.getAdjList(board.calcIndex(6, 12));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 12)));
		Assert.assertEquals(1, testList.size());
		// From Outside Right Bottom Door
		testList = board.getAdjList(board.calcIndex(7, 13));
		Assert.assertTrue(testList.contains(board.calcIndex(6, 13)));
		Assert.assertTrue(testList.contains(board.calcIndex(8, 13)));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 12)));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 14)));
		Assert.assertEquals(4, testList.size());
		// From Inside Right Bottom Door
		testList = board.getAdjList(board.calcIndex(6, 13));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 13)));
		Assert.assertEquals(1, testList.size());
		// Extra Test on Conservatory Corner
		// From Right
		testList = board.getAdjList(board.calcIndex(3, 7));
		Assert.assertTrue(testList.contains(board.calcIndex(3, 6)));
		Assert.assertTrue(testList.contains(board.calcIndex(3, 8)));
		Assert.assertTrue(testList.contains(board.calcIndex(2, 7)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 7)));
		Assert.assertEquals(4, testList.size());
		// From Bottom
		testList = board.getAdjList(board.calcIndex(4, 6));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 6)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 5)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 7)));
		Assert.assertEquals(3, testList.size());
	}
	@Test
	// Test Adjacencies at Board Edges
	// Starting Cells in Red
	public void testAdjacencyBoardEdge() {
		// Left Edge
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(5, 0));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 1)));
		Assert.assertEquals(1, testList.size());
		// Top Edge
		testList = board.getAdjList(board.calcIndex(0, 7));
		Assert.assertTrue(testList.contains(board.calcIndex(1, 7)));
		Assert.assertEquals(1, testList.size());
		// Right Edge
		testList = board.getAdjList(board.calcIndex(7, 24));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 23)));
		Assert.assertEquals(1, testList.size());
		// Bottom Edge
		testList = board.getAdjList(board.calcIndex(24, 10));
		Assert.assertTrue(testList.contains(board.calcIndex(23, 10)));
		Assert.assertEquals(1, testList.size());
	}
	@Test
	// Walkway Tests
	// Starting Cells in Green
	public void testWalkwayAdjacency() {
		// Test Random Square
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(4, 7));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 6)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 8)));
		Assert.assertTrue(testList.contains(board.calcIndex(3, 7)));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 7)));
		Assert.assertEquals(4, testList.size());
		// By Room (First Test)
		testList = board.getAdjList(board.calcIndex(4, 3));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 4)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 2)));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 3)));
		Assert.assertEquals(3, testList.size());
		// By Room (Second Test)
		testList = board.getAdjList(board.calcIndex(5, 3));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 4)));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 2)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 3)));
		Assert.assertEquals(3, testList.size());
	}
	@Test
	// Tests Target Distances
	// Start Cell Green
	// Checks to ensure red cells are included
	public void testTargetDistances() {
		// Distance 1
		board.calcTargets(4, 1, 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 2))));
		// Distance 2
		board.calcTargets(4, 1, 2);
		targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 3))));
		// Distance 3
		board.calcTargets(4, 1, 3);
		targets = board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 4))));
		// Distance 4
		board.calcTargets(4, 1, 4);
		targets = board.getTargets();
		Assert.assertEquals(5, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 5))));
	}
	@Test
	// Test Doors can be targets
	// Tested doors was right library door, in purple
	public void testTargetsDoors() {
		// Right Library Door
		// One Step away
		board.calcTargets(4, 8, 1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 9))));
		// Two Steps Away
		board.calcTargets(4, 7, 2);
		targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 9))));			
	}
	@Test
	// Targets from doorways
	// Starting Cells in purple
	public void testTargetsLeavingRoom() {
		// Library
		// Bottom Door, two steps
		board.calcTargets(11, 3, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 2))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 4))));
		// Right Door, one step
		board.calcTargets(9, 6, 1);
		targets = board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 7))));
	}
}