package raminduweeraman.com.robotsimulation.model;

import javax.inject.Inject;

public class SquareTable {
	private int rows;
	private int columns;
	private static final int TABLE_SIZE =5;

	@Inject
	public SquareTable() {
		this.rows = TABLE_SIZE;
		this.columns = TABLE_SIZE;
	}

	public boolean validatePosition(Position position) {
		int xValue = position.getX();
		int yValue = position.getY();
		if (xValue > rows || xValue < 0 ||
				yValue > columns || yValue < 0) {
			return false;
		}
		return true;
	}


	public int getX() {
		return (rows-1);
	}

	public int getY() {
		return (columns-1);
	}
}
