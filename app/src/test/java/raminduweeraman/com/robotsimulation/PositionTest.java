package raminduweeraman.com.robotsimulation;


import junit.framework.TestCase;

import org.junit.Before;

import raminduweeraman.com.robotsimulation.model.Direction;
import raminduweeraman.com.robotsimulation.model.Position;

public class PositionTest extends TestCase {
	private Position position;

	@Before
	public void setUp() {
		position = new Position(0, 0, Direction.NORTH);
	}

	public void testDerivedPosition() {
		position.setX(1);
		position.setY(2);
		position.setDirection(Direction.EAST);
		assertEquals(Direction.EAST, position.getDirection());
		assertEquals(1, position.getX());
		assertEquals(2, position.getY());
	}

	public void testUpdatePositionXOutOfRange() {
		position.setMaxX(5);
		position.setMaxY(5);
		position.setX(5);
		position.setY(5);
		position.updatePosition(1, 0);
		assertEquals(5, position.getX());
		assertEquals(5, position.getY());

		position.setMaxX(5);
		position.setMaxY(5);
		position.setX(0);
		position.setY(0);
		position.updatePosition(-1, 0);
		assertEquals(0, position.getX());
		assertEquals(0, position.getY());
	}

	public void testUpdatePositionYOutOfRange() {
		position.setMaxX(5);
		position.setMaxY(5);
		position.setX(5);
		position.setY(5);
		position.updatePosition(0, 1);
		assertEquals(5, position.getX());
		assertEquals(5, position.getY());

		position.setMaxX(5);
		position.setMaxY(5);
		position.setX(0);
		position.setY(0);
		position.updatePosition(0, -1);
		assertEquals(0, position.getX());
		assertEquals(0, position.getY());
	}

	public void testUpdatePositionXYWithinRange() {
		position.setX(3);
		position.setY(2);
		position.setMaxX(5);
		position.setMaxY(5);
		position.updatePosition(1, 0);
		assertEquals(4, position.getX());
		assertEquals(2, position.getY());
	}

	public void testPrintToString() {
		String result = position.toString();
		assertEquals("0,0,NORTH", result);
	}
}