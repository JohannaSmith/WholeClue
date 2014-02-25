package clueTests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.RoomCell;

public class BoardTests {
	private static Board board;
	private final int ROWS = 25;
	private final int COLUMNS = 25;
	
	@BeforeClass
	public static void setUp() {
		board = new Board("boardLayout.csv", "legend.txt");
		board.loadConfigFiles();
	}
	@Test
	public void checkLegendLoading() {
		Assert.assertTrue(12 == board.getRoomCount());
		Assert.assertTrue(board.getRoom('C').equals("Conservatory"));
		Assert.assertTrue(board.getRoom('R').equals("Billiard Room"));
		Assert.assertTrue(board.getRoom('S').equals("Study"));
		Assert.assertTrue(board.getRoom('L').equals("Library"));
		Assert.assertTrue(board.getRoom('H').equals("Hall"));
		Assert.assertTrue(board.getRoom('B').equals("Ballroom"));
		Assert.assertTrue(board.getRoom('K').equals("Kitchen"));
		Assert.assertTrue(board.getRoom('D').equals("Dining Room"));
		Assert.assertTrue(board.getRoom('O').equals("Observatory"));
		Assert.assertTrue(board.getRoom('W').equals("Walkway"));
		Assert.assertTrue(board.getRoom('N').equals("Not a Space"));
		Assert.assertTrue(board.getRoom('X').equals("Closet"));
	}
	@Test
	public void testBoardDimensions() {
		Assert.assertEquals(ROWS, board.getNumRows());
		Assert.assertEquals(COLUMNS, board.getNumColumns());
	}
	@Test
	public void testDoorDirection() {
		RoomCell door = board.getRoomCellAt(3, 6);
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT, door.getDoorDirection());
	}
	
	
}
