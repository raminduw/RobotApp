package raminduweeraman.com.robotsimulation.exceptions;

public class RobotCommandException extends RuntimeException {
	private String message;

	public RobotCommandException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
