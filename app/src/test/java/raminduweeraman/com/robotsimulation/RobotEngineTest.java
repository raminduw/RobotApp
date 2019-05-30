package raminduweeraman.com.robotsimulation;


import junit.framework.TestCase;

import org.junit.Before;

import raminduweeraman.com.robotsimulation.model.Command;
import raminduweeraman.com.robotsimulation.model.Direction;
import raminduweeraman.com.robotsimulation.exceptions.RobotCommandException;
import raminduweeraman.com.robotsimulation.model.Position;
import raminduweeraman.com.robotsimulation.model.RobotEngine;
import raminduweeraman.com.robotsimulation.presenter.RobotExecutor;
import raminduweeraman.com.robotsimulation.model.SquareTable;

public class RobotEngineTest extends TestCase {
	
	private RobotExecutor robotEngine;

	@Before
	public void setUp() {
		RobotEngine robot = new RobotEngine();
		SquareTable table = new SquareTable();
		robotEngine = new RobotExecutor(robot,table);
	}

	public void testPlaceCommandWithValidKeyWords() {
		robotEngine.placeCommand("1,1,NORTH");
		Position currentPosition = robotEngine.getRobot().getPosition();
		assertEquals(1, currentPosition.getX());
		assertEquals(1, currentPosition.getY());
		assertEquals(Direction.NORTH, currentPosition.getDirection());
	}

	public void testPlaceCommandWithInvalidKeyWords() {
		try {
			robotEngine.placeCommand("1,10,NORTH");
		} catch (RobotCommandException e) {
			assertEquals("Invalid position", e.getMessage());
		}
	}

	public void testPlaceCommandWithInvalidCoordinate() {
		try {
			robotEngine.placeCommand("1,a,NORTH");
		} catch (RobotCommandException e) {
			assertEquals("Invalid argument", e.getMessage());
		}
	}

	public void testProcessSingleValidMoveCommand() {
		robotEngine.placeCommand("0,0,NORTH");
		robotEngine.otherCommand(Command.MOVE);
		Position currentPosition = robotEngine.getRobot().getPosition();
		assertEquals(0, currentPosition.getX());
		assertEquals(1, currentPosition.getY());
		assertEquals(Direction.NORTH, currentPosition.getDirection());
	}

	public void testProcessSingleValidLeftCommand() {
		robotEngine.placeCommand("0,0,NORTH");
		robotEngine.otherCommand(Command.LEFT);
		Position currentPosition = robotEngine.getRobot().getPosition();
		assertEquals(Direction.WEST, currentPosition.getDirection());
	}

	public void testProcessSingleValidRightCommand() {
		robotEngine.placeCommand("0,0,NORTH");
		robotEngine.otherCommand(Command.RIGHT);
		Position currentPosition = robotEngine.getRobot().getPosition();
		assertEquals(Direction.EAST, currentPosition.getDirection());
	}

	public void testProcessSingleValidReportCommand() {
		robotEngine.placeCommand("0,0,NORTH");
		String result = robotEngine.otherCommand(Command.REPORT);
		assertEquals("0,0,NORTH", result);
	}

	public void testProcessReadCommandFromInput() {
		robotEngine.executeCommand("PLACE 0,0,NORTH");
		String result = robotEngine.executeCommand("REPORT");
		assertEquals("0,0,NORTH", result);
	}

	public void testProcessReadInvalidCommandFromInput() {
		try {
			robotEngine.executeCommand("PL 0,0,NORTH");
		} catch (RobotCommandException e) {
			assertEquals("Invalid command", e.getMessage());
		}
	}

	public void testIgnoreUndefinedPositionOnSingleCommand() {
		robotEngine.getRobot().setPosition(null);
		String result = robotEngine.otherCommand(Command.MOVE);
		assertEquals("", result);
	}

	public void testInvalidPositionOnSingleCommand() {
		try {
			robotEngine.getRobot().setPosition(new Position(0, 0, Direction.NORTH));
			robotEngine.otherCommand(null);
		} catch (RobotCommandException e) {
			assertEquals("Command not found", e.getMessage());
		}
	}
	
}
