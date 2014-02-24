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
		Assert.assertTrue(9 == board.getRoomCount());
		Assert.assertTrue(board.getRoom('C') == "Conservatory");
		Assert.assertTrue(board.getRoom('R') == "Billiard Room");
		Assert.assertTrue(board.getRoom('S') == "Study");
		Assert.assertTrue(board.getRoom('L') == "Library");
		Assert.assertTrue(board.getRoom('H') == "Hall");
		Assert.assertTrue(board.getRoom('B') == "Ballroom");
		Assert.assertTrue(board.getRoom('K') == "Kitchen");
		Assert.assertTrue(board.getRoom('D') == "Dining Room");
		Assert.assertTrue(board.getRoom('O') == "Obervatory");
		Assert.assertTrue(board.getRoom('X') == "Closet");
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
