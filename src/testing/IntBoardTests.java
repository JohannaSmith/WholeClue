package testing;

import intBoard.IntBoard;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntBoardTests {
	IntBoard b;
	@Before
	public void setup() {
		b = new IntBoard();
	}
	@Test
	public void calcIndexes() {
		Assert.assertTrue(b.calcIndex(2, 1) == 9);
		Assert.assertTrue(b.calcIndex(2, 2) == 10);
		Assert.assertTrue(b.calcIndex(3, 2) == 14);
	}
	@Test
	public void testAdjacency0()
	{
		ArrayList<Integer> testList = b.getAdjList(0);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(2, testList.size());
	}
	@Test
	public void testAdjacency10()
	{
		ArrayList<Integer> testList = b.getAdjList(0);
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(14));
		Assert.assertEquals(2, testList.size());
	}
	@Test
	public void testTargets0_3()
	{
		b.startTargets(0, 3);
		Set<Integer> targets= b.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
	}
	@Test
	public void testTargets2_2() {
		b.startTargets(2, 2);
		Set<Integer> targets = b.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(7));
	}
}
