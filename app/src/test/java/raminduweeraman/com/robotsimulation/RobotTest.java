package raminduweeraman.com.robotsimulation;


import junit.framework.TestCase;

import org.junit.Before;

import raminduweeraman.com.robotsimulation.model.Direction;
import raminduweeraman.com.robotsimulation.exceptions.RobotCommandException;
import raminduweeraman.com.robotsimulation.model.Position;
import raminduweeraman.com.robotsimulation.model.RobotEngine;
import raminduweeraman.com.robotsimulation.model.SquareTable;

public class RobotTest extends TestCase {
	private RobotEngine robot;
	private SquareTable table;

	@Before
	public void setUp() {
		robot = new RobotEngine();
		table = new SquareTable();
	}

	public void testPlaceRobotInNullTable() {
		try {
			robot.placeRobot(new Position(0, 0, Direction.NORTH), null);
		} catch (RobotCommandException ex) {
			assertEquals("Table not found", ex.getMessage());
		}
	}

	public void testPlaceRobotInNullPosition() {
		try {
			robot.placeRobot(null, table);
		} catch (RobotCommandException ex) {
			assertEquals("Position not found", ex.getMessage());
		}
	}

	public void testPlaceRobotInPositionOutOfBoundary() {
		try {
			robot.placeRobot(new Position(4, 6, Direction.NORTH), table);
		} catch (RobotCommandException ex) {
			assertEquals("Invalid position", ex.getMessage());
		}
	}

	public void testPlaceRobotInValidPosition() {
		try {
			robot.placeRobot(new Position(2, 3, Direction.NORTH), table);
		} catch (RobotCommandException ex) {
			// Ignore
		}
	}

	public void testMoveCommandInNorthDirection() {
		robot.placeRobot(new Position(0, 0, Direction.NORTH), table);
		robot.move();
		assertEquals(Direction.NORTH, robot.getPosition().getDirection());
		assertEquals(0, robot.getPosition().getX());
		assertEquals(1, robot.getPosition().getY());
	}

	public void testMoveCommandInSouthDirection() {
		robot.placeRobot(new Position(0, 0, Direction.SOUTH), table);
		robot.move();
		assertEquals(Direction.SOUTH, robot.getPosition().getDirection());
		assertEquals(0, robot.getPosition().getX());
		assertEquals(0, robot.getPosition().getY());
	}

	public void testMoveCommandInEastDirection() {
		robot.placeRobot(new Position(0, 0, Direction.EAST), table);
		robot.move();
		assertEquals(Direction.EAST, robot.getPosition().getDirection());
		assertEquals(1, robot.getPosition().getX());
		assertEquals(0, robot.getPosition().getY());
	}

	public void testMoveCommandInWestDirection() {
		robot.placeRobot(new Position(0, 0, Direction.WEST), table);
		robot.move();
		assertEquals(Direction.WEST, robot.getPosition().getDirection());
		assertEquals(0, robot.getPosition().getX());
		assertEquals(0, robot.getPosition().getY());
	}

	public void testRobotMoveToOutsideofBoundary() {
		robot.placeRobot(new Position(4, 4, Direction.NORTH), table);
		robot.move();
		assertEquals(Direction.NORTH, robot.getPosition().getDirection());
		assertEquals(4, robot.getPosition().getX());
		assertEquals(4, robot.getPosition().getY());
	}

	public void testRobotToLeft() {
		robot.setPosition(new Position(2, 1, Direction.WEST));
		robot.left();
		assertEquals(Direction.SOUTH, robot.getPosition().getDirection());
		assertEquals(2, robot.getPosition().getX());
		assertEquals(1, robot.getPosition().getY());
	}

	public void testRobotToRight() {
		robot.setPosition(new Position(2, 1, Direction.WEST));
		robot.right();
		assertEquals(Direction.NORTH, robot.getPosition().getDirection());
		assertEquals(2, robot.getPosition().getX());
		assertEquals(1, robot.getPosition().getY());
	}

	public void testPrintReport() {
		robot.setPosition(new Position(2, 1, Direction.WEST));
		assertEquals("2,1,WEST", robot.report());
	}
}
