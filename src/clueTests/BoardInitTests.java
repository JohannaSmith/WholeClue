package clueTests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.RoomCell;

public class BoardInitTests {
	private static Board board;
	private final int ROWS = 25;
	private final int COLUMNS = 25;
	
	@BeforeClass
	public static void setUp() {
		board = new Board("./ourboardfiles/boardLayout.csv", "./ourboardfiles/legend.txt");
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
	public void testDoors() {
		RoomCell door = board.getRoomCellAt(3, 6);
		Assert.assertTrue(door.isDoorway());
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT, door.getDoorDirection());
		RoomCell door2 = board.getRoomCellAt(11, 3);
		Assert.assertTrue(door2.isDoorway());
		Assert.assertEquals(RoomCell.DoorDirection.DOWN, door2.getDoorDirection());
		RoomCell door3 = board.getRoomCellAt(4, 9);
		Assert.assertTrue(door3.isDoorway());
		Assert.assertEquals(RoomCell.DoorDirection.LEFT, door3.getDoorDirection());
		RoomCell door4 = board.getRoomCellAt(17, 10);
		Assert.assertTrue(door4.isDoorway());
		Assert.assertEquals(RoomCell.DoorDirection.UP, door4.getDoorDirection());
		RoomCell door5 = board.getRoomCellAt(2, 2);
		Assert.assertTrue(!door5.isDoorway());
		Assert.assertEquals(RoomCell.DoorDirection.NONE, door5.getDoorDirection());
	}
	@Test
	public void testRoomInitials() {
		RoomCell room = board.getRoomCellAt(2, 2);
		Assert.assertTrue(room.getInitial() == 'C');
		RoomCell room2 = board.getRoomCellAt(8, 2);
		Assert.assertTrue(room2.getInitial() == 'L');
		RoomCell room3 = board.getRoomCellAt(20, 10);
		Assert.assertTrue(room3.getInitial() == 'D');
		
	}
	
	
}
