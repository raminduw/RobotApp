package raminduweeraman.com.robotsimulation.model;

public enum Direction {
	NORTH("NORTH"),
	EAST("EAST"),
	SOUTH("SOUTH"),
	WEST("WEST");

	private String description;

	Direction(String description) {
		this.description = description;
	}

	public static Direction from(String description) {
		for (Direction direction : values()) {
			if (direction.getDescription().equalsIgnoreCase(description)) {
				return direction;
			}
		}
		return null;
	}

	public Direction changeDirection(int rotatePoint) {
		int resultIndex = ((ordinal() + rotatePoint) + 4) % 4;
		return values()[resultIndex];
	}

	public String getDescription() {
		return description;
	}
}

