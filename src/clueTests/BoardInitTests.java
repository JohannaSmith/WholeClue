package clueTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
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
	public void testDoorDirection() {
		RoomCell door = board.getRoomCellAt(3, 6);
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT, door.getDoorDirection());
	}
	
	@Test
	public void TestCalcIndex() {
		Assert.assertEquals(0, board.calcIndex(0, 0));
		Assert.assertEquals(624, board.calcIndex(24, 24));
		Assert.assertEquals(31, board.calcIndex(1, 6));
		Assert.assertEquals(176, board.calcIndex(7, 1));
	}
	// Bad Column Mismatch
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		Board b = new Board("./badboardfiles/badcolumnsboardlayout.csv", "./ourboardfiles/legend.txt");
		b.loadLegend();
		b.loadBoard();
	}
	// Bad Room
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		Board b = new Board("./badboardfiles/badroomboardlayout.csv", "./ourboardfiles/legend.txt");
		b.loadLegend();
		b.loadBoard();
	}
	// Bad Legend
	@Test (expected = BadConfigFormatException.class)
	public void testBadLegend() throws BadConfigFormatException, FileNotFoundException {
		Board b = new Board("./ourboardfiles/boardlayout.csv", "./badboardfiles/badlegend.txt");
		b.loadLegend();
		b.loadBoard();
	}
	// No Layout File
	@Test (expected = FileNotFoundException.class)
	public void testBadFileName1() throws BadConfigFormatException, FileNotFoundException {
		Board b = new Board("./badboardfiles/no.file", "./ourboardfiles/legend.txt");
		b.loadLegend();
		b.loadBoard();
	}
	// No Legend File
	@Test (expected = FileNotFoundException.class)
	public void testBadFileName2() throws BadConfigFormatException, FileNotFoundException {
		Board b = new Board("./ourboardfiles/boardlayout.csv", "./badboardfiles/no.file");
		b.loadLegend();
		b.loadBoard();
	}
}
