package raminduweeraman.com.robotsimulation;

import junit.framework.TestCase;

import org.junit.Before;

import raminduweeraman.com.robotsimulation.model.Position;
import raminduweeraman.com.robotsimulation.model.SquareTable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SquareTableTest extends TestCase {
	private SquareTable table;
	Position mockPosition;

	@Before
	public void setUp() {
		table = new SquareTable();
	}

	public void testValidatePositionWithinRange() {
		mockPosition = mock(Position.class);
		when(mockPosition.getX()).thenReturn(3);
		when(mockPosition.getX()).thenReturn(3);
		assertTrue(table.validatePosition(mockPosition));
	}

	public void testValidatePositionOutOfRange() {
		mockPosition = mock(Position.class);
		when(mockPosition.getX()).thenReturn(3);
		when(mockPosition.getX()).thenReturn(6);
		assertFalse(table.validatePosition(mockPosition));
	}
	
}